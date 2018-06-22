package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.ReservationUserComment;

public interface ReservationUserCommentService {
	
	public List<ReservationUserComment> getReservationUserComments();
	public ReservationUserComment getReservationUserCommentByProductId(int productId);
	public int getReservationUserCommentCount();


}
