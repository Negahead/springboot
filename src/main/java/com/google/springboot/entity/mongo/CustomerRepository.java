
// @NonNullApi
// to be used on package level to declare that te default behavior for parameters and return values is to
// not accept or produce null values
package com.google.springboot.entity.mongo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * plugin in the type of values and id it works with:customer and String
 *
 *  MongoRepository extends PagingAndSortingRepository extends CrudRepository extends Repository
 *
 *  org.springframework.data.repository.CrudRepository
 */

// org.springframework.data.repository.@NoRepositoryBean exclude repository interfaces from being picked up
// make sure you add that annotation to all repository interfaces that Spring Data should not create instances for at runtime.
@Repository
//@NoRepositoryBean
public interface CustomerRepository extends MongoRepository<Customer,String>{
    /**
     * Spring Data MongoDB uses the MongoTemplate to execute the queries behind your find* methods.
     * The repository proxy has two ways to derive a store-specific query from the method name:It can derive
     * the query from the method name directly,or by using a manually defined query.
     *
     */
    // @NonNull: to be used on a parameter or return value that must not be null
    // @Nullable: to be used on a parameter or return value that can be null
    Customer findFirstByName(String name);


    /**
     *  add the annotation Query repository finder method you can specify a MongoDB
     *  JSON query string to use instead of having the query derived from the method name
     *  ?0 substitute the value from the method arguments into the JSON query String
     */
    @Query("{number:?0}")
    List<Customer> findCustomerByNumber(int number, Pageable pageable);
}
