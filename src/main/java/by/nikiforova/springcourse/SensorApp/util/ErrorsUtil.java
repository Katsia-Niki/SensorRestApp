package by.nikiforova.springcourse.SensorApp.util;

import by.nikiforova.springcourse.SensorApp.exceprion.MeasurementException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult) throws MeasurementException {
        StringBuilder errorsMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for(FieldError error : errors){
            errorsMsg.append(error.getField())
            .append(": ")
            .append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage())
                    .append(";");
        }
        throw new MeasurementException(errorsMsg.toString());
    }
}
