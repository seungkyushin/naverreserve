package kr.or.connect.naverreserve.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.naverreserve.dto.ProductImage;

@Repository
public class ProductImageDao {
	
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);

	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("product_image")
				.usingGeneratedKeyColumns("id");
	}
	
	public List<ProductImage> selectAll()
	{
		return jdbc.query("SELECT id,product_id,type,file_id FROM product_image", rowMapper);
	}
	
	public List<ProductImage> selectByproductId(int productId)
	{
		Map<String,?> paramMap = Collections.singletonMap("productId", productId);
		return jdbc.query("SELECT id,product_id,type,file_id FROM product_image WHERE product_id=:productId", paramMap, rowMapper);
	}
	
	
	public int insert(ProductImage pi)
	{
		BeanPropertySqlParameterSource sqlSource = new BeanPropertySqlParameterSource(pi); 
		return insertAction.executeAndReturnKey(sqlSource).intValue();
	}
	
	
}
