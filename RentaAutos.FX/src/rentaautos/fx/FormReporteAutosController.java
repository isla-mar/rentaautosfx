/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.fx;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author Isla
 */
public class FormReporteAutosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void generarReporte(){
        
        ReporteAutosViewer reporteViewer = new ReporteAutosViewer();
        try {
            
            reporteViewer.mostrarReporte();
        } catch (JRException ex) {
            Logger.getLogger(FormReporteFacturasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
