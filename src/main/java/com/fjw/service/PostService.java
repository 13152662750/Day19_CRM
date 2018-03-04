package com.fjw.service;

import com.fjw.pojo.CrmDepartment;
import com.fjw.pojo.CrmPost;
import com.fjw.util.PageUtil;

import java.util.List;

public interface PostService {

    //分页查询所有职务
    PageUtil findAllPostInfos(int currentPage, int pageSize);

    //根据depid添加职务
    void addDepartmentByDepid(String postname,String depid);

    //修改职务信息
    void updatePostByPostid(CrmPost crmPost);

    //通过职务id查询职务信息
    CrmPost findPostByPostid(String postid);

    //根据部门名称查询所有职务
    List<CrmPost> findAllPostsByDeptid(String depid);
}
