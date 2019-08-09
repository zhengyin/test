package com.izhengyin.test.hadoop.avro;

import java.util.List;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019/4/25 11:49 AM
 */
public class AvroSchemaBean {
    private String namespace;
    private String type;
    private String name;
    private String doc;
    private List<Fields> fields;
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    public String getNamespace() {
        return namespace;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
    public String getDoc() {
        return doc;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }
    public List<Fields> getFields() {
        return fields;
    }

    public static class Fields {

        private String name;
        private List<String> type;
        public Fields(){

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}

