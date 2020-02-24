/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dell
 */
public class Autos {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty Marcas;
    private SimpleObjectProperty AutosCategoria;
    private SimpleDoubleProperty precio;
    private SimpleIntegerProperty existencia;
    private SimpleBooleanProperty activo;
    
    public Autos()
    {
        id= new SimpleIntegerProperty();
        Marcas =new SimpleStringProperty();
        AutosCategoria=new SimpleObjectProperty();
        precio=new SimpleDoubleProperty();
        existencia=new SimpleIntegerProperty();
        activo=new SimpleBooleanProperty(true);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public SimpleIntegerProperty idProperty()
    {
        return id;
    }

    public String getMarcas() {
        return Marcas.get();
    }

    public void setMarcas(String Marcas) {
        this.Marcas.setValue(Marcas);

    }
    
    public SimpleStringProperty MarcasProperty(){
        return Marcas;
    }
    //////////////////////////////////////////////////////////
    public Double getPrecio() {
        return precio.get();
    }

    public void setPrecio(Double precio) {
        this.precio.setValue(precio);

    }
    
    public SimpleDoubleProperty precioProperty(){
        return precio;
    }
    /////////////////////////////////////////////////
    public Integer getExistencia() {
        return existencia.get();
    }

    public void setExistencia(Integer existencia) {
        this.existencia.setValue(existencia);

    }
    
    public SimpleIntegerProperty existenciaProperty(){
        return existencia;
    }
   //////////////////////////////////////////////////////
    public Boolean getActivo() {
        return activo.get();
    }

    public void setActivo(Boolean activo) {
        this.activo.setValue(activo);

    }
    
    public SimpleBooleanProperty activoProperty(){
        return activo;
    }
    //////////////////////////////////////////////////
    public AutosCategoria getAutosCategoria() {
        return (AutosCategoria) AutosCategoria.get();
    }

    public void setAutosCategoria(AutosCategoria AutosCategoria) {
        this.AutosCategoria.setValue(AutosCategoria);

    }
    
    public SimpleObjectProperty AutosCategoriaProperty(){
        return AutosCategoria;
    }
}
