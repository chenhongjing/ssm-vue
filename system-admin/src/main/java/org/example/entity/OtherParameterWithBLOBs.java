package org.example.entity;

public class OtherParameterWithBLOBs extends OtherParameter {
    private String paramData;

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}