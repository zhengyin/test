package com.izhengyin.test.other.serial;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhengyin on 2018/7/30 下午2:40.
 * Email  <zhengyin.name@gmail.com> .
 */
public class EventsStream implements Serializable{
    public long id;
    public CharSequence event;
    public CharSequence platform;
    public CharSequence client;
    public int shop_id;
    public int user_id;
    public int seller_id;
    public CharSequence type;
    public CharSequence uuid;
    public CharSequence utm_source;
    public CharSequence resolution;
    public CharSequence client_ip;
    public CharSequence user_agent;
    public CharSequence country;
    public CharSequence province;
    public CharSequence city;
    public CharSequence self_page;
    public CharSequence ref_page;
    public CharSequence session_id;
    public CharSequence session_duration;
    public CharSequence app_name;
    public CharSequence app_version;
    public CharSequence event_date;
    public long event_time;
    public long event_time_ms;
    public java.util.Map<CharSequence,CharSequence> platform_attr;
    public java.util.Map<CharSequence,Integer> count_attr;
    public java.util.Map<CharSequence,CharSequence> event_attr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CharSequence getEvent() {
        return event;
    }

    public void setEvent(CharSequence event) {
        this.event = event;
    }

    public CharSequence getPlatform() {
        return platform;
    }

    public void setPlatform(CharSequence platform) {
        this.platform = platform;
    }

    public CharSequence getClient() {
        return client;
    }

    public void setClient(CharSequence client) {
        this.client = client;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public CharSequence getType() {
        return type;
    }

    public void setType(CharSequence type) {
        this.type = type;
    }

    public CharSequence getUuid() {
        return uuid;
    }

    public void setUuid(CharSequence uuid) {
        this.uuid = uuid;
    }

    public CharSequence getUtm_source() {
        return utm_source;
    }

    public void setUtm_source(CharSequence utm_source) {
        this.utm_source = utm_source;
    }

    public CharSequence getResolution() {
        return resolution;
    }

    public void setResolution(CharSequence resolution) {
        this.resolution = resolution;
    }

    public CharSequence getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(CharSequence client_ip) {
        this.client_ip = client_ip;
    }

    public CharSequence getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(CharSequence user_agent) {
        this.user_agent = user_agent;
    }

    public CharSequence getCountry() {
        return country;
    }

    public void setCountry(CharSequence country) {
        this.country = country;
    }

    public CharSequence getProvince() {
        return province;
    }

    public void setProvince(CharSequence province) {
        this.province = province;
    }

    public CharSequence getCity() {
        return city;
    }

    public void setCity(CharSequence city) {
        this.city = city;
    }

    public CharSequence getSelf_page() {
        return self_page;
    }

    public void setSelf_page(CharSequence self_page) {
        this.self_page = self_page;
    }

    public CharSequence getRef_page() {
        return ref_page;
    }

    public void setRef_page(CharSequence ref_page) {
        this.ref_page = ref_page;
    }

    public CharSequence getSession_id() {
        return session_id;
    }

    public void setSession_id(CharSequence session_id) {
        this.session_id = session_id;
    }

    public CharSequence getSession_duration() {
        return session_duration;
    }

    public void setSession_duration(CharSequence session_duration) {
        this.session_duration = session_duration;
    }

    public CharSequence getApp_name() {
        return app_name;
    }

    public void setApp_name(CharSequence app_name) {
        this.app_name = app_name;
    }

    public CharSequence getApp_version() {
        return app_version;
    }

    public void setApp_version(CharSequence app_version) {
        this.app_version = app_version;
    }

    public CharSequence getEvent_date() {
        return event_date;
    }

    public void setEvent_date(CharSequence event_date) {
        this.event_date = event_date;
    }

    public long getEvent_time() {
        return event_time;
    }

    public void setEvent_time(long event_time) {
        this.event_time = event_time;
    }

    public long getEvent_time_ms() {
        return event_time_ms;
    }

    public void setEvent_time_ms(long event_time_ms) {
        this.event_time_ms = event_time_ms;
    }

    public Map<CharSequence, CharSequence> getPlatform_attr() {
        return platform_attr;
    }

    public void setPlatform_attr(Map<CharSequence, CharSequence> platform_attr) {
        this.platform_attr = platform_attr;
    }

    public Map<CharSequence, Integer> getCount_attr() {
        return count_attr;
    }

    public void setCount_attr(Map<CharSequence, Integer> count_attr) {
        this.count_attr = count_attr;
    }

    public Map<CharSequence, CharSequence> getEvent_attr() {
        return event_attr;
    }

    public void setEvent_attr(Map<CharSequence, CharSequence> event_attr) {
        this.event_attr = event_attr;
    }


}
