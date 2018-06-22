package kr.or.connect.naverreserve.dao;


import static kr.or.connect.naverreserve.sqlquery.ProductSql.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.naverreserve.dto.Product;

@Repository
public class ProductDao {

	
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert inserAction;
	RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class); 

	public ProductDao(DataSource dataSource)
	{
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		inserAction = new SimpleJdbcInsert(dataSource)
				.withTableName("product")
				.usingGeneratedKeyColumns("id");
	}
	
	//< 모든 정보 추출 
	public List<Product> selectAll() {
		return jdbc.query(SELECT_ALL, rowMapper);
	}
	
	public List<Product> selectLimit(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("start", start);
		params.put("limit", limit);
	
		return jdbc.query(SELECT_LIMIT, params ,rowMapper);
	}
	public List<Product> selectByCategroyId(int start, int limit, int categroyId) {

		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("categroyId", categroyId);
		
		return jdbc.query(SELECT_LIMIT_BY_CATEGORIES,params ,rowMapper);
	}
	
	public List<Product> selectByCategroyId(int categroyId) {

		Map<String, Integer> params = new HashMap<>();
		params.put("categroyId", categroyId);
		
		return jdbc.query("SELECT id,category_id, description,content,event,create_date,modify_date FROM product WHERE category_id=:categroyId",params ,rowMapper);
	}
	
	

	
	public Product selectById(int id) {
		Map<String,?> paramMap = Collections.singletonMap("id",id);
		return jdbc.queryForObject("SELECT id,category_id, description,content,event,create_date,modify_date FROM product WHERE id=:id", paramMap,rowMapper);
	}
	
	
	public Integer selectCategroyCount(int categoryId) {
		Map<String,?> params = Collections.singletonMap("categoryid", categoryId);
		return jdbc.queryForObject("SELECT count(*) FROM product WHERE  category_id=:categoryid", params,Integer.class );
	}
	
	public Integer selectAllCount() {
		return jdbc.queryForObject("SELECT count(*) FROM product", Collections.emptyMap(),Integer.class );
	}
	
}
