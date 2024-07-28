package com.iReserve.seeder;

import com.iReserve.entity.Reservation;
import com.iReserve.repository.ReservationRepository;
import com.iReserve.repository.RoleRepository;
import com.iReserve.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReservationSeeder implements CommandLineRunner {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ReservationSeeder(ReservationRepository reservationRepository, UserRepository userRepository,
                             RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
       if(reservationRepository.count() == 0) {
           new UserSeeder(userRepository, roleRepository, passwordEncoder).run();
           seedReservation();
       }
    }

    private void seedReservation() {
        Reservation reservation = new Reservation();
        Long user_id = userRepository.findFirstByOrderByIdAsc().getId();
        reservation.setUser_id(user_id);
        reservation.setTrainName("TXE005FQ");
        reservation.setTrainNumber("5r4e3w2q1");
        reservation.setSeatNumber(11);
        reservation.setReservationClass("A");
        reservation.setReservationDate(LocalDate.ofEpochDay(3-7-2024));
        reservation.setDestinationFrom("Lagos");
        reservation.setDestinationTo("Owerri");
        reservationRepository.save(reservation);
    }
}
