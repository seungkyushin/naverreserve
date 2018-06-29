package kr.or.connect.naverreserve.dao;

import java.util.Collections;
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
import kr.or.connect.naverreserve.dto.ReservationInfoPrice;

@Repository
public class ReservationInfoPriceDao {
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<ReservationInfoPrice> rowMapper = new BeanPropertyRowMapper<>(ReservationInfoPrice.class); 

	public ReservationInfoPriceDao(DataSource dataSoruce)
	{
		jdbc = new NamedParameterJdbcTemplate(dataSoruce);
		insertAction = new SimpleJdbcInsert(dataSoruce)
				.withTableName("reservation_info_price")
				.usingGeneratedKeyColumns("id");
	}
	
	public Long insert(ReservationInfoPrice data)
	{
		SqlParameterSource params = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public List<ReservationInfoPrice> selectAll() {
		return jdbc.query("select * from reservation_info_price", rowMapper);
	}
	
	public List<ReservationInfoPrice> selectByReservationInfoId(int id) {
		Map<String,?> paramMap = Collections.singletonMap("reservationId", id);
		return jdbc.query("select * from reservation_info_price WHERE reservation_info_id=:reservationId", paramMap, rowMapper);
	}
	

	
}
