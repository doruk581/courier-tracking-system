package com.migros.couriertrackingsystem.application;


import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;
import com.migros.couriertrackingsystem.interfaces.response.CourierTotalDistanceResponse;

public interface CourierTrackingService {

    void controlCourierLocation(final CourierControlRequest request);

    CourierTotalDistanceResponse getCourierTotalDistance(final String id);
}
