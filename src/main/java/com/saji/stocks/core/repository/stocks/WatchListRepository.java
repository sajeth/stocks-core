package com.saji.stocks.core.repository.stocks;

import com.saji.stocks.entities.watchlist.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepository extends JpaRepository<Watchlist, String> {

    @Query("from Watchlist order by priceToBook")
    List<Watchlist> findGoodStocks();
}
