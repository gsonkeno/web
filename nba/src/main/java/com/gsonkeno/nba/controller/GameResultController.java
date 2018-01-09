package com.gsonkeno.nba.controller;

import com.alibaba.fastjson.JSON;
import com.gsonkeno.nba.dao.GameResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("gameResult")
public class GameResultController {
    @Autowired  //按类型装配
    private GameResultDao dao;

    private static final String placeIgnore = "0"; //不分主客场
    private static final String placeHost = "1";  //主场
    private static final String placeGuest = "2"; //客场

    @RequestMapping(value = "getResult", produces = "application/json; charset=utf-8")
    @ResponseBody
    //获取比赛结果
    public String getGameResult(HttpServletRequest request) {
        String game_date_begin = request.getParameter("GAME_DATE_BEGIN");
        String game_date_end = request.getParameter("GAME_DATE_END");
        String team_name = request.getParameter("TEAM_NAME");
        String host_guest_flag = request.getParameter("HOST_GUEST_FLAG");
        int page_size = Integer.valueOf(request.getParameter("PAGE_SIZE"));
        int current_page = Integer.valueOf(request.getParameter("CURRENT_PAGE"));

        StringBuilder querySql = new StringBuilder("select * from  GAME where 1=1 ");
        StringBuilder countsql = new StringBuilder("select count(1) from  GAME where 1=1 ");
        List<Object> params = new ArrayList<Object>();

        StringBuilder clauseSql = new StringBuilder();
        if(!StringUtils.isEmpty(game_date_begin)){
            clauseSql.append(" and GAME_DATE >= ?");
            params.add(game_date_begin);
        }

        if(!StringUtils.isEmpty(game_date_end)){
            clauseSql.append(" and GAME_DATE <= ?");
            params.add(game_date_end);
        }

        if (!StringUtils.isEmpty(team_name)) {
            if (host_guest_flag.equals(placeIgnore)) {
                clauseSql.append(" and (HOST = ? or GUEST = ?)");
                params.add(team_name);
                params.add(team_name);
            } else if (host_guest_flag.equals(placeHost)) {
                clauseSql.append(" and HOST = ?");
                params.add(team_name);
            } else if (host_guest_flag.equals(placeGuest)) {
                clauseSql.append(" and GUEST = ?");
                params.add(placeGuest);
            }
        }

        querySql.append(clauseSql).append(" order by game_date desc, id desc limit " + (current_page-1)*page_size + "," + page_size);
        countsql.append(clauseSql);
        List<Map<String, Object>> result = dao.getGameResult(querySql.toString(),params);
        int totalSize = dao.getTotalSize(countsql.toString(), params);

        Map<String,Object> resp = new HashMap<>();
        resp.put("totalSize", totalSize);
        resp.put("totalPage", (int)Math.ceil((double)totalSize/page_size));
        resp.put("list",result);

        request.getSession();
        System.out.println("getRequestedSessionId:" + request.getRequestedSessionId());
        System.out.println("GameResultController thread classloader is  " +
                Thread.currentThread().getContextClassLoader() +
                Thread.currentThread().getContextClassLoader().hashCode());


        return JSON.toJSON(resp).toString();
    }


    @RequestMapping(value = "getLatestResult", produces = "application/json; charset=utf-8")
    @ResponseBody
    //获取比赛结果
    public String getLatestResult(HttpServletRequest request) {
        int gameCounts = Integer.valueOf(request.getParameter("GAME_COUNTS_L"));
        String teamName = request.getParameter("TEAM_NAME_L");

        List<Map<String, Object>> latestGameResults = dao.getLatestGameResults(teamName, gameCounts);

        List<Object> dates = new ArrayList<>();
        List<Object> scores = new ArrayList<>();
        for (int i = latestGameResults.size()-1; i >=0; i--) {
            Map<String, Object> gameRow = latestGameResults.get(i);
            dates.add((gameRow.get("game_date")+"").substring(5));
            if (gameRow.get("host").equals(teamName)){
                scores.add(gameRow.get("host_score"));
            }else{
                scores.add(gameRow.get("guest_score"));
            }
        }
        Map<String,Object> resp = new HashMap<>();
        resp.put("list",latestGameResults);
        resp.put("dates",dates);
        resp.put("scores",scores);
        return JSON.toJSON(resp).toString();
    }
}	
