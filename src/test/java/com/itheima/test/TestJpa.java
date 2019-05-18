package com.itheima.test;

import com.itheima.pojo.Customer;
import com.itheima.utils.JpaUtil;
import org.junit.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;


public class TestJpa {
    /**
     * 新增
     */
    @Test
    public void testADD(){
        EntityManager entityManager = null;
        EntityTransaction transaction= null;
        try {
            // 获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            Customer  customer = new Customer();
            // 开启事务
            transaction.begin();
            customer.setCustName("土匪");
            // 保存操作
            entityManager.persist(customer);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 修改
     */
    @Test
    public void testMerge(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            // 获取实体管理器
             entityManager = JpaUtil.getEntityManager();
            // 获取事务对象
             transaction = entityManager.getTransaction();
            Customer  customer = new Customer();
            // 开启事务
            transaction.begin();
            Customer c = entityManager.find(Customer.class, 1L);
//            c.setCustName("奥特曼");
            entityManager.merge(c);
            entityManager.persist(customer);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 删除
     */
    @Test
    public void testRemove() {
        EntityManager entityManager = null;
        EntityTransaction transaction= null;
        try {
            // 获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            Customer customer = new Customer();
            // 开启事务
            transaction.begin();
            Customer c = entityManager.find(Customer.class, 3L);
            entityManager.remove(c);
            entityManager.persist(customer);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll(){
    EntityManager entityManager = null;
    EntityTransaction transaction= null;
        try {
            // 获取实体管理器
             entityManager = JpaUtil.getEntityManager();
            // 获取事务对象
             transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 定义jpql
            String jpql = "from Customer";
            // 创建执行对象
            Query query = entityManager.createQuery(jpql);
            // 获取结果集
            List resultList = query.getResultList();
            // 遍历结果
            for (Object o : resultList) {
                System.out.println(o);
            }

            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void findPage(){
        EntityManager entityManager = null;
        EntityTransaction transaction= null;
        try {
            // 获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 定义jpql
            String jpql = "from Customer";
            // 创建执行对象
            Query query = entityManager.createQuery(jpql);
            // 定义分页
            query.setFirstResult(0); // 起始索引
            query.setMaxResults(3);// 显示个数
            // 获取结果集
            List resultList = query.getResultList();
            // 遍历结果
            for (Object o : resultList) {
                System.out.println(o);
            }

            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }
    /**
     * 条件查询
     */
    @Test
    public void findOne(){
        EntityManager entityManager = null;
        EntityTransaction transaction= null;
        try {
            // 获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 定义jpql
            String jpql = "from Customer where custId = ?";
            // 创建执行对象
            Query query = entityManager.createQuery(jpql);
            // 对占位符设置
            query.setParameter(1,2l);
            // 获取结果集
            Object singleResult = query.getSingleResult(); // 获取单个结果集
            // 打印结果
            System.out.println(singleResult);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 排序查询
     */
    @Test
    public void findOrder(){
        EntityManager entityManager = null;
        EntityTransaction transaction= null;
        try {
            // 获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 定义jpql
            String jpql = "from Customer order by custId desc ";
            // 创建执行对象
            Query query = entityManager.createQuery(jpql);
            // 获取结果集
            List resultList = query.getResultList();
            // 打印结果
            for (Object o : resultList) {
                System.out.println(o);
            }
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 统计查询
     */
    @Test
    public void testCount(){
        EntityManager entityManager = null;
        EntityTransaction transaction= null;
        try {
            // 获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 定义jpql
            String jpql = "select count(custId) from Customer";
            // 创建执行对象
            Query query = entityManager.createQuery(jpql);
            // 获取结果集
            Object singleResult = query.getSingleResult();
            // 打印结果
            System.out.println(singleResult);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }
}
