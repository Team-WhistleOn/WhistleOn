package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.domain.user.UserRepository;
import com.halaguys.whistleon.dto.request.UserLoginRequestDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User login(UserLoginRequestDto userDto) throws NoSuchElementException,UnauthorizedException{
        User user = getUserByEmail(userDto.getEmail());
        matchPassword(user.getPassword(),userDto.getPassword());
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws NoSuchElementException{
        return userRepository.findAllByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void matchPassword(String userPassword, String inputPassword) throws UnauthorizedException{
        if(!userPassword.trim().equals(inputPassword.trim())){
            throw new UnauthorizedException();
        }
    }
}
