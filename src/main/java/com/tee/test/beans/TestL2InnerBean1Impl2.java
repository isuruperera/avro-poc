package com.tee.test.beans;

import org.apache.avro.reflect.Nullable;

public class TestL2InnerBean1Impl2 extends TestL2InnerBean1Base {

    public TestL2InnerBean1Impl2() {

    }

    @Nullable
    private Long fish;

    public Long getFish() {
        return fish;
    }

    public void setFish(Long fish) {
        this.fish = fish;
    }
}
