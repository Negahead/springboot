package com.google.springboot.entity.mongo;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Field;

@TypeAlias("info")
public class Info {
    @Field("age")
    private int ageInfo;
    @Field("country")
    private String countryInfo;

    public int getAgeInfo() {
        return ageInfo;
    }

    public void setAgeInfo(int ageInfo) {
        this.ageInfo = ageInfo;
    }

    public String getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(String countryInfo) {
        this.countryInfo = countryInfo;
    }
}
