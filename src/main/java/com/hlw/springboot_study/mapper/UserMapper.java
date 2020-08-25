package com.hlw.springboot_study.mapper;

import com.hlw.springboot_study.service.bo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select(value = "select * from t_user_info")
    List<UserInfo> queryUserList();
}
