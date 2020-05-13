package com.letterblog.blog.controller;

import com.letterblog.blog.entity.Banner;
import com.letterblog.blog.entity.User;
import com.letterblog.blog.service.IBannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/banner")
public class BannerController {

    @Resource
    IBannerService bannerService;

    @RequestMapping(value = "")
    public ModelAndView userList(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ModelAndView modelandview = new ModelAndView();

        List<Banner> bannerList = bannerService.queryAllBanner(pageIndex, pageSize);
        for (Banner banner : bannerList) {
            if(banner.getCreateTime() != null){
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                banner.setCreateTimeStr(sf.format(banner.getCreateTime()));
            }
        }
        modelandview.addObject("bannerList", bannerList);

        modelandview.setViewName("Admin/Banner/index");
        return modelandview;

    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteBanner(@PathVariable("id") Integer id)  {
        bannerService.deleteBanner(id);
        return "redirect:/admin/banner";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editBanner(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        Banner banner = bannerService.queryBannerById(id);
        modelAndView.addObject("banner",banner);

        modelAndView.setViewName("Admin/Banner/edit");
        return modelAndView;
    }

    /**
     * 编辑用户提交
     *
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editUserSubmit(Banner banner, HttpServletRequest request)  {
        bannerService.updateBanner(banner);
        return "redirect:/admin/banner";
    }

    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertBanner(Banner banner)  {
        banner.setBannerState("1");
        bannerService.addBanner(banner);
        return "redirect:/admin/banner";
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insertUserView()  {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/Banner/insert");
        return modelAndView;
    }
}
