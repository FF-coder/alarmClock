package com.clock.alarmclock;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//主控制器
public class PopController implements Initializable {
    public static PopController popController;
    @FXML
    private Label currentTimeText;
    @FXML
    private Pane gridPane;
    @FXML
    private TextField yearInput;
    @FXML
    private TextField monthInput;
    @FXML
    private TextField dayInput;
    @FXML
    private TextField hourInput;
    @FXML
    private TextField minuteInput;
    @FXML
    private TextField secondInput;
    @FXML
    private TextField messageInput;

    public String Nowtime;

    public Date CurrentTime;
    public ArrayList<EventStorage> eventStorage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popController = this;
    }

    public PopController(){
        eventStorage=new ArrayList<>();
        EventHandler<ActionEvent> eventHandler= new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent) {
                getCurrentTime();

            }
        };
        Timeline animation=new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));

        //循环计数
        animation.setCycleCount(Timeline.INDEFINITE);
       // animation.pause();
        animation.play();





    }
    public void searchClock ()
    {

       // System.out.println("searchClock长度:"+eventStorage.size());
        if(eventStorage.size()!=0)
        {
           // System.out.println("现有第一个闹钟："+eventStorage.get(0).clockTime);
            //System.out.println("现有ListLen:"+eventStorage.size());
            for (int i=0;i<eventStorage.size();i++)
            {
              //  System.out.println("进入for search！");
                Date iEventTime= (eventStorage.get(i)).getClockTime();
                System.out.println(iEventTime);
                System.out.println(CurrentTime);
                if(Math.abs(CurrentTime.getTime()-iEventTime.getTime())<4000)
                {
                    //System.out.println("进入for search 的 if中！");
                    InfoWindow alarmClockPop=new InfoWindow(2);
                    alarmClockPop.display("闹钟提醒！","闹钟时间到啦！\n闹钟时间："+(eventStorage.get(i)).getClockTime()+"\n提醒信息："+(eventStorage.get(i)).getMessage());
                }

            }
        }
    }
    @FXML
    protected void SetUpButtonClick() throws ParseException {
        getCurrentTime();
        ArrayList<String> inputList=new ArrayList<>();
        inputList.add(yearInput.getText());
        inputList.add(monthInput.getText());
        inputList.add(dayInput.getText());
        inputList.add(hourInput.getText());
        inputList.add(minuteInput.getText());
        inputList.add(secondInput.getText());
        inputList.add(messageInput.getText());
        int resFlag=1;//在当前时间之后的闹钟
        for(String temp:inputList)
        {
            if (Objects.equals(temp, ""))resFlag=0;
        }

        InfoWindow alertWindow=new InfoWindow(resFlag);
        if(resFlag==0)
        {
            alertWindow.display("出错啦", "闹钟信息未填充完整!\n请将年月日时分秒和提醒信息都填完整哦！");
            //System.out.println(alertWindow.getRes());
        }
        else
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeDate=String.format("%s-%s-%s",inputList.get(0),inputList.get(1),inputList.get(2));
            String timeSecond=String.format("%s:%s:%s",inputList.get(3),inputList.get(4),inputList.get(5));
            String timeString=String.format("%s %s",timeDate,timeSecond);
            Date timeNow=df.parse(Nowtime);
            Date timeInput=df.parse(timeString);
            String TimeInputString=df.format(timeInput);
            System.out.println("clock"+timeInput);
            System.out.println("now:"+timeNow);
            EventStorage eventClock=new EventStorage(inputList.get(6),timeInput);
           // ArrayList<EventStorage> eventStorage=new ArrayList<>();
            if(timeInput.after(timeNow))
            {
                System.out.println("after");
                (this.eventStorage).add(eventClock);
                System.out.println("EVENTLIST:"+eventStorage.get(0).clockTime);
                alertWindow.display("保存成功", "闹钟信息保存成功!\n闹钟时间："+TimeInputString+"\n提醒信息："+inputList.get(6)+"\n到指定时间会进行消息弹窗提醒哦！");
               // System.out.println(alertWindow.getRes());
            }
            else if(timeInput.equals(timeNow))
            {
                System.out.println("equals");
                alertWindow.display("闹钟提醒", "所设的闹钟时间到了哦!\n闹钟时间："+TimeInputString+"\n提醒信息："+inputList.get(6));
            //    System.out.println(alertWindow.getRes());

            }
            else
            {
                System.out.println("before");
                alertWindow.display("出错啦", "闹钟信息有误!\n请填写当前时间之后的时间点哦！");
                //System.out.println(alertWindow.getRes());
            }
            searchClock();

        }


    }
    @FXML
    public void getCurrentTime()
    {
        Date currentTime=new Date();
        SimpleDateFormat timeText=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(timeText.format(currentTime));
        String timeString=timeText.format(currentTime);
        currentTimeText.setText(timeString);
        this.Nowtime=timeString;
        //this.GMT=currentTime.getTime();
        this.CurrentTime=currentTime;
       // System.out.println(this.Nowtime+",GMT:"+this.GMT);
    }




}