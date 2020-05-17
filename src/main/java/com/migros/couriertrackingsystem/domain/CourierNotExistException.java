package com.migros.couriertrackingsystem.domain;

public class CourierNotExistException extends RuntimeException {

    private String id;

    private CourierNotExistException(String id) {
        this.id = id;
    }

    public static CourierNotExistException create(String id) {
        return new CourierNotExistException(id);
    }

    @Override
    public String toString() {
        return "Courier with id: " + this.id + " does not exist in system.";
    }
}
