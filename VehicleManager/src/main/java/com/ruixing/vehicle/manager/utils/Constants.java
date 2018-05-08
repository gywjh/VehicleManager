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
	public final static String QR_GENERATE_PATH = "\\static\\images\\upload\\";
	
	public final static String EXCLE_TEMP_PATH ="\\vehicle\\excle\\";
	/**
	 * 车辆信息表头
	 */
	public final static String[] header= {"车牌号码","车牌颜色","行驶证所属人","驾驶员姓名","驾驶员性别","驾驶员身份证","驾驶员驾照类型","驾驶员所属公司","押运员姓名","押运员性别","押运员身份证","押运员驾照类型","押运员所属公司","货物品类","载重量","记录日期"};
	
	private final static int PAGE_SIZE = 5;
	
	public static final Pageable getPageable(int currentPage,String key)
	{
		Sort sort = new Sort( Direction.DESC, key);
		Pageable pageable = new PageRequest( currentPage, PAGE_SIZE, sort );
		return pageable;
	}
}
