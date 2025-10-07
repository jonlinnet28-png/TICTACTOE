package dk.easv.tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class IntroController {
    @FXML
    private void onClickOpen2Player(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/TicTac2PlayerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("2-Player");
        stage.setScene(scene);

        TicTac2playerController controller = fxmlLoader.getController();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void onClickOpenVsComputerEasy(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/TicTacVsComputerEasyView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Player Versus Computer (Easy)");
        stage.setScene(scene);

        TicTacVsComputerEasyController controller = fxmlLoader.getController();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void onClickOpenVsComputerHard(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/TicTacVsComputerHardView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Player Versus Computer (Hard)");
        stage.setScene(scene);

        TicTacVsComputerHardController controller = fxmlLoader.getController();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
