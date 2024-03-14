package com.bitc.plummarketdb.mapper;

import com.bitc.plummarketdb.DTO.userDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<userDTO> selectChatList(int idx)throws Exception;

    void deleteChatList(userDTO user)throws Exception;

    void EnterChat(@Param("sendIdx") String sendIdx, @Param("receiveIdx") String receiveIdx, @Param("listUid")String listUid)throws Exception;

    int CheckChat(@Param("sendIdx") String sendIdx, @Param("receiveIdx") String receiveIdx, @Param("listUid")String listUid)throws Exception;

    void EnterChat1(String sendIdx, String receiveIdx, String listUid)throws Exception;
}
