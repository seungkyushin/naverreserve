package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.FileInfo;

public interface FileInfoService {
	
	public List<FileInfo> getFileInfoAll();
	public FileInfo getFileInfo(int id);
	public Integer getProductImageId(int id);
	public String getSaveFileName(int id);
	

}
