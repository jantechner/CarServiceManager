package infrastructure;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ClientService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/mainScreen.fxml"));
        primaryStage.setTitle("CarServiceManager");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ConnectionManager.connect("SEBA", "seba");

//        ClientService.addKlient(true, "Rafael", "Klaus", "Piotrowo 3");
//        ClientService.addKlient(true, "Jan", "Teśner", "Jeżyce gdzieś");
//        ClientService.addKlient(false, "Politechnika Poznańska", "7770003699", "pl. Marii Skłodowskiej-Curie 5, Poznań");

        launch(args);
//        ConnectionManager.closeAndCommit();   //TODO zdecydować czy auto-commmit to dobry pomysł
    }

}
