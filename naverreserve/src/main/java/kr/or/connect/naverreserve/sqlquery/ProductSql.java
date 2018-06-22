package kr.or.connect.naverreserve.sqlquery;

public class ProductSql {

	public static final String CATEGORIES = " id,category_id, description,content,event,create_date,modify_date ";
	public static final String SELECT_ALL = "SELECT" + CATEGORIES + "FROM product"; 
	public static final String SELECT_LIMIT = "SELECT" + CATEGORIES + "FROM product limit :start, :limit";
	public static final String SELECT_LIMIT_BY_CATEGORIES = "SELECT" + CATEGORIES + "FROM product WHERE category_id=:categroyId limit :start, :limit";
}	
