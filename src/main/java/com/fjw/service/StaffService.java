package com.fjw.service;

import com.fjw.pojo.CrmStaff;
import com.fjw.util.PageUtil;

public interface StaffService {
    //登录
    CrmStaff findStaff(String loginName,String loginPwd);

    //修改密码
    void updatePwd(CrmStaff staff);

    //分页查询员工信息
    PageUtil findAllStaffsInfoByPage(int currentPage, int pageSize);

    //高级查询，查询员工信息
    PageUtil findStaffInfosByConditon(int currentPage, int pageSize, CrmStaff crmStaff);

    //根据员工id查询员工信息
    CrmStaff findStaffById(String staffId);

    //修改员工信息
    void updateStaffInfo(CrmStaff crmStaff);

    //添加员工
    void addStaff(CrmStaff crmStaff);
}
