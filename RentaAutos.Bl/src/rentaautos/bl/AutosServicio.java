/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class AutosServicio {
    
    private final ArrayList<Autos> catalagodeAutos;
     
    public AutosServicio(){
        catalagodeAutos = new ArrayList<>();
        crearDatosPrueba();
    }
     
    public ArrayList<Autos>ObtenerAutos(){
        return catalagodeAutos;
    }
    
    public void guardar(Autos auto){
         if(auto.getId().equals(0)){
             Integer id = obtenerSguiente();
             auto.setId(id);
             
         }
         catalagodeAutos.add(auto);
    }
    private void crearDatosPrueba() {
       Autos auto1 = new Autos();
       auto1.setId(1);
       auto1.setMarcas("Toyota");
       
       
       Autos auto2 = new Autos();
       auto2.setId(2);
       auto2.setMarcas("Honda");
       
       catalagodeAutos.add(auto1);
       catalagodeAutos.add(auto2);
    }

    private Integer obtenerSguiente() {
       Integer maxId = 1;
       for(Autos auto: catalagodeAutos){
           if(auto.getId() >= maxId){
               maxId = auto.getId()+1;
           }
       }
    

           return maxId;
    
    }
}
