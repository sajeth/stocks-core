package com.saji.stocks.core.services.watchlist;

import com.saji.stocks.core.repository.watchlist.WatchListRepository;
import com.saji.stocks.entities.watchlist.WatchlistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListServiceImpl implements IWatch {

    @Autowired
    private WatchListRepository watchListRepository;

    @Override
    public void createWatchList(String symbol, BigDecimal priceToBook) {
        WatchlistEntity watchlist = new WatchlistEntity();
        watchlist.setSymbol(symbol);
        watchlist.setPriceToBook(priceToBook);
        watchlist.setLogicalDelIn("N");
        watchListRepository.save(watchlist);
    }

    @Override
    public List<String> listGoodStocks() {
        return watchListRepository.findGoodStocks().stream()
                .distinct().map(val -> val.getSymbol()).collect(Collectors.toList());
    }
}
