package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.FileInfo;
import kr.or.connect.naverreserve.dto.ReservationInfo;
import kr.or.connect.naverreserve.dto.ReservationInfoPrice;
import kr.or.connect.naverreserve.dto.ReservationUserComment;
import kr.or.connect.naverreserve.dto.ReservationUserCommentImage;

public interface ReservationService {

	//< 예약
	public List<ReservationInfo> getReservationInfoByEmail(String email);
	public ReservationInfo getReservationInfoById(int id);
	public Long insertReservationInfo(ReservationInfo data);
	
	//< 가격
	public Long insertReservationInfoPrice(ReservationInfoPrice data);
	public List<ReservationInfoPrice> getReservationInfoPrice(int reservationId);
	public int updateReservationStatus(int id,String status);
	
	//< 유저 덧글
	public int insertReservationUserComment(ReservationUserComment data);
	public List<ReservationUserComment> getReservationUserCommentByProductId(int id);
	
	//< 유저 이미지
	public Long insertReservationUserCommentImage(ReservationUserCommentImage data);
	public List<ReservationUserCommentImage> getReservationUserCommentImageByReservationId(int id);
	public ReservationUserCommentImage getReservationUserCommentImageByCommentId(int id);
	public int getReservationUserCommentImageCount(int reservationInfoId);
	public int insertFileInfo(FileInfo data);
	public FileInfo getFileInfoByFileId(int id);
}
