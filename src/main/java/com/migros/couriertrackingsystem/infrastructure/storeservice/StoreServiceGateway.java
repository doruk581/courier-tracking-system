package com.migros.couriertrackingsystem.infrastructure.storeservice;


import com.migros.couriertrackingsystem.domain.Store;

import java.util.Arrays;
import java.util.List;

public class StoreServiceGateway implements StoreService {

    @Override
    public List<Store> getStoreInformation() {

        final Store atesehir = Store
                .builder()
                .latitude(40.9923307)
                .longitude(29.1244229)
                .name("Ataşehir MMM Migros")
                .build();

        final Store novada = Store
                .builder()
                .latitude(40.986106)
                .longitude(29.1161293)
                .name("Novada MMM Migros")
                .build();

        final Store ortakoy = Store
                .builder()
                .latitude(41.055783)
                .longitude(29.0210292)
                .name("Ortaköy MMM Migros")
                .build();

        final Store caddebostan = Store.builder()
                .latitude(40.9632463)
                .longitude(29.0630908)
                .name("Caddebostan MMM Migros")
                .build();

        final Store beylikduzu = Store.builder()
                .latitude(41.0066851)
                .longitude(28.6552262)
                .name("Beylikdüzü 5M Migros")
                .build();

        return Arrays.asList(atesehir, novada, ortakoy, caddebostan, beylikduzu);
    }
}
