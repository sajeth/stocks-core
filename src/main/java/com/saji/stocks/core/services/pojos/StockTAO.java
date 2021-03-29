package com.saji.stocks.core.services.pojos;

import com.saji.stocks.entities.stock.StockEntity;

public class StockTAO {

    private Long stockId;

    private String symbol;

    private String exchange;

    private String sector;

    private String industry;

    public StockTAO(final StockEntity entity) {

        populateData(entity);

    }

    private void populateData(final StockEntity entity) {
        this.exchange = entity.getExchange();
        this.symbol = entity.getSymbol();
        this.industry = entity.getIndustry();
        this.sector = entity.getSector();
        this.stockId = entity.getStockId();
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(final Long stockId) {
        this.stockId = stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;

    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(final String exchange) {
        this.exchange = exchange;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(final String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(final String industry) {
        this.industry = industry;
    }

}
