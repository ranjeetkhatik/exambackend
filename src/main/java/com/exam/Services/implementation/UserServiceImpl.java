package com.exam.Services.implementation;

import com.exam.Services.UserService;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        System.out.println("u are in create user method of UserServiceImpl...");

       User local= this.userRepository.findByUsername(user.getUsername());
       if (local!=null){
           System.out.println("User is Already Exists");
           throw new Exception("User Exists");
       }else {
            //create user
           for (UserRole ur:userRoles){
               roleRepository.save(ur.getRole());
           }
           user.getUserRoles().addAll(userRoles);
          local= this.userRepository.save(user);
       }
        return local;
    }
            //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
    this.userRepository.deleteById(userId);

    }
}
