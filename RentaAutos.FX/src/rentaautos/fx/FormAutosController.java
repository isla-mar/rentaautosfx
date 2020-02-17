/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rentaautos.bl.Autos;
import rentaautos.bl.AutosServicio;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FormAutosController implements Initializable {
   @FXML
   private TableView tableView;
   
   @FXML
   private TableColumn<Autos, Integer> columnaId;
   
      @FXML
   private TableColumn<Autos, String> columnaMarcas;
      
      ObservableList<Autos> data;
      
      AutosServicio servicio;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        servicio = new  AutosServicio();
        
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaMarcas.setCellValueFactory(new PropertyValueFactory("Marcas"));
        
        cargarDatos();
       
    }    
     public void nuevoagregar() throws IOException{
         
         Autos NuevoAuto = new Autos();
         
      abrirVentanaModal(NuevoAuto, "Nuevo Auto");
     }
     
     public void guardar(Autos auto){
          servicio.guardar(auto);
          cargarDatos();
     }

    private void abrirVentanaModal(Autos auto,String titulo) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("NuevoEditarAutos.fxml"));
           Parent root = (Parent) loader.load();
           
           NuevoEditarAutosController controller = loader.getController();
           controller.setController(this);
           controller.setAutos(auto);
     
          Stage stage = new Stage();
          Scene scene = new Scene(root);
          stage.setScene(scene);
          
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.setTitle(titulo);
          stage.show();
    }

    private void cargarDatos() {
      
        data = FXCollections.observableArrayList(servicio.ObtenerAutos());
        
       tableView.setItems(data);
       tableView.refresh();
       
    }

   
}
