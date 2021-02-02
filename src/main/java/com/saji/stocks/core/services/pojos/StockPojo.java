package com.saji.stocks.core.services.pojos;

public class StockPojo {
    private Long stockId;
    private String symbol;
    private String exchange;
    private String sector;
    private String industry;

    public StockPojo(String symbol, String exchange, String sector, String industry) {
        this.symbol = symbol;
        this.exchange = exchange;
        this.sector = sector;
        this.industry = industry;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
