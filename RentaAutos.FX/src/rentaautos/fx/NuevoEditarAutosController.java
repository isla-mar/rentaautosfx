/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.fx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import rentaautos.bl.Autos;
import rentaautos.bl.AutosCategoria;
import rentaautos.bl.CategoriasAutoServicio;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class NuevoEditarAutosController implements Initializable {
   
    
    @ FXML
    JFXButton btnCancelar;
    
   @ FXML
    JFXTextField txtId;
    
   @ FXML
    JFXTextField txtMarcas;
   
   @ FXML
    JFXComboBox cmbCategoria;
   
    @ FXML
    JFXTextField txtPrecio;
    
    @ FXML
    JFXTextField txtExistencia;
    
    @ FXML
    JFXCheckBox chActivo;
    
     private FormAutosController controller;
     private Autos auto;
     private CategoriasAutoServicio categoriasAutosServicio;
     private ObservableList<AutosCategoria>data;
     
     
     public void setController(FormAutosController controller){
         this.controller = controller;
         
         
     }
     
     public void setAutos(Autos auto){
         this.auto = auto;
      
      txtId.textProperty().bindBidirectional(auto.idProperty(), new NumberStringConverter());
      txtMarcas.textProperty().bindBidirectional(auto.MarcasProperty());
      cmbCategoria.valueProperty().bindBidirectional(auto.AutosCategoriaProperty());
      
      cmbCategoria.setConverter(new StringConverter<AutosCategoria>(){
             @Override
             public String toString(AutosCategoria categoria) {
                 return categoria ==null ? "" : categoria.getMarcas();
             }

             @Override
             public AutosCategoria fromString(String string) {
                 
                 if(data == null){
                     return null;
                 }
                 for(AutosCategoria categoria:data){
                     if(categoria.getMarcas().equals(string)){
                         return categoria;
                     }
                 }
               return null;  
             }
      
      
      });
      
      txtPrecio.textProperty().bindBidirectional(auto.precioProperty(), new NumberStringConverter());
      txtExistencia.textProperty().bindBidirectional(auto.existenciaProperty(), new NumberStringConverter());
      chActivo.selectedProperty().bindBidirectional(auto.activoProperty());

            

        
         
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    categoriasAutosServicio=new CategoriasAutoServicio();
    
     data= FXCollections.observableArrayList(categoriasAutosServicio.obtenerCategorias());
    
      cmbCategoria.setItems(data);
    }  
    public void aceptar(){
        String resultado=controller.guardar(auto);
        if(resultado.equals(""))
        {
             cerrar();
        }else{
            Alert alert=new Alert(AlertType.ERROR);
            alert.setTitle("Producto");
            alert.setHeaderText("Errores de validacion");
            alert.setContentText(resultado);
            alert.showAndWait();
        }
       
        
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
