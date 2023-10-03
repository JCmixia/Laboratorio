/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import umg.edu.gt.DTO.DatosDTO;

/**
 *
 * @author Isai
 */
public class ConsultasDAO{
    
    ConexionDAO con = new ConexionDAO();
    
    public List<DatosDTO> findAllCliente() throws Exception{
        List<DatosDTO> Lista = new ArrayList<DatosDTO>();
       
        try{
            String query="SELECT id, nombre, correo, direccion, telefono FROM clientes";
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                DatosDTO dato = new DatosDTO();
                dato.setId(r.getLong("id"));
                dato.setNombre(r.getString("nombre"));
                dato.setDireccion(r.getString("direccion"));
                dato.setCorreo(r.getString("correo"));
                dato.setTelefono(r.getString("telefono"));
                Lista.add(dato);
            }          
        } catch(Exception e){
            System.out.println("Error al realizar la consulta");
        }finally {
            if (con != null) {
                try {
                   con.conexionMysql().close();
                   System.out.println("Cierre de conexion exitosa");
                }catch(SQLException ex){
                    System.out.println("Error al cerrar conexion");
                }
            }  
        } 
        
        System.out.println("El tamaño de la lista" + Lista.size());
        return Lista;
    }
    
    public void insertar(DatosDTO cliente){
  
        try{
            String query="INSERT INTO clientes VALUES ("+cliente.getId()+",'"+cliente.getNombre()+"','"+cliente.getCorreo()+"','"+cliente.getDireccion()+"','"+cliente.getTelefono()+"')";
            //String query="INSERT INTO clientes VALUES ('"+cliente.getNombre()+"','"+cliente.getCorreo()+"','"+cliente.getDireccion()+"','"+cliente.getTelefono()+"')";
            //String query = "INSERT INTO clientes VALUES (6, 'isai', 'isaimixia18@gmail.com','Santa Luxia', '48407205')";  
            Statement s = con.conexionMysql().createStatement();
            s.executeUpdate(query);
            
            System.out.println("-------------------Datos Insertados--------------------------------");
            System.out.println("Id: "+cliente.getId()+"Nombre: "+cliente.getNombre()+"Correo: "+cliente.getCorreo()+"Direccion: "+cliente.getDireccion()+"Telefono: "+cliente.getTelefono());
            System.out.println("---------------------------------------------------");
              
        } catch(Exception e){
            System.out.println("Error al realizar la insercion");
        }
    }
    
    
    public void actualizar(DatosDTO cliente){
  
        try{
            //String query="INSERT INTO clientes VALUES ("+pDatos.getId()+",'"+pDatos.getNombre()+"','"+pDatos.getCorreo()+"','"+pDatos.getDireccion()+"','"+pDatos.getTelefono()+"')";
            String query = "UPDATE clientes SET ";

            if (cliente.getNombre() != null && !cliente.getNombre().isEmpty()) {
                query += "nombre='" + cliente.getNombre() + "', ";
            } else {
                query += "nombre=nombre, "; // Keep the existing value
            }

            if (cliente.getCorreo() != null && !cliente.getCorreo().isEmpty()) {
                query += "correo='" + cliente.getCorreo() + "', ";
            } else {
                query += "correo=correo, "; // Keep the existing value
            }

            if (cliente.getDireccion() != null && !cliente.getDireccion().isEmpty()) {
                query += "direccion='" + cliente.getDireccion() + "', ";
            } else {
                query += "direccion=direccion, "; // Keep the existing value
            }

            if (cliente.getTelefono() != null && !cliente.getTelefono().isEmpty()) {
                query += "telefono='" + cliente.getTelefono() + "', ";
            } else {
                query += "telefono=telefono, "; // Keep the existing value
            }
            
            if (query.endsWith(", ")) {
                query = query.substring(0, query.length() - 2);
            }

            query += " WHERE id='" + cliente.getId() + "'";

            Statement s = con.conexionMysql().createStatement();
            s.executeUpdate(query);
            
            System.out.println("---------------Datos Actualizados------------------------------------");
            System.out.println("Id: "+cliente.getId()+"Nombre: "+cliente.getNombre()+"Correo: "+cliente.getCorreo()+"Direccion: "
                    +cliente.getDireccion()+"Telefono: "+cliente.getTelefono());
            System.out.println("---------------------------------------------------");
              
        } catch(Exception e){
            System.out.println("Error al realizar la actualizacion");
        }
    }
    
    public void eliminar(DatosDTO cliente) throws Exception {
       
        try {
            
            String query = "DELETE FROM clientes WHERE id ="+cliente.getId();
            Statement s = con.conexionMysql().createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            throw new SQLException("Error al eliminar el cliente: " + ex.getMessage());
        }
    }
} 
     //Tener archiva listo
        //SonarQB si es que consigue una version gratis
        //Investigar que es ORM
        //Hibernet, JPA
    //JRebel
    //Como persistir en hibernet


