package com.fh.shop.api.util;


import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UpFile {
	/**
	 * 文件上传
	 * @param photo name值
	 * @param savePath 文件路径
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String upFile(CommonsMultipartFile photo, String savePath) throws IllegalStateException, IOException{
		String newFileName ="";	
		if (!photo.isEmpty()) {
			String oldName = photo.getOriginalFilename();
			int indexOf = oldName.lastIndexOf(".");
			newFileName =UUID.randomUUID()+oldName.substring(indexOf);
			System.out.println(savePath);
			if (!new File(savePath).exists()) {
				new File(savePath).mkdirs();
			}
			photo.transferTo(new File(savePath+newFileName));
		}

		return newFileName;
	}
	/**
	 * 文件下载
	 * @param photo 文件名
	 * @param path	文件路径
	 * @return
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> downFile(String photo,
                                                  String path) throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		// 设置下载的文件类型
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 设置下载的文件名
		httpHeaders.setContentDispositionFormData("attachment", photo);
		// 将文件转为二进制数组
		byte[] file = FileUtils.readFileToByteArray(new File(path));
		// 返回响应信息
		return new ResponseEntity<byte[]>(file, httpHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * zip 包下载
	 * @param attphotos 文件路径（字符串）
	 * @param username	文件名
	 * @param session
	 * @param response
	 * @param request
	 * @param savePath 根路径+文件夹
	 * @return
	 * @throws IOException
	 */
	public static String downFileZip(String attphotos, String username, HttpSession session, HttpServletResponse response,
                                     HttpServletRequest request, String savePath) throws IOException {
		String realPath2 = session.getServletContext().getRealPath("/");
		String[] split = attphotos.split(",");
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(savePath+username+ ".zip")));
		for (int i = 0; i < split.length; i++) {
			String path = split[i];
			String realPath =savePath+path;
			// 获得文件名
			String fileName = path.substring(path.lastIndexOf("/") + 1);
			ZipEntry zipEntry = new ZipEntry(fileName);
			zipOutputStream.putNextEntry(zipEntry);
			FileInputStream fileInputStream = new FileInputStream(new File(realPath2+path));
			byte[] bytes = new byte[1024];
			int flag = fileInputStream.read(bytes);
			while (flag != -1) {
				zipOutputStream.write(bytes, 0, flag);
				flag = fileInputStream.read(bytes);
			}
			
			fileInputStream.close();
		}
		zipOutputStream.close();

		return username+ ".zip";
	}
	

}
