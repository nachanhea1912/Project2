package com.bitc.plummarketdb.service;


import com.bitc.plummarketdb.DTO.userDTO;

public interface UserService {
    int isUserInfo(String userId, String userPw)throws Exception;

    userDTO getUserInfo(String nick)throws Exception;

    void insertUser(userDTO user)throws Exception;

    void UploadProFile(userDTO user)throws Exception;

    void MinusRating(String nick)throws Exception;

    void PlusRating(String nick)throws Exception;

    userDTO UserLogin(String androidID)throws Exception;
}
