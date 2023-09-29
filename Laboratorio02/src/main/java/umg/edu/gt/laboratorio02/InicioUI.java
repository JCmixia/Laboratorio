import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
    private boolean band;
    private String band2;
    //sprivate band ;

    /**
     * @return the mensaje
     */
    @PostConstruct
    public void init(){
        setMensaje("Hola mundo, mi primer comentario web con jsf2");
      
    }
   
    public void mensaje1(){
        mensaje = "Hola mundo desde un metodo";
        
       // setMensaje("Hola mundo desde un metodo ------>");
    }
    
    
    /**
     * @param mensaje the mensaje to set
     */
    public void motrarDatos(){
       
        System.out.println("Dato 1: "+getDato1());
        System.out.println("Dato 2: "+getDato2());
        System.out.println("Nombre: "+getDato1()+" Apellido: "+getDato2());
        setDato3("Nombre: " + getDato1() + " Apellido: " + getDato2());

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

}
