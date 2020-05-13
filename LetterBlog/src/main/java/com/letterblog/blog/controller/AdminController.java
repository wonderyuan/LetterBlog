package com.letterblog.blog.controller;

import com.letterblog.blog.entity.Article;
import com.letterblog.blog.entity.Comment;
import com.letterblog.blog.entity.User;
import com.letterblog.blog.service.ArticleService;
import com.letterblog.blog.service.CommentService;
import com.letterblog.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.letterblog.blog.util.MyUtils.getIpAddr;

/**
 *
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping("/admin")
    public String index(Model model) {
        //文章列表
        List<Article> articleList = articleService.listRecentArticle(5);
        model.addAttribute("articleList", articleList);
        //评论列表
        List<Comment> commentList = commentService.listRecentComment(5);
        model.addAttribute("commentList", commentList);
        return "Admin/index";
    }

    /**
     * 登录页面显示
     *
     * @return
     */
    @RequestMapping("/adminlogin")
    public String loginPage() {
        return "Admin/adminlogin";
    }

    /**
     * 登录验证
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUserByNameOrEmail(username);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名无效！");
        } else if (!"ADMIN".equals(user.getUserRole())) {
            map.put("code", 0);
            map.put("msg", "非管理员，拒绝登录！");
            String result = new JSONObject(map).toString();
            return result;
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "密码错误！");
        } else {
            //登录成功
            map.put("code", 1);
            map.put("msg", "");
            //添加session
            request.getSession().setAttribute("admin", user);
            user.setUserLastLoginTime(new Date());
            userService.updateUser(user);

        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        session.invalidate();
        return "redirect:/adminlogin";
    }


}
