package com.smart.server.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.smart.mvc.model.PersistentObject;

import java.util.Date;

/**
 * 队列模型
 *
 * @author YJ
 */
public class QueueModel extends PersistentObject {

    /**
     * 首字母
     */
    private String firstNum;
    /**
     * 病人卡号
     */
    private String patientCard;

    /**
     * 业务类型
     */
    private Integer type;

    /**
     * 业务类型名称
     */
    private String typeName;

    /**
     * 病人名字
     */
    private String patientName;
    /**
     * 医生名字
     */
    private String doctorName;
    /**
     * 科室ID
     */
    private Integer departId;
    /**
     * 诊室ID
     */
    private Integer roomId;
    /**
     * 是否VIP
     */
    private Integer isVip;
    /**
     * 是否优先
     */
    private Integer isFirst;
    /**
     * 就诊状态
     */
    private Integer status;
    /**
     * 排号时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    //科室名称
    private String departName;
    //诊室名称
    private String roomName;

    /**
     * 排队号
     */
    private String number;

    /**
     * 医生Id
     */
    private Integer doctorId;

    public String getNumber() {
        return number;
    }

    public String getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(String firstNum) {
        this.firstNum = firstNum;
    }

    public String getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(String patientCard) {
        this.patientCard = patientCard;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Integer getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setNumber(Integer number) {
        if (number < 10) {
            this.number = firstNum + "00" + number;
        } else if (number < 100) {
            this.number = firstNum + "0" + number;
        } else {
            this.number = firstNum + number;
        }
    }
}
