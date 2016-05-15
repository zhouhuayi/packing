package com.dream.packing.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dream.packing.entity.Params;
import com.dream.packing.mapper.CommonMapper;

/**
 * 类名称：通用DAO实现层 描述：通用数据处理层 创建人：周化益 创建时间：2015-08-05
 */
@Repository("commonDao")
public class CommonDao<T> {

	@Autowired
	private CommonMapper commonMapper;

	/**
	 * 增删改SQL操作
	 * 
	 * @param sql 操作的sql语句
	 * @return
	 */
	public long executeAction(String sql) {
		return commonMapper.executeAction(sql);
	}

	/**
	 * 查询单条语句
	 * 
	 * @param sql 操作的sql语句
	 * @return
	 */
	public Map<String, Object> findOneData(String sql) {
		return commonMapper.findOneData(sql);
	}

	/**
	 * 查询多条语句
	 * 
	 * @param sql 操作的sql语句
	 * @return
	 */
	public List<Map<String, Object>> findManyData(String sql) {
		return commonMapper.findManyData(sql);
	}

	/**
	 * 查找数量
	 * 
	 * @author zhy
	 * @param sql 查询的sql 语句
	 * @return
	 */
	public long findCount(String sql) {
		return commonMapper.findCount(sql);
	}
	
	/**
	 * 拼接SQL实现预处理语句
	 * 
	 * @author zhy
	 * @param sql 预处理的语句
	 * @param param 拼接的参数
	 * @return
	 */
	public String sqlAppend(String sql, Map<String, Object> param) {
		Iterator<String> it = param.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			StringBuffer value = new StringBuffer(param.get(key).toString().trim());
			int index = value.indexOf("'");
			int last = value.lastIndexOf("'");
			int valueLength = value.length();

			while(index == 0) {
				value = value.deleteCharAt(index);
				index = value.indexOf("'");
				last --;
				valueLength --;
			}
			
			while(last == valueLength-1) {
				value = value.deleteCharAt(last);
				last = value.indexOf("'");
				valueLength --;
			}
			
			sql = sql.replaceAll(':' + key, '\'' + value.toString() + '\'');
		}
		return sql;
	}
	
	/**
	 * 得到实体名
	 * 
	 * @author 周化益
	 * @param entityName 实体Class
	 * @return 实体名
	 */
	private String getTableName(Class<? extends Object> entityName) {
		return entityName.getSimpleName();
	}
	
	/**
	 * 添加实体
	 * 
	 * @author 周化益
	 * @param entityName 实体Class
	 * @param addData 添加的数据
	 * @return 主键ID
	 */
	public long addClass(Class<T> entityName, Map<String, Object> addData) {
		Params params = new Params();
		params.setTables(getTableName(entityName));
		params.setInsertMap(addData);
		commonMapper.addEntity(params);
		return params.getId();
	}
	
	/**
	 * 批量添加数据
	 * 
	 * @author zhy
	 * @param entityName 实体Class
	 * @param listMap	批量数据集合
	 * @return
	 */
	public int batchAdd(Class<T> entityName, List<Map<String, Object>> listMap) {
		Params params = new Params();
		params.setTables(getTableName(entityName));
		params.setInsertMap(listMap.get(0));
		params.setBacthInsertMap(listMap);
		return commonMapper.batchAdd(params);
	}
}
