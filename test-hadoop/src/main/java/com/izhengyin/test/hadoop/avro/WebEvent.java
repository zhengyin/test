/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.izhengyin.test.hadoop.avro;
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class WebEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"WebEvent\",\"namespace\":\"com.izhengyin.bigdata.analysis.common.pojo\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"event\",\"type\":\"string\"},{\"name\":\"user_id\",\"type\":\"int\"},{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"uuid\",\"type\":\"string\"},{\"name\":\"utm_source\",\"type\":\"string\"},{\"name\":\"resolution\",\"type\":\"string\"},{\"name\":\"client_ip\",\"type\":\"string\"},{\"name\":\"user_agent\",\"type\":\"string\"},{\"name\":\"country\",\"type\":\"string\"},{\"name\":\"province\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"self_page\",\"type\":\"string\"},{\"name\":\"ref_page\",\"type\":\"string\"},{\"name\":\"session_id\",\"type\":\"string\"},{\"name\":\"event_date\",\"type\":\"string\"},{\"name\":\"event_time\",\"type\":\"long\"},{\"name\":\"event_attr_int\",\"type\":{\"type\":\"map\",\"values\":\"int\"}},{\"name\":\"event_attr_str\",\"type\":{\"type\":\"map\",\"values\":\"string\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public long id;
  @Deprecated public CharSequence event;
  @Deprecated public int user_id;
  @Deprecated public CharSequence type;
  @Deprecated public CharSequence uuid;
  @Deprecated public CharSequence utm_source;
  @Deprecated public CharSequence resolution;
  @Deprecated public CharSequence client_ip;
  @Deprecated public CharSequence user_agent;
  @Deprecated public CharSequence country;
  @Deprecated public CharSequence province;
  @Deprecated public CharSequence city;
  @Deprecated public CharSequence self_page;
  @Deprecated public CharSequence ref_page;
  @Deprecated public CharSequence session_id;
  @Deprecated public CharSequence event_date;
  @Deprecated public long event_time;
  @Deprecated public java.util.Map<CharSequence,Integer> event_attr_int;
  @Deprecated public java.util.Map<CharSequence,CharSequence> event_attr_str;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public WebEvent() {}

  /**
   * All-args constructor.
   */
  public WebEvent(Long id, CharSequence event, Integer user_id, CharSequence type, CharSequence uuid, CharSequence utm_source, CharSequence resolution, CharSequence client_ip, CharSequence user_agent, CharSequence country, CharSequence province, CharSequence city, CharSequence self_page, CharSequence ref_page, CharSequence session_id, CharSequence event_date, Long event_time, java.util.Map<CharSequence,Integer> event_attr_int, java.util.Map<CharSequence,CharSequence> event_attr_str) {
    this.id = id;
    this.event = event;
    this.user_id = user_id;
    this.type = type;
    this.uuid = uuid;
    this.utm_source = utm_source;
    this.resolution = resolution;
    this.client_ip = client_ip;
    this.user_agent = user_agent;
    this.country = country;
    this.province = province;
    this.city = city;
    this.self_page = self_page;
    this.ref_page = ref_page;
    this.session_id = session_id;
    this.event_date = event_date;
    this.event_time = event_time;
    this.event_attr_int = event_attr_int;
    this.event_attr_str = event_attr_str;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return event;
    case 2: return user_id;
    case 3: return type;
    case 4: return uuid;
    case 5: return utm_source;
    case 6: return resolution;
    case 7: return client_ip;
    case 8: return user_agent;
    case 9: return country;
    case 10: return province;
    case 11: return city;
    case 12: return self_page;
    case 13: return ref_page;
    case 14: return session_id;
    case 15: return event_date;
    case 16: return event_time;
    case 17: return event_attr_int;
    case 18: return event_attr_str;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, Object value$) {
    switch (field$) {
    case 0: id = (Long)value$; break;
    case 1: event = (CharSequence)value$; break;
    case 2: user_id = (Integer)value$; break;
    case 3: type = (CharSequence)value$; break;
    case 4: uuid = (CharSequence)value$; break;
    case 5: utm_source = (CharSequence)value$; break;
    case 6: resolution = (CharSequence)value$; break;
    case 7: client_ip = (CharSequence)value$; break;
    case 8: user_agent = (CharSequence)value$; break;
    case 9: country = (CharSequence)value$; break;
    case 10: province = (CharSequence)value$; break;
    case 11: city = (CharSequence)value$; break;
    case 12: self_page = (CharSequence)value$; break;
    case 13: ref_page = (CharSequence)value$; break;
    case 14: session_id = (CharSequence)value$; break;
    case 15: event_date = (CharSequence)value$; break;
    case 16: event_time = (Long)value$; break;
    case 17: event_attr_int = (java.util.Map<CharSequence,Integer>)value$; break;
    case 18: event_attr_str = (java.util.Map<CharSequence,CharSequence>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'event' field.
   */
  public CharSequence getEvent() {
    return event;
  }

  /**
   * Sets the value of the 'event' field.
   * @param value the value to set.
   */
  public void setEvent(CharSequence value) {
    this.event = value;
  }

  /**
   * Gets the value of the 'user_id' field.
   */
  public Integer getUserId() {
    return user_id;
  }

  /**
   * Sets the value of the 'user_id' field.
   * @param value the value to set.
   */
  public void setUserId(Integer value) {
    this.user_id = value;
  }

  /**
   * Gets the value of the 'type' field.
   */
  public CharSequence getType() {
    return type;
  }

  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(CharSequence value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'uuid' field.
   */
  public CharSequence getUuid() {
    return uuid;
  }

  /**
   * Sets the value of the 'uuid' field.
   * @param value the value to set.
   */
  public void setUuid(CharSequence value) {
    this.uuid = value;
  }

  /**
   * Gets the value of the 'utm_source' field.
   */
  public CharSequence getUtmSource() {
    return utm_source;
  }

  /**
   * Sets the value of the 'utm_source' field.
   * @param value the value to set.
   */
  public void setUtmSource(CharSequence value) {
    this.utm_source = value;
  }

  /**
   * Gets the value of the 'resolution' field.
   */
  public CharSequence getResolution() {
    return resolution;
  }

  /**
   * Sets the value of the 'resolution' field.
   * @param value the value to set.
   */
  public void setResolution(CharSequence value) {
    this.resolution = value;
  }

  /**
   * Gets the value of the 'client_ip' field.
   */
  public CharSequence getClientIp() {
    return client_ip;
  }

  /**
   * Sets the value of the 'client_ip' field.
   * @param value the value to set.
   */
  public void setClientIp(CharSequence value) {
    this.client_ip = value;
  }

  /**
   * Gets the value of the 'user_agent' field.
   */
  public CharSequence getUserAgent() {
    return user_agent;
  }

  /**
   * Sets the value of the 'user_agent' field.
   * @param value the value to set.
   */
  public void setUserAgent(CharSequence value) {
    this.user_agent = value;
  }

  /**
   * Gets the value of the 'country' field.
   */
  public CharSequence getCountry() {
    return country;
  }

  /**
   * Sets the value of the 'country' field.
   * @param value the value to set.
   */
  public void setCountry(CharSequence value) {
    this.country = value;
  }

  /**
   * Gets the value of the 'province' field.
   */
  public CharSequence getProvince() {
    return province;
  }

  /**
   * Sets the value of the 'province' field.
   * @param value the value to set.
   */
  public void setProvince(CharSequence value) {
    this.province = value;
  }

  /**
   * Gets the value of the 'city' field.
   */
  public CharSequence getCity() {
    return city;
  }

  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(CharSequence value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'self_page' field.
   */
  public CharSequence getSelfPage() {
    return self_page;
  }

  /**
   * Sets the value of the 'self_page' field.
   * @param value the value to set.
   */
  public void setSelfPage(CharSequence value) {
    this.self_page = value;
  }

  /**
   * Gets the value of the 'ref_page' field.
   */
  public CharSequence getRefPage() {
    return ref_page;
  }

  /**
   * Sets the value of the 'ref_page' field.
   * @param value the value to set.
   */
  public void setRefPage(CharSequence value) {
    this.ref_page = value;
  }

  /**
   * Gets the value of the 'session_id' field.
   */
  public CharSequence getSessionId() {
    return session_id;
  }

  /**
   * Sets the value of the 'session_id' field.
   * @param value the value to set.
   */
  public void setSessionId(CharSequence value) {
    this.session_id = value;
  }

  /**
   * Gets the value of the 'event_date' field.
   */
  public CharSequence getEventDate() {
    return event_date;
  }

  /**
   * Sets the value of the 'event_date' field.
   * @param value the value to set.
   */
  public void setEventDate(CharSequence value) {
    this.event_date = value;
  }

  /**
   * Gets the value of the 'event_time' field.
   */
  public Long getEventTime() {
    return event_time;
  }

  /**
   * Sets the value of the 'event_time' field.
   * @param value the value to set.
   */
  public void setEventTime(Long value) {
    this.event_time = value;
  }

  /**
   * Gets the value of the 'event_attr_int' field.
   */
  public java.util.Map<CharSequence,Integer> getEventAttrInt() {
    return event_attr_int;
  }

  /**
   * Sets the value of the 'event_attr_int' field.
   * @param value the value to set.
   */
  public void setEventAttrInt(java.util.Map<CharSequence,Integer> value) {
    this.event_attr_int = value;
  }

  /**
   * Gets the value of the 'event_attr_str' field.
   */
  public java.util.Map<CharSequence,CharSequence> getEventAttrStr() {
    return event_attr_str;
  }

  /**
   * Sets the value of the 'event_attr_str' field.
   * @param value the value to set.
   */
  public void setEventAttrStr(java.util.Map<CharSequence,CharSequence> value) {
    this.event_attr_str = value;
  }

  /** Creates a new WebEvent RecordBuilder */
  public static com.izhengyin.test.hadoop.avro.WebEvent.Builder newBuilder() {
    return new com.izhengyin.test.hadoop.avro.WebEvent.Builder();
  }
  
  /** Creates a new WebEvent RecordBuilder by copying an existing Builder */
  public static com.izhengyin.test.hadoop.avro.WebEvent.Builder newBuilder(com.izhengyin.test.hadoop.avro.WebEvent.Builder other) {
    return new com.izhengyin.test.hadoop.avro.WebEvent.Builder(other);
  }
  
  /** Creates a new WebEvent RecordBuilder by copying an existing WebEvent instance */
  public static com.izhengyin.test.hadoop.avro.WebEvent.Builder newBuilder(com.izhengyin.test.hadoop.avro.WebEvent other) {
    return new com.izhengyin.test.hadoop.avro.WebEvent.Builder(other);
  }
  
  /**
   * RecordBuilder for WebEvent instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<WebEvent>
    implements org.apache.avro.data.RecordBuilder<WebEvent> {

    private long id;
    private CharSequence event;
    private int user_id;
    private CharSequence type;
    private CharSequence uuid;
    private CharSequence utm_source;
    private CharSequence resolution;
    private CharSequence client_ip;
    private CharSequence user_agent;
    private CharSequence country;
    private CharSequence province;
    private CharSequence city;
    private CharSequence self_page;
    private CharSequence ref_page;
    private CharSequence session_id;
    private CharSequence event_date;
    private long event_time;
    private java.util.Map<CharSequence,Integer> event_attr_int;
    private java.util.Map<CharSequence,CharSequence> event_attr_str;

    /** Creates a new Builder */
    private Builder() {
      super(com.izhengyin.test.hadoop.avro.WebEvent.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.izhengyin.test.hadoop.avro.WebEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.event)) {
        this.event = data().deepCopy(fields()[1].schema(), other.event);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.user_id)) {
        this.user_id = data().deepCopy(fields()[2].schema(), other.user_id);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.type)) {
        this.type = data().deepCopy(fields()[3].schema(), other.type);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.uuid)) {
        this.uuid = data().deepCopy(fields()[4].schema(), other.uuid);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.utm_source)) {
        this.utm_source = data().deepCopy(fields()[5].schema(), other.utm_source);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.resolution)) {
        this.resolution = data().deepCopy(fields()[6].schema(), other.resolution);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.client_ip)) {
        this.client_ip = data().deepCopy(fields()[7].schema(), other.client_ip);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.user_agent)) {
        this.user_agent = data().deepCopy(fields()[8].schema(), other.user_agent);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.country)) {
        this.country = data().deepCopy(fields()[9].schema(), other.country);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.province)) {
        this.province = data().deepCopy(fields()[10].schema(), other.province);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.city)) {
        this.city = data().deepCopy(fields()[11].schema(), other.city);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other.self_page)) {
        this.self_page = data().deepCopy(fields()[12].schema(), other.self_page);
        fieldSetFlags()[12] = true;
      }
      if (isValidValue(fields()[13], other.ref_page)) {
        this.ref_page = data().deepCopy(fields()[13].schema(), other.ref_page);
        fieldSetFlags()[13] = true;
      }
      if (isValidValue(fields()[14], other.session_id)) {
        this.session_id = data().deepCopy(fields()[14].schema(), other.session_id);
        fieldSetFlags()[14] = true;
      }
      if (isValidValue(fields()[15], other.event_date)) {
        this.event_date = data().deepCopy(fields()[15].schema(), other.event_date);
        fieldSetFlags()[15] = true;
      }
      if (isValidValue(fields()[16], other.event_time)) {
        this.event_time = data().deepCopy(fields()[16].schema(), other.event_time);
        fieldSetFlags()[16] = true;
      }
      if (isValidValue(fields()[17], other.event_attr_int)) {
        this.event_attr_int = data().deepCopy(fields()[17].schema(), other.event_attr_int);
        fieldSetFlags()[17] = true;
      }
      if (isValidValue(fields()[18], other.event_attr_str)) {
        this.event_attr_str = data().deepCopy(fields()[18].schema(), other.event_attr_str);
        fieldSetFlags()[18] = true;
      }
    }
    
    /** Creates a Builder by copying an existing WebEvent instance */
    private Builder(com.izhengyin.test.hadoop.avro.WebEvent other) {
            super(com.izhengyin.test.hadoop.avro.WebEvent.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.event)) {
        this.event = data().deepCopy(fields()[1].schema(), other.event);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.user_id)) {
        this.user_id = data().deepCopy(fields()[2].schema(), other.user_id);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.type)) {
        this.type = data().deepCopy(fields()[3].schema(), other.type);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.uuid)) {
        this.uuid = data().deepCopy(fields()[4].schema(), other.uuid);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.utm_source)) {
        this.utm_source = data().deepCopy(fields()[5].schema(), other.utm_source);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.resolution)) {
        this.resolution = data().deepCopy(fields()[6].schema(), other.resolution);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.client_ip)) {
        this.client_ip = data().deepCopy(fields()[7].schema(), other.client_ip);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.user_agent)) {
        this.user_agent = data().deepCopy(fields()[8].schema(), other.user_agent);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.country)) {
        this.country = data().deepCopy(fields()[9].schema(), other.country);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.province)) {
        this.province = data().deepCopy(fields()[10].schema(), other.province);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.city)) {
        this.city = data().deepCopy(fields()[11].schema(), other.city);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other.self_page)) {
        this.self_page = data().deepCopy(fields()[12].schema(), other.self_page);
        fieldSetFlags()[12] = true;
      }
      if (isValidValue(fields()[13], other.ref_page)) {
        this.ref_page = data().deepCopy(fields()[13].schema(), other.ref_page);
        fieldSetFlags()[13] = true;
      }
      if (isValidValue(fields()[14], other.session_id)) {
        this.session_id = data().deepCopy(fields()[14].schema(), other.session_id);
        fieldSetFlags()[14] = true;
      }
      if (isValidValue(fields()[15], other.event_date)) {
        this.event_date = data().deepCopy(fields()[15].schema(), other.event_date);
        fieldSetFlags()[15] = true;
      }
      if (isValidValue(fields()[16], other.event_time)) {
        this.event_time = data().deepCopy(fields()[16].schema(), other.event_time);
        fieldSetFlags()[16] = true;
      }
      if (isValidValue(fields()[17], other.event_attr_int)) {
        this.event_attr_int = data().deepCopy(fields()[17].schema(), other.event_attr_int);
        fieldSetFlags()[17] = true;
      }
      if (isValidValue(fields()[18], other.event_attr_str)) {
        this.event_attr_str = data().deepCopy(fields()[18].schema(), other.event_attr_str);
        fieldSetFlags()[18] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public Long getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'id' field has been set */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'id' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'event' field */
    public CharSequence getEvent() {
      return event;
    }
    
    /** Sets the value of the 'event' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setEvent(CharSequence value) {
      validate(fields()[1], value);
      this.event = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'event' field has been set */
    public boolean hasEvent() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'event' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearEvent() {
      event = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'user_id' field */
    public Integer getUserId() {
      return user_id;
    }
    
    /** Sets the value of the 'user_id' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setUserId(int value) {
      validate(fields()[2], value);
      this.user_id = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'user_id' field has been set */
    public boolean hasUserId() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'user_id' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearUserId() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'type' field */
    public CharSequence getType() {
      return type;
    }
    
    /** Sets the value of the 'type' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setType(CharSequence value) {
      validate(fields()[3], value);
      this.type = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'type' field has been set */
    public boolean hasType() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'type' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearType() {
      type = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'uuid' field */
    public CharSequence getUuid() {
      return uuid;
    }
    
    /** Sets the value of the 'uuid' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setUuid(CharSequence value) {
      validate(fields()[4], value);
      this.uuid = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'uuid' field has been set */
    public boolean hasUuid() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'uuid' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearUuid() {
      uuid = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'utm_source' field */
    public CharSequence getUtmSource() {
      return utm_source;
    }
    
    /** Sets the value of the 'utm_source' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setUtmSource(CharSequence value) {
      validate(fields()[5], value);
      this.utm_source = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'utm_source' field has been set */
    public boolean hasUtmSource() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'utm_source' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearUtmSource() {
      utm_source = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'resolution' field */
    public CharSequence getResolution() {
      return resolution;
    }
    
    /** Sets the value of the 'resolution' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setResolution(CharSequence value) {
      validate(fields()[6], value);
      this.resolution = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'resolution' field has been set */
    public boolean hasResolution() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'resolution' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearResolution() {
      resolution = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'client_ip' field */
    public CharSequence getClientIp() {
      return client_ip;
    }
    
    /** Sets the value of the 'client_ip' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setClientIp(CharSequence value) {
      validate(fields()[7], value);
      this.client_ip = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'client_ip' field has been set */
    public boolean hasClientIp() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'client_ip' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearClientIp() {
      client_ip = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'user_agent' field */
    public CharSequence getUserAgent() {
      return user_agent;
    }
    
    /** Sets the value of the 'user_agent' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setUserAgent(CharSequence value) {
      validate(fields()[8], value);
      this.user_agent = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'user_agent' field has been set */
    public boolean hasUserAgent() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'user_agent' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearUserAgent() {
      user_agent = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the 'country' field */
    public CharSequence getCountry() {
      return country;
    }
    
    /** Sets the value of the 'country' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setCountry(CharSequence value) {
      validate(fields()[9], value);
      this.country = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'country' field has been set */
    public boolean hasCountry() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'country' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearCountry() {
      country = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /** Gets the value of the 'province' field */
    public CharSequence getProvince() {
      return province;
    }
    
    /** Sets the value of the 'province' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setProvince(CharSequence value) {
      validate(fields()[10], value);
      this.province = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the 'province' field has been set */
    public boolean hasProvince() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the 'province' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearProvince() {
      province = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    /** Gets the value of the 'city' field */
    public CharSequence getCity() {
      return city;
    }
    
    /** Sets the value of the 'city' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setCity(CharSequence value) {
      validate(fields()[11], value);
      this.city = value;
      fieldSetFlags()[11] = true;
      return this; 
    }
    
    /** Checks whether the 'city' field has been set */
    public boolean hasCity() {
      return fieldSetFlags()[11];
    }
    
    /** Clears the value of the 'city' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearCity() {
      city = null;
      fieldSetFlags()[11] = false;
      return this;
    }

    /** Gets the value of the 'self_page' field */
    public CharSequence getSelfPage() {
      return self_page;
    }
    
    /** Sets the value of the 'self_page' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setSelfPage(CharSequence value) {
      validate(fields()[12], value);
      this.self_page = value;
      fieldSetFlags()[12] = true;
      return this; 
    }
    
    /** Checks whether the 'self_page' field has been set */
    public boolean hasSelfPage() {
      return fieldSetFlags()[12];
    }
    
    /** Clears the value of the 'self_page' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearSelfPage() {
      self_page = null;
      fieldSetFlags()[12] = false;
      return this;
    }

    /** Gets the value of the 'ref_page' field */
    public CharSequence getRefPage() {
      return ref_page;
    }
    
    /** Sets the value of the 'ref_page' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setRefPage(CharSequence value) {
      validate(fields()[13], value);
      this.ref_page = value;
      fieldSetFlags()[13] = true;
      return this; 
    }
    
    /** Checks whether the 'ref_page' field has been set */
    public boolean hasRefPage() {
      return fieldSetFlags()[13];
    }
    
    /** Clears the value of the 'ref_page' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearRefPage() {
      ref_page = null;
      fieldSetFlags()[13] = false;
      return this;
    }

    /** Gets the value of the 'session_id' field */
    public CharSequence getSessionId() {
      return session_id;
    }
    
    /** Sets the value of the 'session_id' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setSessionId(CharSequence value) {
      validate(fields()[14], value);
      this.session_id = value;
      fieldSetFlags()[14] = true;
      return this; 
    }
    
    /** Checks whether the 'session_id' field has been set */
    public boolean hasSessionId() {
      return fieldSetFlags()[14];
    }
    
    /** Clears the value of the 'session_id' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearSessionId() {
      session_id = null;
      fieldSetFlags()[14] = false;
      return this;
    }

    /** Gets the value of the 'event_date' field */
    public CharSequence getEventDate() {
      return event_date;
    }
    
    /** Sets the value of the 'event_date' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setEventDate(CharSequence value) {
      validate(fields()[15], value);
      this.event_date = value;
      fieldSetFlags()[15] = true;
      return this; 
    }
    
    /** Checks whether the 'event_date' field has been set */
    public boolean hasEventDate() {
      return fieldSetFlags()[15];
    }
    
    /** Clears the value of the 'event_date' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearEventDate() {
      event_date = null;
      fieldSetFlags()[15] = false;
      return this;
    }

    /** Gets the value of the 'event_time' field */
    public Long getEventTime() {
      return event_time;
    }
    
    /** Sets the value of the 'event_time' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setEventTime(long value) {
      validate(fields()[16], value);
      this.event_time = value;
      fieldSetFlags()[16] = true;
      return this; 
    }
    
    /** Checks whether the 'event_time' field has been set */
    public boolean hasEventTime() {
      return fieldSetFlags()[16];
    }
    
    /** Clears the value of the 'event_time' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearEventTime() {
      fieldSetFlags()[16] = false;
      return this;
    }

    /** Gets the value of the 'event_attr_int' field */
    public java.util.Map<CharSequence,Integer> getEventAttrInt() {
      return event_attr_int;
    }
    
    /** Sets the value of the 'event_attr_int' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setEventAttrInt(java.util.Map<CharSequence,Integer> value) {
      validate(fields()[17], value);
      this.event_attr_int = value;
      fieldSetFlags()[17] = true;
      return this; 
    }
    
    /** Checks whether the 'event_attr_int' field has been set */
    public boolean hasEventAttrInt() {
      return fieldSetFlags()[17];
    }
    
    /** Clears the value of the 'event_attr_int' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearEventAttrInt() {
      event_attr_int = null;
      fieldSetFlags()[17] = false;
      return this;
    }

    /** Gets the value of the 'event_attr_str' field */
    public java.util.Map<CharSequence,CharSequence> getEventAttrStr() {
      return event_attr_str;
    }
    
    /** Sets the value of the 'event_attr_str' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder setEventAttrStr(java.util.Map<CharSequence,CharSequence> value) {
      validate(fields()[18], value);
      this.event_attr_str = value;
      fieldSetFlags()[18] = true;
      return this; 
    }
    
    /** Checks whether the 'event_attr_str' field has been set */
    public boolean hasEventAttrStr() {
      return fieldSetFlags()[18];
    }
    
    /** Clears the value of the 'event_attr_str' field */
    public com.izhengyin.test.hadoop.avro.WebEvent.Builder clearEventAttrStr() {
      event_attr_str = null;
      fieldSetFlags()[18] = false;
      return this;
    }

    public WebEvent build() {
      try {
        WebEvent record = new WebEvent();
        record.id = fieldSetFlags()[0] ? this.id : (Long) defaultValue(fields()[0]);
        record.event = fieldSetFlags()[1] ? this.event : (CharSequence) defaultValue(fields()[1]);
        record.user_id = fieldSetFlags()[2] ? this.user_id : (Integer) defaultValue(fields()[2]);
        record.type = fieldSetFlags()[3] ? this.type : (CharSequence) defaultValue(fields()[3]);
        record.uuid = fieldSetFlags()[4] ? this.uuid : (CharSequence) defaultValue(fields()[4]);
        record.utm_source = fieldSetFlags()[5] ? this.utm_source : (CharSequence) defaultValue(fields()[5]);
        record.resolution = fieldSetFlags()[6] ? this.resolution : (CharSequence) defaultValue(fields()[6]);
        record.client_ip = fieldSetFlags()[7] ? this.client_ip : (CharSequence) defaultValue(fields()[7]);
        record.user_agent = fieldSetFlags()[8] ? this.user_agent : (CharSequence) defaultValue(fields()[8]);
        record.country = fieldSetFlags()[9] ? this.country : (CharSequence) defaultValue(fields()[9]);
        record.province = fieldSetFlags()[10] ? this.province : (CharSequence) defaultValue(fields()[10]);
        record.city = fieldSetFlags()[11] ? this.city : (CharSequence) defaultValue(fields()[11]);
        record.self_page = fieldSetFlags()[12] ? this.self_page : (CharSequence) defaultValue(fields()[12]);
        record.ref_page = fieldSetFlags()[13] ? this.ref_page : (CharSequence) defaultValue(fields()[13]);
        record.session_id = fieldSetFlags()[14] ? this.session_id : (CharSequence) defaultValue(fields()[14]);
        record.event_date = fieldSetFlags()[15] ? this.event_date : (CharSequence) defaultValue(fields()[15]);
        record.event_time = fieldSetFlags()[16] ? this.event_time : (Long) defaultValue(fields()[16]);
        record.event_attr_int = fieldSetFlags()[17] ? this.event_attr_int : (java.util.Map<CharSequence,Integer>) defaultValue(fields()[17]);
        record.event_attr_str = fieldSetFlags()[18] ? this.event_attr_str : (java.util.Map<CharSequence,CharSequence>) defaultValue(fields()[18]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
