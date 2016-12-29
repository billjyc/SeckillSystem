package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by billjyc on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao类实现依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() throws Exception {
        /**
         *
         * 15:15:49.607 [main] DEBUG org.mybatis.spring.transaction.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@1be2019a] will not be managed by Spring
         15:15:49.635 [main] DEBUG org.seckill.dao.SeckillDao.reduceNumber - ==>  Preparing: UPDATE seckill SET number = number - 1 WHERE seckill_id = ? and start_time <= ? and end_time >= ? and number > 0;
         15:15:49.689 [main] DEBUG org.seckill.dao.SeckillDao.reduceNumber - ==> Parameters: 1000(Long), 2016-12-19 15:15:49.081(Timestamp), 2016-12-19 15:15:49.081(Timestamp)
         15:15:49.697 [main] DEBUG org.seckill.dao.SeckillDao.reduceNumber - <==    Updates: 0

         */
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("update count = " + updateCount);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000l;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for(Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

}