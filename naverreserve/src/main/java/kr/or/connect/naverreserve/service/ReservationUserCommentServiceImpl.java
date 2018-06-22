package kr.or.connect.naverreserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.ReservationUserCommentDao;
import kr.or.connect.naverreserve.dto.ReservationUserComment;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService{

	@Autowired
	ReservationUserCommentDao reservationUserCommentDao;
	
	@Override
	public List<ReservationUserComment> getReservationUserComments() {
		return reservationUserCommentDao.selectAll();
	}

	@Override
	public ReservationUserComment getReservationUserCommentByProductId(int productId) {
		// TODO Auto-generated method stub
		return reservationUserCommentDao.selectByProductId(productId);
	}

	@Override
	public int getReservationUserCommentCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
