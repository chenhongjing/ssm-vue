package org.example.entity;

public class DynamicParameterWithBLOBs extends DynamicParameter {
    private String paramData;

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}