package org.web.project.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    @Select("SELECT SYSDATE FROM DUAL")
    public String getTime();
}
