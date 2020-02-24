/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

import java.util.ArrayList;

/**
 *
 * @author mauricio.bonilla
 */
public class CategoriasAutoServicio {
    private final ArrayList<AutosCategoria> listadeCategoria;

    public CategoriasAutoServicio() {
     listadeCategoria= new ArrayList<>();
     
     crearDatosdePrueba();
    }
    public ArrayList<AutosCategoria> obtenerCategorias(){
    return listadeCategoria;
    }

        private void crearDatosdePrueba() {
        AutosCategoria ACategoria1=new AutosCategoria("Automoviles");
        ACategoria1.setId(1);
        
        AutosCategoria ACategoria2=new AutosCategoria("Motos");
        ACategoria2.setId(2);
        
        listadeCategoria.add(ACategoria1);
        listadeCategoria.add(ACategoria2);
    }
    
    
}
