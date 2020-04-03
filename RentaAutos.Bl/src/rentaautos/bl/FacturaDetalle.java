/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name="FacturaDetalle")
public class FacturaDetalle {
    private Integer id;
    private Factura factura;
    private Autos autos;
    private Integer cantidad;
    private Double precio;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name="facturaId", nullable=false)

    public Factura getFactura() {
        return factura;
    }
    


    public void setFactura(Factura factura) {
        this.factura = factura;
    }
     @ManyToOne
    @JoinColumn(name="autosId", nullable=false)

    public Autos getAutos() {
        return autos;
    }

    public void setAutos(Autos autos) {
        this.autos = autos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}
