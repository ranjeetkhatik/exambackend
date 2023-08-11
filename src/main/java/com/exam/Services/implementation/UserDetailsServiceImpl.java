package com.exam.Services.implementation;

import com.exam.model.User;
import com.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username+" user came in loadUserByUsername() method...");
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("user not found rk");
            throw new UsernameNotFoundException(" user not found ranjeet!!");
        }
        return user;
    }
}
