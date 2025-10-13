package by.nikiforova.springcourse.SensorApp.service;

import by.nikiforova.springcourse.SensorApp.model.Sensor;
import by.nikiforova.springcourse.SensorApp.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void registerSensor(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
