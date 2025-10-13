package by.nikiforova.springcourse.SensorApp.util;

import by.nikiforova.springcourse.SensorApp.model.Sensor;
import by.nikiforova.springcourse.SensorApp.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if(sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "Sensor.name", "Сенсор с таким именем уже существует");
        }

    }
}
