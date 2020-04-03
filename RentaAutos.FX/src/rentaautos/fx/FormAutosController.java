/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.fx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
    
    @FXML
   private TableColumn<Autos, String> colCategoria;
    
    @FXML
   private TableColumn<Autos, Double> colPrecio;
    
    @FXML
   private TableColumn<Autos, Integer> colExistencia;
    
    @FXML
   private TableColumn<Autos, Boolean> colActivo;
      
    @FXML
   private TableColumn colEditar;
                 
    @FXML
   private TableColumn colEliminar; 
    
     @FXML
    private TableColumn colImagen;
      
     @FXML
    private JFXTextField txtBuscar;
      
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
        colCategoria.setCellValueFactory(c-> new SimpleStringProperty(c.getValue()
                .getAutosCategoria().getMarcas()));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        colExistencia.setCellValueFactory(new PropertyValueFactory<>("Existencia"));
        colActivo.setCellValueFactory(new PropertyValueFactory<>("Activo"));


        definirColumnaEditar();
        definirColumnaEliminar();
        definirColumnaImagen();
        
        
        cargarDatos();
       
    }    
     public void nuevoagregar() throws IOException{
         
         Autos NuevoAuto = new Autos();
         
      abrirVentanaModal(NuevoAuto, "Nuevo Auto");
     }
     
     public String guardar(Autos auto){
          String resultado=servicio.guardar(auto);
          if(resultado.equals(""))
          {
           cargarDatos();   
          }
          return resultado;
     }
     public void buscar()
     {
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
      if(txtBuscar.getText()==null||txtBuscar.getText().equals("")){
       data = FXCollections.observableArrayList(servicio.obtenerAutos());   
      }else{
       data = FXCollections.observableArrayList(servicio.ObtenerAutos(txtBuscar.getText()));   
      }
       tableView.setItems(data);
       tableView.refresh();
       
    }

    private void definirColumnaEditar() {
        colEditar.setCellFactory(param-> new TableCell<String , String>(){
            final JFXButton btn=new JFXButton("Editar");
            
            @Override
            public void updateItem(String item,boolean empty)
            {
              super.updateItem(item, empty);
              if(empty)
              {
                  setGraphic(null);
                  setText(null);
              }else{
                  
                  btn.getStyleClass().add("jfx-button-info-outline");
                  btn.setOnAction(event->{
                      
                  tableView.getSelectionModel().select(getTableRow().getItem());
                    Autos autoExistente=(Autos) getTableRow().getItem();
                    Autos autos = servicio.clonar(autoExistente);
                           try{
                     abrirVentanaModal(autos, "Editar Autos");
                     
                 }catch(IOException ex){
                    Logger.getLogger(FormAutosController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                
                  });
                  setGraphic(btn);
                  setText(null);
              }
            }
            
            
        });
    }

    private void definirColumnaEliminar() {
      colEliminar.setCellFactory(param-> new TableCell<String , String>(){
            final JFXButton btn=new JFXButton("Eliminar");
            
            @Override
            public void updateItem(String item,boolean empty)
            {
              super.updateItem(item, empty);
              if(empty)
              {
                  setGraphic(null);
                  setText(null);
              }else{
                  btn.getStyleClass().add("jfx-button-danger-outline");
                  btn.setOnAction(event->{
                      
                  tableView.getSelectionModel().select(getTableRow().getItem());
                  Autos autos=(Autos) getTableRow().getItem();
                
                  
                 quitar(autos);
                  
             
                  });
                  setGraphic(btn);
                  setText(null);
              }
            }
         });  
        
    }
 private void quitar(Autos autos) {
             Alert alert=new Alert(AlertType.CONFIRMATION,
             "¿Esta seguro que desea eliminar el vehiculo"+ autos.getMarcas()+"?",
               ButtonType.YES,ButtonType.NO);
             
             alert.showAndWait();
             if(alert.getResult()==ButtonType.YES){
               servicio.eliminar(autos);
               cargarDatos();
             }
             
            
              
          }

    private void definirColumnaImagen() {
        colImagen.setCellFactory(param-> new TableCell<String , String>(){
            final ImageView img = new ImageView();
            
            @Override
            public void updateItem(String item,boolean empty)
            {
              super.updateItem(item, empty);
              if(empty)
              {
                  setGraphic(null);
                  setText(null);
              }else{
                  Autos autos=(Autos) getTableRow().getItem();
                  if (autos != null)
                  {
                       img.imageProperty().set(autos.getImageView());
                       img.setFitWidth(100);
                       img.setPreserveRatio(true);
                    
                       setGraphic(img);
                       setText(null); 
                  }
                  
              }
            }
         });  
        
    }
   
}

//Autos autos=(Autos) getTableRow().getItem();