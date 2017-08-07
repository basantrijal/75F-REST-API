package com.iot.rest.repository;

import com.iot.rest.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by basant on 8/7/17.
 */
@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findByDeviceId(String deviceId);
}
