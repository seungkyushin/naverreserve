package kr.or.connect.naverreserve.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.naverreserve.dto.FileInfo;

@Repository
public class FileInfoDao {

	private String colums = "id,file_name,save_file_name,content_type,delete_flag,create_date,modify_date";
	NamedParameterJdbcTemplate jdbc;
	RowMapper<FileInfo> rowMapper = new BeanPropertyRowMapper<>(FileInfo.class);
	public FileInfoDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<FileInfo> selectAll(){
		return jdbc.query("SELECT "+ colums +" FROM file_info",rowMapper);
	}
	
	public FileInfo selectByproductImageId(int productImageId){
		Map<String,?> paramMap = Collections.singletonMap("id",productImageId);
		return jdbc.queryForObject("SELECT "+ colums +" FROM file_info WHERE id=:id", paramMap, rowMapper);
	}
	
}
