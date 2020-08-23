package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.domain.user.UserRepository;
import com.halaguys.whistleon.dto.request.UserLoginRequestDto;
import com.halaguys.whistleon.dto.request.UserRegistRequestDto;
import com.halaguys.whistleon.dto.response.UserInfoResponseDto;
import com.halaguys.whistleon.dto.response.UserStatResponseDto;
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
    public Optional<User> getUserById(int userId) {
        return userRepository.findUserByUserId(userId);
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
                .age(userDto.getAge())
                .height(userDto.getHeight())
                .position(userDto.getPosition())
                .build();
        userRepository.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return getUserByEmail(email)
                .isPresent();
    }

    @Override
    public UserInfoResponseDto getUserInfo(String email) {
        User user = Optional.of(getUserByEmail(email)
                .orElseThrow(NoSuchElementException::new))
                .get();
        return UserInfoResponseDto
                .builder()
                .userName(user.getUserName())
                .age(user.getAge())
                .email(user.getEmail())
                .location(user.getLocation())
                .height(user.getHeight())
                .teamName(user.getTeam() == null ? "false" : user.getTeam().getTeamName())
                .position(user.getPosition())
                .build();
    }

    @Override
    public UserStatResponseDto getUserStat(String email) {
        User user = Optional.of(getUserByEmail(email)
                .orElseThrow(NoSuchElementException::new))
                .get();
        return UserStatResponseDto
                .builder()
                .age(user.getAge())
                .height(user.getHeight())
                .win(user.getWin())
                .draw(user.getDraw())
                .lose(user.getLose())
                .manner(user.getManner())
                .mvpCount(user.getMvpCount())
                .position(user.getPosition())
                .build();
    }

}
