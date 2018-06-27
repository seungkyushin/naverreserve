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

import kr.or.connect.naverreserve.dto.ProductPrice;

@Repository
public class ProductPriceDao {
	

	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<ProductPrice> rowMapper = new BeanPropertyRowMapper<>(ProductPrice.class);
	
	public ProductPriceDao(DataSource dataSource) {
		
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("product_price")
				.usingGeneratedKeyColumns("id");
		
	}
	
	public List<ProductPrice> selectAll() {
		return jdbc.query("select * from product_price", rowMapper);
	}
	
	public List<ProductPrice> selectByProductId(int productId) {
		Map<String,?> params = Collections.singletonMap("productId", productId);
		return jdbc.query("select * from product_price where product_id=:productId", params,rowMapper);
	}
	
	public ProductPrice selectById(int id) {
		Map<String,?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject("select * from product_price where id=:id", params,rowMapper);
	}
	

}
