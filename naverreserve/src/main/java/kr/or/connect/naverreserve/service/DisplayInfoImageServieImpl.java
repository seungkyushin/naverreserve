package kr.or.connect.naverreserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.DisplayInfoDao;
import kr.or.connect.naverreserve.dao.DisplayInfoImageDao;
import kr.or.connect.naverreserve.dto.DisplayInfoImage;

@Service
public class DisplayInfoImageServieImpl implements DisplayInfoImageServie{

	@Autowired
	DisplayInfoImageDao displayInfoImageDao;
	
	
	@Override
	public List<DisplayInfoImage> getDisplayInfoImages() {
		return displayInfoImageDao.selectAll();
	}

	@Override
	public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId) {
		return displayInfoImageDao.selectByDisplayInfoId(displayInfoId);
	}

	@Override
	public int getDisplayInfoImageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
