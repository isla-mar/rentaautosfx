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

/**
 *
 * @author mauricio.bonilla
 */
public class CategoriasAutoServicio {
   
    public ArrayList<AutosCategoria> obtenerCategorias(){
        
        /////////////////////////////////////////////////////////////////
     Session session = HibernateUtil.getSessionFactory().openSession();
     
     Transaction tx = session.beginTransaction();
     
     Criteria query = session.createCriteria(AutosCategoria.class);
     List<AutosCategoria> resultado = query.list();
     tx.commit();
     session.close();
     return new ArrayList<>(resultado);
    }

       
    
    
}
