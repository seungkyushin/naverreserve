package kr.or.connect.naverreserve.sqlquery;
public class ReservationUserCommentSql {
	public static final String TABLENAME = "reservation_user_comment";
	public static final String CATEGORIES = " id, product_id, reservation_info_id, score, comment, create_date,modify_date ";
	public static final String SELECT_ALL = "SELECT" + CATEGORIES + "FROM "+ TABLENAME;
	public static final String SELECT_BY_PRODUCTID = "SELECT" + CATEGORIES + "FROM "+ TABLENAME + " WHERE product_id=:productId";

}
