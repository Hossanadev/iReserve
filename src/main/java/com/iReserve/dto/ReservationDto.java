package com.iReserve.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReservationDto {
    @NotEmpty(message = "Required!")
    private String trainNumber;

    @NotEmpty(message = "Required!")
    private String trainName;

    @NotEmpty(message = "Required!")
    private int seatNumber;

    @NotEmpty(message = "Required!")
    private String reservationClass;

    @NotEmpty(message = "Required!")

    @NotEmpty(message = "Required!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate reservationDate;

    @NotEmpty(message = "Required!")
    private String destinationFrom;

    @NotEmpty(message = "Required!")
    private String destinationTo;
}
