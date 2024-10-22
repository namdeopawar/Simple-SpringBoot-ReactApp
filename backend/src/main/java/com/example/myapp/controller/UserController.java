   package com.example.myapp.controller;

   import com.example.myapp.model.User;
   import com.example.myapp.service.UserService;
   import org.springframework.http.ResponseEntity;
   import org.springframework.web.bind.annotation.*;

   import java.util.List;

   @RestController
   @RequestMapping("/api")  // Ensure this is set correctly
   public class UserController {

       private final UserService userService;

       public UserController(UserService userService) {
           this.userService = userService;
       }

       @GetMapping("/users")  // This should match the endpoint you are trying to access
       public ResponseEntity<List<User>> getAllUsers() {
           List<User> users = userService.findAllUsers();  // Ensure this method exists in your service
           return ResponseEntity.ok(users);
       }

       @PostMapping("/users")
       public ResponseEntity<User> createUser(@RequestBody User user) {
           User createdUser = userService.saveUser(user);
           return ResponseEntity.status(201).body(createdUser);
       }
   }
