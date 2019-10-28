package com.datangedu.cn.model.sysUser;

import java.util.Date;

public class ServiceOrder {
	private String serviceNo;

	private String memberId;

	private String serviceId;

	private Integer totalPrice;

	private Integer status;

	private Date createTime;

	private Integer payType;

	private String content;

	private String sName;

	private String cellphone;

	private Integer serviceNum;

	public Integer getLL() {
		return LL;
	}

	public void setLL(Integer lL) {
		LL = lL;
	}

	private Integer LL;

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Integer getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(Integer serviceNum) {
		this.serviceNum = serviceNum;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo == null ? null : serviceNo.trim();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId == null ? null : memberId.trim();
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId == null ? null : serviceId.trim();
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}