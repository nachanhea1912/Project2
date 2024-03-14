package com.bitc.plummarketdb.DTO;

import lombok.Data;

@Data
public class ChatDTO {
    private int chatIdx;
    private String chatUserNick;
    private String chatContent;
    private String chatCreateDate;
    private int chatListIdx;

}
