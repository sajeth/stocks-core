/**
 * @author saji 06-Apr-2018
 */
module stocks.core {
    requires transitive stocks.entities;
    requires transitive stocks.redis;
    requires transitive stocks.analysis;
    requires transitive stocks.candlesticks;
    requires spring.data.jpa;
    requires spring.orm;
    requires spring.data.commons;
    requires spring.context;
    requires spring.tx;
    requires spring.jdbc;
    requires spring.beans;
    requires spring.core;
    requires java.logging;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires com.zaxxer.hikari;
    exports com.saji.stocks.core.repository.batch to stocks.batch;
    exports com.saji.stocks.core.services.stock to stocks.batch, stocks.services, stocks.mongo;
    exports com.saji.stocks.core.services.pojos to stocks.services, stocks.batch, stocks.mongo;
    exports com.saji.stocks.core.dto to stocks.batch, stocks.services;
    exports com.saji.stocks.core.config to stocks.batch, stocks.services;
    opens com.saji.stocks.core.config to spring.core, spring.beans, spring.context;
    opens com.saji.stocks.core.services.stock to spring.core, spring.beans, spring.context;
    opens com.saji.stocks.core.repository.batch to spring.core, spring.data.commons;

}