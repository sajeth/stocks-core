package com.saji.stocks.core.repository.stocks;

import com.saji.stocks.entities.stock.StockEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    @Query("from StockEntity where logicalDeleteIn='N'")
    List<StockEntity> listNewStocks();

    @Query("from StockEntity where symbol=?1 and logicalDeleteIn='N'")
    StockEntity findStockBySymbol(final String symbol);


    @Query("from StockEntity where logicalDeleteIn='N'")
    List<StockEntity> findActiveStocks();

    @Query("from StockEntity where logicalDeleteIn='N'")
    List<StockEntity> findAllActiveStocks(Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update StockEntity set exchange=?2,industry=?3,sector=?4,logicalDeleteIn='N' where stockId=?1")
    void updateStock(final Long stockId, final String exchange, final String industry, final String sector);

    @Transactional
    @Modifying
    @Query("update StockEntity set logicalDeleteIn='Y' where symbol=?1")
    void deleteStock(final String stockId);

}
