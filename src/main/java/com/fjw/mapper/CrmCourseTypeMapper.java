package com.fjw.mapper;

import com.fjw.pojo.CrmCourseType;
import com.fjw.pojo.CrmCourseTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmCourseTypeMapper {
    int countByExample(CrmCourseTypeExample example);

    int deleteByExample(CrmCourseTypeExample example);

    int deleteByPrimaryKey(String coursetypeid);

    int insert(CrmCourseType record);

    int insertSelective(CrmCourseType record);

    List<CrmCourseType> selectByExample(CrmCourseTypeExample example);

    CrmCourseType selectByPrimaryKey(String coursetypeid);

    int updateByExampleSelective(@Param("record") CrmCourseType record, @Param("example") CrmCourseTypeExample example);

    int updateByExample(@Param("record") CrmCourseType record, @Param("example") CrmCourseTypeExample example);

    int updateByPrimaryKeySelective(CrmCourseType record);

    int updateByPrimaryKey(CrmCourseType record);
}