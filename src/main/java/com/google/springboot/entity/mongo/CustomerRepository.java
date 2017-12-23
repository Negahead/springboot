package com.google.springboot.entity.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * plugin in the type of values and id it works with:customer and String
 */
@Component
public interface CustomerRepository extends MongoRepository<Customer,String>{
    /**
     * Spring Data MongoDB uses the MongoTemplate to execute the queries behind your find* methods.
     *
     */
    Customer findFirstByName(String name);


    /**
     *  add the annotation Query repository finder method you can specify a MongoDB
     *  JSON query string to use instead of having the query derived from the method name
     *  ?0 substitute the value from the method arguments into the JSON query String
     */
    @Query("{number:?0}")
    Customer findCustomerByNumber(int number);
}
