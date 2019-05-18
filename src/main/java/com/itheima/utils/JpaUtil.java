package com.itheima.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

/**
 * Jpa 工具类，当程序启动时开始加载EntityManagerFactory
 */
public class JpaUtil implements Serializable {

    private static EntityManagerFactory factory;
    static {
         factory = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     *  创建实体管理器EntityManager
     * @return
     */
    public static EntityManager getEntityManager(){
        return  factory.createEntityManager();
    }
}
