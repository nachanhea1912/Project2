package com.bitc.plummarketdb.controller;


import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.DTO.ListImageDTO;
import com.bitc.plummarketdb.service.WriteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class WriteController {
    @Autowired
    private WriteService writeService;

    @RequestMapping(value = "/insertList", method = {RequestMethod.POST})
    @ResponseBody
    public int androidInsertList(HttpServletRequest request, Model model) throws Exception {
//         전송된 데이터를 파라미터로 받아오는 부분
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String nick = request.getParameter("nick");
        String loc = request.getParameter("loc");
        String money = request.getParameter("money");
        int money1 = Integer.parseInt(money);

        // ListDTO 객체를 생성하고 값을 설정합니다.
        ListDTO list = new ListDTO();
        list.setListTitle(title);
        list.setListContent(content);
        list.setListUserNick(nick);
        list.setListMoney(money1);

        list.setListLoc(loc);
//
        writeService.InsertList(list);
//        // WriteService를 사용하여 리스트에 항목을 추가합니다.
        return list.getListIdx();
    }

    @RequestMapping(value = "/selectList", method = {RequestMethod.GET})
    @ResponseBody
    public String androidSelectList(HttpServletRequest request)throws Exception{

        JSONObject data1 = new JSONObject();

        List<ListDTO> list1 = writeService.SelectList();

        JSONArray jsonArray = new JSONArray();
        for(ListDTO list : list1){
            JSONObject data = new JSONObject();
            data.put("list_idx", list.getListIdx());
            data.put("list_title", list.getListTitle());
            data.put("list_content", list.getListContent());
            data.put("list_user_nick", list.getListUserNick());
            data.put("list_money", list.getListMoney());
            data.put("list_image_name", list.getListImageName());

            jsonArray.put(data);
        }

        return jsonArray.toString();
    }

    @RequestMapping(value = "/selectPanmaeList", method = {RequestMethod.POST})
    @ResponseBody
    public String androidPanmaeSelectList(HttpServletRequest request)throws Exception{
        String userNick = request.getParameter("nick");

        List<ListDTO> list1 = writeService.PanmaeSelectList(userNick);

        JSONArray jsonArray = new JSONArray();
        for(ListDTO list : list1){
            JSONObject data = new JSONObject();
            data.put("list_idx", list.getListIdx());
            data.put("list_title", list.getListTitle());
            data.put("list_content", list.getListContent());
            data.put("list_user_nick", list.getListUserNick());
            data.put("list_money", list.getListMoney());

            jsonArray.put(data);
        }

        return jsonArray.toString();
    }

    @RequestMapping(value = "/Modify", method = {RequestMethod.POST})
    @ResponseBody
// 수정되는거면 반환값이 없어서 void 를 적고 반환값이 있으면 return을 씀.
// 받아온 값인 title, content,money,idx 모두를 listDTO(com.bitc.plummarketdb.dto.listdto)타입의 'list'에 넣는다.
    public void Modify(HttpServletRequest request, ListDTO list)throws Exception{

        list.setListIdx(Integer.parseInt(request.getParameter("idx")));
        list.setListTitle(request.getParameter("title"));
        list.setListContent(request.getParameter("content"));
        list.setListMoney(Integer.parseInt(request.getParameter("money")));



        writeService.Modify(list);

    }

    @RequestMapping(value = "/insertImage", method = {RequestMethod.POST})
    @ResponseBody
    public void SelectListImage(@RequestBody List<ListImageDTO> list)throws Exception{
        System.out.println(list);

        if (list.size() >= 3) {
            ListImageDTO item = list.get(2); // 3번째 요소를 가져옴
            writeService.InsertImageList(item);
        }else if(list.size() >= 2){
            ListImageDTO item = list.get(1); // 3번째 요소를 가져옴
            writeService.InsertImageList(item);
        }else{
            ListImageDTO item = list.get(0); // 3번째 요소를 가져옴
            writeService.InsertImageList(item);

        }
    }
    @RequestMapping(value = "/SearchListTitle", method = {RequestMethod.POST})
    @ResponseBody
    public String SearchListTitle(HttpServletRequest request)throws Exception{
        String Search = request.getParameter("Search");

        System.out.println(Search);

        JSONObject data1 = new JSONObject();

        List<ListDTO> list1 = writeService.SearchListTitle(Search);

        JSONArray jsonArray = new JSONArray();
        for(ListDTO list : list1){
            JSONObject data = new JSONObject();
            data.put("list_idx", list.getListIdx());
            data.put("list_title", list.getListTitle());
            data.put("list_content", list.getListContent());
            data.put("list_user_nick", list.getListUserNick());
            data.put("list_money", list.getListMoney());

            jsonArray.put(data);
        }

        return jsonArray.toString();
    }


    @RequestMapping(value = "/selectPanmaeCompleteList", method = {RequestMethod.POST})
    @ResponseBody
    public String selectPanmaeCompleteList(HttpServletRequest request)throws Exception{
        String userNick = request.getParameter("nick");

        JSONObject data1 = new JSONObject();

        List<ListDTO> list1 = writeService.PanmaeSelectCompleteList(userNick);

        JSONArray jsonArray = new JSONArray();
        for(ListDTO list : list1){
            JSONObject data = new JSONObject();
            data.put("list_idx", list.getListIdx());
            data.put("list_title", list.getListTitle());
            data.put("list_content", list.getListContent());
            data.put("list_user_nick", list.getListUserNick());
            data.put("list_money", list.getListMoney());

            jsonArray.put(data);
        }

        return jsonArray.toString();
    }

    @RequestMapping(value = "/selectPanmaeHideList", method = {RequestMethod.POST})
    @ResponseBody
    public String selectPanmaeHideList(HttpServletRequest request)throws Exception{
        String userNick = request.getParameter("nick");

        JSONObject data1 = new JSONObject();

        List<ListDTO> list1 = writeService.PanmaeSelectHideList(userNick);

        JSONArray jsonArray = new JSONArray();
        for(ListDTO list : list1){
            JSONObject data = new JSONObject();
            data.put("list_idx", list.getListIdx());
            data.put("list_title", list.getListTitle());
            data.put("list_content", list.getListContent());
            data.put("list_user_nick", list.getListUserNick());
            data.put("list_money", list.getListMoney());

            jsonArray.put(data);
        }

        return jsonArray.toString();
    }

    @RequestMapping(value = "/updateSellReservation", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellReservation(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellReservation(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateSellOngoing", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellOngoing(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellOngoing(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateSellComplete", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellComplete(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellComplete(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateSellHide", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellHide(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellHide(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateSellHideRemove", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellHideRemove(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellHideRemove(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateSellupdate", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellupdate(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellupdate(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateSellRervation", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellRervation(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellRervation(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateSellRervationDelete", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellRervationDelete(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellRervationDelete(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "selectSellState", method = {RequestMethod.POST})
    @ResponseBody
    public String selectSellState(HttpServletRequest request,ListDTO list) throws Exception {

            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);

            ListDTO listItem = writeService.selectSellState(list);

            JSONObject data = new JSONObject();
            data.put("list_sell_state", listItem.getListSellState());

            return data.toString();
    }

    @RequestMapping(value = "/updateSellDelete", method = {RequestMethod.POST})
    @ResponseBody
    public void updateSellDelete(HttpServletRequest request, ListDTO list)throws Exception {

        try {
            String idxString = request.getParameter("idx");
            int listIdx = Integer.parseInt(idxString);
            list.setListIdx(listIdx);
            writeService.updateSellDelete(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getListGumaeList", method = {RequestMethod.GET})
    @ResponseBody
    public String getListGumaeData(HttpServletRequest request)throws Exception{
        String userNick = request.getParameter("nick");

        List<ListDTO> list1 = writeService.getListGumaeList(userNick);

        JSONArray jsonArray = new JSONArray();
        for(ListDTO list : list1){
            JSONObject data = new JSONObject();
            data.put("list_idx", list.getListIdx());
            data.put("list_title", list.getListTitle());
            data.put("list_content", list.getListContent());
            data.put("list_user_nick", list.getListUserNick());
            data.put("list_money", list.getListMoney());
            data.put("list_sell_state", list.getListSellState());
            data.put("list_image_name", list.getListImageName());

            jsonArray.put(data);
        }

        return jsonArray.toString();
    }
}

