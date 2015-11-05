package com.hk.common.service;

import java.io.Serializable;

import com.hk.common.pager.Pager;
import com.hk.common.query.ObjectQuery;

public interface IBaseService<T> {
	/**
	 * 根据id查询实体记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T findById(Serializable id);
	
	/**
	 * 根据条件查询实体列表
	 * @param query
	 * @return
	 */
	public Pager<T> findAll(ObjectQuery query);
	
	/**
	 * 根据条件查询记录数
	 * @param query
	 * @return
	 */
	public int getCount(ObjectQuery query);
	
	/**
	 * 保存实体
	 * @param t
	 * @throws Exception
	 */
	public void save(T t);
	
	/**
	 * 更新实体
	 * @param t
	 * @throws Exception
	 */
	public void update(T t);
	
	/**
	 * 删除实体
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(Serializable id);
	
}
