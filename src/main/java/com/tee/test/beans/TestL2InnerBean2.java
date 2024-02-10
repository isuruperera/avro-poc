package com.tee.test.beans;


import org.apache.avro.reflect.AvroDefault;
import org.apache.avro.reflect.Nullable;

import java.time.LocalDate;

public class TestL2InnerBean2 {

    @Nullable
    private LocalDate date1;

    @Nullable
    private Boolean testCondition;

    @Nullable
    private Integer testInt;

    @Nullable
    @AvroDefault("null")
    private Long test3Int = 0L;

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public Boolean getTestCondition() {
        return testCondition;
    }

    public void setTestCondition(Boolean testCondition) {
        this.testCondition = testCondition;
    }

    public Integer getTestInt() {
        return testInt;
    }

    public void setTestInt(Integer testInt) {
        this.testInt = testInt;
    }

    public Long getTest3Int() {
        return test3Int;
    }

    public void setTest3Int(Long test3Int) {
        this.test3Int = test3Int;
    }
}
