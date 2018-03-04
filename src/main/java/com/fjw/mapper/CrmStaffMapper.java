package com.fjw.mapper;

import com.fjw.pojo.CrmStaff;
import com.fjw.pojo.CrmStaffExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmStaffMapper {
    int countByExample(CrmStaffExample example);

    int deleteByExample(CrmStaffExample example);

    int deleteByPrimaryKey(String staffid);

    int insert(CrmStaff record);

    int insertSelective(CrmStaff record);

    List<CrmStaff> selectByExample(CrmStaffExample example);

    CrmStaff selectByPrimaryKey(String staffid);

    int updateByExampleSelective(@Param("record") CrmStaff record, @Param("example") CrmStaffExample example);

    int updateByExample(@Param("record") CrmStaff record, @Param("example") CrmStaffExample example);

    int updateByPrimaryKeySelective(CrmStaff record);

    int updateByPrimaryKey(CrmStaff record);

    //修改密码
    void updateLoginPwd(CrmStaff crmStaff);

    //查询所有员工信息
    List<CrmStaff> selectAllStaffsInfosByPage();

    //高级查询，查询员工信息
    List<CrmStaff> findStaffInfosByConditon(CrmStaff crmStaff);

    //根据员工id查询员工信息
    CrmStaff findStaffInfosById(String staffId);

}