package com.bitc.plummarketdb.controller;

import com.bitc.plummarketdb.DTO.GansimDTO;
import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.service.GansimService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GansimController {

    @Autowired
    private GansimService gansimService;

    @GetMapping("/gansim")
    @ResponseBody
    public String GansimList(HttpServletRequest request) throws Exception {
        String id = request.getParameter("userId");

        List<GansimDTO> list1 = gansimService.selectGansimList(id);

        JSONArray jsonArray = new JSONArray();
        for(GansimDTO list : list1){
            JSONObject data = new JSONObject();
            data.put("fav_idx", list.getFavListIdx());
            data.put("fav_title", list.getFavTitle());
            data.put("fav_money", list.getFavMoney());
            data.put("fav_image", list.getFavImage());
            data.put("fav_sell_state", list.getFavSellState());

            jsonArray.put(data);
        }

        return jsonArray.toString();
    }

    @PostMapping("/gansimGet")
    @ResponseBody
    public int GansimGet(HttpServletRequest request)throws Exception{
        String id = request.getParameter("list_user_id");
        String list_idx = request.getParameter("list_idx");


        int result = gansimService.gansimChk(id, list_idx);

        if(result == 0) {
            gansimService.insertGansim(id, list_idx);
        }else{
            System.out.println("이미 관심목록입니다.");
        }

        return result;
    }
}
