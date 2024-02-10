package com.tee.test.beans;

import org.apache.avro.reflect.Nullable;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TestL1InnerBean {

    public TestL1InnerBean() {

    }

    private Set<TestL2InnerBean1Base> l2InnerBean1Set;

    @Nullable
    private BigDecimal testBD;

    @Nullable
    private String testStr;

    @Nullable
    private TestL2InnerBean2 l2InnerBean2;

    public Set<TestL2InnerBean1Base> getL2InnerBean1Set() {
        return l2InnerBean1Set;
    }

    public void setL2InnerBean1Set(HashSet<TestL2InnerBean1Base> l2InnerBean1Set) {
        this.l2InnerBean1Set = l2InnerBean1Set;
    }

    public BigDecimal getTestBD() {
        return testBD;
    }

    public void setTestBD(BigDecimal testBD) {
        this.testBD = testBD;
    }

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public TestL2InnerBean2 getL2InnerBean2() {
        return l2InnerBean2;
    }

    public void setL2InnerBean2(TestL2InnerBean2 l2InnerBean2) {
        this.l2InnerBean2 = l2InnerBean2;
    }
}
