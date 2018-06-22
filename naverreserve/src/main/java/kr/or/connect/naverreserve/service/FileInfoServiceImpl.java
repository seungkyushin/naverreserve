package kr.or.connect.naverreserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.FileInfoDao;
import kr.or.connect.naverreserve.dto.FileInfo;

@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	FileInfoDao fileInfoDao;
	
	@Override
	public List<FileInfo> getFileInfoAll() {
		return fileInfoDao.selectAll();
	}

	@Override
	public FileInfo getFileInfo(int id) {
		return fileInfoDao.selectByproductImageId(id);
	}

	@Override
	public String getSaveFileName(int id) {
		FileInfo fileinfo = fileInfoDao.selectByproductImageId(id);
		
		return fileinfo.getFileName();
	}

	@Override
	public Integer getProductImageId(int id) {
		String src = getSaveFileName(id);
		src = src.replace(".","_");
		String[] arySrc = src.split("_");
		int result = Integer.parseInt(arySrc[2]);
		return result;
	}

}
