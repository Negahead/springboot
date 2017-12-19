package com.google.springboot.mapper.r;

import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.entity.response.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoRMapper {
    UserInfoResponse getUserInfo();
    List<Integer> getExistingIds(OrgOperationRequest request);
}
