package com.clock.alarmclock;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//弹出窗口信息
public class InfoWindow {
    public int res;

    public InfoWindow(int res)
    {
        this.res=res;
    }
//    public void setRes(int res) {
//        this.res = res;
//    }
    public int getRes(){
        return this.res;
    }

    public void display(String title, String msg){
        Stage stage = new Stage();
        stage.setMinHeight(250);
        stage.setMinWidth(280);
        stage.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label();
        label.setText(msg);
        label.setAlignment(Pos.CENTER_LEFT);
        Button btn1 = new Button("OK");
        btn1.setOnMouseClicked(event -> {
          //  res=true;
            System.out.println("OK");

            stage.close();
        });
        btn1.setAlignment(Pos.BOTTOM_RIGHT);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,btn1);
        //设置居中
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox,200,200);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.showAndWait();

       //return res;
    }
}
