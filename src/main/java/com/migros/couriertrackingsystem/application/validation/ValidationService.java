package com.migros.couriertrackingsystem.application.validation;

public interface ValidationService<T> {
    ValidationResult validate(T request);
}
