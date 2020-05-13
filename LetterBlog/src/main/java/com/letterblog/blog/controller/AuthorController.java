package com.letterblog.blog.controller;

import com.github.pagehelper.PageInfo;
import com.letterblog.blog.entity.Article;
import com.letterblog.blog.entity.Category;
import com.letterblog.blog.entity.Tag;
import com.letterblog.blog.entity.User;
import com.letterblog.blog.enums.ArticleStatus;
import com.letterblog.blog.service.ArticleService;
import com.letterblog.blog.service.TagService;
import com.letterblog.blog.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.letterblog.blog.util.MyUtils.getIpAddr;

@Controller
public class AuthorController {
    private Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Resource
    UserService userService;
    @Resource
    TagService tagService;
    @Resource
    ArticleService articleService;

    @RequestMapping("/login")
    public String login() {
        return "/Home/login";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "/Home/register";
    }

    @RequestMapping(value = "/toRegister", method = RequestMethod.POST, produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String toRegister(User user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("username:" + username + "password:" + password);
        User user = userService.getUserByNameOrEmail(username);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名无效！");
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "密码错误！");
        } else {
            //登录成功
            map.put("code", 1);
            map.put("msg", "");
            //添加session
            request.getSession().setAttribute("user", user);
            user.setUserLastLoginTime(new Date());
            userService.updateUser(user);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping("/author/{authorId}")
    public String getArticleListByAuthor(@PathVariable("authorId") Integer authorId,
                                         @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                         Model model) {

        //该分类信息
        User user = userService.getUserById(authorId);
        if (user == null) {
            return "redirect:/404";
        }
        Category category = new Category();
        category.setCategoryName(user.getUserNickname() + "发表的文章");
        model.addAttribute("category", category);

        Article article = new Article();
        user.setShow(true);
        article.setUser(user);
        model.addAttribute("article", article);

        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("userId", user.getUserId());
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

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
        model.addAttribute("pageUrlPrefix", "/category/" + pageIndex + "?pageIndex");
        return "Home/Page/articleListByAuthor";
    }

    @RequestMapping(value = "/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }
}
