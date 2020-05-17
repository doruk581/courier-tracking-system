package com.migros.couriertrackingsystem.infrastructure.storeservice;


import com.migros.couriertrackingsystem.domain.Store;

import java.util.List;

/*Treat this as a separate domain service
 * And make Http call..
 * */

public interface StoreService {
    List<Store> getStoreInformation();
}
