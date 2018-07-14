package com.bobochen.easyblogback.eurekadatatemplate.entitiy;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="yananindex",type="yanantype")
public class MyUserEl {
    private int id;
    private String name;
    private String gender;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}