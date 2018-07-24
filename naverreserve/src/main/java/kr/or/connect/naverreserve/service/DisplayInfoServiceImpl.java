package kr.or.connect.naverreserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.DisplayInfoDao;
import kr.or.connect.naverreserve.dto.DisplayInfo;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService{

	@Autowired
	DisplayInfoDao displayInfoDao;
	
	@Override
	public List<DisplayInfo> getDisplayInfos() {
		return displayInfoDao.selectAll();
	}

	@Override
	public DisplayInfo getDisplayInfoByProductId(int productId) {
		return displayInfoDao.selectByProductId(productId);
	}

	@Override
	public int getDisplayInfoCount() {
			return 0;
	}

}
