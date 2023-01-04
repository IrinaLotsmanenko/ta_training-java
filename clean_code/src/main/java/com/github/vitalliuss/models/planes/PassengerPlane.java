package com.github.vitalliuss.models.planes;

import java.util.Objects;

public class PassengerPlane extends Plane {
    private int passengerCapacity;

    public PassengerPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, int passengerCapacity) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengerCapacity=" + passengerCapacity +
                        '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PassengerPlane)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PassengerPlane plane = (PassengerPlane) o;
        return passengerCapacity == plane.passengerCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerCapacity);
    }
}
