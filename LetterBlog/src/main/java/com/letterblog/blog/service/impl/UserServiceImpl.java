package com.letterblog.blog.service.impl;

import com.letterblog.blog.mapper.ArticleMapper;
import com.letterblog.blog.mapper.CommentMapper;
import com.letterblog.blog.mapper.UserMapper;
import com.letterblog.blog.entity.User;
import com.letterblog.blog.service.UserService;
import org.apache.ws.security.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<User> listUser() {
        List<User> userList = userMapper.listUser();
        for (int i = 0; i < userList.size(); i++) {
            Integer articleCount = articleMapper.countArticleByUser(userList.get(i).getUserId());
            userList.get(i).setArticleCount(articleCount);
            if (userList.get(i).getUserAvatar() == null || StringUtils.isEmpty(userList.get(i).getUserAvatar())) {
                userList.get(i).setUserAvatar("/upload/head.jpg");
            }
        }
        return userList;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        Integer hour = getDatePoor(user.getUserLastLoginTime(), new Date());
        if (hour < 24) {
            if (hour < 1) {
                user.setLastLoginTiming("刚刚");
            } else {
                user.setLastLoginTiming(hour + "小时前");
            }
        } else {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy年-MM月-dd日");
            String time = sf.format(user.getUserLastLoginTime());
            user.setLastLoginTiming(time);
        }
        Integer commentNum = commentMapper.countCommentByEmail(user.getUserEmail());
        user.setCommentNum(commentNum);
        Integer articleCount = articleMapper.countArticleByUser(user.getUserId());
        user.setArticleCount(articleCount);
        logger.info("getUserById end", user);
        if (user.getUserAvatar() == null || StringUtils.isEmpty(user.getUserAvatar())) {
            user.setUserAvatar("/upload/head.jpg");
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public User insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        User user = userMapper.getUserByNameOrEmail(str);
        if (user != null && (user.getUserAvatar() == null || StringUtils.isEmpty(user.getUserAvatar()))) {
            user.setUserAvatar("/upload/head.jpg");
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        User user = userMapper.getUserByName(name);
        if (user != null && (user.getUserAvatar() == null || StringUtils.isEmpty(user.getUserAvatar()))) {
            user.setUserAvatar("/upload/head.jpg");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userMapper.getUserByEmail(email);
        if (user != null && (user.getUserAvatar() == null || StringUtils.isEmpty(user.getUserAvatar()))) {
            user.setUserAvatar("/upload/head.jpg");
        }
        return user;
    }

    @Override
    public String register(User user) {
        User u = userMapper.getUserByEmail(user.getUserEmail());
        if(u != null){
            return "邮箱已被使用！";
        }
        User u2 = userMapper.getUserByName(user.getUserName());
        if(u2 != null){
            return "登录名已被使用！";
        }
        user.setUserRegisterTime(new Date());
        user.setUserStatus(1);
        user.setUserRole("USER");
        userMapper.insert(user);
        return "success";
    }

    /**
     * 计算日期相差小时数
     */
    private Integer getDatePoor(Date startDate, Date endDate) {
        long nh = 1000 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少小时
        int hour = (int) (diff / nh);
        return hour;
    }
}
