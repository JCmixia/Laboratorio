/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.DTO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Isai
 */
@Entity
@Table(name = "detalles_ordenes")
public class Detalle_orden implements Serializable {

   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "orden_id")
    private Long orden_id;

    @Column(name = "producto_id")
    private Long producto_id;

    @Column(name = "cantidad")
    private Long cantidad;

    @Column(name = "precio")
    private Long precio;

    public Detalle_orden() {

    }

    public Detalle_orden(Long orden_id, Long producto_id, Long cantidad, Long precio) {
        this.orden_id = orden_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.precio = precio;
    }

     /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the orden_id
     */
    public Long getOrden_id() {
        return orden_id;
    }

    /**
     * @return the producto_id
     */
    public Long getProducto_id() {
        return producto_id;
    }

    /**
     * @return the cantidad
     */
    public Long getCantidad() {
        return cantidad;
    }

    /**
     * @return the precio
     */
    public Long getPrecio() {
        return precio;
    }

    /**
     * @param orden_id the orden_id to set
     */
    public void setOrden_id(Long orden_id) {
        this.orden_id = orden_id;
    }

    /**
     * @param producto_id the producto_id to set
     */
    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Long precio) {
        this.precio = precio;
    }

}
