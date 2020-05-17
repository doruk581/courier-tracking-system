package com.migros.couriertrackingsystem.domain;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Optional;

public interface CourierTrackingRepository {

    void save(final Courier courier);

    Optional<LocalDateTime> findLastEntrance(final String storeName, final String courierName);

    LinkedList<Courier> findCourierLocations(final String courier);
}
