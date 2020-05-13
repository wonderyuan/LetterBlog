package com.letterblog.blog.service.impl;

import com.letterblog.blog.entity.Page;
import com.letterblog.blog.mapper.PageMapper;
import com.letterblog.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 *
 *
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired(required = false)
    private PageMapper pageMapper;

    @Override
    public Page getPageByKey(Integer status, String key) {
        return pageMapper.getPageByKey(status, key);
    }

    @Override
    public Page getPageById(Integer id) {
        return pageMapper.getPageById(id);
    }

    @Override
    public List<Page> listPage(Integer status) {
        return pageMapper.listPage(status);
    }


    @Override
    public void insertPage(Page page) {
        pageMapper.insert(page);
    }

    @Override
    public void deletePage(Integer id) {
        pageMapper.deleteById(id);
    }

    @Override
    public void updatePage(Page page) {
        pageMapper.update(page);
    }
}
