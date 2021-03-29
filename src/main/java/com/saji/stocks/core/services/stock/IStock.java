package com.saji.stocks.core.services.stock;

import com.saji.stocks.core.services.pojos.StockTAO;

import java.util.List;

/**
 * @author saji 12-Sep-2018
 */
public interface IStock {

    void updateStock(final StockTAO stock);

    StockTAO findStockBySymbol(final String symbol);

    List<String> listNewStocks();

    List<StockTAO> listAllStocks();

    List<String> listGoodStocks();

    List<StockTAO> listAllActiveStocks();

    List<String> findDailyStocks();

    List<String> findWeeklyStocks();

    List<String> findMonthlyStocks();

    List<String> find3MonthlyStocks();

    void deleteStock(final String symbol);

    String createStock(final StockTAO pojo);

    void createWatchList(final String symbol);

}
