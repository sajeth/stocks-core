package com.saji.stocks.core.services.stock;

import com.saji.stocks.core.services.pojos.StockPojo;
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

    List<StockTAO> listAllActiveStocks();

    List<String> findDailyStocks();

    List<String> findWeeklyStocks();

    List<String> findMonthlyStocks();

    void deleteStock(final String symbol);

    String createStock(final StockPojo pojo);

}
