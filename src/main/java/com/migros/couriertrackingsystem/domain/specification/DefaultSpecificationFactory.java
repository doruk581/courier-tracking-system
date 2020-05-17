package com.migros.couriertrackingsystem.domain.specification;


import com.migros.couriertrackingsystem.domain.Store;
import com.migros.couriertrackingsystem.domain.calculate.CalculationService;

import java.time.LocalDateTime;
import java.util.List;
/*
* Factory Pattern
* Specification Pattern
* */
public class DefaultSpecificationFactory implements SpecificationFactory {

    @Override
    public Specification<List<Store>> isRadius100MeterAwaySpecification(Double latitude, Double longitude, CalculationService calculationService) {
        return IsRadius100MeterAwaySpecification.create(latitude, longitude, calculationService);
    }

    @Override
    public Specification<LocalDateTime> entranceTimeSpecification(LocalDateTime currentTime) {
        return EntranceTimeSpecification.create(currentTime);
    }
}
