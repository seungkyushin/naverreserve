package kr.or.connect.naverreserve.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
				.withTableName("product_price")
				.usingGeneratedKeyColumns("id");
		
	}
	
	public List<ReservationUserComment> selectAll() {
		return jdbc.query("select * from display_info", rowMapper);
	}
	
	public ReservationUserComment selectByProductId(int productId) {
		Map<String,?> paramMap = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject("select * from display_info WHERE product_id=:productId", paramMap, rowMapper);
	}
	

	
}
