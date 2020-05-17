package com.migros.couriertrackingsystem.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    private String name;

    private Double latitude;

    private Double longitude;

    private LocalDateTime entranceTime;

}
