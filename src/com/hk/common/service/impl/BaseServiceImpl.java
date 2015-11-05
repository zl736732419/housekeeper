package com.hk.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hk.common.mapper.BaseMapper;
import com.hk.common.pager.Pager;
import com.hk.common.query.ObjectQuery;
import com.hk.common.service.IBaseService;

@Transactional
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	protected abstract BaseMapper<T> getBaseMapper();

	@Override
	public T findById(Serializable id) {
		return getBaseMapper().findById(id);
	}

	@Override
	public void save(T t) {
		getBaseMapper().save(t);
	}

	@Override
	public void update(T t) {
		getBaseMapper().update(t);
	}

	@Override
	public void delete(Serializable id) {
		getBaseMapper().deleteById(id);
	}

	@Override
	public Pager<T> findAll(ObjectQuery query) {
		Pager<T> pager = new Pager<T>(query.getPageNo(), query.getPageSize(), getCount(query));
		
		//更新页码,保证当前用户传递过来的值是有效的值
		query.setPageNo(pager.getPageNo());
		query.setPageSize(pager.getPageSize());
		
		List<T> list = getBaseMapper().findAll(query.getParams());
		pager.setRows(list);
		return pager;
	}

	@Override
	public int getCount(ObjectQuery query) {
		return getBaseMapper().getCount(query.getParams());
	}

}
