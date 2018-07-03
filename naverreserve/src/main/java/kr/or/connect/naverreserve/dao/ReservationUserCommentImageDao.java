package kr.or.connect.naverreserve.dao;

import static kr.or.connect.naverreserve.sqlquery.ProductSql.SELECT_LIMIT;
import static kr.or.connect.naverreserve.sqlquery.ReservationUserCommentImageSql.SELECT_ALL;
import static kr.or.connect.naverreserve.sqlquery.ReservationUserCommentImageSql.SELECT_BY_RESERVATION_INFO_ID;
import static kr.or.connect.naverreserve.sqlquery.ReservationUserCommentImageSql.SELECT_BY_RSERVATION_USER_COMMENT_ID;
import static kr.or.connect.naverreserve.sqlquery.ReservationUserCommentImageSql.TABLE_NAME;

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

import kr.or.connect.naverreserve.dto.ReservationUserCommentImage;

@Repository
public class ReservationUserCommentImageDao {

	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<ReservationUserCommentImage> rowMapper = new BeanPropertyRowMapper<>(ReservationUserCommentImage.class);
	
	public ReservationUserCommentImageDao(DataSource dataSource) {
		
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName(TABLE_NAME)
				.usingGeneratedKeyColumns("id");
		
	}
	
	public List<ReservationUserCommentImage> selectAll() {
		return jdbc.query(SELECT_ALL, rowMapper);
	}
	
	public Long insert(ReservationUserCommentImage data) {
	SqlParameterSource 	params = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public List<ReservationUserCommentImage> selectByReservationInfoId(int reservationInfoId) {
		
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId",reservationInfoId);
		return jdbc.query(SELECT_BY_RESERVATION_INFO_ID, params, rowMapper);
	}
	
	public ReservationUserCommentImage selectByReservationUserCommentId(int id) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("reservationUserCommentId",id);
		return jdbc.queryForObject(SELECT_BY_RSERVATION_USER_COMMENT_ID, params, rowMapper);
	}
	

	
}
