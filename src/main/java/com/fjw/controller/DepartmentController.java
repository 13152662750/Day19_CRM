package com.fjw.controller;

import com.fjw.pojo.CrmDepartment;
import com.fjw.service.DepartmentService;
import com.fjw.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //查询所有部门
    @RequestMapping("listDepartment")
    public String showAllDepartments(@RequestParam(defaultValue = "1") int currentPage,
                                     @RequestParam(defaultValue = "2") int pageSize, Model model) {
        PageUtil pageUtil = departmentService.findPageDepartments(currentPage, pageSize);
        model.addAttribute("pageUtil", pageUtil);
        return "department/listDepartment";
    }

    //跳转到修改部门信息页面并回显部门信息
    @RequestMapping("toUpdateDepartment")
    public String toUpdateDepartment(String depid, Model model) {
        //通过id查询部门信息
        CrmDepartment department = departmentService.findDepartmentById(depid);
        model.addAttribute("department",department);
        return "department/updateDepartment";
    }

    //修改部门信息
    @RequestMapping("updateDepartment")
    public String updateDepartment(CrmDepartment department){
        //修改部门名称
        departmentService.updateDepartment(department);
        return "forward:listDepartment";
    }


    //跳转到添加部门页面
    @RequestMapping("toAddDepartment")
    public String toAddDepartment(){
        return "department/addDepartment";
    }

    //添加部门
    @RequestMapping("addDepartment")
    public String addDepartment(CrmDepartment department){
        departmentService.addDepartment(department);
        return "forward:listDepartment";
    }
}
