package com.saji.stocks.core.dto;

public class Stock {
    private long id;

    public Stock(long id) {
        this.id = id;
    }

    public Stock() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
