package com.iReserve.service;

import com.iReserve.dto.ReservationDto;
import com.iReserve.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    String createReservation(ReservationDto reservationDto);
    Reservation getReservationById(Long id);
    String updateReservation(ReservationDto reservationDto, Long id);
    String deleteReservation(Long id);
}