package com.google.springboot.entity.impl;

import com.google.springboot.interfaces.UserDAO;

import java.util.Arrays;
import java.util.List;

public class JdbcUserDAO implements UserDAO {

    @Override
    public List<String> getAllUserNames() {
        System.out.println("*** getting username from RDBMS***");
        return Arrays.asList("Jim","john","Rob");
    }
}
