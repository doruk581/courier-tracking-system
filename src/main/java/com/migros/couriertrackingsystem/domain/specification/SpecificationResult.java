package com.migros.couriertrackingsystem.domain.specification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SpecificationResult {
    private Boolean isAvailable;
    private String message;

}
