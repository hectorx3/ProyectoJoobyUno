package com.mycompany.proyectojoobyuno.app.controller;

import com.google.gson.Gson;
import com.mycompany.proyectojoobyuno.app.dao.Usuario;
import com.mycompany.proyectojoobyuno.app.model.Response;
import com.mycompany.proyectojoobyuno.app.service.UsuarioService;
import com.mycompany.proyectojoobyuno.core.MyPoolConnection;
import java.util.Optional;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jooby.Results;
import org.jooby.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsuarioController {
    
    private static Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
    private static final Gson g = new Gson();
    
    public static Route.ZeroArgHandler home = () -> {
        return Results.html("index")
                .put("model", "asdlñakjsñdlkj añlaksjd lkasjd");
    };
    
    public static Route.Handler agregar = (req, rsp) -> {
        rsp.type("application/json");
        final Optional<String> id = req.param("id").toOptional();
        final Optional<String> name = req.param("name").toOptional();
        final Optional<String> email = req.param("email").toOptional();
        
        try{
            final int add = new UsuarioService().addUser(
                    id.filter(x -> x.length()>0).map(Integer::parseInt), 
                    name, 
                    email
            );
            
            if(add==1) {
                final String res = g.toJson(new Response(0, "Agregado correctamente"));
                rsp.status(200).send(res);
            }
            
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
            if(e.getMessage().contains("llave duplicada")){
                LOG.error("Llave dupliada");
            }
            final String res = g.toJson(new Response(1, "Error al agregar"));
            rsp.status(200).send(res);
        }
    };
    
}
