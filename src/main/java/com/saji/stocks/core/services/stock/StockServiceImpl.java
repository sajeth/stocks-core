package com.saji.stocks.core.services.stock;

import com.saji.stocks.core.repository.stocks.StockRepository;
import com.saji.stocks.core.services.pojos.StockTAO;
import com.saji.stocks.entities.stock.StockEntity;
import com.saji.stocks.redis.constants.DateConstant;
import com.saji.stocks.redis.services.IRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author saji 12-Sep-2018
 */
@Service
public class StockServiceImpl implements IStock {
    @Autowired
    IRedis iRedis;
    @Autowired
    private StockRepository stockRepository;



    @Override
    public void updateStock(StockTAO stock) {
        stockRepository.updateStock(stock.getStockId(), stock.getExchange(), stock.getIndustry(), stock.getSector());
    }

    @Override
    public StockTAO findStockBySymbol(String symbol) {

        return new StockTAO(stockRepository.findStockBySymbol(symbol));
    }

    @Override
    public List<StockTAO> listAllActiveStocks() {

        return stockRepository.findActiveStocks().stream()
                .filter(val -> val.getLogicalDelIn().equalsIgnoreCase("N"))
                .map(val -> new StockTAO(val)).collect(Collectors.toList());
    }

    @Override
    public List<String> findDailyStocks() {
        return findStocks(DateConstant.D);
    }


    private List<String> findStocks(DateConstant date) {
        return listAllActiveStocks().stream()
                .filter(val ->
                        iRedis.findCache(val.getSymbol().concat(":").concat(date.name()))
                )
                .map(StockTAO::getSymbol).collect(Collectors.toList());

    }

    @Override
    public List<String> findWeeklyStocks() {

        return findStocks(DateConstant.W);
    }


    @Override
    public List<String> findMonthlyStocks() {
        return findStocks(DateConstant.M);

    }

    @Override
    public List<String> find3MonthlyStocks() {
        return findStocks(DateConstant.M3);
    }

    @Override
    public void deleteStock(String symbol) {
        stockRepository.deleteStock(symbol);
    }

    @Override
    public String createStock(StockTAO pojo) {
        String message = "Stock is already existing";

        StockEntity existingEntity = stockRepository.findStockBySymbol(pojo.getSymbol());

        if (null == existingEntity || iRedis.isStockNew(pojo.getSymbol())) {
            StockEntity newEntity = new StockEntity();
         //   newEntity.setExchange(pojo.getExchange());
            newEntity.setIndustry(pojo.getIndustry());
            newEntity.setSector(pojo.getSector());
            newEntity.setSymbol(pojo.getSymbol());
            stockRepository.save(newEntity);
            message = "Stock Added successfully";
        } else if (existingEntity.getLogicalDelIn().equalsIgnoreCase("Y")) {

            stockRepository.updateStock(existingEntity.getStockId(), pojo.getExchange(), pojo.getIndustry(), pojo.getSector());
            message = "Stock Updated successfully";
        }
        return message;
    }



}
