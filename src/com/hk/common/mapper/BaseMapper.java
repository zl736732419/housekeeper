package com.hk.common.mapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 定义通用CURD操作
 *
 * @author zhenglian
 * @data 2015年10月15日 下午10:35:12
 * @param <T>
 */
public interface BaseMapper<T> {
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
	 * 
	 * @param query
	 * @return
	 */
	public List<T> findAll(HashMap<String, Object> params);

	/**
	 * 根据条件查询记录数
	 * 
	 * @param query
	 * @return
	 */
	public int getCount(HashMap<String, Object> params);

	/**
	 * 保存实体
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void save(T t);

	/**
	 * 更新实体
	 * 
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
	public void deleteById(Serializable id);
	
	/**
	 * 根据用户id连接字符串批量删除多个用户
	 * @param ids
	 */
	public void deleteByIds(String ids);
}
