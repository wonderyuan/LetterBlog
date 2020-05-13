package com.letterblog.blog.mapper;

import com.letterblog.blog.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {

    List<Banner> queryAllBanner();

    int addBanner(Banner banner);

    int updateBanner(Banner banner);

    int deleteBanner(@Param("id") int id);

    List<Banner> selectNormalBanner();

    Banner queryBannerById(@Param("id") Integer id);
}
