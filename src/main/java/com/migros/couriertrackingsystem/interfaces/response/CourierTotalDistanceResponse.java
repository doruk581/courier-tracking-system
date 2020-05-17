package com.migros.couriertrackingsystem.interfaces.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourierTotalDistanceResponse {

    private String courier;
    private Long totalDistance;
}
