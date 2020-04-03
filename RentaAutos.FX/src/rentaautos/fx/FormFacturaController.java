/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.fx;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import rentaautos.bl.Autos;
import rentaautos.bl.AutosCategoria;
import rentaautos.bl.AutosServicio;
import rentaautos.bl.CategoriasAutoServicio;
import rentaautos.bl.Factura;
import rentaautos.bl.FacturaDetalle;
import rentaautos.bl.FacturasServicio;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FormFacturaController implements Initializable {
     
   @FXML
   TableView tableView;
   
   @FXML
   TabPane tabPaneAutos;
   
   @FXML
   TableColumn<Autos, String>colMarcas;
   
    @FXML
   TableColumn<Autos, Double>colPrecio;
    
    @FXML
   TableColumn colEliminar;
       
    @FXML
    Label lblTotal;
    
    @FXML
    Label lblImpuesto;
    
    ObservableList<Autos> data;
    Factura nuevaFactura;
    
    AutosServicio servicioAutos;
    CategoriasAutoServicio servicioCategoria;
    FacturasServicio servicioFactura;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        servicioAutos= new  AutosServicio();
        servicioCategoria = new CategoriasAutoServicio();
        servicioFactura= new FacturasServicio();
        colMarcas.setCellValueFactory(new PropertyValueFactory("Marcas"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        
        definirColumnaEliminar();
        
        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        tableView.refresh();
        
        crearTabs();
        nuevaFactura();
        
        
    } 
        
    public void nuevaFactura()
    {
        nuevaFactura= new Factura();
        data.clear();
        calcularFactura();
    }
    
     public void guardar()
    {
        servicioFactura.guardar(nuevaFactura);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Factura Guardada");
        alert.showAndWait();
        nuevaFactura();
    }

   private void definirColumnaEliminar() {
        colEliminar.setCellFactory(param -> new TableCell<String, String>() {
            final JFXButton btn = new JFXButton("Eliminar");
            
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.getStyleClass().add("jfx-button-danger-outline");
                    btn.setOnAction(event -> {
                        tableView.getSelectionModel().select(getTableRow().getItem());
                        Autos autos = (Autos) getTableRow().getItem();
                      data.remove(autos);
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }            
        });        
    }

    private void crearTabs() {
        
        ArrayList<AutosCategoria>categorias =  servicioCategoria.obtenerCategorias();
        ArrayList<Autos> autos = servicioAutos.ObtenerAutosActivos();
        
        
        for(AutosCategoria categoria: categorias){
            TilePane tilePane = new TilePane();
            tilePane.setPadding(new Insets(10, 10, 10, 10));
            tilePane.setHgap(10);
            tilePane.setVgap(10);
            
              List<Autos> autosPorCategoria = autos.stream()
                .filter(p -> Objects.equals(p.getAutosCategoria().getId(), categoria.getId()))
                    .collect(Collectors.toList());   
              
              
              for(Autos auto: autosPorCategoria){
                  VBox vbox = new VBox();
                  Label lblMarcas = new Label();
                  Label lblPrecio = new Label();
                  
                  lblMarcas.setText(auto.getMarcas());
                  lblPrecio.setText(auto.getPrecio().toString());
                  
                  ImageView imagen= new ImageView(auto.getImageView());
                  imagen.setUserData(auto);
                  imagen.setFitWidth(100);
                  imagen.setPreserveRatio(true);
                  
                  vbox.getChildren().add(imagen);
                  vbox.getChildren().add(lblMarcas);
                  vbox.getChildren().add(lblPrecio);
                  vbox.setAlignment(Pos.CENTER);
                  
                  imagen.addEventHandler(MouseEvent.MOUSE_CLICKED,
                          new EventHandler<MouseEvent>(){
                              @Override
                  public void handle(MouseEvent event){
                      Object object = event.getSource();
                      ImageView image = (ImageView)object;
                      Autos userData =(Autos) image.getUserData();
                                  
                              
                     agregarAutos(userData);         }
                  
              });
                  tilePane.getChildren().add(vbox);
                  
              }
              
              Tab tab = new Tab(categoria.getMarcas(), tilePane);
              
              tabPaneAutos.getTabs().add(tab);
            
        }
        
        
    }
    
    private void agregarAutos(Autos autos){
        data.add(autos);
        calcularFactura();
        
    }
    
    private void removerAuto(Autos autos)
    {
        data.remove(autos);
    }


    private void calcularFactura() {
     Double total=0.00;
     Double impuesto=0.00;
     
     nuevaFactura.getFacturaDetalle().clear();
    
     for(Autos autos: data){
         FacturaDetalle detalle= new FacturaDetalle();
         detalle.setFactura(nuevaFactura);
         detalle.setAutos(autos);
         detalle.setCantidad(1);
         detalle.setPrecio(autos.getPrecio());
          
         nuevaFactura.getFacturaDetalle().add(detalle);
         total+=autos.getPrecio();
     }
     
     impuesto= total -(total/1.15);
     
     lblTotal.setText("Total:"+ FormatoMoneda(total));
     lblImpuesto.setText("Impuesto:"+ FormatoMoneda(impuesto));
     
     nuevaFactura.setTotal(total);
     nuevaFactura.setImpuesto(impuesto);
    }
    
    
    private String FormatoMoneda(Double valor){
        DecimalFormat df= new DecimalFormat("#,##0.00");
        return df.format(valor);
        
    }
}
