package com.saji.stocks.core;

import com.saji.stocks.core.config.CoreConfig;
import com.saji.stocks.core.services.stock.IStock;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CoreConfig.class, CacheConfig.class);
        IStock iStock = context.getBean(IStock.class);
        iStock.findDailyStocks().forEach(val -> System.out.println(val));
    }

}
