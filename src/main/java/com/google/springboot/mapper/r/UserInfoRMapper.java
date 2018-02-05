package com.google.springboot.mapper.r;

import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.entity.response.MysqlDateTime;
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

    NestedClass mybatis(@Param("flag") int flag);

    Map<String,String> mapResult(Map<String, Object> map1);

    Map<String,Object> getMySQLVersion();


    List<MysqlDateTime> mysqlDateTime();

    String concatLike(Map<String, String> params);
}
