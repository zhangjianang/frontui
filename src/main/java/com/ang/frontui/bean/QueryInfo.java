package com.ang.frontui.bean;

import java.util.List;

public class QueryInfo {
    private List<QueryItem> measure;
    private List<QueryItem> dimension;

    public List<QueryItem> getMeasure() {
        return measure;
    }

    public void setMeasure(List<QueryItem> measure) {
        this.measure = measure;
    }

    public List<QueryItem> getDimension() {
        return dimension;
    }

    public void setDimension(List<QueryItem> dimension) {
        this.dimension = dimension;
    }
}
