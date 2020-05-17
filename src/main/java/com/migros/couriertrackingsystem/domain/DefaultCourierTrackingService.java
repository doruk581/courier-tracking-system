package com.migros.couriertrackingsystem.domain;

import com.migros.couriertrackingsystem.application.CourierTrackingService;
import com.migros.couriertrackingsystem.domain.calculate.CalculationService;
import com.migros.couriertrackingsystem.domain.specification.Specification;
import com.migros.couriertrackingsystem.domain.specification.SpecificationFactory;
import com.migros.couriertrackingsystem.domain.specification.SpecificationResult;
import com.migros.couriertrackingsystem.infrastructure.storeservice.StoreService;
import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;
import com.migros.couriertrackingsystem.interfaces.response.CourierTotalDistanceResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class DefaultCourierTrackingService implements CourierTrackingService {

    private final CourierTrackingRepository repository;

    private final StoreService storeService;

    private final SpecificationFactory specificationFactory;

    private final CourierFactory courierFactory;

    private final CalculationService calculationService;

    public DefaultCourierTrackingService(CourierTrackingRepository repository,
                                         StoreService storeService,
                                         SpecificationFactory specificationFactory,
                                         CourierFactory courierFactory,
                                         CalculationService calculationService) {
        this.repository = repository;
        this.storeService = storeService;
        this.specificationFactory = specificationFactory;
        this.courierFactory = courierFactory;
        this.calculationService = calculationService;
    }

    @Override
    public void controlCourierLocation(final CourierControlRequest request) {
        final Courier courier = courierFactory.createFrom(request);

        final List<Store> storeList = storeService.getStoreInformation();

        final Specification<List<Store>> storeSpecification = specificationFactory.isRadius100MeterAwaySpecification(courier.getLongitude(), courier.getLatitude(), calculationService);

        final SpecificationResult specificationResult = storeSpecification.isSatisfiedBy(storeList);

        final String storeName = specificationResult.getMessage();

        if (specificationResult.getIsAvailable()) {

            final LocalDateTime lastEntranceTime = repository.findLastEntrance(storeName, courier.getName()).orElse(null);

            final Specification<LocalDateTime> timeSpecification = specificationFactory.entranceTimeSpecification(courier.getTime());

            final SpecificationResult timeResult = timeSpecification.isSatisfiedBy(lastEntranceTime);

            if (timeResult.getIsAvailable()) {
                log.info("Courier:{} in radius of 100 meters of  Store:{}", courier.getName(), storeName);
                courier.enrichWithStoreInformation(storeName);
            }
        }

        repository.save(courier);
    }

    @Override
    public CourierTotalDistanceResponse getCourierTotalDistance(String id) {

        final LinkedList<Courier> couriers = repository.findCourierLocations(id);

        if (couriers.size() <= 1)
            return CourierTotalDistanceResponse.builder().courier(id).totalDistance((long) 0).build();

        float sum = 0;
        for (int i = 0; i < couriers.size() - 1; i++) {
            final int pos = (i + 1);
            final Courier point1 = couriers.get(i);
            final Courier point2 = couriers.get(pos);

            final float distance = calculationService.calculateDistanceBetweenPoints(point1.getLatitude(), point1.getLongitude(), point2.getLatitude(), point2.getLongitude());

            sum = sum + distance;
        }
        return CourierTotalDistanceResponse.builder().totalDistance((long) sum).courier(id).build();
    }
}
