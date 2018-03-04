package com.fjw.mapper;

import com.fjw.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmPostMapper {
    int countByExample(CrmPostExample example);

    int deleteByExample(CrmPostExample example);

    int deleteByPrimaryKey(String postid);

    int insert(CrmPost record);

    int insertSelective(CrmPost record);

    List<CrmPost> selectByExample(CrmPostExample example);

    CrmPost selectByPrimaryKey(String postid);

    int updateByExampleSelective(@Param("record") CrmPost record, @Param("example") CrmPostExample example);

    int updateByExample(@Param("record") CrmPost record, @Param("example") CrmPostExample example);

    int updateByPrimaryKeySelective(CrmPost record);

    int updateByPrimaryKey(CrmPost record);

    //两表连接分页查询所有职务
    List<CrmPost> findPagePostInfos();

    //通过职务id修改部门名称、职务名称。
    void updateByPrimaryKeySelective2(CrmPost crmPost);
}