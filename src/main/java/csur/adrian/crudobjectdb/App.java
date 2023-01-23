package csur.adrian.crudobjectdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("pedidos-view"));
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Gestor de Comandas");
    }

    protected static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void openNewWindow(String fxml) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource(fxml + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Carta de Productos");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
            (scene).getWindow() );
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
