package minitienda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class conexionBD {
    
    public static Connection conectar(){
        
      Connection conexion = null;
      
      try{
          
          String url = "jdbc:mysql://localhost:3306/tienda_doña_luz?useSSL=false&serverTimezone=UTC";
          String user = "root";
          String password = "root12345678";
          
          conexion = DriverManager.getConnection(url,user,password);
          System.out.println("[info] ¡Conexion exitosa a la base de datos de MySQL!");
      
      }catch (SQLException e){
      
          System.out.println("[ERROR] No se pudo conectar a la base de datos: " + e.getMessage()); 
         
      }
      
      return conexion;  
    
    }
   
public class OperacionesBD {

    public static void registrarVenta(String nombreCliente, int cantidad, double total, String obsequio) {
        
        String sql = "INSERT INTO ventas (nombre_cliente, cantidad_productos, total_compra, obsequio) VALUES (?, ?, ?, ?)";
        
        try (Connection conexion = conexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
                       
            ps.setString(1, nombreCliente); 
            ps.setInt(2, cantidad);         
            ps.setDouble(3, total); 
            ps.setString(4, obsequio);
            
            ps.executeUpdate();
            System.out.println("[info] ¡Venta registrada y guardada en la base de datos con éxito!");
            
        } catch (SQLException e) {
            System.out.println("[ERROR al registrar la venta]: " + e.getMessage());
        }
    }
}
}
