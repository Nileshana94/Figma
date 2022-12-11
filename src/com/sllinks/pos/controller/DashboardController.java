package com.sllinks.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DashboardController {

    public Label lblDate;
    public Label lblTime;

    public void initialize(){
        setDateAndTime();

    }

    private void setDateAndTime() {
        /*Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String formatedDate = dateFormat.format(date);
        lblDate.setText(formatedDate);*/

        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        final DateFormat timeFormat = DateFormat.getDateInstance();
        Timeline timeLine =new Timeline(new KeyFrame(Duration.ZERO,e->{
            /*Calendar calendar=Calendar.getInstance();
            lblTime.setText(timeFormat.format(calendar.getTime()));*/
            lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        } ),new KeyFrame(Duration.seconds(1)));

        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }
}
