package com.migros.couriertrackingsystem.domain.specification;


import com.migros.couriertrackingsystem.domain.Store;
import com.migros.couriertrackingsystem.domain.calculate.CalculationService;

import java.util.List;

public class IsRadius100MeterAwaySpecification implements Specification<List<Store>> {

    private Double longitude;
    private Double latitude;
    private CalculationService calculationService;

    private IsRadius100MeterAwaySpecification(Double longitude, Double latitude, CalculationService calculationService) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.calculationService = calculationService;
    }

    public static IsRadius100MeterAwaySpecification create(Double longitude, Double latitude, CalculationService calculationService) {
        return new IsRadius100MeterAwaySpecification(longitude, latitude, calculationService);
    }

    @Override
    public SpecificationResult isSatisfiedBy(List<Store> entity) {

        for (final Store store : entity) {
            final Float inArea = calculationService.calculateDistanceBetweenPoints(this.latitude, this.longitude, store.getLatitude(), store.getLongitude());

            if (inArea <= 100f)
                return SpecificationResult.builder().isAvailable(Boolean.TRUE).message(store.getName()).build();
        }

        return SpecificationResult.builder().isAvailable(Boolean.FALSE).build();
    }

}
