package com.bitc.plummarketdb.controller;

import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.service.WirteSoujoungService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class WirteSoujoungController {
    @Autowired
    private WirteSoujoungService wirteSoujoungService;


    //    글 등록  코드
    // 안드로이드 앱에서 전송한 데이터를 받아서 리스트에 새로운 항목을 추가하는 메서드입니다.
    @RequestMapping(value = "/writeList", method = {RequestMethod.POST})
    @ResponseBody
    public void androidInsertList(HttpServletRequest request, Model model) throws Exception {
//        안드로이드 에서 전송된 데이터를 파라미터로 받아오는 부분
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String nick = request.getParameter("nick");
        String loc = request.getParameter("loc");

        String idx = request.getParameter("idx");
        int idx1 = (idx != null && !idx.isEmpty()) ? Integer.parseInt(idx) : 0;


        String money = request.getParameter("money");
        int money1 = Integer.parseInt(money);

        // 안드로이드 에서 받아온 데이터를 기반으로 ListDTO 객체를 생성하고 값을 설정합니다.
        ListDTO list = new ListDTO();
        list.setListIdx(idx1);
        list.setListTitle(title);
        list.setListContent(content);
        list.setListMoney(money1);
        list.setListUserNick(nick);
        list.setListLoc(loc);
        System.out.println(list);
//
//       wirteSoujoungService를 통해 WirteSoujoungService의 InsertList 메서드를 호출하여 데이터베이스에 새로운 목록을 추가합니다.
        wirteSoujoungService.UpdateList(list);
    }

    @RequestMapping(value = "/detailList", method = {RequestMethod.GET})
    @ResponseBody
    public String androidListSelect(HttpServletRequest request) throws Exception {
        String idx = "40";
        ListDTO list = wirteSoujoungService.selectList(idx);




        return list.toString();
    }





}



