package com.letterblog.blog.controller;

import cn.hutool.http.HtmlUtil;
import com.letterblog.blog.dto.JsonResult;
import com.letterblog.blog.entity.Article;
import com.letterblog.blog.entity.Comment;
import com.letterblog.blog.entity.User;
import com.letterblog.blog.enums.ArticleStatus;
import com.letterblog.blog.enums.Role;
import com.letterblog.blog.service.ArticleService;
import com.letterblog.blog.service.CommentService;
import com.letterblog.blog.service.UserService;
import com.letterblog.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @date 2017/9/10
 */

@Controller
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    /**
     * '添加评论
     *
     * @param request
     * @param comment
     */
    @RequestMapping(value = "/comment", method = {RequestMethod.POST})
    public JsonResult insertComment(HttpServletRequest request, Comment comment) {
        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        Article art = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
        User u = (User) request.getSession().getAttribute("user");
        if(u == null){
            return new JsonResult().fail("请先登录！");
        }
        if (u.getUserId() == art.getArticleUserId()) {
            comment.setCommentRole(Role.OWNER.getValue());
        } else {
            comment.setCommentRole(Role.VISITOR.getValue());
        }
        User user = userService.getUserByEmail(comment.getCommentAuthorEmail());
        comment.setCommentAuthorAvatar(user == null || StringUtils.isEmpty(user.getUserAvatar()) ? "upload/head.jpg" : user.getUserAvatar());

        //过滤字符，防止XSS攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
        try {
            commentService.insertComment(comment);
            //更新文章的评论数
            Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
            articleService.updateCommentCount(article.getArticleId());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();
    }


}
