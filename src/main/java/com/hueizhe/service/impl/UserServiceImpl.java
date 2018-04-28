package com.hueizhe.service.impl;

import com.hueizhe.domain.User;
import com.hueizhe.repository.UserRepository;
import com.hueizhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void signIn(User user) {
        userRepository.insert(user);
    }
}
