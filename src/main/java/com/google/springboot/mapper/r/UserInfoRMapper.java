package com.google.springboot.mapper.r;

import com.google.springboot.entity.request.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoRMapper {
    UserInfoResponse getUserInfo();
}
