package kr.or.connect.naverreserve.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.FileInfoDao;
import kr.or.connect.naverreserve.dao.ReservationInfoDao;
import kr.or.connect.naverreserve.dao.ReservationInfoPriceDao;
import kr.or.connect.naverreserve.dao.ReservationUserCommentDao;
import kr.or.connect.naverreserve.dao.ReservationUserCommentImageDao;
import kr.or.connect.naverreserve.dto.FileInfo;
import kr.or.connect.naverreserve.dto.ReservationInfo;
import kr.or.connect.naverreserve.dto.ReservationInfoPrice;
import kr.or.connect.naverreserve.dto.ReservationUserComment;
import kr.or.connect.naverreserve.dto.ReservationUserCommentImage;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	ReservationInfoDao reservationInfoDao;
	
	@Autowired
	ReservationInfoPriceDao reservationInfoPriceDao;
	
	@Autowired
	ReservationUserCommentDao reservationUserCommentDao;
	
	@Autowired
	ReservationUserCommentImageDao reservationUserCommentImageDao;
	
	@Autowired
	FileInfoDao fileInfoDao;
	
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
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();		
		
		data.setReservationDate(dateFormat.format(date));
		data.setCreateDate(dateFormat.format(date));
		data.setModifyDate(dateFormat.format(date));
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

	@Override
	public int insertReservationUserComment(ReservationUserComment data) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		data.setCreateDate(dateFormat.format(date));
		data.setModifyDate(dateFormat.format(date));
		return reservationUserCommentDao.insert(data);
	}

	@Override
	public List<ReservationUserComment> getReservationUserCommentByProductId(int id) {
		return reservationUserCommentDao.selectByProductId(id);
	}

	@Override
	public Long insertReservationUserCommentImage(ReservationUserCommentImage data) {
		return reservationUserCommentImageDao.insert(data);
	}

	@Override
	public int insertFileInfo(FileInfo data) {
		return fileInfoDao.insert(data);
	}
	
	@Override
	public FileInfo getFileInfoByFileId(int id) {
		return fileInfoDao.selectByproductImageId(id);
	}
	

	@Override
	public int getReservationUserCommentImageCount(int reservationInfoId) {
		List<ReservationUserCommentImage> list = reservationUserCommentImageDao.selectByReservationInfoId(reservationInfoId);
		return list.size();
	}

	@Override
	public List<ReservationUserCommentImage> getReservationUserCommentImageByReservationId(int id) {
		return reservationUserCommentImageDao.selectByReservationInfoId(id);
	}

	@Override
	public ReservationUserCommentImage getReservationUserCommentImageByCommentId(int id) {
		return reservationUserCommentImageDao.selectByReservationUserCommentId(id);
	}

	

	
}
