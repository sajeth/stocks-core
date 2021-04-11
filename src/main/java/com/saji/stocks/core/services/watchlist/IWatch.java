package com.saji.stocks.core.services.watchlist;

import java.math.BigDecimal;
import java.util.List;

public interface IWatch {
    public void createWatchList(final String symbol, final BigDecimal priceToBook);

    public List<String> listGoodStocks();
}
