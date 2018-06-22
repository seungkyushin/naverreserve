package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.DisplayInfo;

public interface DisplayInfoService {
	
	public List<DisplayInfo> getDisplayInfos();
	public DisplayInfo getDisplayInfoByProductId(int productId);
	public int getDisplayInfoCount();
	

}
