   package com.example.myapp.service;

   import com.example.myapp.model.User;
   import org.springframework.stereotype.Service;

   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.List;
   import java.util.Map;

   @Service
   public class UserService {
       private final Map<Long, User> userDatabase = new HashMap<>();
       private long currentId = 1;

       public List<User> findAllUsers() {
           return new ArrayList<>(userDatabase.values());
       }

       public User findUserById(Long id) {
           return userDatabase.get(id);
       }

       public User saveUser(User user) {
           user.setId(currentId++);
           userDatabase.put(user.getId(), user);
           return user;
       }
   }
