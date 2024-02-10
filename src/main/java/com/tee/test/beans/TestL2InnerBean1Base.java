package com.tee.test.beans;

import org.apache.avro.reflect.Nullable;
import org.apache.avro.reflect.Union;

@Union({TestL2InnerBean1Impl1.class, TestL2InnerBean1Impl2.class})
public abstract class TestL2InnerBean1Base {

    @Nullable
    private Double testNum;

    @Nullable
    private Long testLng;

    public Double getTestNum() {
        return testNum;
    }

    public void setTestNum(Double testNum) {
        this.testNum = testNum;
    }

    public Long getTestLng() {
        return testLng;
    }

    public void setTestLng(Long testLng) {
        this.testLng = testLng;
    }
}
