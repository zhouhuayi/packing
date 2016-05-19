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
	protected String sqlAppend(String sql, Map<String, Object> searchParams) {
		for (String key : searchParams.keySet()) {
			sql = sql.replaceAll(':' + key, '\'' + searchParams.get(key).toString().replaceAll("'", "") + '\'');
		}
		return sql;
	}
	
	/**
	 * 直接拼接参数
	 * 
	 * @author zhy
	 * @param searchParams 查询参数
	 * @return
	 */
	protected StringBuffer appendWhereSql(Map<String, Object> searchParams) {
		StringBuffer sqlBuffer = new StringBuffer();
		if(searchParams != null && searchParams.size() > 0) {
			sqlBuffer.append(" where ");
			for (String key : searchParams.keySet()) {
				sqlBuffer.append(key).append(" = ").append('\'').append(searchParams.get(key).toString().replaceAll("'", "")).append('\'');
			}
		}
		return sqlBuffer;
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
	
	/**
	 * 通过条件修改实体
	 * 
	 * @author 周化益
	 * @param entityName 实体Class
	 * @param updataData 修改数据
	 * @param whereSql 条件语句
	 * @return 成功或失败
	 */
	public boolean updateByWhere(Class<T> entityName, Map<String , Object> updateData, String whereSql){
		boolean bool = false;
		try {
			StringBuffer sb = new StringBuffer("update ");
			sb.append(getTableName(entityName)).append(" set ");
			Iterator<String> it = updateData.keySet().iterator();
			StringBuffer updateBuffer = new StringBuffer();
			
			while (it.hasNext()) {
				String key = it.next();
				if(updateData.get(key) == null) {
					updateBuffer.append(key).append('=').append("null").append(',');
				} else{
					updateBuffer.append(key).append('=').append(':'+key).append(',');
				}
			}
			
			sb.append(updateBuffer.substring(0, updateBuffer.length() - 1)).append(whereSql);
			bool = commonMapper.executeAction(sqlAppend(sb.toString(), updateData)) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
}
