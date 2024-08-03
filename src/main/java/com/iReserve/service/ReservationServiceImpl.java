package com.iReserve.service;

import com.iReserve.dto.ReservationDto;
import com.iReserve.entity.Reservation;
import com.iReserve.entity.User;
import com.iReserve.repository.ReservationRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final HttpSession httpSession;

    public ReservationServiceImpl(ReservationRepository reservationRepository, HttpSession httpSession) {
        this.reservationRepository = reservationRepository;
        this.httpSession = httpSession;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public String createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        mapDtoToEntity(reservationDto, reservation);
        return "Reservation created successfully!";
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public String updateReservation(ReservationDto reservationDto, Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        assert reservation != null;
        mapDtoToEntity(reservationDto, reservation);
        return "Reservation updated successfully!";
    }

    @Override
    @Transactional
    public String deleteReservation(Long id) {
        reservationRepository.deleteReservationById(id);
        return "Reservation deleted successfully!";
    }

    private void mapDtoToEntity(ReservationDto reservationDto, Reservation reservation) {
        User user = (User) httpSession.getAttribute("user");
        reservation.setUser(user);
        reservation.setTrainName(reservationDto.getTrainName());
        reservation.setTrainNumber(reservationDto.getTrainNumber());
        reservation.setReservationClass(reservationDto.getReservationClass());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setSeatNumber(reservationDto.getSeatNumber());
        reservation.setDestinationFrom(reservationDto.getDestinationFrom());
        reservation.setDestinationTo(reservationDto.getDestinationTo());
        reservationRepository.save(reservation);
    }
}
