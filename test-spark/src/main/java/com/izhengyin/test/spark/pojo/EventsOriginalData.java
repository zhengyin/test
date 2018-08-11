package com.izhengyin.test.spark.pojo;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhengyin on 2018/7/6 下午2:43.
 * Email  <zhengyin.name@gmail.com> .
 */
public class EventsOriginalData implements Serializable{
    private String event;
    private String uniqueId;
    private String eventDate;
    private Long eventTime;
    private String platform;
    private String terminal;
    private String client;
    private String shortSessionId;
    private String requestUri;
    private String queryString;
    private String requestMethod;
    private String uuid;
    private Integer userId;
    private String utmSource;
    private String clientIp;
    private String selfUrl;
    private String refUrl;
    private String selfPage;
    private String refPage;
    private String userAgent;
    private Map<String,Object> collectData;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getShortSessionId() {
        return shortSessionId;
    }

    public void setShortSessionId(String shortSessionId) {
        this.shortSessionId = shortSessionId;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUtmSource() {
        return utmSource;
    }

    public void setUtmSource(String utmSource) {
        this.utmSource = utmSource;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public String getRefUrl() {
        return refUrl;
    }

    public void setRefUrl(String refUrl) {
        this.refUrl = refUrl;
    }

    public String getSelfPage() {
        return selfPage;
    }

    public void setSelfPage(String selfPage) {
        this.selfPage = selfPage;
    }

    public String getRefPage() {
        return refPage;
    }

    public void setRefPage(String refPage) {
        this.refPage = refPage;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Map<String, Object> getCollectData() {
        return collectData;
    }

    public void setCollectData(Map<String, Object> collectData) {
        this.collectData = collectData;
    }


    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "EventsOriginalData{" +
                "event='" + event + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventTime=" + eventTime +
                ", platform='" + platform + '\'' +
                ", terminal='" + terminal + '\'' +
                ", client='" + client + '\'' +
                ", shortSessionId='" + shortSessionId + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", queryString='" + queryString + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", uuid='" + uuid + '\'' +
                ", userId=" + userId +
                ", utmSource='" + utmSource + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", selfUrl='" + selfUrl + '\'' +
                ", refUrl='" + refUrl + '\'' +
                ", selfPage='" + selfPage + '\'' +
                ", refPage='" + refPage + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", collectData=" + JSON.toJSONString(collectData) +
                '}';
    }
}
