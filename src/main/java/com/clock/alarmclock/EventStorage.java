package com.clock.alarmclock;

import java.util.Date;

//用于存储事件闹钟
public class EventStorage {
    String message;
    Date clockTime;
    public EventStorage(){
        message=null;
        clockTime=null;
    }
    public EventStorage(String message,Date clockTime)
    {
        this.message=message;
        this.clockTime=clockTime;
    }
    public Date getClockTime()
    {
        return this.clockTime;
    }
    public String getMessage()
    {
        return this.message;
    }
}
