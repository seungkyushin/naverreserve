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
import kr.or.connect.naverreserve.dto.DisplayInfoImage;

@Repository
public class DisplayInfoImageDao {

	
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<DisplayInfoImage> rowMapper = new BeanPropertyRowMapper<>(DisplayInfoImage.class);
	
	public DisplayInfoImageDao(DataSource dataSource) {
		
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("display_info_image")
				.usingGeneratedKeyColumns("id");
		
	}
	
	public List<DisplayInfoImage> selectAll() {
		return jdbc.query("select * from display_info_image", rowMapper);
	}
	
	public DisplayInfoImage selectByDisplayInfoId(int displayInfoId) {
		Map<String,?> paramMap = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject("select * from display_info_image WHERE display_info_id=:displayInfoId", paramMap, rowMapper);
	}
	
	
	
	
}
