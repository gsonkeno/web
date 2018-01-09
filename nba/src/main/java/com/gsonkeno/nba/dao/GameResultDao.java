package com.gsonkeno.nba.dao;

import com.gsonkeno.nba.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 比赛结果管理dao
 * @author gaosong
 *
 */
@Repository
public class GameResultDao {

	@Autowired   //按类型装配
	private JdbcOperations jdbcTemplate;
	
	public void addGameResult(String[] params){
		String sql ="insert into GAME_RESULT values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, params);
		System.out.println(rows);
	}

	public List<Map<String,Object>> testQuery(){
	    return jdbcTemplate.queryForList("SELECT  * from GAME");
    }

	public List<Map<String,Object>> getGameResult(String sql, List<Object> params){
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString(), params.toArray());
		return list;
	}
	public List<Map<String,Object>> getGameResult(String game_date_begin,String game_date_end,
			String team_name, String host_guest){
		StringBuilder sql = new StringBuilder("select * from  GAME where 1=1 ");
		List<String> params = new ArrayList<String>();
		
		if(!StringUtils.isEmpty(game_date_begin)){
			sql.append(" and GAME_DATE >= ?");
			params.add(game_date_begin);
		}
		
		if(!StringUtils.isEmpty(game_date_end)){
			sql.append(" and GAME_DATE <= ?");
			params.add(game_date_end);
		}
		
		if(!StringUtils.isEmpty(team_name) && !StringUtils.isEmpty(host_guest)){
			sql.append(" and ?  like ? ");
			params.add(host_guest);
			params.add( "%" + team_name + "%");
		}else if (!StringUtils.isEmpty(team_name)) {
			sql.append(" and (HOST  = ?  or GUEST= ? )");
			params.add("%火箭%" );
			params.add("%火箭%" );
		}
		
		sql.append(" order by GAME_DATE asc");
		List<Map<String, Object>> list ;
		if(params.isEmpty()){
			list = jdbcTemplate.queryForList(sql.toString());
		}else{
			list = jdbcTemplate.queryForList(sql.toString(),params.toArray());
		}
		return list;
		
	}

    public int getTotalSize(String countsql, List<Object> params) {
	    return jdbcTemplate.queryForObject(countsql, params.toArray(),Integer.class);
    }

    public List<Map<String,Object>> getLatestGameResults(String teamName, int pastGameCounts){
		String sql = "select id, host, guest, host_score, guest_score, game_date, host_quarter1, host_quarter2, host_quarter3, host_quarter4," +
				"guest_quarter1, guest_quarter2, guest_quarter3, guest_quarter4," +
				"(IFNULL(host_quarter5,0) + IFNULL(host_quarter6,0) + IFNULL(host_quarter7,0)  + IFNULL(host_quarter8,0) + IFNULL(host_quarter9,0)) as host_ot," +
                " (IFNULL(guest_quarter5,0) + IFNULL(guest_quarter6,0) + IFNULL(guest_quarter7,0) + IFNULL(guest_quarter8,0) + IFNULL(guest_quarter9,0)) as guest_ot " +
				"from game where game_date <? and (host= ? or guest = ?) ORDER BY game_date DESC LIMIT 0,?";


		return jdbcTemplate.queryForList(sql, DateUtil.getNowDay(),teamName, teamName, pastGameCounts);
	}
}
