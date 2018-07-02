package kr.or.connect.naverreserve.dao;

import static kr.or.connect.naverreserve.sqlquery.ReservationUserCommentSql.SELECT_ALL;
import static kr.or.connect.naverreserve.sqlquery.ReservationUserCommentSql.SELECT_BY_PRODUCTID;

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

import kr.or.connect.naverreserve.dto.ReservationUserComment;

@Repository
public class ReservationUserCommentDao {

	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<ReservationUserComment> rowMapper = new BeanPropertyRowMapper<>(ReservationUserComment.class);
	
	public ReservationUserCommentDao(DataSource dataSource) {
		
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_user_comment")
				.usingGeneratedKeyColumns("id");
		
	}
	
	public List<ReservationUserComment> selectAll() {
		return jdbc.query(SELECT_ALL, rowMapper);
	}
	
	public Long insert(ReservationUserComment data) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(params).longValue();

	}
	
	public List<ReservationUserComment> selectByProductId(int id) {
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("productId", id);
		return jdbc.query(SELECT_BY_PRODUCTID, paramMap, rowMapper);
	}
	
	


	
}
