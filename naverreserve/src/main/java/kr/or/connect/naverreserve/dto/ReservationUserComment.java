package kr.or.connect.naverreserve.dto;

import java.util.Date;

public class ReservationUserComment {
 private int id;
 private int productId;
 private int reservationInfoId;
 private float score;
 private String comment;
 private Date createDate;
 private Date modifyDate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public int getReservationInfoId() {
	return reservationInfoId;
}
public void setReservationInfoId(int reservationInfoId) {
	this.reservationInfoId = reservationInfoId;
}
public float getScore() {
	return score;
}
public void setScore(float score) {
	this.score = score;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public Date getModifyDate() {
	return modifyDate;
}
public void setModifyDate(Date modifyDate) {
	this.modifyDate = modifyDate;
}
@Override
public String toString() {
	return "ReservationUserComment [id=" + id + ", productId=" + productId + ", reservationInfoId=" + reservationInfoId
			+ ", score=" + score + ", comment=" + comment + ", createDate=" + createDate + ", modifyDate=" + modifyDate
			+ "]";
}
 
 
	
}
