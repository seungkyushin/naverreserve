package kr.or.connect.naverreserve.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
				.withTableName("reservation_user_comment_image")
				.usingGeneratedKeyColumns("id");
		
	}
	
	public List<ReservationUserCommentImage> selectAll() {
		return jdbc.query("select * from reservation_user_comment_image", rowMapper);
	}
	
	
}
