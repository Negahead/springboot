package com.google.springboot.mapper.w;

import com.google.springboot.entity.request.OrgOperationRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoWMapper {
    int transferOrgCrew(OrgOperationRequest request);

    void mybatisParameter(@Param("id") int id,@Param("stringList") List<String> stringList);

    void HashMapParameter(@Param("playerMap") Map<String, Integer> playerMap);

    @Insert("insert into A(age) VALUES (#{id})")
    void stringToIntInMybatis(@Param("id") String id);
}
