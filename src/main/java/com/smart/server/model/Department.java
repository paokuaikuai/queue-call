package com.smart.server.model;


import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.smart.mvc.model.PersistentObject;

/**
 * 科室
 * 
 * @author WS
 */
public class Department extends PersistentObject {

	private static final long serialVersionUID = 564115576254799088L;

	/** 科室编码 */
	private String departCode;
	/** 科室名称 */
	private String departName;
	/** 科室类型 */
	private Integer departType;
	/** 科室首字母 */
	private String firstNum;
	/** 屏幕号 */
	private String screenNum;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 更新时间*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public Integer getDepartType() {
		return departType;
	}
	public void setDepartType(Integer departType) {
		this.departType = departType;
	}
	public String getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(String firstNum) {
		this.firstNum = firstNum;
	}
	public String getScreenNum() {
		return screenNum;
	}
	public void setScreenNum(String screenNum) {
		this.screenNum = screenNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	


}
