package com.google.springboot.entity.mongo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *  Spring Data MongoDB will map the class Customer into a collection called customer,if you want to
 *  change the name of the collection,you can use Spring Data MongoDB's  @Document annotations on the class.
 */

@Document(collection = "bar")
public class Customer {
    /**
     * ' @Id mapped to the "_id" field in mongo database
     * A property or field without an annotation but named id will be mapped to the "_id" field in mongo database
     */
    @Id
    private String id;

    /**
     * firstName and lastName are left unannotated,It is assumed that they will be mapped to fields
     * that share the same names as the properties themselves.
     */

    private String name;
    /**
     * if number in mongoDB is like [12,34],it will retrieve 12
     */
    private int number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Customer(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
