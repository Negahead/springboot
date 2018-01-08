package com.google.springboot.entity.redis;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,String>{
}
