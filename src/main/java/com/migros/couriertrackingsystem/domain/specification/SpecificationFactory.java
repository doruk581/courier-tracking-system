package com.migros.couriertrackingsystem.domain.specification;


import com.migros.couriertrackingsystem.domain.Store;
import com.migros.couriertrackingsystem.domain.calculate.CalculationService;

import java.time.LocalDateTime;
import java.util.List;

public interface SpecificationFactory {

    Specification<List<Store>> isRadius100MeterAwaySpecification(final Double latitude, final Double longitude, final CalculationService calculationService);

    Specification<LocalDateTime> entranceTimeSpecification(final LocalDateTime currentTime);

}
