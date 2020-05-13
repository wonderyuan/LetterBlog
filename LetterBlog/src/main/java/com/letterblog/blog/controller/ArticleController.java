package com.letterblog.blog.controller;


import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson.JSON;

import com.letterblog.blog.dto.ArticleParam;
import com.letterblog.blog.dto.JsonResult;
import com.letterblog.blog.dto.UploadFileVO;
import com.letterblog.blog.entity.*;
import com.letterblog.blog.enums.ArticleStatus;

import com.letterblog.blog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文章的controller
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    private Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private CategoryService categoryService;

    public final String allowSuffix = ".bmp.jpg.jpeg.png.gif";

    /**
     * 文章详情页显示
     *
     * @param articleId 文章ID
     * @return modelAndView
     */
    @RequestMapping(value = "/article/{articleId}")
    public String getArticleDetailPage(@PathVariable("articleId") Integer articleId, Model model) {

        //文章信息，分类，标签，作者，评论
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), articleId);
        if (article == null) {
            return "Home/Error/404";
        }

        //用户信息
        User user = userService.getUserById(article.getArticleUserId());
        user.setShow(true);
        article.setUser(user);


        //文章信息
        model.addAttribute("article", article);

        //评论列表
        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList", commentList);

        //相关文章
        List<Integer> categoryIds = articleService.listCategoryIdByArticleId(articleId);
        List<Article> similarArticleList = articleService.listArticleByCategoryIds(categoryIds, 5);
        model.addAttribute("similarArticleList", similarArticleList);

        //猜你喜欢
        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
        model.addAttribute("mostViewArticleList", mostViewArticleList);

        //获取下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId);
        model.addAttribute("afterArticle", afterArticle);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId);
        model.addAttribute("preArticle", preArticle);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        return "Home/Page/articleDetail";
    }

    /**
     * 点赞增加
     *
     * @param id 文章ID
     * @return 点赞量数量
     */
    @RequestMapping(value = "/article/like/{id}", method = {RequestMethod.POST})
    @ResponseBody
    public String increaseLikeCount(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), id);
        Integer articleCount = article.getArticleLikeCount() + 1;
        article.setArticleLikeCount(articleCount);
        articleService.updateArticle(article);
        return JSON.toJSONString(articleCount);
    }

    /**
     * 文章访问量数增加
     *
     * @param id 文章ID
     * @return 访问量数量
     */
    @RequestMapping(value = "/article/view/{id}", method = {RequestMethod.POST})
    @ResponseBody
    public String increaseViewCount(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), id);
        Integer articleCount = article.getArticleViewCount() + 1;
        article.setArticleViewCount(articleCount);
        articleService.updateArticle(article);
        return JSON.toJSONString(articleCount);
    }

    @RequestMapping(value = "/article/write")
    public String writeArticle(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "/Home/Page/write";
    }

    /**
     * 前台发表文章
     */
    @RequestMapping(value = "/article/write/insertSubmit", method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session, ArticleParam articleParam) {
        Article article = new Article();
        //用户ID
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        article.setHeadImg(articleParam.getHeadImg());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/author/" + user.getUserId();
    }

    /**
     * 编辑文章页面显示
     */
    @RequestMapping(value = "/article/edit/{id}")
    public ModelAndView editArticleView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Article article = articleService.getArticleByStatusAndId(null, id);
        modelAndView.addObject("article", article);


        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);

        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("tagList", tagList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);

        modelAndView.setViewName("/Home/Page/articleEdit");
        return modelAndView;
    }

    /**
     * 编辑文章提交
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/article/edit/editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam, HttpSession session) {
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        article.setHeadImg(articleParam.getHeadImg());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        User user = (User) session.getAttribute("user");
        articleService.updateArticleDetail(article);
        return "redirect:/author/" + user.getUserId();
    }

    @RequestMapping(value = "/article/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
    }



    @RequestMapping(value = "/article/upload/img")
    @ResponseBody
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        //1.文件后缀过滤，只允许部分后缀
        //文件的完整名称,如spring.jpeg
        String filename = file.getOriginalFilename();
        //文件名,如spring
        String name = UUID.randomUUID().toString().replace("-", "").substring(0, 18);
        //文件后缀,如.jpeg
        String suffix = filename.substring(filename.lastIndexOf("."));

        String newFileName = name + suffix;

        if (allowSuffix.indexOf(suffix) == -1) {
            return new JsonResult().fail("不允许上传该后缀的文件！");
        }
        //2.创建文件目录
        //目标文件
        File descFile = new File(request.getServletContext().getRealPath("/") + "/upload/" + newFileName);
        System.out.println(request.getServletContext().getRealPath("/") + "/upload/" + newFileName);
        //判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            descFile.getParentFile().mkdirs();
        }
        //3.存储文件
        //将内存中的数据写入磁盘
        try {
            file.transferTo(descFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败，cause:{}", e);
        }
        //完整的url
        String fileUrl = "/upload/" + newFileName;
        //4.返回URL
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setTitle(newFileName);
        uploadFileVO.setSrc(fileUrl);
        return new JsonResult().ok(uploadFileVO);
    }
}
