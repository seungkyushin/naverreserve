package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.DisplayInfoImage;

public interface DisplayInfoImageServie {
	
	
	public List<DisplayInfoImage> getDisplayInfoImages();
	public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId);
	public int getDisplayInfoImageCount();

}
