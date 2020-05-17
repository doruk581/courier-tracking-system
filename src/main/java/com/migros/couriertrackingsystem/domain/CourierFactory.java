package com.migros.couriertrackingsystem.domain;


import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;

public interface CourierFactory {

    Courier createFrom(final CourierControlRequest request);
}
