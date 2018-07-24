package kr.or.connect.naverreserve.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.naverreserve.dto.ReservationInfo;

@Repository
public class ReservationInfoDao {
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<ReservationInfo> rowMapper = new BeanPropertyRowMapper<>(ReservationInfo.class); 

	public ReservationInfoDao(DataSource dataSoruce)
	{
		jdbc = new NamedParameterJdbcTemplate(dataSoruce);
		insertAction = new SimpleJdbcInsert(dataSoruce)
				.withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");
	}
	
	public int insert(ReservationInfo data)
	{
		SqlParameterSource params = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public List<ReservationInfo> selectAll() {
		return jdbc.query("select * from reservation_info", rowMapper);
	}
	
	public ReservationInfo selectByProductId(int productId) {
		Map<String,?> paramMap = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject("select * from reservation_info WHERE product_id=:productId", paramMap, rowMapper);
	}
	
	public List<ReservationInfo> selectByEmail(String email) {
		Map<String,?> paramMap = Collections.singletonMap("reservation_email", email);
		return jdbc.query("select * from reservation_info WHERE reservation_email=:reservation_email", paramMap, rowMapper);
	}
	
	public int updateStatusById(int id,String status) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		paramMap.put("status", status);
		
		return jdbc.update("UPDATE reservation_info SET status=:status WHERE id=:id", paramMap);
	}
	
}
