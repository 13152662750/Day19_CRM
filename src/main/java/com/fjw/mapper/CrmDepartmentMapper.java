package com.fjw.mapper;

import com.fjw.pojo.CrmDepartment;
import com.fjw.pojo.CrmDepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmDepartmentMapper {
    int countByExample(CrmDepartmentExample example);

    int deleteByExample(CrmDepartmentExample example);

    int deleteByPrimaryKey(String depid);

    int insert(CrmDepartment record);

    int insertSelective(CrmDepartment record);

    //分页查询所有部门
    List<CrmDepartment> selectByExample(CrmDepartmentExample example);

    CrmDepartment selectByPrimaryKey(String depid);

    int updateByExampleSelective(@Param("record") CrmDepartment record, @Param("example") CrmDepartmentExample example);

    int updateByExample(@Param("record") CrmDepartment record, @Param("example") CrmDepartmentExample example);

    int updateByPrimaryKeySelective(CrmDepartment record);

    int updateByPrimaryKey(CrmDepartment record);


}