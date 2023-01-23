package csur.adrian.crudobjectdb;

import dao.PedidoDAO;
import dao.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Pedido;

import models.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    final String HOY = Utils.getToday();
    @FXML
    public TableView tablePedidosView;
    @FXML
    public TableColumn colId;
    @FXML
    public TableColumn colDate;
    @FXML
    public TableColumn colCliente;
    @FXML
    public TableColumn colStatus;
    @FXML
    public TableColumn colProductos;
    @FXML
    public TextField clienteInput;
    @FXML
    public ComboBox<Producto> productosCombo;
    private PedidoDAO pedidos;
    private ProductoDAO productos;


    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidos = new PedidoDAO();
        productos = new ProductoDAO();
        initTablePedido();
    }

   public void initTablePedido() {

       tablePedidosView.getItems().clear();

       colId.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("id"));
       colDate.setCellValueFactory(new PropertyValueFactory<Pedido, String>("fecha"));
       colCliente.setCellValueFactory(new PropertyValueFactory<Pedido, String>("cliente"));
       colStatus.setCellValueFactory(new PropertyValueFactory<Pedido, String>("estado"));
       colProductos.setCellValueFactory(new PropertyValueFactory<Pedido, Producto>("producto"));


       ObservableList<Producto> productosLista = FXCollections.observableArrayList();
       productosLista.addAll(productos.getAll());
       productosCombo.setItems(productosLista);

       ObservableList<Pedido> listaPed = FXCollections.observableArrayList();


       listaPed.addAll(pedidos.getAll());

       tablePedidosView.setItems(listaPed);

       tablePedidosView.setRowFactory(row -> new TableRow<Pedido>() {
           @Override
           protected void updateItem(Pedido item, boolean empty) {
               super.updateItem(item, empty);
           }
       });
   }

    @FXML
    public void btnVerCarta(ActionEvent event) {
        verCarta();
    }

    @FXML
    public void btnMakeOrder(ActionEvent event) {
        makeOrder();
        initTablePedido();
    }

    @FXML
    public void btnMarkAsDone(ActionEvent event) {
        markAsDone();
        initTablePedido();
    }

    @FXML
    public void btnTodayOrders(ActionEvent event) {
        todayOrders();
    }

    @FXML
    public void btnCancelOrder(ActionEvent event) {
        cancelOrder();
        initTablePedido();
    }

    @FXML
    public void btnAllOrders(ActionEvent event) {
        initTablePedido();
    }

    public void markAsDone() {
        if(tablePedidosView.getSelectionModel().getSelectedItem() == null) return;
        Pedido pedido = (Pedido) tablePedidosView.getSelectionModel().getSelectedItem();
        pedido.setEstado("Recogido");
        pedidos.update(pedido);
    }

    public void makeOrder() {
        if (clienteInput.getText().isEmpty() || productosCombo.getValue() == null) return;
        try {
            var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
            Pedido pedido = new Pedido();
            pedido.setCliente(clienteInput.getText());
            pedido.setProducto(productos.get(productosCombo.getValue().getId()));
            pedido.setFecha(HOY);
            pedido.setEstado("Pendiente");
            pedidos.add(pedido);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void cancelOrder() {
        if(tablePedidosView.getSelectionModel().getSelectedItem() == null) return;
        Pedido selectedped = (Pedido) tablePedidosView.getSelectionModel().getSelectedItem();
        pedidos.delete(selectedped);
    }

    public void todayOrders() {
        ObservableList<Pedido> listaPed = FXCollections.observableArrayList();
        listaPed.addAll(pedidos.getTodayPedidos());
        tablePedidosView.setItems(listaPed);
    }

    public void verCarta() {
        try {
            App.openNewWindow("productos-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


