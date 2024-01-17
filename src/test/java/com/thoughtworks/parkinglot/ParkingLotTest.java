package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.parkinglot.exceptions.ParkingLotFullException;
import main.java.com.thoughtworks.parkinglot.exceptions.NotInTheParkingLotException;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.beans.Transient;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ParkingLotTest {

    @Test
    void shouldParkACarWhenSpaceIsAvailable() throws ParkingLotFullException, AlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car = new Car();

        parkingLot.park(car);

        assertThat(parkingLot.isParked(car), is(true));
    }

    @Test
    void shouldNotParkACarWhenSpaceIsNotAvailable() throws ParkingLotFullException, AlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Parkable car1 = new Car();
        Parkable car2 = new Car();

        parkingLot.park(car1);

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car2));
    }

    @Test
    void shouldNotParkACarWhenItIsAlreadyParked() throws ParkingLotFullException, AlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car = new Car();

        parkingLot.park(car);

        assertThrows(AlreadyParkedException.class, () -> parkingLot.park(car));
    }

    @Test
    void shouldReturnTrueWhenCarIsUnparked() throws ParkingLotFullException, AlreadyParkedException,NotInTheParkingLotException{
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car = new Car();

        parkingLot.park(car);
        parkingLot.unpark(car);

        assertFalse(parkingLot.isParked(car));
    }

    @Test
    void shoudlUnparkThecarIfpresent()throws ParkingLotFullException, AlreadyParkedException, NotInTheParkingLotException{
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car = new Car();

        parkingLot.park(car);
        parkingLot.unpark(car);

        assertFalse(parkingLot.isParked(car));
    }

    @Test 
    void shouldNotUnparkTheCarWhenNotPresent() throws NotInTheParkingLotException{
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car = new Car();

        assertThrows(NotInTheParkingLotException.class , () -> parkingLot.unpark(car));
    }
}
