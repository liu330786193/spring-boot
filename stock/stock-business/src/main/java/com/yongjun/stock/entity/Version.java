package com.yongjun.stock.entity;

import java.util.Date;
/**
 * APP版本信息实体类
 */
public class Version {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String appName;

    /**
     * 
     */
    private String appOs;

    /**
     * 
     */
    private String appVersion;

    /**
     * 
     */
    private Integer mustFlag;

    /**
     * 
     */
    private Date issureTime;

    /**
     * 
     */
    private String issureNote;

    /**
     * 
     */
    private String issureUrl;

    /**
     * 
     */
    private String filename;

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
     * 
     * @return app_name 
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 
     * @param appName 
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * 
     * @return app_os 
     */
    public String getAppOs() {
        return appOs;
    }

    /**
     * 
     * @param appOs 
     */
    public void setAppOs(String appOs) {
        this.appOs = appOs == null ? null : appOs.trim();
    }

    /**
     * 
     * @return app_version 
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * 
     * @param appVersion 
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     * 
     * @return must_flag 
     */
    public Integer getMustFlag() {
        return mustFlag;
    }

    /**
     * 
     * @param mustFlag 
     */
    public void setMustFlag(Integer mustFlag) {
        this.mustFlag = mustFlag;
    }

    /**
     * 
     * @return issure_time 
     */
    public Date getIssureTime() {
        return issureTime;
    }

    /**
     * 
     * @param issureTime 
     */
    public void setIssureTime(Date issureTime) {
        this.issureTime = issureTime;
    }

    /**
     * 
     * @return issure_note 
     */
    public String getIssureNote() {
        return issureNote;
    }

    /**
     * 
     * @param issureNote 
     */
    public void setIssureNote(String issureNote) {
        this.issureNote = issureNote == null ? null : issureNote.trim();
    }

    /**
     * 
     * @return issure_url 
     */
    public String getIssureUrl() {
        return issureUrl;
    }

    /**
     * 
     * @param issureUrl 
     */
    public void setIssureUrl(String issureUrl) {
        this.issureUrl = issureUrl == null ? null : issureUrl.trim();
    }

    /**
     * 
     * @return filename 
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 
     * @param filename 
     */
    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }
}