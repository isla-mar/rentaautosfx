/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dell
 */
public class AutosServicio {
    
    
     
    public ArrayList<Autos>ObtenerAutosActivos(){
       ///////////////////////////////////////////////////////////////////// 
     Session session = HibernateUtil.getSessionFactory().openSession();
     
     Transaction tx = session.beginTransaction();
     
     Criteria query = session.createCriteria(Autos.class);
     query.add(Restrictions.eq("activo", true));
     List<Autos> resultado= query.list();
     
     tx.commit();
     session.close();
     return new ArrayList<>(resultado);
    }
      public ArrayList<Autos> obtenerAutos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Autos.class);
        List<Autos> resultado = query.list();
        
        tx.commit();
        session.close();
 
        return new ArrayList<>(resultado);
    }
    
    
    public ArrayList<Autos>ObtenerAutos(String buscar){
       /////////////////////////////////////////////////////////////// 
      Session session = HibernateUtil.getSessionFactory().openSession();
     
     Transaction tx = session.beginTransaction();
     
     Criteria query = session.createCriteria(Autos.class);
     query.add(Restrictions.like("Marcas",buscar,MatchMode.ANYWHERE ));
     List<Autos> resultado = query.list();
     tx.commit();
     session.close();
     return new ArrayList<>(resultado);
    }
    
    public String guardar(Autos autos){
        //////////////////////////////////////////////////////////
        String resultado=validarAutos(autos);
        if(resultado.equals("")){
             Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
        
        try{
            session.saveOrUpdate(autos);
            
            tx.commit();
         
         }catch (Exception e){
            tx.rollback();
         return e.getMessage();
         }finally{
                   session.close();
                   }
            return "";
    }
        return resultado;
    }
    
    
    public void eliminar(Autos autos)
    {
        //////////////////////////////////////////////////////////////
     Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
         
        try{
            session.delete(autos);
            
            tx.commit();
         
         }catch (Exception e){
            tx.rollback();
            
            System.out.println(e.getMessage());
         }finally{
                   session.close();
                   }
           
    }
     
    public Autos clonar(Autos autos){
        Autos autoClonado = new Autos();
        
        autoClonado.setId(autos.getId());
        autoClonado.setMarcas(autos.getMarcas());
        autoClonado.setAutosCategoria(autos.getAutosCategoria());
        autoClonado.setPrecio(autos.getPrecio());
        autoClonado.setExistencia(autos.getExistencia());
        autoClonado.setActivo(autos.getActivo());
        autoClonado.setImagen(autos.getImagen());


        return autoClonado;
        
    }    
    


    private String validarAutos(Autos autos) {
        if(autos.getMarcas()==null||autos.getMarcas().equals("")){
            return "ingrese la marca";
        }
        if(autos.getAutosCategoria()==null){
            return "seleccione una categoria";
        }
        if(autos.getPrecio()<0){
            return "ingrese el Precio mayo o igua a cero plox";
        }
        if(autos.getExistencia()<0){
            return "la existencia deber ser mayo a cero";
        }

        return"";
    }


}
