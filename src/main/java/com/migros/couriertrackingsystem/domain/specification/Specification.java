package com.migros.couriertrackingsystem.domain.specification;

public interface Specification<E> {
    SpecificationResult isSatisfiedBy(E entity);
}