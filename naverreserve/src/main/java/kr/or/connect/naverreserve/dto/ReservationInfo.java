package kr.or.connect.naverreserve.dto;

import java.util.Date;

public class ReservationInfo {
 private int id;
 private int productId;
 private String reservationName;
 private String reservationTel;
 private String reservationEmail;
 private Date reservationDate;
 private Date createDate;
 private Date modifyDate;
 private String status;
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
public String getReservationName() {
	return reservationName;
}
public void setReservationName(String reservationName) {
	this.reservationName = reservationName;
}
public String getReservationTel() {
	return reservationTel;
}
public void setReservationTel(String reservationTel) {
	this.reservationTel = reservationTel;
}
public String getReservationEmail() {
	return reservationEmail;
}
public void setReservationEmail(String reservationEmail) {
	this.reservationEmail = reservationEmail;
}
public Date getReservationDate() {
	return reservationDate;
}
public void setReservationDate(Date reservationDate) {
	this.reservationDate = reservationDate;
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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "ReservationInfo [id=" + id + ", productId=" + productId + ", reservationName=" + reservationName
			+ ", reservationTel=" + reservationTel + ", reservationEmail=" + reservationEmail + ", reservationDate="
			+ reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status
			+ "]";
}

 
}
