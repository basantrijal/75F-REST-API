package com.iot.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iot.rest.utils.CustomDateAndTimeDeserialize;
import com.iot.rest.utils.CustomDateAndTimeSerialize;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by basant on 8/7/17.
 */
@Entity
@Table(name="weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotNull(message="DeviceId is required.")
    @Column(name="device_id", nullable = false)
    private String deviceId;

    @NotNull(message="Timestamp is required.")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using=CustomDateAndTimeDeserialize.class)
    @JsonSerialize(using= CustomDateAndTimeSerialize.class)
    @Column(name="timestamp", nullable = false)
    private Date timestamp;

    @NotNull(message="Temperature is required.")
    @Column(name="temperature", nullable = false, precision = 3, scale = 1)
    private BigDecimal temperature;

    @NotNull(message="Humidity is required.")
    @Min(0)
    @Max(100)
    @Column(name="humidity", nullable = false)
    private Integer humidity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
