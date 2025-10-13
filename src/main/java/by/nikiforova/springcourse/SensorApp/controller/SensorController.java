package by.nikiforova.springcourse.SensorApp.controller;

import by.nikiforova.springcourse.SensorApp.dto.SensorDTO;
import by.nikiforova.springcourse.SensorApp.exceprion.MeasurementException;
import by.nikiforova.springcourse.SensorApp.model.Sensor;
import by.nikiforova.springcourse.SensorApp.service.SensorService;
import by.nikiforova.springcourse.SensorApp.util.ErrorsUtil;
import by.nikiforova.springcourse.SensorApp.util.MeasurementErrorResponse;
import by.nikiforova.springcourse.SensorApp.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                     BindingResult bindingResult) throws MeasurementException {
        Sensor sensorToAdd = convertToSensor(sensorDTO);
        sensorValidator.validate(sensorToAdd, bindingResult);
        if(bindingResult.hasErrors()){
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }

        sensorService.registerSensor(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
