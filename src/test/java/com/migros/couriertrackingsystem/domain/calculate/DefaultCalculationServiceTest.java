package com.migros.couriertrackingsystem.domain.calculate;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DefaultCalculationServiceTest {

    private DefaultCalculationService defaultCalculationService;

    @Before
    public void initiate() {
        defaultCalculationService = new DefaultCalculationService();
    }

    @Test
    public void controlThatCalculateDistanceBetweenPointsShouldReturnZeroWhenBothPointsSame() {
        final Double lat1 = 40.9923307;
        final Double long1 = 29.1244229;

        final Double lat2 = 40.9923307;
        final Double long2 = 29.1244229;

        final Float expected = defaultCalculationService.calculateDistanceBetweenPoints(lat1, long1, lat2, long2);

        assertThat(expected, is(equalTo(0f)));
    }

    @Test
    public void controlThatCalculateDistanceBetweenPointsShouldReturnCorrectValue() {
        final Double lat1 = 40.9923307;
        final Double long1 = 29.1244229;

        final Double lat2 = 40.986106;
        final Double long2 = 29.1161293;

        final Float expected = defaultCalculationService.calculateDistanceBetweenPoints(lat1, long1, lat2, long2);

        assertThat(expected, is(equalTo(981.65686F)));
    }


}