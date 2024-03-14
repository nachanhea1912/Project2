package com.bitc.plummarketdb.service;


import com.bitc.plummarketdb.DTO.userDTO;
import com.bitc.plummarketdb.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int isUserInfo(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception {
        return userMapper.isUserInfo(userId,userPw);
    }

    @Override
    public userDTO getUserInfo(String nick) throws Exception {
        return userMapper.getUserInfo(nick);
    }

    @Override
    public void insertUser(userDTO user) throws Exception {
        userMapper.insertUser(user);
    }

    @Override
    public void UploadProFile(userDTO user) throws Exception {
        userMapper.UploadProFile(user);
    }

    @Override
    public void MinusRating(String nick) throws Exception {
        userMapper.MinusRating(nick);
    }

    @Override
    public void PlusRating(String nick) throws Exception {
        userMapper.PlusRating(nick);
    }

    @Override
    public userDTO UserLogin(String androidID) throws Exception {
        return userMapper.UserLogin(androidID);
    }
}