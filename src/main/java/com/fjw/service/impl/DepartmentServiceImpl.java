package com.fjw.service.impl;

import com.fjw.mapper.CrmDepartmentMapper;
import com.fjw.pojo.CrmDepartment;
import com.fjw.pojo.CrmDepartmentExample;
import com.fjw.service.DepartmentService;
import com.fjw.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private CrmDepartmentMapper crmDepartmentMapper;

    //分页查询部门
    @Override
    public PageUtil findPageDepartments(int currentPage,int pageSize) {
        //先使用分页插件
        PageHelper.startPage(currentPage,pageSize);

        CrmDepartmentExample example=new CrmDepartmentExample();
        //当example条件为空时，查询全部
        //CrmDepartmentExample.Criteria criteria = example.createCriteria();
        //查询出当前页的所有记录
        List<CrmDepartment> departments = crmDepartmentMapper.selectByExample(example);
        PageUtil pageUtil=new PageUtil();
        pageUtil.setItems(departments);

        pageUtil.setCurrentPage(currentPage);

        //使用分页插件查询出所有记录数
        PageInfo<CrmDepartment> pageInfo=new PageInfo<CrmDepartment>(departments);
        long totalCount = pageInfo.getTotal();//总记录数

        //计算出总页数
        long totalPage=totalCount/pageSize;
        if(totalCount%pageSize!=0){
            totalPage++;
        }
        pageUtil.setTotalPage(totalPage);

        return pageUtil;
    }

    //通过id查询部门信息
    @Override
    public CrmDepartment findDepartmentById(String depid) {
        CrmDepartment department = crmDepartmentMapper.selectByPrimaryKey(depid);
        return department;
    }

    //修改部门名称
    @Override
    public void updateDepartment(CrmDepartment department) {
        crmDepartmentMapper.updateByPrimaryKey(department);
    }

    //添加部门
    @Override
    public void addDepartment(CrmDepartment department) {
        //手动添加id
        String depid = UUID.randomUUID().toString();
        department.setDepid(depid);
        crmDepartmentMapper.insert(department);
    }

    //查询所有部门
    @Override
    public List<CrmDepartment> findAllDepts() {
        CrmDepartmentExample example=new CrmDepartmentExample();
        return crmDepartmentMapper.selectByExample(example);
    }
}
