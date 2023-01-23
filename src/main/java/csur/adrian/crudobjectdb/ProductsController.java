package csur.adrian.crudobjectdb;

import dao.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Producto;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {
    @FXML
    public TableView tableProductosView;
    @FXML
    public TableColumn colIdProducto;
    @FXML
    public TableColumn colNombreProducto;
    @FXML
    public TableColumn colTipoProducto;
    @FXML
    public TableColumn colPrecioProducto;
    @FXML
    public TableColumn colDisponibilidadProducto;

    ProductoDAO prodao = new ProductoDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initProductos();
    }

    public void initProductos() {
        ObservableList<Producto> listaProd = FXCollections.observableArrayList();
        listaProd.addAll(prodao.getAll());

        tableProductosView.setItems(listaProd);

        colIdProducto.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colDisponibilidadProducto.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));

    }

}
