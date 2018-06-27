package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.ReservationInfo;
import kr.or.connect.naverreserve.dto.ReservationInfoPrice;

public interface ReservationService {

	//< 예약
	public List<ReservationInfo> getReservationInfoByEmail(String email);
	public ReservationInfo getReservationInfoById(int id);
	public Long insertReservationInfo(ReservationInfo data);
	
	//< 가격
	public Long insertReservationInfoPrice(ReservationInfoPrice data);
	public List<ReservationInfoPrice> getReservationInfoPrice(int reservationId);
}
