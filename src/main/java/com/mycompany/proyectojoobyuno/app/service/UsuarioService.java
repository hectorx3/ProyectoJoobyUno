package com.mycompany.proyectojoobyuno.app.service;

import com.mycompany.proyectojoobyuno.app.dao.Usuario;
import java.util.Optional;

public class UsuarioService {
    
    public int addUser(
            final Optional<Integer> id,
            final Optional<String> nombre,
            final Optional<String> correo
    ){
        
        if(id.isPresent() && nombre.isPresent() && correo.isPresent()){ 
                final int nuevoUsuario = new Usuario().insertUser(
                    id.filter(x -> x>0).orElse(-999999999), 
                    nombre.filter(x -> x.length()>0).get(), 
                    correo.filter(x -> x.length()>0).get()
                );
                return nuevoUsuario;
        }else {
            return 0;
        }
    }
    
}
