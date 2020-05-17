package com.migros.couriertrackingsystem;

import com.migros.couriertrackingsystem.domain.Store;
import com.migros.couriertrackingsystem.domain.calculate.CalculationService;
import com.migros.couriertrackingsystem.domain.calculate.DefaultCalculationService;
import com.migros.couriertrackingsystem.domain.specification.IsRadius100MeterAwaySpecification;
import com.migros.couriertrackingsystem.domain.specification.SpecificationResult;
import com.migros.couriertrackingsystem.infrastructure.storeservice.StoreService;
import com.migros.couriertrackingsystem.infrastructure.storeservice.StoreServiceGateway;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class IsRadius100MeterAwaySpecificationTest {

    private CalculationService calculationService;

    private IsRadius100MeterAwaySpecification isRadius100MeterAwaySpecification;

    private StoreService storeService;

    @Before
    public void initiate() {
        calculationService = new DefaultCalculationService();
        storeService = new StoreServiceGateway();
    }

    @Test
    public void controlThatIsSatisfiedMethodReturnTrueWhenTwoLocationsNearBetween100Meters() {
        isRadius100MeterAwaySpecification = IsRadius100MeterAwaySpecification.create(29.1244229, 40.9923307,calculationService);

        final List<Store> storeList = storeService.getStoreInformation();

        final SpecificationResult expected = isRadius100MeterAwaySpecification.isSatisfiedBy(storeList);

        assertTrue(expected.getIsAvailable());
    }

}