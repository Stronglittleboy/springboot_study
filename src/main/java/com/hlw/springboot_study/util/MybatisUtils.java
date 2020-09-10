package com.hlw.springboot_study.util;

/**
 * @program: test
 * @description: Mybatis的工具类
 * @author: houlongwang
 * @create: 2019-02-06 20:08
 **/

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 工具类的内容：
 * 1.创建SQLSessionFactory
 * 2.获取SQLsession
 * 3.是否自动提交事务
 */
public class MybatisUtils {
    /**
     * 获取SQLSessionFactory，通过传递的资源配置文件
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory(){
        String resourcepath="mybatisDemo/mybatis-config.xml";
        InputStream is = MybatisUtils.class.getClassLoader().getResourceAsStream(resourcepath);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory;
    }

    /**
     * 获取sqlsession
     * @return
     */
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        return sqlSession;
    }
    public static SqlSession getSqlSession(Boolean isAutoCommit){
        SqlSession sqlSession = getSqlSessionFactory().openSession(isAutoCommit);
        return sqlSession;
    }
}


