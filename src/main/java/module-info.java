/**
 * @author saji 06-Apr-2018
 */
module stocks.database.connector {
    requires transitive stocks.entities;
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
    requires java.annotation;
    exports com.saji.stocks.core.config to stocks.batch, stocks.data, stocks.kite, stocks.configurations;
    exports com.saji.stocks.core.repository.transactions to stocks.batch;
    exports com.saji.stocks.core.repository.data to stocks.data;
    exports com.saji.stocks.core.repository.kite to stocks.kite;
    exports com.saji.stocks.core.repository.configurations to stocks.configurations;
    opens com.saji.stocks.core.config to spring.core, spring.beans, spring.context;
    opens com.saji.stocks.core.repository.transactions to spring.core, spring.data.commons;
    opens com.saji.stocks.core.repository.configurations to spring.core, spring.data.commons;
    opens com.saji.stocks.core.repository.kite to spring.core, spring.data.commons;
    opens com.saji.stocks.core.repository.data to spring.core, spring.data.commons;


}