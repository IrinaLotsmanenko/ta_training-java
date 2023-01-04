package com.github.vitalliuss.services;

import com.github.vitalliuss.models.MilitaryType;
import com.github.vitalliuss.models.planes.ExperimentalPlane;
import com.github.vitalliuss.models.planes.MilitaryPlane;
import com.github.vitalliuss.models.planes.PassengerPlane;
import com.github.vitalliuss.models.planes.Plane;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Vitali Shulha
 * @version 1.1
 */
public class Airport {
    private List<Plane> planes;

    public Airport(List<Plane> planes) {
        this.planes = planes;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public List<Plane> getPassengerPlanes() {
        List<Plane> passengerPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add(plane);
            }
        }
        return passengerPlanes;
    }

    public List<Plane> getMilitaryPlanes() {
        List<Plane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add(plane);
            }
        }
        return militaryPlanes;
    }

    public List<Plane> getTransportMilitaryPlanes() {
        List<Plane> transportMilitaryPlanes = new ArrayList<>();
        for (Plane militaryPlanes : getMilitaryPlanes()) {
            if (((MilitaryPlane) militaryPlanes).getMilitaryType() == MilitaryType.TRANSPORT) {
                transportMilitaryPlanes.add(militaryPlanes);
            }
        }
        return transportMilitaryPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengerCapacity() {
        PassengerPlane planeWithMaxCapacity = (PassengerPlane) getPassengerPlanes().get(0);
        for (Plane passengerPlane : getPassengerPlanes()) {
            if (((PassengerPlane) passengerPlane).getPassengerCapacity() > planeWithMaxCapacity.getPassengerCapacity()) {
                planeWithMaxCapacity = (PassengerPlane) passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<Plane> getBomberMilitaryPlanes() {
        List<Plane> bomberMilitaryPlanes = new ArrayList<>();
        for (Plane plane : getMilitaryPlanes()) {
            if (((MilitaryPlane) plane).getMilitaryType() == MilitaryType.BOMBER) {
                bomberMilitaryPlanes.add(plane);
            }
        }
        return bomberMilitaryPlanes;
    }

    public List<Plane> getExperimentalPlanes() {
        List<Plane> experimentalPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                experimentalPlanes.add(plane);
            }
        }
        return experimentalPlanes;
    }

    public void sortByMaxFlightDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
    }

    public void sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
    }

    public void sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

}
