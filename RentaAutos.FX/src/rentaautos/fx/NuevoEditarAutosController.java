/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import rentaautos.bl.Autos;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class NuevoEditarAutosController implements Initializable {
   
    
    @ FXML
    Button btnCancelar;
    
   @ FXML
    TextField txtId;
    
   @ FXML
    TextField txtMarcas;
    
     private FormAutosController controller;
     private Autos auto;
     
     
     public void setController(FormAutosController controller){
         this.controller = controller;
         
         
     }
     
     public void setAutos(Autos auto){
         this.auto = auto;
      
         txtId.textProperty().bindBidirectional(auto.idProperty(), new NumberStringConverter());
      txtMarcas.textProperty().bindBidirectional(auto.MarcasProperty());
        
         
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }  
    public void aceptar(){
        controller.guardar(auto);
        cerrar();
        
    }
    
    public void cancelar(){
        
        cerrar();
    }
    
    private void cerrar()
    {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
