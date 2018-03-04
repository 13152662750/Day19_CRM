package com.fjw.controller;

import com.fjw.pojo.CrmDepartment;
import com.fjw.pojo.CrmPost;
import com.fjw.pojo.CrmStaff;
import com.fjw.service.DepartmentService;
import com.fjw.service.PostService;
import com.fjw.service.StaffService;
import com.fjw.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private DepartmentService departmentService;
    //登录
    @RequestMapping("login")
    public String login(String loginName, String loginPwd, Model model, HttpServletRequest request){
        CrmStaff staff = staffService.findStaff(loginName, loginPwd);
        if(staff==null){
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
        request.getSession().setAttribute("staff",staff);
        return "frame";
    }

    //跳转到更改密码页面
    @RequestMapping("{updPwd}")
    public String toUpdatePwd(@PathVariable String updPwd){
        return "staff/"+updPwd;
    }

    //更改密码
    @RequestMapping("updatePwd")
    public String updatePwd(String oldPassword,String newPassword,String reNewPassword,HttpServletRequest request){
        System.out.println(oldPassword+"---"+newPassword);
        CrmStaff staff = (CrmStaff) request.getSession().getAttribute("staff");
        if(oldPassword!=null&&newPassword!=null&&reNewPassword!=null&&newPassword.equals(reNewPassword)){//都不为空
            String loginPwd=staff.getLoginpwd();//原密码:md5格式
            String staffId=staff.getStaffid();//id
            //先加密再比较
            if (loginPwd.equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {//原密码验证通过
                loginPwd=DigestUtils.md5DigestAsHex(newPassword.getBytes());
                staff.setLoginpwd(loginPwd);
                staff.setStaffid(staffId);
                staffService.updatePwd(staff);
                return "login";
            }
            return "login";//原密码错误---可提示用户
        }
        return "login";//三者中有空---可提示用户
    }

    //重新登录：注销当前用户
    @RequestMapping("logout")
    public String logOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

    //分页查询员工信息
    @RequestMapping("listStaff")
    public String listStaff(@RequestParam(defaultValue = "1") int currentPage,
                            @RequestParam(defaultValue = "2") int pageSize,
                            Model model){

        //查询所有部门
        List<CrmDepartment> depts = departmentService.findAllDepts();
        model.addAttribute("depts",depts);

        PageUtil pageUtil=staffService.findAllStaffsInfoByPage(currentPage,pageSize);
        model.addAttribute("pageUtil",pageUtil);
        return "staff/listStaff";
    }

    //高级查询员工信息
    @RequestMapping("findStaffInfosByConditon")
    public String findStaffInfosByConditon(@RequestParam(defaultValue = "1") int currentPage,
                                           @RequestParam(defaultValue = "2") int pageSize,
                                           CrmStaff crmStaff,Model model){
        //查询所有部门
        List<CrmDepartment> depts = departmentService.findAllDepts();
        model.addAttribute("depts",depts);

        PageUtil pageUtil=staffService.findStaffInfosByConditon(currentPage,pageSize,crmStaff);
        model.addAttribute("pageUtil",pageUtil);
        System.out.println(crmStaff.getCrmPost().getCrmDepartment().getDepid()+"--"+crmStaff.getCrmPost().getPostid()+"--"+crmStaff.getStaffname());
        return "staff/listStaff";
    }

    //跳转到修改员工信息页面并回显所有部门，二级联动
    @RequestMapping("toEditStaff")
    public String toEditStaff(String staffId,Model model) {
        //查询所有部门
        List<CrmDepartment> depts = departmentService.findAllDepts();
        model.addAttribute("depts",depts);

        //根据员工id查询员工信息
        CrmStaff staff=staffService.findStaffById(staffId);
        model.addAttribute("staff",staff);
        return "staff/editStaff";
    }

    //修改员工信息
    @RequestMapping("editStaff")
    public String editStaff(CrmStaff crmStaff){
        staffService.updateStaffInfo(crmStaff);
        return "redirect:listStaff";
    }

    //跳转到添加员工页面
    @RequestMapping("toAddStaff")
    public String toAddStaff(Model model){
        //查询所有部门
        List<CrmDepartment> depts = departmentService.findAllDepts();
        model.addAttribute("depts",depts);
        return "staff/addStaff";
    }

    //添加员工
    @RequestMapping("addStaff")
    public String addStaff(CrmStaff crmStaff){
        staffService.addStaff(crmStaff);

        return "redirect:listStaff";
    }
}
