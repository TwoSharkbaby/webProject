package org.web.project.service;

import lombok.extern.log4j.Log4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.plaf.PanelUI;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j
public class SampleServiceTest {

    @Autowired
    public SampleService sampleService;

    @Test
    public void testClass(){
        log.info(sampleService);
        log.info(sampleService.getClass().getName());
    }

    @Test
    public void testAdd() throws Exception {
        log.info(sampleService.doAdd("123", "456"));
    }

    @Test
    public void testAddWithException() throws Exception {
//        sampleService.doAdd("123", "ABCA");
    }

}