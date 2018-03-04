package com.fjw.controller;

import com.fjw.pojo.CrmDepartment;
import com.fjw.pojo.CrmPost;
import com.fjw.service.DepartmentService;
import com.fjw.service.PostService;
import com.fjw.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private DepartmentService departmentService;
    //分页查询所有部门和职务
    @RequestMapping("listPost")
    public String listPost(@RequestParam(defaultValue = "1") int currentPage,
                           @RequestParam(defaultValue = "3") int pageSize,Model model){
        PageUtil pageUtil=postService.findAllPostInfos(currentPage,pageSize);
        model.addAttribute("pageUtil",pageUtil);
        return "post/listPost";
    }

    //跳转到添加职务页面并查询所有部门进行回显
    @RequestMapping("toAddPost")
    public String toAddPost(Model model){
        List<CrmDepartment> departments=departmentService.findAllDepts();
        model.addAttribute("departments",departments);
        return "post/addPost";
    }

    //添加职务
    @RequestMapping("addPost")
    public String addPost(CrmPost crmPost){
        String postname=crmPost.getPostname();
        String depid=crmPost.getCrmDepartment().getDepid();
        //根据depid添加职务
        postService.addDepartmentByDepid(postname,depid);
        return "forward:listPost";
    }

    //跳转到修改职务页面
    @RequestMapping("toUpdatePost")
    public String toUpdatePost(CrmPost crmPost,Model model){
        //查询出所有部门进行回显
        List<CrmDepartment> departments = departmentService.findAllDepts();
        model.addAttribute("departments",departments);

        //通过职务id查询出职务
        CrmPost post=postService.findPostByPostid(crmPost.getPostid());
        model.addAttribute("crmPost",post);
        return "post/updatePost";
    }

    //修改职务信息
    @RequestMapping("updatePost")
    public String updatePost(CrmPost crmPost){
        postService.updatePostByPostid(crmPost);
        return "forward:listPost";
    }

    //根据部门名称查询所有职务
    @RequestMapping("findAllPosts")
    @ResponseBody
    public List<CrmPost> findAllPosts(String depid){
        return postService.findAllPostsByDeptid(depid);
    }
}
