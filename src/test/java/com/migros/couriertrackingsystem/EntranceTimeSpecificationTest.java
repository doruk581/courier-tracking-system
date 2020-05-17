package com.migros.couriertrackingsystem;

import com.migros.couriertrackingsystem.domain.specification.EntranceTimeSpecification;
import com.migros.couriertrackingsystem.domain.specification.SpecificationResult;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EntranceTimeSpecificationTest {

    private EntranceTimeSpecification entranceTimeSpecification;

    @Test
    public void controlThatIsSatisfiedMethodShouldReturnFalseWhenTimesEqual() {

        entranceTimeSpecification = EntranceTimeSpecification.create(LocalDateTime.now());

        final SpecificationResult expected = entranceTimeSpecification.isSatisfiedBy(LocalDateTime.now());

        assertThat(expected.getIsAvailable(), is(equalTo(Boolean.FALSE)));
    }

    @Test
    public void controlThatIsSatisfiedMethodShouldReturnFalseWhenTimesBetween30Seconds() {

        entranceTimeSpecification = EntranceTimeSpecification.create(LocalDateTime.now());

        final SpecificationResult expected = entranceTimeSpecification.isSatisfiedBy(LocalDateTime.now().plusSeconds(30));

        assertThat(expected.getIsAvailable(), is(equalTo(Boolean.FALSE)));
    }

    @Test
    public void controlThatIsSatisfiedMethodShouldReturnFalseWhenTimesBetween45Seconds() {

        entranceTimeSpecification = EntranceTimeSpecification.create(LocalDateTime.now());

        final SpecificationResult expected = entranceTimeSpecification.isSatisfiedBy(LocalDateTime.now().plusSeconds(45));

        assertThat(expected.getIsAvailable(), is(equalTo(Boolean.FALSE)));
    }

    @Test
    public void controlThatIsSatisfiedMethodShouldReturnFalseWhenTimesBetween1Minute() {

        entranceTimeSpecification = EntranceTimeSpecification.create(LocalDateTime.now());

        final SpecificationResult expected = entranceTimeSpecification.isSatisfiedBy(LocalDateTime.now().plusMinutes(1));

        assertThat(expected.getIsAvailable(), is(equalTo(Boolean.FALSE)));
    }

    @Test
    public void controlThatIsSatisfiedMethodShouldReturnTrueWhenTimesBetween61Seconds() {

        entranceTimeSpecification = EntranceTimeSpecification.create(LocalDateTime.now());

        final SpecificationResult expected = entranceTimeSpecification.isSatisfiedBy(LocalDateTime.now().minusMinutes(2));

        assertThat(expected.getIsAvailable(), is(equalTo(Boolean.TRUE)));
    }

}