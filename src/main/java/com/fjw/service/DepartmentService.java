package com.fjw.service;

import com.fjw.pojo.CrmDepartment;
import com.fjw.pojo.CrmPost;
import com.fjw.util.PageUtil;

import java.util.List;

public interface DepartmentService {
    //分页查询部门
    PageUtil findPageDepartments(int currentPage,int pageSize);

    //通过id查询部门信息
    CrmDepartment findDepartmentById(String depid);

    //修改部门信息
    void updateDepartment(CrmDepartment department);

    //添加部门
    void addDepartment(CrmDepartment department);

    //查询所有部门
    List<CrmDepartment> findAllDepts();



}
