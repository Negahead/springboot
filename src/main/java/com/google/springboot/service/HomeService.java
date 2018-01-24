package com.google.springboot.service;

import com.google.springboot.entity.POJO.Mercedes;
import com.google.springboot.entity.POJO.Tree;
import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.beans.Person;
import com.google.springboot.entity.enums.ErrorCodeEnum;
import com.google.springboot.entity.mongo.Customer;
import com.google.springboot.entity.mongo.CustomerRepository;
import com.google.springboot.entity.redis.Movie;
import com.google.springboot.entity.redis.RedisRepository;
import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.entity.response.MysqlDateTime;
import com.google.springboot.entity.response.NestedClass;
import com.google.springboot.entity.response.UserInfoResponse;
import com.google.springboot.interfaces.UserDAO;
import com.google.springboot.mapper.r.UserInfoRMapper;
import com.google.springboot.mapper.w.UserInfoWMapper;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

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

    @Autowired
    RedisRepository redisRepository;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    JavaMailSender javaMailSender;


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
        NestedClass nestedClass = userInfoRMapper.mybatis(0);
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

    public ResponseResult invokeMySQLProcedure() {
        Map<String,Object> version = userInfoRMapper.getMySQLVersion();
        if(version != null) {
            return new ResponseResult<>(version);
        }
        return new ResponseResult<>("");
    }

    public ResponseResult uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        System.out.println("original file name is ");
        System.out.println(originalFilename);
        System.out.println("file content type is " + file.getContentType());
        System.out.println(file.getName());
        try {
            file.transferTo(new File("/home/will/Desktop/copy"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseResult<>("");
    }

    public ResponseResult poi() {
        try {
            Workbook workbook = new XSSFWorkbook("/home/will/poi.xlsx");
            Sheet sheet0 = workbook.getSheetAt(0);
            for(Row row : sheet0) {
                for(Cell cell : row) {
//                    switch (cell.getCellTypeEnum()) {
//                        case STRING:
//                            System.out.printf("%20s",cell.getStringCellValue());
//                        case NUMERIC:
//                            System.out.printf("%20s",cell.getNumericCellValue());
//                        case BLANK:
//                            System.out.printf("%20s","blank value");
//                        case BOOLEAN:
//                            System.out.printf("%20s",cell.getBooleanCellValue());
//                            default:
//                                System.out.printf("%20s","default");
//                    }
                    System.out.printf("%20s",cell.getStringCellValue());
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseResult<>("");
    }

    public ResponseResult redisNewHash(String id, String name) {
        Movie movie = new Movie(id,name);
        redisRepository.add(movie);
        return new ResponseResult<>("OK");
    }

    public ResponseResult redisHashValue(String id) {
        /**
         * set the serializer both to get the correct answer, BOTH!!!!!!!!!!!!!!!!!!!!!!!!
         * template.setHashKeySerializer(new StringRedisSerializer());
         * template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
         */
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        Integer o = (Integer)hashOps.get("myhash", "Clair Foy");
        hashOps.put("myhash","Conan",45);
        Map<String, Object> entries = hashOps.entries("myhash");
        if(entries != null) {
            return new ResponseResult<>(entries);
        }
        return new ResponseResult<>("");
    }

    public ResponseResult redisList() {
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        List<Object> mylist = listOps.range("mylist", 0, -1);
        if(mylist != null) {
            return new ResponseResult<>(mylist);
        }
        return new ResponseResult<>("");
    }

    public ResponseResult redisListOps(String param) {
        List<String> cities = new ArrayList<>();
        cities.add("New York");
        cities.add("London");
        cities.add("Sydney");

        Map<String,Integer> actors = new HashMap<>();
        actors.put("Tom Hardy",45);
        actors.put("Clair Foy",26);
        /**
         * template.setKeySerializer(new StringRedisSerializer());
         * template.setValueSerializer(new StringRedisSerializer());
         * those two serializer will run smoothly with string.
         */
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        listOps.set("mylist",0,param);
        /**
         * template.setValueSerializer(new StringRedisSerializer());,this will fail,error is "you can't cast List to String"
         * but,you you change to template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
         * the saved value is changed to "\"faker\"","[\"New York\",\"London\",\"Sydney\"]"
         * if you retrieve it however,it is returned as ArrayList,and "faker"
         */
        listOps.set("mylist",1,cities);

        /**
         * you can not set map
         */
//        listOps.set("mylist",2,actors);
        return new ResponseResult<>("");
    }

    public ResponseResult retrieveRedisListByIndex(int index) {
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        Object result = listOps.index("mylist", index);
        if(result != null) {
            return new ResponseResult<>(result);
        }
        return new ResponseResult<>("");
    }

    public ResponseResult sendMail() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.163.com");
//        mailSender.setPort(25);
//
//        mailSender.setUsername("ZW282615SC@163.com");
//        mailSender.setPassword("1234SC.ZW");


        /**
         *  SSL mail basic properties settings:
         */
        Properties props = new Properties();
        //props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.host","smtp.163.com");
        /**
         * 163 mail :
         *      IMAP imap.163.com 993(SSL) 143(no SSL)
         *      SMTP smtp.163.com 465/994  25
         *      POP3 pop.163.com  995      110
         */
        props.put("mail.smtp.port",465);
        //props.put("mail.smtp.socketFactory.port",465);
        props.put("mail.smtp.auth","true");
        //props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); // SSL

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                /**
                 * this is not the email password,WangYi give(let me set) me a third party mail usage password.
                 */
                return new PasswordAuthentication("ZW282615SC@163.com","2b172b");
            }
        };

        Session session = Session.getDefaultInstance(props,authenticator);

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type","text/HTML;charset=UTF-8");
            /**
             * you must set this,and it must be your valid send mail account! or error
             */
            msg.setFrom("ZW282615SC@163.com");
            //msg.setReplyTo(InternetAddress.parse("ZW282615SC@163.com",false));
            msg.setSubject("subject","UTF-8");
            msg.setText("text","UTF-8");
            /**
             * invalid date,will cause 554 error code,which is marked as a spam email
             */
            msg.setSentDate(new Date());
            /**
             * send group mails,receiver will see all the other people who also got the mail.so use carefully!
             */
            msg.setRecipients(
                    Message.RecipientType.TO,
                    new InternetAddress[]{new InternetAddress("1097503158@qq.com",false),
                                          new InternetAddress("hahaiamzhouwei@gmail.com",false)});

            Multipart multipart = new MimeMultipart();

            /**
             * create the multi-part message body,msg.setText("text","UTF-8") is useless now
             */
            BodyPart msgBody = new MimeBodyPart();
            msgBody.setDescription("body part description");
            msgBody.setText("body part text");
            multipart.addBodyPart(msgBody);

            /**
             * part two is attachment
             */
            BodyPart multiBody = new MimeBodyPart();
            DataSource source = new FileDataSource("/home/will/openssl.cnf");
            multiBody.setDataHandler(new DataHandler(source));
            multiBody.setFileName("openssl.cnf");
            multipart.addBodyPart(multiBody);

            msg.setContent(multipart);


            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return new ResponseResult<>("mail sent");
    }

    public ResponseResult stringToIntInMybatis(String id) {
        userInfoWMapper.stringToIntInMybatis(id);
        return new ResponseResult<>("mail sent");
    }

    public ResponseResult mysqlDateTime() {
        List<MysqlDateTime> response = userInfoRMapper.mysqlDateTime();
        return new ResponseResult<>(response);
    }
}
