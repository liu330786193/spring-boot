package com.yongjun.stock.entity;

import java.util.Date;
/**
 * 系统消息实体类
 */
public class SmsLog {
    /**
     * 
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long companyId;

    /**
     * 手机号 - 不会带国别
     */
    private String phoneNo;

    /**
     * 短信类别 1客户注册获取骓码 2客户忘记密码获取验证码 3后台用户忘记密码获取验证码
     */
    private Integer msgType;

    /**
     * 验证码
     */
    private String verifCode;

    /**
     * 1 submail
     */
    private Integer msgChannel;

    /**
     * 1 成功 0 失败
     */
    private Integer status;

    /**
     * 
     */
    private String errMsg;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date sucessTime;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商户ID
     * @return company_id 商户ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 商户ID
     * @param companyId 商户ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 手机号 - 不会带国别
     * @return phone_no 手机号 - 不会带国别
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * 手机号 - 不会带国别
     * @param phoneNo 手机号 - 不会带国别
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    /**
     * 短信类别 1客户注册获取骓码 2客户忘记密码获取验证码 3后台用户忘记密码获取验证码
     * @return msg_type 短信类别 1客户注册获取骓码 2客户忘记密码获取验证码 3后台用户忘记密码获取验证码
     */
    public Integer getMsgType() {
        return msgType;
    }

    /**
     * 短信类别 1客户注册获取骓码 2客户忘记密码获取验证码 3后台用户忘记密码获取验证码
     * @param msgType 短信类别 1客户注册获取骓码 2客户忘记密码获取验证码 3后台用户忘记密码获取验证码
     */
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    /**
     * 验证码
     * @return verif_code 验证码
     */
    public String getVerifCode() {
        return verifCode;
    }

    /**
     * 验证码
     * @param verifCode 验证码
     */
    public void setVerifCode(String verifCode) {
        this.verifCode = verifCode == null ? null : verifCode.trim();
    }

    /**
     * 1 submail
     * @return msg_channel 1 submail
     */
    public Integer getMsgChannel() {
        return msgChannel;
    }

    /**
     * 1 submail
     * @param msgChannel 1 submail
     */
    public void setMsgChannel(Integer msgChannel) {
        this.msgChannel = msgChannel;
    }

    /**
     * 1 成功 0 失败
     * @return status 1 成功 0 失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 1 成功 0 失败
     * @param status 1 成功 0 失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return err_msg 
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 
     * @param errMsg 
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return sucess_time 
     */
    public Date getSucessTime() {
        return sucessTime;
    }

    /**
     * 
     * @param sucessTime 
     */
    public void setSucessTime(Date sucessTime) {
        this.sucessTime = sucessTime;
    }
}