package com.ang.frontui.bean;

public class UserMeasure {

    private Long id;
    private String measureName;
    private String measureDef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

    public String getMeasureDef() {
        return measureDef;
    }

    public void setMeasureDef(String measureDef) {
        this.measureDef = measureDef;
    }
}
