package com.letterblog.blog.service.impl;

import com.letterblog.blog.entity.Link;
import com.letterblog.blog.mapper.LinkMapper;
import com.letterblog.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 *
 *
 * @date 2017/9/4
 */
@Service
public class LinkServiceImpl implements LinkService {
	
	@Autowired(required = false)
	private LinkMapper linkMapper;
	
	@Override
	public Integer countLink(Integer status)  {
		return linkMapper.countLink(status);
	}
	
	@Override
	public List<Link> listLink(Integer status)  {
		return linkMapper.listLink(status);
	}

	@Override
	public void insertLink(Link link)  {
		linkMapper.insert(link);
	}

	@Override
	public void deleteLink(Integer id)  {
		linkMapper.deleteById(id);
	}

	@Override
	public void updateLink(Link link)  {
		linkMapper.update(link);
	}

	@Override
	public Link getLinkById(Integer id)  {
		return linkMapper.getLinkById(id);
	}

}
