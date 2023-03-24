package com.example.softwaredesign;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RiddleController implements Initializable {
    private Main main;
    private Parent root;
    private Scene scene;
    private Stage stage;
    private int iterator = 0;
    @FXML
    private Label moneyStatus;
    @FXML
    private Label currentQuestion;
    @FXML
    private TextField currentAnswer;

    public void goBackToMainScreen(ActionEvent event) throws IOException {
        main.getEngine().getCreature().increaseCoins(riddle.getEarnedMoney());
        main.getEngine().getCreature().getHealth().increaseVital(10);
        main.getEngine().getCreature().getHunger().decreaseVital(5);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gameScreen.fxml"));
        root = loader.load();
        GameController controller = loader.getController();
        controller.setMain(main);
        controller.adaptScreenToCreature();
        controller.loadImages();
        controller.updateBars();
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        main.initSchedulers(controller);
    }
    private Riddle riddle = new Riddle("Riddle");
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        currentQuestion.setText(riddle.getQuestions()[iterator]);
        System.out.println("RIDDLEE");
    }
    public void submit(ActionEvent event) throws IOException {
        if(currentAnswer.getText().equals(riddle.getAnswers()[iterator]) && iterator != 5){
            riddle.increaseEarnedMoney();
            iterator++;
            moneyStatus.setText("Earned money: " + riddle.getEarnedMoney());
            if(iterator == riddle.getQuestions().length){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("GAME SUCCESSFULLY COMPLETED");
                alert.setContentText("You earned " + riddle.getEarnedMoney() + " coins\n" +
                        "Health increased: +10\n" +
                        "Hunger decreased: -5");
                alert.show();
                goBackToMainScreen(event);
            }else{
                currentQuestion.setText(riddle.getQuestions()[iterator]);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("GAME OVER");
            alert.setContentText("You earned " + riddle.getEarnedMoney() + " coins\n" +
                    "Health increased: +10\n" +
                    "Hunger decreased: -5");
            alert.show();

            goBackToMainScreen(event);
        }
    }
    public void setMain(Main theMainInstance){
        this.main = theMainInstance;
    }
}

