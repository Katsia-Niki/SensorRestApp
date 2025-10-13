package by.nikiforova.springcourse.SensorApp.controller;

import by.nikiforova.springcourse.SensorApp.exceprion.MeasurementException;
import by.nikiforova.springcourse.SensorApp.util.MeasurementErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {



    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
