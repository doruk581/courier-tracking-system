package com.migros.couriertrackingsystem.domain.specification;

import java.time.LocalDateTime;

public class EntranceTimeSpecification implements Specification<LocalDateTime> {

    private LocalDateTime currentTime;


    private EntranceTimeSpecification(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public static EntranceTimeSpecification create(LocalDateTime currentTime) {
        return new EntranceTimeSpecification(currentTime);
    }

    @Override
    public SpecificationResult isSatisfiedBy(LocalDateTime entity) {

        if (entity == null)
            return SpecificationResult.builder().isAvailable(Boolean.TRUE).build();

        final Boolean isAvailable = this.currentTime.minusMinutes(1).isBefore(entity)
                || this.currentTime.minusMinutes(1).isEqual(entity);

        return SpecificationResult.builder()
                .isAvailable(!isAvailable)
                .build();
    }
}
