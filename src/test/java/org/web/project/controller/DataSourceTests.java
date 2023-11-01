package org.web.project.controller;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.web.project.mapper.TimeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j
public class DataSourceTests {

    @Setter(onMethod_ = { @Autowired })
    private DataSource dataSource;

//    @Setter(onMethod_ = { @Autowired })
    @Autowired
    private TimeMapper timeMapper;
    @Test
    public void testConnection() {
        try(Connection con = dataSource.getConnection()){
            log.info(con);
        }catch(Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSQL(){
        log.info(timeMapper.getTime());
    }
}
