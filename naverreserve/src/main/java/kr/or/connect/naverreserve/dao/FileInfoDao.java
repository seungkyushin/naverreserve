package kr.or.connect.naverreserve.dao;

import static kr.or.connect.naverreserve.sqlquery.FileInfoSql.*;
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

import kr.or.connect.naverreserve.dto.FileInfo;

@Repository
public class FileInfoDao {

	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert insertAction;
	RowMapper<FileInfo> rowMapper = new BeanPropertyRowMapper<>(FileInfo.class);
	
	public FileInfoDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName(TABLE_NAME)
				.usingGeneratedKeyColumns("id");
		
	}
	
	public List<FileInfo> selectAll(){
		return jdbc.query(SELECT_ALL,rowMapper);
	}
	

	
	public FileInfo selectByproductImageId(int productImageId){
		Map<String,?> paramMap = Collections.singletonMap("ProductId",productImageId);
		return jdbc.queryForObject(SELECT_BY_PRODUCTID, paramMap, rowMapper);
	}
	
	public int insert(FileInfo data){
		SqlParameterSource param = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(param).intValue();
		}
}
