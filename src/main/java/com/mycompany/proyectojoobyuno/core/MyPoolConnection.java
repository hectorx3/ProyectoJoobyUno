package com.mycompany.proyectojoobyuno.core;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class MyPoolConnection {
    
    private static HikariDataSource hds;

    private MyPoolConnection() {
        Config conf = ConfigFactory.load();
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(conf.getString("db.url"));
        config.setDriverClassName(conf.getString("db.driver"));
        config.setUsername(conf.getString("db.user"));
        config.setPassword(conf.getString("db.password"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTestQuery("select * from pruebauno.usuario");
        config.setMaximumPoolSize(20);

        hds = new HikariDataSource(config);
        
        try {
            System.out.println(hds.getConnection().isClosed());
            System.out.println(hds.getConnection().isValid(10));
        } catch (SQLException ex) {
            Logger.getLogger(MyPoolConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static DataSource getInstance(){
        if(hds!=null) return hds;
        return new MyPoolConnection().hds;
    }
    
    public static void closed(){
        if(hds!=null) hds.close();
    }
    
}
