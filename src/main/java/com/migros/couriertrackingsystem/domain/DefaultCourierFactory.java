package com.migros.couriertrackingsystem.domain;


import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;

//Builder pattern as lombok
public class DefaultCourierFactory implements CourierFactory {

    @Override
    public Courier createFrom(CourierControlRequest request) {
        return Courier
                .builder()
                .latitude(request.getLat())
                .longitude(request.getLng())
                .name(request.getCourier())
                .time(request.getTime())
                .build();
    }
}
