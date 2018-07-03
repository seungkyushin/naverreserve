package kr.or.connect.naverreserve.sqlquery;

public class FileInfoSql {

	public static final String TABLE_NAME = "file_info";
	public static final String CATEGORIES = " id,file_name,save_file_name,content_type,delete_flag,create_date,modify_date ";
	public static final String SELECT_ALL = "SELECT" + CATEGORIES + "FROM "+ TABLE_NAME;
	public static final String SELECT_BY_PRODUCTID = "SELECT" + CATEGORIES + "FROM "+ TABLE_NAME + " WHERE id=:ProductId";
	
	

	
}
