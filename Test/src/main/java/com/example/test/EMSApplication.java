package com.example.test;


import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EMSApplication extends Application {

    private static final Logger logger = Logger.getLogger(EMSApplication.class.getName());
    private DoctorDao doctorDao = new DoctorDao();
    public static final String BLANK = "";

    @Override
    public void start(Stage stage) {
        stage.setTitle("Doctor's Database Application");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Doctor's Database Application");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label doctorIdLabel = new Label("Doctor's ID: *");
        grid.add(doctorIdLabel, 0, 1);

        TextField doctorIdField = new TextField();
        grid.add(doctorIdField, 1, 1);

        Label doctorNameLabel = new Label("Doctor's Name:");
        grid.add(doctorNameLabel, 0, 2);

        TextField doctorNameField = new TextField();
        grid.add(doctorNameField, 1, 2);

        Label grossSalaryLabel = new Label("Doctor's Gross Salary:");
        grid.add(grossSalaryLabel, 0, 3);

        TextField grossSalaryField = new TextField();
        grid.add(grossSalaryField, 1, 3);

        Label netSalaryLabel = new Label("Doctor's Net Salary::");
        grid.add(netSalaryLabel, 0, 4);

        TextField netSalaryField = new TextField();
        grid.add(netSalaryField, 1, 4);

        Button displayButton = new Button("Display");
        Button resetButton = new Button("Reset");
        Button deleteButton = new Button("Delete");
        Button quitButton = new Button("Quit");
        Button connectButton = new Button("Connect");

        HBox hBoxDisplay = new HBox(connectButton, displayButton, resetButton, deleteButton, quitButton);
        grid.add(hBoxDisplay, 1, 5);

        displayButton.setOnAction(actionEvent -> {
            String doctorId = doctorIdField.getText().trim();
            if (!BLANK.equals(doctorId)) {
                try {
                    Doctor docResult = doctorDao.getDoctor(doctorId);
                    if (docResult != null) {
                        doctorIdField.setText(Integer.toString(docResult.getDoctorId()));
                        doctorNameField.setText(docResult.getDoctorName());
                        grossSalaryField.setText(Double.toString(docResult.getGrossSalary()));
                        netSalaryField.setText(Double.toString(docResult.getNetSalary()));
                    } else {
                        this.alert("Error", "No Doctor found with the ID: "+doctorId+"!", AlertType.ERROR);
                    }

                } catch (Exception exception) {
                    logger.log(Level.SEVERE, exception.getMessage());
                }
            } else {
                this.alert("Error", "Please Enter The Doctor ID!", AlertType.ERROR);
            }
        });

        resetButton.setOnAction(actionEvent -> {
            doctorIdField.setText(BLANK);
            doctorNameField.setText(BLANK);
            grossSalaryField.setText(BLANK);
            netSalaryField.setText(BLANK);
        });

        deleteButton.setOnAction(actionEvent -> {
            String doctorId = doctorIdField.getText().trim();
            if (!BLANK.equals(doctorId)) {
                try {
                    if (doctorDao.isUserExists(doctorId)) {
                        boolean isDeleteSuccess = doctorDao.deleteDoctor(doctorId);
                        if (isDeleteSuccess) {
                            this.alert("Delete", "Record Deleted Successfully!", AlertType.INFORMATION);
                        } else {
                            this.alert("Error", "Failed!", AlertType.ERROR);
                        }
                    } else {
                        this.alert("Error", "Doctor Does Not Exists In The Database", AlertType.ERROR);
                    }
                } catch (Exception exception) {
                    logger.log(Level.SEVERE, exception.getMessage());
                }
            } else {
                this.alert("Error", "Please Enter Doctor ID!", AlertType.ERROR);
            }
        });


        quitButton.setOnAction(actionEvent -> {
            this.alert("Close", "You Decided To Close The Database Connection And Quit The Application.", AlertType.INFORMATION);
            stage.close();
        });

        connectButton.setOnAction(actionEvent -> {

            this.alert("Connect", "Connection Successful", AlertType.INFORMATION);

        });

        Scene scene = new Scene(grid, 500, 275);
        stage.setScene(scene);

        stage.show();
    }

    public void alert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}