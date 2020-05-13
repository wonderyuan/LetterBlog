package com.letterblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.letterblog.blog.entity.Banner;
import com.letterblog.blog.mapper.BannerMapper;
import com.letterblog.blog.service.IBannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerServiceImpl implements IBannerService {
    @Resource
    BannerMapper bannerMapper;

    @Override
    public List<Banner> queryAllBanner(int page, int size) {
        PageHelper.startPage(page,size);
        if(page <1){
            page=1;
        }
        List<Banner> list = bannerMapper.queryAllBanner();
        return list;
    }

    @Override
    public int addBanner(Banner banner) {
        return bannerMapper.addBanner(banner);
    }

    @Override
    public int updateBanner(Banner banner) {
        return bannerMapper.updateBanner(banner);
    }

    @Override
    public int deleteBanner(Integer bannerId) {
        return bannerMapper.deleteBanner(bannerId);
    }

    @Override
    public List<Banner> queryNormalBanner() {
        return bannerMapper.selectNormalBanner();
    }

    @Override
    public Banner queryBannerById(Integer bannerId) {
        return bannerMapper.queryBannerById(bannerId);
    }
}
