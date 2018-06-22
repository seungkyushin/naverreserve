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

import kr.or.connect.naverreserve.dto.Category;

@Repository
public class CategoryDao {

	
	 NamedParameterJdbcTemplate jdbc;
	 SimpleJdbcInsert insertAction;
	 RowMapper<Category> rowMapper = new BeanPropertyRowMapper<>(Category.class);
	 
	
	 public CategoryDao(DataSource dataSource) {
		 jdbc = new NamedParameterJdbcTemplate(dataSource);
		 insertAction = new SimpleJdbcInsert(dataSource)
				 .withTableName("category")
				 .usingGeneratedKeyColumns("id");
		 
	 }
	 
	 public List<Category> selectAll() {
		  return jdbc.query("SELECT id,name FROM category",rowMapper);
	 }
	 
	 public List<Category> selectById(int id) {
		 Map<String,?> paramMap = Collections.singletonMap("id", id);
		  return jdbc.query("SELECT id,name FROM category WhERE id=:id",paramMap,rowMapper);
	 }
	
	
	 public int selectCount() {
		 return jdbc.queryForObject("SELECT count(*) FROM category", Collections.emptyMap(), Integer.class);
	 }
	
	
	
}
