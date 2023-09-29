/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.laboratorio;



import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.sql.SQLException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.net.http.HttpClient;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import umg.edu.gt.DAO.ConexionDAO;
import umg.edu.gt.DAO.ConsultasDAO;
import umg.edu.gt.DTO.DatosDTO;

/**
 *
 * @author soporte
 */
//esto es una notacion, para llamarlo dedede el xhtml
@ManagedBean(name="bkn_Inicio")
public class InicioUI implements Serializable{

    private String mensaje;
    
    private String dato1;
    private String dato2;
    private String dato3;
    private String dato4;
    private String dato5;
    
    private boolean band;
    private List<DatosDTO> lista = new ArrayList<DatosDTO>();
    
   
    //sprivate band ;

    /**
     * @return the mensaje
     */
    @PostConstruct
    public void init(){
        //setMensaje("Hola mundo, mi primer comentario web con jsf2"); 
        
        ConexionDAO con = new ConexionDAO();
        ConsultasDAO consulta = new ConsultasDAO();
        
        
        try{
            
            setLista(consulta.findAllCliente());
            System.out.println("la conexion es: " + con.conexionMysql());
            System.out.println("La lista es: " + getLista().size());
            System.out.println("El nombre es: " + getLista().get(0).getNombre());
            
        }catch(Exception ex){
            System.out.println("Error de la conexion" + ex);
        }
        
         this.mensaje1();
         
        /*
         try{
             this.consumeWS();
             
         }catch(Exception ex){
             System.out.println("Error al consumir el WS");
         }
       */
    }
   
    public void mensaje1(){
        mensaje = "Bienvenidos a mi pagina web";
       // setMensaje("Hola mundo desde un metodo ------>");
    }
   
    /**
     * @param mensaje the mensaje to set
     */
    public void motrarDatos() throws Exception{ 
        
        ConexionDAO con = new ConexionDAO();
        ConsultasDAO consulta = new ConsultasDAO();
        DatosDTO datos = new DatosDTO();
        
        //datos.setId(Long.parseLong(dato1));
        datos.setNombre(dato2);
        datos.setCorreo(dato3);
        datos.setDireccion(dato4);
        datos.setTelefono(dato5);
        
        
        try{
            consulta.insertar(datos);
            setLista(consulta.findAllCliente());
            System.out.println("la conexion es: " + con.conexionMysql());
            System.out.println("La lista es: " + getLista().size());
            System.out.println("El nombre es: " + getLista().get(0).getNombre());
            
        }catch(Exception ex){
            System.out.println("Error de la conexion" + ex);
        }finally {
            if (con != null) {
                try {
                   con.conexionMysql().close();
                   System.out.println("Cierre de conexion exitosa");
                }catch(SQLException ex){
                    System.out.println("Error al cerrar conexion" + ex.getMessage());
                }
            }  
        } 
        /*
        
        System.out.println("Dato 1: "+getDato1());
        System.out.println("Dato 2: "+getDato2());
        System.out.println("Nombre: "+getDato1()+" Apellido: "+getDato2());
        setDato3("Nombre: " + getDato1() + " Apellido: " + getDato2());      
        */
        
        /*
        try{
            this.consumeWS();
        }catch(Exception e){
            System.out.println("Erro al consumir el WS");
       
        }
        */
        
        
        
    }
    
     /**
     * @return the band
     */
    public boolean isBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(boolean band) {
        this.band = band;
    }
    /**
     * @param mensaje the mensaje to set
     */
    
    public String getMensaje() {
        return mensaje;
    }
     
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the dato1
     */
    public String getDato1() {
        return dato1;
    }

    /**
     * @param dato1 the dato1 to set
     */
    public void setDato1(String dato1){
        this.dato1 = dato1;
    }

    /**
     * @return the dato2
     */
    public String getDato2() {
        return dato2;
    }

    /**
     * @param dato2 the dato2 to set
     */
    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    /**
     * @return the dato3
     */
    public String getDato3() {
        return dato3;
    }

    /**
     * @param dato3 the dato3 to set
     */
    public void setDato3(String dato3) {
        this.dato3 = dato3;
    }
    
        /**
     * @return the lista
     */
    public List<DatosDTO> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<DatosDTO> lista) {
        this.lista = lista;
    }
    
    /**
     * @return the dato4
     */
    public String getDato4() {
        return dato4;
    }

    /**
     * @param dato4 the dato4 to set
     */
    public void setDato4(String dato4) {
        this.dato4 = dato4;
    }
    
    /**
     * @return the dato5
     */
    public String getDato5() {
        return dato5;
    }

    /**
     * @param dato5 the dato5 to set
     */
    public void setDato5(String dato5) {
        this.dato5 = dato5;
    }
    
    public void consumeWS() throws IOException, InterruptedException{
        /*
        try{
         //URL url = new URL("http://localhost:8080/LabWS/webresources/DesarrolloWeb/primerWS");
         URL url = new URL("http://localhost:8080/LabWS/webresources/DesarrolloWeb/primerWS");
         HttpURLConnection conn =(HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.connect();
         
         int responseCode = conn.getResponseCode();
         if(responseCode !=200){
             throw new RuntimeException("Ocurrio un error: "+  responseCode);
         }else{
             
             StringBuilder informationString = new StringBuilder();
             Scanner scanner = new Scanner(url.openStream());
             
             while(scanner.hasNext()){
                 informationString.append(scanner.nextLine());
             }
             
             scanner.close();
             
             System.out.println(informationString);
         }
        }catch(Exception e){
            e.printStackTrace();
        }
        */
        /*
        try{
           
        } catch(Exception e){
            e.printStackTrace();
        }
        */
        
        String url= "http://localhost:8090/LabWS/webresources/DesarrolloWeb/primerWS";
               
        HttpClient client = HttpClient.newHttpClient();
        
        // Crear una solicitud GET sin parámetros
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Imprimir el cuerpo de la respuesta
        System.out.println(response.body());
        
        // Crear una solicitud POST con parámetros
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
               .build();
        
        // Enviar la solicitud y obtener la respuesta
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Imprimir el cuerpo de la respuesta
        System.out.println("respuesta: " + response.body());
                
        
        //JsonParser parser = new JsonParser();        
        
        
        //JsonArray jsonArray = JsonParser.parseString(response.body()).getAsJsonArray();
        
       
       
       // JsonArray jsonArray = parser.parse(response.body()).getAsJsonArray();
        /*
        // Ahora puedes trabajar con el objeto JSON de tipo array
        for (int i = 0; i < jsonArray.size(); i++) {
            String nombre = jsonArray.get(i).getAsJsonObject().get("name").getAsString();
            String link = jsonArray.get(i).getAsJsonObject().get("url").getAsString();
        
           
            System.out.println("Elemento " + i + ":");
            System.out.println("Nombre: " + nombre);
            System.out.println("Edad: " + link);
        }
        */
    }

}