package com.migros.couriertrackingsystem.domain.calculate;


public interface CalculationService {

    Float calculateDistanceBetweenPoints(final Double latP1, final Double longP1, final Double latP2, final Double longP2);
}
