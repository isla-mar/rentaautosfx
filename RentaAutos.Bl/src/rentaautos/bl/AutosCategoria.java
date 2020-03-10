/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author mauricio.bonilla
 */
@Entity
@Table(name="AutosCategoria") ///////////////////////////////////////////
public class AutosCategoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String Marcas;
    
     @OneToMany(mappedBy="autosCategoria")
     private Set<Autos> autos;

    public Set<Autos> getAutos() {
        return autos;
    }

    public AutosCategoria(String Marcas) {
        this.Marcas = Marcas;
    }

    public AutosCategoria() {
    }
    

    public void setAutos(Set<Autos> autos) {
        this.autos = autos;
    }
     
     

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarcas() {
        return Marcas;
    }

    public void setMarcas(String Marcas) {
        this.Marcas = Marcas;
    }
    
  
}
