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

import kr.or.connect.naverreserve.dto.DisplayInfo;

@Repository
public class DisplayInfoDao {

	
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<DisplayInfo> rowMapper = new BeanPropertyRowMapper<>(DisplayInfo.class);
	private String colums = "id,product_id,opening_hours,place_name,place_lot,place_street,tel,homepage,email,create_date,modify_date";
	public DisplayInfoDao(DataSource dataSource) {
		
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("display_info")
				.usingGeneratedKeyColumns("id");
		
	}
	
	public List<DisplayInfo> selectAll() {
		return jdbc.query("select " + colums + " from display_info", rowMapper);
	}
	
	public DisplayInfo selectByProductId(int productId) {
		 Map<String,?> paramMap = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject("SELECT " + colums + " FROM display_info WHERE product_id=:productId", paramMap, rowMapper);
	}
	
	
}
