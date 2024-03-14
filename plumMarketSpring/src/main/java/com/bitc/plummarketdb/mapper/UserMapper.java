package com.bitc.plummarketdb.mapper;


import com.bitc.plummarketdb.DTO.userDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int isUserInfo(String userId, String userPw) throws Exception;

    userDTO getUserInfo(@Param("nick")String nick)throws Exception;

    void insertUser(userDTO user)throws Exception;

    void UploadProFile(userDTO user)throws Exception;

    void MinusRating(String nick)throws Exception;

    void PlusRating(String nick)throws Exception;

    userDTO UserLogin(@Param("id") String androidID)throws Exception;
}
