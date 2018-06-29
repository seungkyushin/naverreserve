package kr.or.connect.naverreserve.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.ReservationInfoDao;
import kr.or.connect.naverreserve.dao.ReservationInfoPriceDao;
import kr.or.connect.naverreserve.dto.ReservationInfo;
import kr.or.connect.naverreserve.dto.ReservationInfoPrice;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	ReservationInfoDao reservationInfoDao;
	
	@Autowired
	ReservationInfoPriceDao reservationInfoPriceDao;
	
	
	@Override
	public List<ReservationInfo> getReservationInfoByEmail(String email) {
		return reservationInfoDao.selectByEmail(email);
	}

	@Override
	public ReservationInfo getReservationInfoById(int id) {
		return reservationInfoDao.selectByProductId(id);
	}

	@Override
	public Long insertReservationInfo(ReservationInfo data) {
		
		Date Date = new Date();
		data.setReservationDate(Date);
		data.setCreateDate(Date);
		data.setModifyDate(Date);
		return reservationInfoDao.insert(data);
	}

	@Override
	public Long insertReservationInfoPrice(ReservationInfoPrice data) {
		return reservationInfoPriceDao.insert(data);
		
	}

	@Override
	public List<ReservationInfoPrice> getReservationInfoPrice(int reservationId) {
		return reservationInfoPriceDao.selectByReservationInfoId(reservationId);
	}

	@Override
	public int updateReservationStatus(int id, String status) {
		return reservationInfoDao.updateStatusById(id,status);
	}

	
}
