/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dell
 */
public class Autos {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty Marcas;
    
    public Autos()
    {
        id= new SimpleIntegerProperty();
        Marcas =new SimpleStringProperty();
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
}
