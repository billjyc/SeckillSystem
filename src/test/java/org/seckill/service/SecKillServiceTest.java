package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by billjyc on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SecKillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = secKillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() throws Exception {
        Seckill seckill = secKillService.getById(1000l);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1001l;
        Exposer exposer = secKillService.exportSeckillUrl(id);
        if(exposer.isExposed()) {

            long phone = 13000000000l;

            String md5 = "ca7048b13ba0589e537864287520ba11";
            try {
                SeckillExecution seckillExecution = secKillService.executeSeckill(id, phone, md5);
                logger.info("result={}", seckillExecution);
            } catch(RepeatKillException e1) {
                logger.error(e1.getMessage(), e1);
            } catch (SeckillCloseException e2) {
                logger.error(e2.getMessage(), e2);
            }
        } else {
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000l;
        long phone = 13000000000l;

        String md5 = "ca7048b13ba0589e537864287520ba11";
        try {
            SeckillExecution seckillExecution = secKillService.executeSeckill(id, phone, md5);
            logger.info("result={}", seckillExecution);
        } catch(RepeatKillException e1) {
            logger.error(e1.getMessage(), e1);
        } catch (SeckillCloseException e2) {
            logger.error(e2.getMessage(), e2);
        }

    }

    @Test
    public void executeSeckillProcedure() {
        long seckillId = 1001l;
        long phone = 13500000000l;
        Exposer exposer = secKillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution = secKillService.executeSeckillByProcedure(seckillId, phone, md5);
            logger.info(seckillExecution.getStateInfo());
        }
    }

}