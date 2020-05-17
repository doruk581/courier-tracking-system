package com.migros.couriertrackingsystem.domain;

import com.migros.couriertrackingsystem.domain.calculate.CalculationService;
import com.migros.couriertrackingsystem.domain.specification.SpecificationFactory;
import com.migros.couriertrackingsystem.infrastructure.storeservice.StoreService;
import com.migros.couriertrackingsystem.interfaces.response.CourierTotalDistanceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCourierTrackingServiceTest {

    @Mock
    private CourierTrackingRepository repository;

    @Mock
    private SpecificationFactory specificationFactory;

    @Mock
    private StoreService storeService;

    @Mock
    private CourierFactory courierFactory;

    @Mock
    private CalculationService calculationService;

    @InjectMocks
    private DefaultCourierTrackingService defaultCourierTrackingService;

    @Test
    public void controlThatGetCourierTotalDistanceShouldReturnCorrectResultWhenCourierListSizeNotEnough() {

        final LinkedList<Courier> couriers = new LinkedList<>();
        final Courier courier = Courier.builder().name("111").build();
        couriers.add(courier);

        when(repository.findCourierLocations(any())).thenReturn(couriers);

        final CourierTotalDistanceResponse response = defaultCourierTrackingService.getCourierTotalDistance("111");

        assertThat(response.getCourier(), equalTo("111"));
        assertThat(response.getTotalDistance(), equalTo(0L));

    }

}