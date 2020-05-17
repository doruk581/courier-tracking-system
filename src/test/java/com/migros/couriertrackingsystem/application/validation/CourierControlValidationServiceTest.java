package com.migros.couriertrackingsystem.application.validation;

import com.migros.couriertrackingsystem.interfaces.ErrorCode;
import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CourierControlValidationServiceTest {

    private CourierControlValidationService courierControlValidationService;

    @Before
    public void initiate() {
        courierControlValidationService = new CourierControlValidationService();
    }

    @Test
    public void controlThatValidateMethodShouldReturnFalseCourierIdIsNull() {
        final CourierControlRequest courierControlRequest = new CourierControlRequest();

        final ValidationResult validationResult = courierControlValidationService.validate(courierControlRequest);

        assertThat(validationResult.getIsValid(), is(equalTo(Boolean.FALSE)));
        assertThat(validationResult.getErrorCode(), is(equalTo(ErrorCode.COURIERNOTVALID)));
    }

    @Test
    public void controlThatValidateMethodShouldReturnFalseCourierIdIsEmpty() {
        final CourierControlRequest courierControlRequest = new CourierControlRequest();
        courierControlRequest.setCourier("");

        final ValidationResult validationResult = courierControlValidationService.validate(courierControlRequest);

        assertThat(validationResult.getIsValid(), is(equalTo(Boolean.FALSE)));
        assertThat(validationResult.getErrorCode(), is(equalTo(ErrorCode.COURIERNOTVALID)));
    }

    @Test
    public void controlThatValidateMethodShouldReturnFalseWhenLatitudeIsNull() {
        final CourierControlRequest courierControlRequest = new CourierControlRequest();
        courierControlRequest.setCourier("dada");

        final ValidationResult validationResult = courierControlValidationService.validate(courierControlRequest);

        assertThat(validationResult.getIsValid(), is(equalTo(Boolean.FALSE)));
        assertThat(validationResult.getErrorCode(), is(equalTo(ErrorCode.LATITUDENOTVALID)));
    }

    @Test
    public void controlThatValidateMethodShouldReturnFalseWhenLongitudeIsNull() {
        final CourierControlRequest courierControlRequest = new CourierControlRequest();
        courierControlRequest.setCourier("dada");
        courierControlRequest.setLat(40d);

        final ValidationResult validationResult = courierControlValidationService.validate(courierControlRequest);

        assertThat(validationResult.getIsValid(), is(equalTo(Boolean.FALSE)));
        assertThat(validationResult.getErrorCode(), is(equalTo(ErrorCode.LONGITUDENOTVALID)));
    }

    @Test
    public void controlThatValidateMethodShouldReturnFalseWhenTimeIsNull() {
        final CourierControlRequest courierControlRequest = new CourierControlRequest();
        courierControlRequest.setCourier("dada");
        courierControlRequest.setLat(40d);
        courierControlRequest.setLng(40d);

        final ValidationResult validationResult = courierControlValidationService.validate(courierControlRequest);

        assertThat(validationResult.getIsValid(), is(equalTo(Boolean.FALSE)));
        assertThat(validationResult.getErrorCode(), is(equalTo(ErrorCode.TIMENOTVALID)));
    }

    @Test
    public void controlThatValidateMethodShouldReturnTrueWhenAllInputsValid() {
        final CourierControlRequest courierControlRequest = new CourierControlRequest();
        courierControlRequest.setCourier("dada");
        courierControlRequest.setLat(40d);
        courierControlRequest.setLng(40d);
        courierControlRequest.setTime(LocalDateTime.now());

        final ValidationResult validationResult = courierControlValidationService.validate(courierControlRequest);

        assertThat(validationResult.getIsValid(), is(equalTo(Boolean.TRUE)));
    }

}