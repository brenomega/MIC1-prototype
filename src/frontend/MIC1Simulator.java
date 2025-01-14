package frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MIC1Simulator extends Application {

    @Override
    public void start(Stage primaryStage) {
        SimulatorController controller = new SimulatorController();
        Pane root = controller.createSimulatorPane();

        Scene scene = new Scene(root, 800, 600); // Tamanho ajustável
        primaryStage.setTitle("MIC-1 Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
