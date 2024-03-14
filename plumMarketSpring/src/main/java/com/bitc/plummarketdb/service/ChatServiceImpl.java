package com.bitc.plummarketdb.service;


import com.bitc.plummarketdb.DTO.userDTO;
import com.bitc.plummarketdb.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatMapper chatMapper;

    @Override
    public List<userDTO> selectChatList(int idx) throws Exception {
        return chatMapper.selectChatList(idx);
    }

    @Override
    public void deleteChatList(userDTO user) throws Exception {
        chatMapper.deleteChatList(user);
    }

    @Override
    public void EnterChat(String sendIdx, String receiveIdx, String listUid) throws Exception {
        chatMapper.EnterChat(sendIdx,receiveIdx,listUid);
    }

    @Override
    public int CheckChat(String sendIdx, String receiveIdx, String listUid) throws Exception {
        return chatMapper.CheckChat(sendIdx,receiveIdx,listUid);
    }

    @Override
    public void EnterChat1(String sendIdx, String receiveIdx, String listUid) throws Exception {
        chatMapper.EnterChat1(sendIdx,receiveIdx,listUid);
    }


}
