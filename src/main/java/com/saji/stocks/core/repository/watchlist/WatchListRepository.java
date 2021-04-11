package com.saji.stocks.core.repository.watchlist;

import com.saji.stocks.entities.watchlist.WatchlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepository extends JpaRepository<WatchlistEntity, String> {

    @Query("from WatchlistEntity order by priceToBook")
    List<WatchlistEntity> findGoodStocks();
}
