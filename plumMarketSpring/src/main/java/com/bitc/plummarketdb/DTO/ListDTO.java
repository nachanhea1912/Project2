package com.bitc.plummarketdb.DTO;

import lombok.Data;

@Data
public class ListDTO {
    private int listIdx;
    private String listTitle;
    private String listContent;
    private String listUserNick;
    private String listCreateDate;
    private String listCompletedYn;
    private String listHitCnt;
    private String listLoc;
    private int listMoney;
    private String listUpdateDate;
    private String listImageName;
    private String listSellState;

    private String userProfile;
    private String userIdx;
}
