package com.fjw.service.impl;

import com.fjw.mapper.CrmDepartmentMapper;
import com.fjw.mapper.CrmPostMapper;
import com.fjw.mapper.CrmStaffMapper;
import com.fjw.pojo.CrmStaff;
import com.fjw.pojo.CrmStaffExample;
import com.fjw.service.StaffService;
import com.fjw.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.util.List;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private CrmStaffMapper crmStaffMapper;
    //登录
    @Override
    public CrmStaff findStaff(String loginName, String loginPwd) {
        CrmStaffExample example=new CrmStaffExample();
        CrmStaffExample.Criteria criteria= example.createCriteria();
        //先根据用户名查询用户
        criteria.andLoginnameEqualTo(loginName);

        List<CrmStaff> staffs = crmStaffMapper.selectByExample(example);
        CrmStaff crmStaff=null;
        if(staffs!=null&&staffs.size()>0){
            crmStaff = staffs.get(0);
            //比较密码
            if(crmStaff.getLoginpwd().equals(DigestUtils.md5DigestAsHex(loginPwd.getBytes()))){
                return crmStaff;
            }
            return null;
        }
        return crmStaff;
    }

    //修改密码
    @Override
    public void updatePwd(CrmStaff staff) {
        crmStaffMapper.updateLoginPwd(staff);
    }

    //分页查询所有员工信息
    @Override
    public PageUtil findAllStaffsInfoByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);


        //List<CrmStaff> crmStaffs = crmStaffMapper.selectByExample(example);

        //三表联合查询
        List<CrmStaff> crmStaffs = crmStaffMapper.selectAllStaffsInfosByPage();

        PageUtil pageUtil=new PageUtil();
        pageUtil.setItems(crmStaffs);

        pageUtil.setCurrentPage(currentPage);

        //查询所有记录数
        PageInfo<CrmStaff> pageInfo=new PageInfo<CrmStaff>(crmStaffs);
        long totalCount = pageInfo.getTotal();
        //计算出总页数
        long totalPage = totalCount / pageSize;
        if(totalCount%pageSize!=0){
            totalPage++;
        }
        pageUtil.setTotalPage(totalPage);
        return pageUtil;
    }

    //高级查询，查询员工信息
    @Override
    public PageUtil findStaffInfosByConditon(int currentPage, int pageSize, CrmStaff crmStaff) {
        PageHelper.startPage(currentPage,pageSize);
        List<CrmStaff> crmStaffs = crmStaffMapper.findStaffInfosByConditon(crmStaff);
        PageUtil pageUtil=new PageUtil();
        pageUtil.setItems(crmStaffs);

        pageUtil.setCurrentPage(currentPage);

        //查询所有记录数
        PageInfo<CrmStaff> pageInfo=new PageInfo<CrmStaff>(crmStaffs);
        long totalCount = pageInfo.getTotal();
        //计算出总页数
        long totalPage = totalCount / pageSize;
        if(totalCount%pageSize!=0){
            totalPage++;
        }
        pageUtil.setTotalPage(totalPage);
        return pageUtil;
    }

    //根据员工id查询员工信息
    @Override
    public CrmStaff findStaffById(String staffId) {
        return crmStaffMapper.findStaffInfosById(staffId);
    }

    //修改员工信息
    @Override
    public void updateStaffInfo(CrmStaff crmStaff) {
        String postid = crmStaff.getCrmPost().getPostid();
        crmStaff.setPostid(postid);

        String depid = crmStaff.getCrmPost().getCrmDepartment().getDepid();
        crmStaff.getCrmPost().setDepid(depid);

        crmStaffMapper.updateByPrimaryKeySelective(crmStaff);
    }

    //添加员工
    @Override
    public void addStaff(CrmStaff crmStaff) {
        //手动添加员工id
        String staffid = UUID.randomUUID().toString();
        crmStaff.setStaffid(staffid);

        //登录密码进行md5加密
        String loginpwd = crmStaff.getLoginpwd();
        String loginpwd_md5 = DigestUtils.md5DigestAsHex(loginpwd.getBytes());
        crmStaff.setLoginpwd(loginpwd_md5);

        //获取postid
        String postid = crmStaff.getCrmPost().getPostid();

        crmStaff.setPostid(postid);

        crmStaffMapper.insertSelective(crmStaff);
    }
}
