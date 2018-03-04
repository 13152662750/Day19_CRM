package com.fjw.service.impl;

import com.fjw.mapper.CrmDepartmentMapper;
import com.fjw.mapper.CrmPostMapper;
import com.fjw.pojo.*;
import com.fjw.service.PostService;
import com.fjw.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private CrmPostMapper crmPostMapper;

    //分页查询所有职务:两个表连接查询
    @Override
    public PageUtil findAllPostInfos(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);

        List<CrmPost> crmPosts =crmPostMapper.findPagePostInfos();

        PageUtil pageUtil=new PageUtil();

        pageUtil.setItems(crmPosts);
        pageUtil.setCurrentPage(currentPage);

        //查询出总的条数
        PageInfo<CrmPost> pageInfo=new PageInfo<>(crmPosts);
        long totalCount=pageInfo.getTotal();

        //计算总页数
        long totalPage=totalCount/pageSize;
        if(totalCount%pageSize!=0){
            totalPage++;
        }
        pageUtil.setTotalPage(totalPage);
        return pageUtil;
    }


    //根据depid添加职务
    @Override
    public void addDepartmentByDepid(String postname,String depid) {
        CrmPost crmPost=new CrmPost();
        crmPost.setDepid(depid);
        crmPost.setPostname(postname);

        //手动添加postid
        String postid = UUID.randomUUID().toString();
        crmPost.setPostid(postid);

        crmPostMapper.insert(crmPost);
    }

    //通过id修改职务信息
    @Override
    public void updatePostByPostid(CrmPost crmPost) {
        String depid = crmPost.getCrmDepartment().getDepid();
        crmPost.setDepid(depid);
        crmPostMapper.updateByPrimaryKeySelective(crmPost);
    }

    //通过职务id查询职务信息
    @Override
    public CrmPost findPostByPostid(String postid) {
        return crmPostMapper.selectByPrimaryKey(postid);
    }

    //根据部门id查询所有职务
    @Override
    public List<CrmPost> findAllPostsByDeptid(String depid) {
        CrmPostExample example=new CrmPostExample();
        CrmPostExample.Criteria criteria = example.createCriteria();
        criteria.andDepidEqualTo(depid);
        List<CrmPost> crmPosts = crmPostMapper.selectByExample(example);
        return crmPosts;
    }
}
