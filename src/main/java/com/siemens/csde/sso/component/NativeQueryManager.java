 package com.siemens.csde.sso.component;

 import com.google.common.collect.Lists;
 import com.siemens.csde.sso.util.BeanMapUtil;
 import java.math.BigInteger;
 import java.util.List;
 import java.util.Map;
 import javax.persistence.EntityManager;
 import javax.persistence.PersistenceContext;
 import javax.persistence.Query;
 import lombok.extern.slf4j.Slf4j;
 import org.apache.commons.collections4.CollectionUtils;
 import org.apache.commons.collections4.MapUtils;
 import org.hibernate.SQLQuery;
 import org.hibernate.transform.Transformers;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageImpl;
 import org.springframework.data.domain.Pageable;
 import org.springframework.stereotype.Component;

 /**
  * NativeQueryManager Native Query 管理
  *
  * @author z004267r
  * @version 1.0-SNAPSHOT
  * @date 8/23/2019 5:14 PM
  **/
 @SuppressWarnings("unchecked")
 @Component("nativeQueryManager")
 @Slf4j
 public class NativeQueryManager {

     @PersistenceContext
     private EntityManager em;

     /**
      * 根据自定义sql、参数进行查询数据记录
      *
      * @param clazz clazz
      * @param sql sql
      * @param param param
      * @return java.util.List<T>
      * @author z004267r
      * @date 8/23/2019 5:15 PM
      */
     public <T> List<T> queryByConditionNQ(Class clazz, String sql, Map<String, Object> param) {

         Query query = em.createNativeQuery(sql);
         if (MapUtils.isNotEmpty(param)) {
             for (Map.Entry<String, Object> entry : param.entrySet()) {
                 query.setParameter(entry.getKey(), entry.getValue());
             }
         }
         query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
         List results = query.getResultList();
         if (CollectionUtils.isEmpty(results)) {
             return Lists.newArrayList();
         }
         List<T> content = (List<T>) BeanMapUtil.mapList(results, clazz);
         return content;

     }

     /**
      * 根据自定义sql、参数进行查询数据条数
      *
      * @param countSql countSql
      * @param param param
      * @return int
      * @author z004267r
      * @date 8/23/2019 5:15 PM
      */
     public int queryCount(String countSql, Map<String, Object> param) {
         Query countQ = em.createNativeQuery(countSql);
         if (param != null) {
             for (Map.Entry<String, Object> entry : param.entrySet()) {
                 countQ.setParameter(entry.getKey(), entry.getValue());
             }
         }
         int total = new BigInteger(countQ.getSingleResult().toString()).intValue();
         return total;

     }

     /**
      * 根据自定义sql、参数进行分页查询
      *
      * @param clazz clazz
      * @param sql sql
      * @param param param
      * @param pageRequest pageRequest
      * @return org.springframework.data.domain.Page<T>
      * @author z004267r
      * @date 8/23/2019 5:15 PM
      */
     public <T> Page<T> queryByConditionNQ(Class clazz, String sql, Map<String, Object> param, Pageable pageRequest) {

         Query query = em.createNativeQuery(sql);

         String countSql = "select count(*) from (" + sql + ") count_sql";
         Query countQ = em.createNativeQuery(countSql);
         if (param != null) {
             for (Map.Entry<String, Object> entry : param.entrySet()) {
                 query.setParameter(entry.getKey(), entry.getValue());
                 countQ.setParameter(entry.getKey(), entry.getValue());
             }
         }

         List<Object> totals = countQ.getResultList();
         Long total = 0L;
         for (Object elementObj : totals) {
             BigInteger elementBi = (BigInteger) elementObj;//转换成大类型
             Long element = elementBi.longValue(); //转换成long类型
             total += element == null ? 0 : element;
         }

         query.setFirstResult((int) pageRequest.getOffset());
         query.setMaxResults(pageRequest.getPageSize());
       /* List<T> content = total > pageRequest.getOffset() ? query.getResultList() : Collections
                .<T> emptyList();*/
         query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
         List results = Lists.newArrayList();
         if (total > pageRequest.getOffset()) {
             results = query.getResultList();
         }
         List<T> content = (List<T>) BeanMapUtil.mapList(results, clazz);
         return new PageImpl<>(content, pageRequest, total);
     }
 }