package com.cliniccare.appointmentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cliniccare.appointmentservice.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(String patientId);
}
