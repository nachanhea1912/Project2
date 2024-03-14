package com.bitc.plummarketdb.service;

import com.bitc.plummarketdb.DTO.userDTO;

import java.util.List;

public interface ChatService {
    List<userDTO> selectChatList(int idx)throws Exception;

    void deleteChatList(userDTO user)throws Exception;

    void EnterChat(String sendIdx, String receiveIdx, String listUid)throws Exception;

    int CheckChat(String sendIdx, String receiveIdx, String listUid)throws Exception;


    void EnterChat1(String sendIdx, String receiveIdx, String listUid)throws Exception;
}
