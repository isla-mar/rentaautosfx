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
    
    
    public ArrayList<Autos>ObtenerAutos(String buscar){
        
        if(buscar==null||buscar.equals(""))
        {
          return catalagodeAutos;  
        }
        String bminusc=buscar.toLowerCase();
        ArrayList<Autos>busqueda= new ArrayList<>();
        
        
                catalagodeAutos.forEach(auto->{
                if(auto.getMarcas().toLowerCase().contains(buscar)==true){
                    
                    busqueda.add(auto);
                }
             });
                
        return busqueda;
    }
    
    public String guardar(Autos auto){
        String resultado=validarAutos(auto);
        
        if(resultado.equals(""))
        {
         if(auto.getId().equals(0)){
             Integer id = obtenerSguiente();
             
             auto.setId(id);
             
             catalagodeAutos.add(auto);
         }else {
            catalagodeAutos.forEach(autoExistente->{
                if(autoExistente.getId().equals(auto.getId()))
                {
                    autoExistente.setMarcas(auto.getMarcas());
                    autoExistente.setAutosCategoria(auto.getAutosCategoria());
                    autoExistente.setPrecio(auto.getPrecio());
                    autoExistente.setExistencia(auto.getExistencia());
                    autoExistente.setActivo(auto.getActivo());
                }
            });
         } 
         return "";
        }
        return resultado;
        
         
         
    }
    
    public void eliminar(Autos auto)
    {
       catalagodeAutos.remove(auto);
    }
            
    private void crearDatosPrueba() {
        AutosCategoria Acategoria1= new AutosCategoria("Automoviles");
        Acategoria1.setId(1);
        
        
        
        Autos auto1 = new Autos();
        auto1.setId(1);
        auto1.setMarcas("Toyota");
        auto1.setAutosCategoria(Acategoria1);
        auto1.setPrecio(200000.00);
        auto1.setExistencia(50);
       
       Autos auto2 = new Autos();
       auto2.setId(2);
       auto2.setMarcas("Honda");
       auto2.setAutosCategoria(Acategoria1);
       auto2.setPrecio(250000.00);
       auto2.setExistencia(40);
       
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

    private String validarAutos(Autos auto) {
        if(auto.getMarcas()==null||auto.getMarcas().equals("")){
            return "ingrese la marca";
        }
        if(auto.getAutosCategoria()==null){
            return "seleccione una categoria";
        }
        if(auto.getPrecio()<0){
            return "ingrese el Precio mayo o igua a cero plox";
        }
        if(auto.getExistencia()<0){
            return "la existencia deber ser mayo a cero";
        }

        return"";
    }
}
