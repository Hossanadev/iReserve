package com.iReserve.controller;

import com.iReserve.entity.Reservation;
import com.iReserve.service.ReservationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {
    private final ReservationServiceImpl reservationServiceImpl;

    public APIController(ReservationServiceImpl reservationServiceImpl) {
        this.reservationServiceImpl = reservationServiceImpl;
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<Reservation> getSingleReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationServiceImpl.getReservationById(reservationId);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservation);
    }
}