package com.bobochen.easyblogback.allinone.test.threadtest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class User {
    private String name;
    private Integer age;
    private Long money;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone = "GMT+8")
    private Date birthday;
    private String email;
    private BigDecimal price;
}
