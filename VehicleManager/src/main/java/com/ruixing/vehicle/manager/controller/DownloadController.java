package com.ruixing.vehicle.manager.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruixing.vehicle.manager.utils.Constants;
import com.ruixing.vehicle.manager.vehicle.service.IVehicleService;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/download")
public class DownloadController {

	@Resource
	private IVehicleService vehicleService;

	@RequestMapping(path = "/export", method = RequestMethod.GET)
	public void exportVehicleInfo(HttpServletResponse response) {
		String fileName = vehicleService.exprotVehicleInfo();
		downLoad(response, fileName);
	}

	private void downLoad(HttpServletResponse response, String fileName) {
		File file = new File(System.getProperty("user.dir") + Constants.EXCLE_TEMP_PATH, fileName);
		if (file.exists()) {
			response.setContentType("application/octet-stream");//
			response.setHeader("content-type", "application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
				System.out.println("success");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
