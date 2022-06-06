package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface UserService {

    public Page<User> getAllUsers(Pageable pageable);
    public User register(User user);
    public User update(Long userId, User userRequest);
}
