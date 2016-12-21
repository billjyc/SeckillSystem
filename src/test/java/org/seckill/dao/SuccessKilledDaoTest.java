package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by billjyc on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        /*
15:26:35.097 [main] DEBUG org.mybatis.spring.transaction.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@7728643a] will not be managed by Spring
15:26:35.117 [main] DEBUG org.seckill.dao.SuccessKilledDao.insertSuccessKilled - ==>  Preparing: insert ignore into success_killed(seckill_id, user_phone) values (?, ?)
15:26:35.171 [main] DEBUG org.seckill.dao.SuccessKilledDao.insertSuccessKilled - ==> Parameters: 1000(Long), 13900000000(Long)
15:26:35.182 [main] DEBUG org.seckill.dao.SuccessKilledDao.insertSuccessKilled - <==    Updates: 1
15:26:35.189 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7544a1e4]
insertCount = 1

         */
        long id = 1001;
        long phone = 13900000000l;
        int insertCount = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount = " + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        /*
15:30:47.462 [main] DEBUG org.mybatis.spring.transaction.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@4d157787] will not be managed by Spring
15:30:47.482 [main] DEBUG org.seckill.dao.SuccessKilledDao.queryByIdWithSeckill - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id = s.seckill_id where sk.seckill_id=? and sk.user_phone=?
15:30:47.548 [main] DEBUG org.seckill.dao.SuccessKilledDao.queryByIdWithSeckill - ==> Parameters: 1000(Long), 13900000000(Long)
15:30:47.624 [main] DEBUG org.seckill.dao.SuccessKilledDao.queryByIdWithSeckill - <==      Total: 1
15:30:47.632 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7544a1e4]
SuccessKilled{seckillId=1000, userPhone=13900000000, state=-1, createTime=Mon Dec 19 15:26:35 PST 2016}
Seckill{seckillId=1000, name='1000元秒杀iphone 7', number=100, startTime=Wed Nov 11 00:00:00 PST 2015, endTime=Thu Nov 12 00:00:00 PST 2015, createTime=Mon Dec 19 08:36:10 PST 2016}

         */
        long id = 1001;
        long phone = 13900000000l;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}