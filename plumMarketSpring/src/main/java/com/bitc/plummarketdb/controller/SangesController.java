package com.bitc.plummarketdb.controller;

import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.service.SangesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class SangesController {
    @Autowired
    private SangesService sangesService;

    @RequestMapping(value = "SangesList", method = {RequestMethod.POST})
    @ResponseBody
    public String androidSelectList(HttpServletRequest request,ListDTO list) throws Exception {

        String idxString = request.getParameter("idx");
        System.out.println("안드로이드에서 받아온 id : " + idxString);

        int listIdx = Integer.parseInt(idxString);
        list.setListIdx(listIdx);

        ListDTO listItem = sangesService.SangesList(list);

        JSONObject data = new JSONObject();
        data.put("list_title", listItem.getListTitle());
        data.put("list_content", listItem.getListContent());
        data.put("list_user_nick", listItem.getListUserNick());
        data.put("list_money", listItem.getListMoney());

        return data.toString();
    }
}