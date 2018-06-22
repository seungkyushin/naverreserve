package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.ReservationUserCommentImage;

public interface ReservationUserCommentImageService {

	public List<ReservationUserCommentImage> getReservationUserCommentImages();
	public ReservationUserCommentImage getReservationUserCommentImageById(int id);
	public int geReservationUserCommentImageCount();
	
}
