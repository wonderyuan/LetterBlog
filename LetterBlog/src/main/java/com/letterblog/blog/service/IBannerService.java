package com.letterblog.blog.service;

import com.letterblog.blog.entity.Banner;

import java.util.List;

public interface IBannerService {
    List<Banner> queryAllBanner(int page, int size);

    int addBanner(Banner banner);

    int updateBanner(Banner banner);

    int deleteBanner(Integer bannerId);

    List<Banner> queryNormalBanner();

    Banner queryBannerById(Integer bannerId);
}
