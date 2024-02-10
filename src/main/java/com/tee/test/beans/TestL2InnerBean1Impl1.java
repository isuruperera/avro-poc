package com.tee.test.beans;

import org.apache.avro.reflect.Nullable;

import java.util.Date;

public class TestL2InnerBean1Impl1 extends TestL2InnerBean1Base {

    public TestL2InnerBean1Impl1() {

    }

    @Nullable
    private Date testDate;

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }
}
