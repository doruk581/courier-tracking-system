package com.migros.couriertrackingsystem.application.validation;


import com.migros.couriertrackingsystem.interfaces.ErrorCode;
import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;

import java.util.function.Predicate;

public class CourierControlValidationService implements ValidationService<CourierControlRequest> {

    private static final Predicate<String> isNullOrEmptyString = s -> s == null || s.isEmpty();

    @Override
    public ValidationResult validate(CourierControlRequest request) {
        if (isNullOrEmptyString.test(request.getCourier()))
            return ValidationResult.error("Courier is not acceptable!", ErrorCode.COURIERNOTVALID);

        if (request.getLat() == null)
            return ValidationResult.error("Latitude is not acceptable!", ErrorCode.LATITUDENOTVALID);

        if (request.getLng() == null)
            return ValidationResult.error("Longitude is not acceptable!", ErrorCode.LONGITUDENOTVALID);

        if (request.getTime() == null)
            return ValidationResult.error("Time is not acceptable!", ErrorCode.TIMENOTVALID);

        return ValidationResult.success();
    }
}
