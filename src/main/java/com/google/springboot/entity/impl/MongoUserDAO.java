package com.google.springboot.entity.impl;

import com.google.springboot.interfaces.UserDAO;

import java.util.Arrays;
import java.util.List;

public class MongoUserDAO implements UserDAO {
    @Override
    public List<String> getAllUserNames() {
        System.out.println("*** getting usernames from MongoDB***");
        return Arrays.asList("*** Getting usernames from MongoDB ***");
    }
}
