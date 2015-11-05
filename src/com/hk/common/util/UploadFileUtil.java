package com.hk.common.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * 处理文件上传和下载操作
 *
 * @author zhenglian
 * @data 2015年10月21日 下午10:41:20
 */
public class UploadFileUtil {

	/**
	 * 保存客户端上传的文件到本地指定目录
	 * 
	 * @param file
	 *            要保存的文件
	 * @param localDir
	 *            本地目录
	 * @param fileName
	 *            文件名称
	 */
	public static void uploadFile(MultipartFile file, String localDir,
			String fileName) throws Exception {

		File dir = new File(localDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File localFile = new File(dir, fileName);

		if (!localFile.exists()) {
			localFile.createNewFile();
		}

		file.transferTo(localFile);

	}
	
	public static void uploadUserPhoto(MultipartFile file) {
		
	}

}
