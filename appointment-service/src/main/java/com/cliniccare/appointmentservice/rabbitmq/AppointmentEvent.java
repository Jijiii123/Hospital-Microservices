package com.cliniccare.appointmentservice.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEvent {

    private Long appointmentId;
    private String patientId;
    private String doctorId;
    private String date;  // sous forme yyyy-MM-dd
    private String time;  // sous forme HH:mm
    private String status; // BOOKED / CANCELLED
}

