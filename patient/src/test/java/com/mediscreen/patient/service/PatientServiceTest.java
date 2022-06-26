package com.mediscreen.patient.service;

import com.mediscreen.patient.exceptions.PatientException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void should_addPatient_ok() throws PatientException {
        Patient patient = new Patient("test", "test");
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient savedPatient = patientService.addPatient(patient);

        assertNotNull(savedPatient);
        verify(patientRepository).save(patient);
    }

    @Test
    public void should_addPatient_ko() {
        assertThrows(PatientException.class, () -> patientService.addPatient(new Patient("", "test")),
                "First name and/or last name not accepted");
    }

    @Test
    public void should_updatePatient_ok() throws PatientException {
        Patient patient = new Patient("test", "test");
        patient.setId(1L);
        when(patientRepository.existsById(anyLong())).thenReturn(true);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient updatedPatient = patientService.updatePatient(patient);

        assertNotNull(updatedPatient);
        verify(patientRepository).existsById(1L);
        verify(patientRepository).save(patient);
    }

    @Test
    public void should_updatePatient_ko_doesn_exist() {
        Patient patient = new Patient("test", "test");
        patient.setId(1L);
        when(patientRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(PatientException.class, () -> patientService.updatePatient(patient),
                "The requested resource doesn't exist.");
        verify(patientRepository).existsById(1L);
    }

    @Test
    public void should_patientList_ok() {
        when(patientRepository.findAll()).thenReturn(Collections.singletonList(new Patient()));

        List<Patient> patients = patientService.patientList();

        assertEquals(1, patients.size());
        verify(patientRepository).findAll();
    }

    @Test
    public void should_patientList_ko_empty_list() {
        when(patientRepository.findAll()).thenReturn(Collections.emptyList());

        List<Patient> patients = patientService.patientList();

        assertEquals(0, patients.size());
        verify(patientRepository).findAll();
    }

    @Test
    public void should_deletePatient_ok() {
        when(patientRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(patientRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> patientService.deletePatient(1L));
        verify(patientRepository).existsById(1L);
        verify(patientRepository).deleteById(1L);
    }

    @Test
    public void should_deletePatient_ko() {
        when(patientRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(PatientException.class, () -> patientService.deletePatient(1L),
                "Couldn't perform the operation. The requested resource doesn't exist.");
        verify(patientRepository).existsById(1L);
    }

    @Test
    public void should_findPatientById_ok() throws PatientException {
        Patient patient = new Patient();
        when(patientRepository.findPatientById(anyLong())).thenReturn(patient);

        Patient result = patientService.findPatientById(1L);

        assertNotNull(result);
        assertEquals(patient, result);
        verify(patientRepository).findPatientById(1L);
    }

    @Test
    public void should_findPatientById_ko_no_result() {
        when(patientRepository.findPatientById(anyLong())).thenReturn(null);

        assertThrows(PatientException.class, () -> patientService.findPatientById(1L),
                "No patient found");
        verify(patientRepository).findPatientById(1L);

    }

}