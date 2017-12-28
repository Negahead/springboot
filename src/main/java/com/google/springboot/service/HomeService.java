package com.google.springboot.service;

import com.google.springboot.entity.POJO.Mercedes;
import com.google.springboot.entity.POJO.Tree;
import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.beans.Person;
import com.google.springboot.entity.enums.ErrorCodeEnum;
import com.google.springboot.entity.mongo.Customer;
import com.google.springboot.entity.mongo.CustomerRepository;
import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.entity.response.NestedClass;
import com.google.springboot.entity.response.UserInfoResponse;
import com.google.springboot.interfaces.UserDAO;
import com.google.springboot.mapper.r.UserInfoRMapper;
import com.google.springboot.mapper.w.UserInfoWMapper;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
/**
 *  the @CacheConfig(cachenames="instruments") registers every method annotated with the spring
 *  framework caching annotations with the specific cache.
 */
//@CacheConfig(cacheNames = "instruments")
/**
 * At a high level,Spring creates proxies for all the classes annotated with @Transactional,either on the
 * class or on any of the methods.The proxy allows the framework to inject transactional logic before
 * and after the method being invoked,mainly for starting and committing the transaction.
 *
 * only public methods should be annotated with @Transactional.
 */
@Transactional
public class HomeService {
    @Autowired
    UserInfoRMapper userInfoRMapper;

    @Autowired
    UserInfoWMapper userInfoWMapper;

    /**
     *  '@Autowired is fundamentally about type-driven injection with optional semantic qualifiers,
     *  the bean's id(name) will be used as a fallback,this is the case when you use @Qualifier("beanName")use
     *
     *  '@Inject @YourQualifier private Foo foo()
     *
     *  '@Resource is similar to @Autowired and @Inject,but the main difference is the execution paths token to
     *  find out the required bean to inject,@Resource will narrow down the search first by name then
     *  by type and finally by Qualifiers
     */
//    @Autowired
//    UserDAO userDAO;

    @Autowired
    Person person;

    @Autowired
    Tree tree;

    @Autowired
    Mercedes mercedes;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    @Qualifier("mongoClient")
    MongoClient mongoClient;

    public ResponseResult home() {
        try {
            Class.forName("com.google.springboot");
            return new ResponseResult<>("Hello World");
        } catch (ClassNotFoundException e) {
            person.setName("will");
            return new ResponseResult<>(person.getName()+tree.getName()+mercedes.getName());
        }
    }

    /**
     * the result the the subsequent invocations will be cached.
     * @return
     */
    //@Cacheable()
    public ResponseResult getUserInfo() {
        UserInfoResponse userInfoResponse =  userInfoRMapper.getUserInfo();
        if(userInfoResponse != null) {
            return new ResponseResult<>(userInfoResponse);
        }
        //return new ResponseResult<>(userDAO.getAllUserNames());
        return new ResponseResult<>("");
    }

    public ResponseResult transferOrgCrew(OrgOperationRequest request) {
        if(request.getUserIds() == null || request.getUserIds().isEmpty()) {
            return new ResponseResult<>(ErrorCodeEnum.PARAMETER_ERROR);
        }
        List<Integer> existingIds = userInfoRMapper.getExistingIds(request);
        int affectedRow = userInfoWMapper.transferOrgCrew(request);
        return new ResponseResult<>(affectedRow);
    }

    public ResponseResult invokeMongoDB() {
        Customer customer = customerRepository.findFirstByName("Tom Hardy");
        if(customer != null) {
            return new ResponseResult<>(customer);
        }

        return new ResponseResult<>("");
    }

    public ResponseResult testMongo() {
        /**
         * the class MongoTemplate us the central class of Spring's MongoDB support providing a rich set to interact
         * with the database,this is the place to look for functionality such as incrementing counters or ad-hoc CRUD operations.
         * the preferred way to reference the operations on MongoTemplate instance is its interface
         * MongoOperations.
         * or you can :
         *     MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(),"will"));
         *
         *  the difference between insert and save operations is that save operation will perform an insert if the object
         *  is not already present.
         */
        MongoOperations mongoOPs = new MongoTemplate(mongoClient,"will");
        Customer customer = new Customer();
        //mongoOPs.insert(new Customer("Emma",20));
        if(mongoOPs.collectionExists("bar") && mongoOPs.collectionExists(Customer.class)) {
            customer = mongoOPs.findOne(Query.query(where("name").is("Claire Foy")), Customer.class, "bar");
            mongoOPs.count(Query.query(where("number").is(22)),Customer.class,"bar");
        }
        return new ResponseResult<>(customer);
    }

    public ResponseResult findCollectionsByNumber(int number) {
        /**
         * Pages are zero indexed, thus providing 0 for page will return the first page.
         */
        Pageable pageable = new PageRequest(0,50);
        List<Customer> customerList = customerRepository.findCustomerByNumber(number,pageable);
        if(customerList != null) {
            return new ResponseResult<>(customerList);
        }
        return new ResponseResult<>("");
    }

    public ResponseResult mybatis() {
        NestedClass nestedClass = userInfoRMapper.mybatis();
        if(nestedClass != null) {
            return new ResponseResult<>(nestedClass);
        }
        return new ResponseResult<>("");
    }

    public ResponseResult mybatisParameter() {
        List<String> stringList = new ArrayList<>();
        stringList.add("dopa");
        stringList.add("faker");
        stringList.add("rookie");
        userInfoWMapper.mybatisParameter(1,stringList);

        Map<String,Integer> playerMap = new HashMap<>();
        playerMap.put("java",12);
        playerMap.put("perl",20);
        playerMap.put("python",9);
        userInfoWMapper.HashMapParameter(playerMap);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("memo",1);
        map1.put("logOper","logOper");
        Map<String,String> resultMap = userInfoRMapper.mapResult(map1);
        return new ResponseResult<>("");
    }
}
