package com.mycompany.proyectojoobyuno.app.model;

/**
 *
 * @author Irais
 */
public class Response {
    
    private final int id;
    private final String data;

    public Response(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }
    
}
