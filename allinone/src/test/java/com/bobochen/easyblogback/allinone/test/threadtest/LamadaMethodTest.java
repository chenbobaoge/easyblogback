package com.bobochen.easyblogback.allinone.test.threadtest;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LamadaMethodTest {
    @Test
    public void test(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"name1"));
        users.add(new User(2,"name2"));
        users.stream().forEach(User::printId);
        users.stream().forEach(x->User.printU());
    }
}

@Data
@AllArgsConstructor
class User{
    private int id;
    private String name;


    public void printId(){
        System.out.println(id);
    }

    public static void printU(){
        System.out.println("ssssss");
    }
}