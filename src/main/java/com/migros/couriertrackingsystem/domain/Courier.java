package com.migros.couriertrackingsystem.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

    private Double longitude;
    private Double latitude;
    private String name;
    private LocalDateTime time;
    private String storeName;
    private LocalDateTime storeEntranceTime;


    public void enrichWithStoreInformation(final String storeName) {
        this.setStoreName(storeName);
        this.setStoreEntranceTime(this.time);
    }
}
