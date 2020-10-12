package datastructure.unionfind;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集题目一
 * 实例有三个属性，其中任何一个属性相同就认为两个实例相等
 * 给你一个实例集合，求这个集合中的不同的实例个数
 */
public class UnionFind_01 {
    //TODO code实现

    public static int distinctUser(User[] users){
        if(users == null || users.length == 0){
            return 0 ;
        }

        UnionSet<User> unionset = new UnionSet<>(users);
        Map<String,User> mapA = new HashMap<>();
        Map<String,User> mapB = new HashMap<>();
        Map<String,User> mapC = new HashMap<>();
        for (User user : users) {
            User sameUser = mapA.containsKey(user.a) ? mapA.get(user.a) : (mapB.containsKey(user.b) ? mapB.get(user.b) :
                    (mapC.containsKey(user.c) ? mapC.get(user.c):null));

            if(sameUser != null){
                unionset.union(sameUser,user);
            }else{
                mapA.put(user.a,user);
                mapB.put(user.b,user);
                mapC.put(user.c,user);
            }
        }

        return unionset.getSizeMap().size();
    }

    static class User{
        String a ;
        String b ;
        String c ;
    }
}
