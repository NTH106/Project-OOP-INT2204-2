package com.oop.iotapp;

public class TemperatureData {
    private Long userId, status, temperature, fanSpeed, ecoMode, timerMode;
    private String timer_start, timer_stop;

    public TemperatureData(){
        this.userId = 0L;
        this.status = 0L;
        this.temperature = 0L;
        this.fanSpeed = 0L;
        this.ecoMode = 0L;
        this.timerMode = 0L;
        this.timer_start = "#";
        this.timer_stop = "#";
    }

    public TemperatureData(Long userId, Long status, Long temperature, Long fanSpeed, Long ecoMode, Long timerMode, String timer_start, String timer_stop) {
        this.userId = userId;
        this.status = status;
        this.temperature = temperature;
        this.fanSpeed = fanSpeed;
        this.ecoMode = ecoMode;
        this.timerMode = timerMode;
        this.timer_start = timer_start;
        this.timer_stop = timer_stop;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
        this.temperature = temperature;
    }

    public Long getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(Long fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    public Long getEcoMode() {
        return ecoMode;
    }

    public void setEcoMode(Long ecoMode) {
        this.ecoMode = ecoMode;
    }

    public Long getTimerMode() {
        return timerMode;
    }

    public void setTimerMode(Long timerMode) {
        this.timerMode = timerMode;
    }

    public String getTimer_start() {
        return timer_start;
    }

    public void setTimer_start(String timer_start) {
        this.timer_start = timer_start;
    }

    public String getTimer_stop() {
        return timer_stop;
    }

    public void setTimer_stop(String timer_stop) {
        this.timer_stop = timer_stop;
    }
}
