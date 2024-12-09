package com.demo.crm.serviceimpl;

import com.demo.crm.entity.User;
import com.demo.crm.repository.UserRepository;
import com.demo.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public User getUserInfo(long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
        }
        return new User();
    }

    @Override
    public String saveUserInfo(User user) {
        try {
            User newUser = User.builder()
                    .userName(user.getUserName())
                    .userMail(user.getUserMail())
                    .Password(user.getPassword())
                    .Status("USER")
                    .build();
            userRepository.save(newUser);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "User Saved Successfully!";
    }

    @Override
    public List<User> getAllUser() {
        return List.of();
    }
}
