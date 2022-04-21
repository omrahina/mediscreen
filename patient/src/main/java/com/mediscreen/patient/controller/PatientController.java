package com.mediscreen.patient.controller;

import com.mediscreen.patient.exceptions.PatientException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patient/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        log.info("Request add a new patient");
        try {
            Patient savedPatient = patientService.addPatient(patient);
            return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
        } catch (PatientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("patient/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient) {
        log.info("Request update a patient");
        patient.setId(id);
        try {
            return new ResponseEntity<>(patientService.updatePatient(patient), HttpStatus.OK);
        } catch (PatientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") long id) {
        log.info("Request patient " + id);
        try {
            Patient patient = patientService.findPatientById(id);
            return new ResponseEntity<>(patient, HttpStatus.FOUND);
        } catch (PatientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/patient/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") long id) {
        log.info("Request delete patient " + id);
        try {
            patientService.deletePatient(id);
            return new ResponseEntity<>("The patient was successfully deleted", HttpStatus.OK);
        } catch (PatientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> patientList() {
        log.info("Request patient list");
        List<Patient> patients = patientService.patientList();
        if (!CollectionUtils.isEmpty(patients)) {
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Patient list empty.");
    }
}
