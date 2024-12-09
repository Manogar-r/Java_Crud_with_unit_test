package com.demo.crm.service;

import com.demo.crm.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    public User getUserInfo(long id);

    public String saveUserInfo(User user);

    public List<User> getAllUser();
}
