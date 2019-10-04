package com.mfh.pos.test;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.tbee.javafx.scene.layout.MigPane;

public class TimerTest extends Application {
    private Label timerLabel = new Label();
    private StringProperty timeSecs = new SimpleStringProperty();
    private Duration time = Duration.ZERO;

    @Override
    public void start(Stage stage) throws Exception {
        MigPane rootPanel = new MigPane();
        rootPanel.add(timerLabel, "grow");
        timerLabel.textProperty().bind(timeSecs);
        startTimeCount();

        Scene scene = new Scene(rootPanel);
        stage.setTitle("MFH_FX");
        stage.setScene(scene);
        stage.setMinHeight(300);
        stage.setMinWidth(300);
        stage.show();
    }

    private void startTimeCount() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                doSetTime(actionEvent);
                            }
                        })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void doSetTime(ActionEvent actionEvent) {
        Duration duration = ((KeyFrame) actionEvent.getSource()).getTime();
        time = time.add(duration);
        Double second = time.toSeconds();
        timeSecs.set(timeSet(second.intValue()));
    }

    private String timeSet(int seconds) {
        int hour = seconds / (60 * 60);
        seconds = seconds % (60 * 60);
        int minute = seconds / 60;
        seconds = seconds % 60;
        System.out.println(hour + ":" + minute + ":" + seconds);

        StringBuilder timeBuilder = new StringBuilder();
        timeBuilder
                .append("Hours: ").append(hour < 10 ? "0" + hour : hour)
                .append(", Mins: ").append(minute < 10 ? "0" + minute : minute)
                .append(", Sec: ").append(seconds < 10 ? "0" + seconds : seconds);
        return timeBuilder.toString();
    }
}
