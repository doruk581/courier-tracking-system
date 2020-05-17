package com.migros.couriertrackingsystem.domain.calculate;

import com.migros.couriertrackingsystem.domain.Constants;


public class DefaultCalculationService implements CalculationService {

    @Override
    public Float calculateDistanceBetweenPoints(Double latP1, Double longP1, Double latP2, Double longP2) {

        final double dLat = Math.toRadians(latP1 - latP2);
        final double dLng = Math.toRadians(longP1 - longP2);
        final double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latP2)) * Math.cos(Math.toRadians(latP1)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        final float dist = (float) (Constants.earthRadius * c);

        return dist;
    }


}
