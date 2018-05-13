package com.ruixing.vehicle.manager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 用户信息实体类
 * 
 */
@Entity
@Table(name = "users")
public class UserInfo {
	
	/**用户ID*/
	@Id
    @GeneratedValue
	@Column(length = 10)
	private Integer id;
	
	/**用户手机号*/
	@Column(name = "phone_id", nullable = false, length = 20)
	private String phoneId;
	
	/**密码*/
	@Column(nullable = false)
	private String password;
	
	/**用户名称*/
	@Column(name = "user_name", nullable = false, length = 20)
	private String userName;
	
	/**性别*/
	@Column(nullable = false, length = 10)
	private String sex;
	
	/**身份证号*/
	@Column(name = "id_card", nullable = false, length = 20)
	private String idCard;
	
	/**职务*/
	@Column(nullable = false, length = 50)
	private String title;
	
	/**警务编号*/
	@Column(name = "police_officer", nullable = false, length = 20)
	private String policeOfficer;
	
	/**单位名称*/
	@Column(name = "organization_name", nullable = false, length = 30)
	private String organizationName;
	
	/**地址*/
	@Column(nullable = false)
	private String address;
	
	/**角色*/
	@Column(nullable = false, length = 20)
	private String role;
		
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoliceOfficer() {
		return policeOfficer;
	}

	public void setPoliceOfficer(String policeOfficer) {
		this.policeOfficer = policeOfficer;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}
	
	
}
