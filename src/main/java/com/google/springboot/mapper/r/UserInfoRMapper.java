package com.google.springboot.mapper.r;

import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.entity.response.NestedClass;
import com.google.springboot.entity.response.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoRMapper {
    UserInfoResponse getUserInfo();
    List<Integer> getExistingIds(OrgOperationRequest request);

    NestedClass mybatis();

    Map<String,String> mapResult(Map<String, Object> map1);
}
