package com.ruixing.vehicle.manager.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class Constants {

	/**
	 * 二维码长度
	 */
	public final static int QR_LENGTH = 12;
	
	
	/**
	 * 二维码图片宽
	 */
	public final static int QR_WIDTH = 300;
	
	
	/**
	 * 二维码图片高
	 */
	public final static int QR_HIGTH = 300;
	
	/**
	 * 二维码图片高
	 */
	public final static String QR_IMAGE_TYPE = "png";
	
	/**
	 * 二维码生成存放路径
	 */
	public final static String QR_GENERATE_PATH = "/static/images/upload/";
	
	private final static int PAGE_SIZE = 5;
	
	public static final Pageable getPageable(int currentPage,String key)
	{
		Sort sort = new Sort( Direction.DESC, key);
		Pageable pageable = new PageRequest( currentPage, PAGE_SIZE, sort );
		return pageable;
	}
}
