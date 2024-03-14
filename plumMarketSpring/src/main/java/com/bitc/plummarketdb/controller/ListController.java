package com.bitc.plummarketdb.controller;


import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.DTO.userDTO;
import com.bitc.plummarketdb.service.ListService;
import com.bitc.plummarketdb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ListController {
    @Autowired
    private ListService listService;
    @Autowired
    private UserService userService;

    @PostMapping("/countHit")
    @ResponseBody
    public void updateSellReservation(HttpServletRequest request)throws Exception {

        String idx = request.getParameter("idx");

        listService.updateHit(idx);


    }

    @PostMapping("/userInfo")
    @ResponseBody
    public String userSelect(HttpServletRequest request)throws Exception{
        String nick = request.getParameter("nick");

        JSONObject data = new JSONObject();
        userDTO user = userService.getUserInfo(nick);

        data.put("userIdx", user.getUserIdx());
        data.put("userId", user.getUserId());
        data.put("userPw", user.getUserPw());
        data.put("userNick", user.getUserNick());
        data.put("userEmail", user.getUserEmail());
        data.put("userAddress", user.getUserAdress());
        data.put("userPhone_num", user.getUserPhoneNum());
        data.put("delete", user.getUserDeletedYn());
        data.put("favlist", user.getUserFavlist());
        data.put("userRating", user.getUserRating());
        data.put("userProfile",user.getUserProfile());
        data.put("userComment",user.getUserComment());
        data.put("listCount",user.getListCount());

        return data.toString();
    }

    @PostMapping("/mRating")
    @ResponseBody
    public void minusRating(HttpServletRequest request)throws Exception{
        String nick = request.getParameter("nick");
        userService.MinusRating(nick);

    }

    @PostMapping("/pRating")
    @ResponseBody
    public void PlusRating(HttpServletRequest request)throws Exception{
        String nick = request.getParameter("nick");
        userService.PlusRating(nick);

    }


    @PostMapping("/UpdateBuy")
    @ResponseBody
    public void UpdateBuy(HttpServletRequest request)throws Exception{
       String nick = request.getParameter("nick");
       String idx = request.getParameter("list_idx");
       listService.UpdateBuy(nick,idx);
    }

    @PostMapping("/DetailPageInfo")
    @ResponseBody
    public String DetailPageInfo(HttpServletRequest request)throws Exception{
        String listIdx = request.getParameter("list_idx");

        List<ListDTO> DetailList = listService.DetailPageInfo(listIdx);

        JSONArray jsonArray = new JSONArray();
        for(ListDTO item : DetailList){
            JSONObject data = new JSONObject();
            data.put("list_idx", item.getListIdx());
            data.put("list_title", item.getListTitle());
            data.put("list_create_date", item.getListCreateDate());
            data.put("list_content", item.getListContent());
            data.put("list_user_nick", item.getListUserNick());
            data.put("list_money", item.getListMoney());
            data.put("list_loc", item.getListLoc());
            data.put("list_hit_cnt", item.getListHitCnt());
            data.put("list_image_name", item.getListImageName());
            data.put("user_profile", item.getUserProfile());
            data.put("user_idx",item.getUserIdx());
            data.put("list_sell_state",item.getListSellState());
            jsonArray.put(data);

        }
        if(jsonArray.isNull(0)){
            ListDTO noImageDetail = listService.NoImageDetail(listIdx);
            JSONObject data1 = new JSONObject();
            data1.put("list_idx", noImageDetail.getListIdx());
            data1.put("list_title", noImageDetail.getListTitle());
            data1.put("list_create_date", noImageDetail.getListCreateDate());
            data1.put("list_content", noImageDetail.getListContent());
            data1.put("list_user_nick", noImageDetail.getListUserNick());
            data1.put("list_money", noImageDetail.getListMoney());
            data1.put("list_loc", noImageDetail.getListLoc());
            data1.put("list_hit_cnt", noImageDetail.getListHitCnt());
            data1.put("user_profile", noImageDetail.getUserProfile());
            data1.put("user_idx",noImageDetail.getUserIdx());
            data1.put("list_sell_state",noImageDetail.getListSellState());
            data1.put("list_image_name", "noImage");
            JSONArray jsonArray1 = new JSONArray();

            jsonArray1.put(data1);
            return  jsonArray1.toString();
        }else {


            return jsonArray.toString();
        }

    }

}
