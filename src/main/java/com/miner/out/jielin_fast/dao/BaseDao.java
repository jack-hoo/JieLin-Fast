/**
 * Copyright (C), 2008-2018, 杭州迪火科技有限公司
 * FileName: BaseDao
 * Author:   shugan
 * Date:     2018/2/24 22:26
 * Description: 通用dao
 */
package com.miner.out.jielin_fast.dao;

import java.util.List;
import java.util.Map;

/**
 * 〈通用dao〉
 *
 * @author shugan
 * @create 2018/2/24
 * @since 1.0.0
 */
public interface BaseDao<T> {
    void save(T t);

    void save(Map<String, Object> map);

    int saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int delete(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    T queryObject(Object id);

    List<T> queryList(Map<String, Object> map);

    List<T> queryList(Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();
}