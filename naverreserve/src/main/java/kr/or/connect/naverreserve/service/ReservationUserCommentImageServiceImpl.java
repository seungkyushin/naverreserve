package kr.or.connect.naverreserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.ReservationUserCommentImageDao;
import kr.or.connect.naverreserve.dto.ReservationUserCommentImage;

@Service
public class ReservationUserCommentImageServiceImpl implements ReservationUserCommentImageService{

	@Autowired
	ReservationUserCommentImageDao reservationUserCommentImageDao;
	
	@Override
	public List<ReservationUserCommentImage> getReservationUserCommentImages() {
		return reservationUserCommentImageDao.selectAll();
	}

	@Override
	public ReservationUserCommentImage getReservationUserCommentImageById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int geReservationUserCommentImageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
