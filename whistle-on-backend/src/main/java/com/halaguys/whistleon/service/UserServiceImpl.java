package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.domain.user.UserRepository;
import com.halaguys.whistleon.dto.request.UserLoginRequestDto;
import com.halaguys.whistleon.dto.request.UserRegistRequestDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User login(UserLoginRequestDto userDto) throws NoSuchElementException,UnauthorizedException{
        Optional<User> user = getUserByEmail(userDto.getEmail());
        String password = user.map(User::getPassword)
                .orElseThrow(NoSuchElementException::new);
        matchPassword(password,userDto.getPassword());
        return user.get();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
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

    @Override
    public boolean checkEmail(String email) {
        return getUserByEmail(email)
                .isPresent();
    }
}
