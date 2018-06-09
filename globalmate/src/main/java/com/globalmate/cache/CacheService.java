package com.globalmate.cache;

//import org.springframework.data.redis.core.ZSetOperations;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Map;
import java.util.Set;

public interface CacheService {
	public boolean set(final String key, final Object value);
	
	public boolean set(final String key, final Object value, final Long exp);
	
	public boolean hasKey(String key);
	
	public boolean putHash(String key, Object fieldKey, Object fieldValue);
	
	public Object getHash(String key, Object fieldKey);

    public boolean hasHashKey(String key, Object fieldKey);
	
	public Map<String, String> getHash(String key);
	
	public boolean deleteHash(String key, Object fieldKey);

    public boolean deleteHash(String key);
	
	public <T> T get(final String key);
	
	public boolean delete(final String key);
	
	public boolean setSerializer(final String key, final Object value, final Long exp);
	
	public boolean setSerializer(final String key, final Object value);
	
	public <T> T getSerializer(final String key, Class<?> clazz);
	
	public boolean addSetMember(String key, Object... members);
	
	public boolean hasSetMember(String key, Object member);
	
	public Set<String> getKeys(String pattern);
	/**
	 * 获取hash表下的所有属性字段
	 * @param key (不支持通配符)
	 * @return
	 */
	public Set<String> getHashTableFields(String key);
	
	/**
	 * 设置延期
	 * @param key
	 * @param timeout 延期时长  单位:秒
 	 * @return
	 */
	public boolean expire(String key, long timeout);
	
	/**
	 * 批量删除符合模版key的数据
	 * @param pattern
	 * @return
	 * boolean
	 * @date 2015年11月4日
	 */
	public boolean deleteByPattern(final String pattern);
	
	/**
	 * 有序集合数据添加
	 * @param key
	 * @param value
	 * @param score
	 * @return boolean
	 * @date 2015年11月10日
	 */
	public boolean addZSetMember(String key, Object value, double score);
	
	/**
	 * 返回根据分数排序的集合列表，返回全部 start=0,end=-1
	 * @param key
	 * @param start
	 * @param end
	 * @return Set<TypedTuple<Object>>
	 * @date 2015年11月11日
	 */
	public Set<ZSetOperations.TypedTuple<Object>> zSetWithScores(String key, long start, long end);

	public Set<ZSetOperations.TypedTuple<Object>> zSetReverseRangeWithScores(String key, long start, long end);
	
	/**
	 * 根据value值获取score
	 * @param key
	 * @param value
	 * @return
	 * @return Object
	 * @date 2015年11月25日
	 */
	public Object zSetscore(String key, String value);
	/**
	 * 获取缓存大小
	 * @return
	 */
	public long dbSize();
	
	
	boolean set(final byte[] key, final byte[] value);
	
	byte[] getByte(final byte[] key);
	
	public boolean setNX(String key, Long value);

	public Object getLock(String key);

	public Long getSet(String key, Long value);

}
