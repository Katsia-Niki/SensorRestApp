package by.nikiforova.springcourse.SensorApp.controller;

import by.nikiforova.springcourse.SensorApp.dto.MeasurementDTO;
import by.nikiforova.springcourse.SensorApp.dto.MeasurementResponse;
import by.nikiforova.springcourse.SensorApp.exceprion.MeasurementException;
import by.nikiforova.springcourse.SensorApp.model.Measurement;
import by.nikiforova.springcourse.SensorApp.service.MeasurementService;
import by.nikiforova.springcourse.SensorApp.util.ErrorsUtil;
import by.nikiforova.springcourse.SensorApp.util.MeasurementErrorResponse;
import by.nikiforova.springcourse.SensorApp.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) throws MeasurementException {
        Measurement measurementToAdd = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }

        measurementService.addMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public MeasurementResponse getAllMeasurements() {
        return new MeasurementResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO).toList());
    }

    @GetMapping("rainyDaysCount")
    public long getRainyDaysCount() {
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
