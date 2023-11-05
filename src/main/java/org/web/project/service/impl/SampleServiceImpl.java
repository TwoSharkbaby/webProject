package org.web.project.service.impl;

import org.springframework.stereotype.Service;
import org.web.project.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {


    @Override
    public Integer doAdd(String str1, String str2) throws Exception {
        return Integer.parseInt(str1) + Integer.parseInt(str2);
    }
}
