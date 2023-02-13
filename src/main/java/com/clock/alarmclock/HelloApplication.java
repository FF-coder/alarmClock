package com.clock.alarmclock;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static com.clock.alarmclock.PopController.popController;

//首界面窗口 启动类
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UI.fxml"));



        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Alarm Clock!");
        scene.setOnMouseEntered(event -> {
           // System.out.println("scene触发enter");
            popController.searchClock();

        });
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}