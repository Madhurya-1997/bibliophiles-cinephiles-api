package com.heritage.bibandcineapi.services;


import com.heritage.bibandcineapi.exception.ResourceNotFoundException;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Page<User> getAllUsers(Pageable pageable)  {
        return userRepository.findAll(pageable);
    }

    @Override
    public User register(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setEmail(user.getEmail());
        newUser.setRole();
        return userRepository.save(newUser);
    }

    @Override
    public User update(Long userId, User userRequest) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user.setEmail(userRequest.getEmail());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setRole();
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found !!"));
    }
}
