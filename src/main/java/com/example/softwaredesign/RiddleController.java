package com.example.softwaredesign;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RiddleController extends Screen implements Initializable {
    private int iterator = 0;
    @FXML
    private Label moneyStatus;
    @FXML
    private Label currentQuestion;
    @FXML
    private TextField currentAnswer;

    public void goBackToMainScreen() throws IOException {
        engine.getCreature().increaseCoins(riddle.getEarnedMoney());
        engine.getCreature().getHealth().increaseVital(10);
        engine.getCreature().getHunger().decreaseVital(5);
        displayGameScreen();
    }
    private Riddle riddle = new Riddle("Riddle");
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        currentQuestion.setText(riddle.getQuestions()[iterator]);
    }
    public void submit() throws IOException {
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
                goBackToMainScreen();
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

            goBackToMainScreen();
        }
    }
}

