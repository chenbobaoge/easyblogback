package com.bobochen.easyblogback.allinone.test.threadtest;

import com.bobochen.easyblogback.allinone.test.threadtest.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JscksonTest {

    @Test
    public void modelToJson(){
        try {
            User user = new User();
            user.setName("小民");
            user.setEmail("xiaomin@sina.com");
            user.setAge(20);
            user.setBirthday(new Date());
            user.setPrice(new BigDecimal("3.69"));
            user.setMoney(21474836470L);
            ObjectMapper mapper = new ObjectMapper();


            String json = mapper.writeValueAsString(user);
            System.out.println(json);
            List<User> users = new ArrayList<User>();
            users.add(user);
            users.add(user);
            users.add(user);
            String json2 = mapper.writeValueAsString(users);
            System.out.println(json2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void jsonTo(){
        try {
            String json1="{\"name\":\"小民\",\"age\":20,\"money\":21474836470,\"birthday\":\"2018-07-14 16:19:28\",\"email\":\"xiaomin@sina.com\",\"price\":3.69}";
            String json2="[{\"name\":\"小民\",\"age\":20,\"birthday\":\"2018-07-14 07:58:10\",\"email\":\"xiaomin@sina.com\"},{\"name\":\"小民\",\"age\":20,\"birthday\":\"2018-07-14 07:58:10\",\"email\":\"xiaomin@sina.com\"},{\"name\":\"小民2\",\"age\":20,\"birthday\":\"2018-07-14 07:58:10\",\"email\":\"xiaomin@sina.com\"}]";
            ObjectMapper mapper = new ObjectMapper();
            User user1 = mapper.readValue(json1,User.class);
            List<User> users1 = mapper.readValue(json2,new TypeReference<List<User>>() {});
            user1.setBirthday(new Date());
            user1.setAge(100);
            String json11 = mapper.writeValueAsString(user1);
            System.out.println(json11);

            users1.get(2).setBirthday(new Date());
            users1.get(2).setAge(200);
            String json12 = mapper.writeValueAsString(users1);
            System.out.println(json12);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
