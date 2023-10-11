/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Isai
 */
@Entity
@Table(name = "clientes")
public class clientes implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id", columnDefinition  = "INT")
    private String idCliente;
   
    @Column(name = "nombre", columnDefinition  = "VARCHAR(255)")
    private String nombre;
    
    @Column(name = "correo", columnDefinition  = "VARCHAR(255)")
    private String correo;
        
    @Column(name = "direccion", columnDefinition  = "VARCHAR(255)")
    private String direccion;
    
    @Column(name = "telefono", columnDefinition  = "VARCHAR(255)")
    private String telefono;
     
    public clientes(){
        
    }
}
