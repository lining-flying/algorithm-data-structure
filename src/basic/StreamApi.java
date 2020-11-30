package basic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamApi {

    public static void main(String[] args){
        List<User> users = new ArrayList<>();
        users.add(new User(18,"aaa"));
        users.add(new User(15,"bbb"));
        users.add(new User(24,"ccc"));
        users.add(new User(26,"ddd"));
        users.add(new User(28,"eee"));
        users.add(new User(33,"fff"));
        users.add(new User(37,"ggg"));

        List<User> maxList = users.stream().collect(Collectors.groupingBy(user-> user.getAge()/10,Collectors.toList()))
                .entrySet().stream().map(entry -> entry.getValue().stream()
                .collect(Collectors.maxBy((u1,u2) -> u1.getAge()  - u2.getAge()))).map(Optional::get).collect(Collectors.toList());
        for(User usr : maxList){
            System.out.println(usr);
        }

        maxList = users.stream().collect(Collectors.groupingBy(user-> user.getAge()/10,
                Collectors.collectingAndThen(Collectors.reducing((c1,c2) -> c1.getAge()>c2.getAge() ? c1 : c2),Optional::get))).entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());

        for(User user : maxList){
            System.out.println(user);
        }
    }


    static class User{
        int age ;
        String name ;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name=" + name +
                    '}';
        }
    }
}
