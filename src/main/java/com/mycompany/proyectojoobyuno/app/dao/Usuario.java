package com.mycompany.proyectojoobyuno.app.dao;

import com.mycompany.proyectojoobyuno.core.MyPoolConnection;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;

public class Usuario {
    
    public int insertUser(
            final int id,
            final String nombre,
            final String correo
    ){
        final DataSource ds = MyPoolConnection.getInstance();
        final Jdbi jdbi = Jdbi.create(ds);
        final int users = jdbi.withHandle(handle -> {
            handle
                .createUpdate("insert into pruebauno.usuario(id, nombre, correo) values(?, ?, ?)")
                .bind(0, id)
                .bind(1, nombre)
                .bind(2, correo)
                .execute();

            return 1;
        });
        return users;
    }
    
}
