package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.domain.user.UserRepository;
import com.halaguys.whistleon.dto.request.UserLoginRequestDto;
import com.halaguys.whistleon.dto.request.UserRegistRequestDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Transactional
    @Override
    public User getUserByEmail(String email) throws NoSuchElementException{
        return userRepository.findUserByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void matchPassword(String userPassword, String inputPassword) throws UnauthorizedException{
        if(!userPassword.trim().equals(inputPassword.trim())){
            throw new UnauthorizedException();
        }
    }

    @Override
    public void regist(UserRegistRequestDto userDto) {
        User user = User.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .location(userDto.getLocation())
                .password(userDto.getPassword())
                .build();
        userRepository.save(user);
    }
}
