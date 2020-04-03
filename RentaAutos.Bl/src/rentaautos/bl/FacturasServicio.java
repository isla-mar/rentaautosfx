
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dell
 */
public class FacturasServicio {
       public void guardar(Factura factura){
      
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
        
        try{
            session.saveOrUpdate(factura);
              for(FacturaDetalle detalle: factura.getFacturaDetalle()) {
                Integer id = detalle.getAutos().getId();
                
                Criteria query = session.createCriteria(Autos.class);
                query.add(Restrictions.eq("id", id));
                query.setMaxResults(1);
                
                Autos autosExistente = ( Autos) query.uniqueResult();
                
                Integer nuevaExistencia = 
                        autosExistente.getExistencia() - detalle.getCantidad();
                
                autosExistente.setExistencia(nuevaExistencia);
                
                session.saveOrUpdate(autosExistente);
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }    
}

