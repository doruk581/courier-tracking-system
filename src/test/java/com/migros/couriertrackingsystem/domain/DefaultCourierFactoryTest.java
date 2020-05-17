package com.migros.couriertrackingsystem.domain;

import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultCourierFactoryTest {

    private DefaultCourierFactory defaultCourierFactory;

    @Before
    public void initiate() {
        defaultCourierFactory = new DefaultCourierFactory();
    }

    @Test
    public void controlThatCreateFromMethodWorksCorrectly() {
        final CourierControlRequest courierControlRequest = new CourierControlRequest();
        courierControlRequest.setTime(LocalDateTime.of(2020, 5, 20, 5, 30, 30));
        courierControlRequest.setLng(23.42);
        courierControlRequest.setLat(20.10);
        courierControlRequest.setCourier("Doruk");

        final Courier courier = defaultCourierFactory.createFrom(courierControlRequest);

        assertThat(courier.getLatitude(), equalTo(courierControlRequest.getLat()));
        assertThat(courier.getLongitude(), equalTo(courierControlRequest.getLng()));
        assertThat(courier.getTime(), equalTo(courierControlRequest.getTime()));
        assertThat(courier.getName(), equalTo(courierControlRequest.getCourier()));
    }

}