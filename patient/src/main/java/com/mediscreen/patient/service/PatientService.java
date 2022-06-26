package com.mediscreen.patient.service;

import com.mediscreen.patient.exceptions.PatientException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient newPatient) throws PatientException {
        if (!newPatient.getFirstName().isBlank() && !newPatient.getLastName().isBlank()) {
            Patient savedPatient = patientRepository.save(newPatient);
            log.info("patient {} saved", newPatient.getFirstName());
            return savedPatient;
        }

        log.error("Unable to save new patient");
        throw new PatientException("First name and/or last name not accepted");
    }

    public Patient updatePatient(Patient patient) throws PatientException {
        if (patientRepository.existsById(patient.getId())) {
            Patient updatedPatient = patientRepository.save(patient);
            log.info("patient id {} updated", patient.getId());
            return updatedPatient;
        }

        log.error("The requested resource doesn't exist.");
        throw new PatientException("The requested resource doesn't exist.");
    }

    public List<Patient> patientList() {
        List<Patient> patients = patientRepository.findAll();
        if (!patients.isEmpty()) {
            log.info("patient(s) retrieved.");
            return patients;
        }
        log.debug("No patient found");
        return List.of();
    }

    public Patient findPatientById(long id) throws PatientException {
        Patient patient = patientRepository.findPatientById(id);
        if (patient != null) {
            log.info("patient found");
            return patient;
        }
        log.error("No patient found");
        throw new PatientException("No patient found");
    }

    public void deletePatient(long id) throws PatientException {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            log.info("patient entity deleted");
        } else {
            log.error("The requested resource doesn't exist.");
            throw new PatientException("Couldn't perform the operation. The requested resource doesn't exist.");
        }
    }

}
