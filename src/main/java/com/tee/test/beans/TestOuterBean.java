package com.tee.test.beans;

import com.tee.test.encoder.LocalDateEncoder;
import org.apache.avro.reflect.AvroEncode;
import org.apache.avro.reflect.Nullable;

import java.time.LocalDate;
import java.util.List;

public class TestOuterBean {

    private List<TestL1InnerBean> l1InnerBeanList;
    @Nullable
    private String prop1;
    @Nullable
    private String prop2;

    @Nullable
    @AvroEncode(using = LocalDateEncoder.class)
    private LocalDate testLD;

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public List<TestL1InnerBean> getL1InnerBeanList() {
        return l1InnerBeanList;
    }

    public void setL1InnerBeanList(List<TestL1InnerBean> l1InnerBeanList) {
        this.l1InnerBeanList = l1InnerBeanList;
    }

    public LocalDate getTestLD() {
        return testLD;
    }

    public void setTestLD(LocalDate testLD) {
        this.testLD = testLD;
    }
}
