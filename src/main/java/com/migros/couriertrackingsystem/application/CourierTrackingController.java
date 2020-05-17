package com.migros.couriertrackingsystem.application;

import com.migros.couriertrackingsystem.application.validation.ValidationResult;
import com.migros.couriertrackingsystem.application.validation.ValidationService;
import com.migros.couriertrackingsystem.interfaces.ApiError;
import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@Slf4j
public class CourierTrackingController {

    private final CourierTrackingService courierTrackingService;

    private final ValidationService<CourierControlRequest> courierControlRequestValidationService;

    public CourierTrackingController(CourierTrackingService courierTrackingService, ValidationService<CourierControlRequest> courierControlRequestValidationService) {
        this.courierTrackingService = courierTrackingService;
        this.courierControlRequestValidationService = courierControlRequestValidationService;
    }

    @GetMapping(path = "/hello")
    public String returnOk() {
        return "OK";
    }

    @PostMapping(path = "/courier/control")
    public ResponseEntity courierControl(@RequestBody CourierControlRequest command) {

        final ValidationResult validationResult = courierControlRequestValidationService.validate(command);

        if (!validationResult.getIsValid()) {
            log.warn(validationResult.getMessage());
            return new ResponseEntity<>(ApiError.create(HttpStatus.BAD_REQUEST.value(), validationResult.getMessage(), validationResult.getErrorCode().code()), HttpStatus.BAD_REQUEST);
        }
        courierTrackingService.controlCourierLocation(command);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/courier/totaldistance/{id}")
    public ResponseEntity getTotalDistance(@PathVariable("id") String id) {
        return ResponseEntity.ok(courierTrackingService.getCourierTotalDistance(id));
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        log.warn("Unhandled exception occurred!", ex);
        return new ResponseEntity<>(ApiError.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), "CTS1000"), HttpStatus.BAD_REQUEST);
    }
}
