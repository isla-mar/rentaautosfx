/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

/**
 *
 * @author mauricio.bonilla
 */
public class AutosCategoria {
    private Integer id;
    private String Marcas;

    public AutosCategoria(String Marcas) {
        this.Marcas = Marcas;
    }

    public AutosCategoria() {
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
    
    @Override  
    public String toString(){
        return Marcas;
    }
}
