package kr.or.connect.naverreserve.sqlquery;

public class ReservationUserCommentImageSql {
	
	public static final String TABLE_NAME = "reservation_user_comment_image";
	public static final String CATEGORIES = " id, reservation_info_id, reservation_user_comment_id, file_id ";
	public static final String SELECT_ALL = "SELECT" + CATEGORIES + "FROM "+ TABLE_NAME;
	public static final String SELECT_BY_RESERVATION_INFO_ID = "SELECT" + CATEGORIES + "FROM "+ TABLE_NAME + " WHERE reservation_info_id=:reservationInfoId";
	public static final String SELECT_BY_RSERVATION_USER_COMMENT_ID = "SELECT" + CATEGORIES + "FROM "+ TABLE_NAME + " WHERE reservation_user_comment_id=:reservationUserCommentId";
	
}
