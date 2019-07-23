package com.mycompany.proyectojoobyuno;

import com.mycompany.proyectojoobyuno.app.controller.UsuarioController;
import com.mycompany.proyectojoobyuno.core.MyPoolConnection;
import javax.sql.DataSource;
import org.jooby.Jooby;
import org.jooby.thymeleaf.Thl;

public class App extends Jooby {
    
  {  
    use(new Thl());
    initPool();
    get("/", UsuarioController.home);
    post("/add", UsuarioController.agregar); 
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }
  
  public void initPool(){
      try {
          final DataSource ds = MyPoolConnection.getInstance();
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }
  
}
