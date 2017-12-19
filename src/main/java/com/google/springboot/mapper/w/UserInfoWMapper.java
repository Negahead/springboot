package com.google.springboot.mapper.w;

import com.google.springboot.entity.request.OrgOperationRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoWMapper {
    int transferOrgCrew(OrgOperationRequest request);
}
