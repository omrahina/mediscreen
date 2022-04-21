package com.mediscreen.patient.controller;

import com.mediscreen.patient.exceptions.PatientException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String home(Model model, @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("patients", patientService.patientList());
        model.addAttribute("patient", new Patient());
        if (error != null){
            model.addAttribute("error", error);
        }
        return "home";
    }

    @GetMapping("/patient/add")
    public String showAddPatientPage(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @PostMapping("/patient/add")
    public String addPatient(@Valid Patient patient, BindingResult result, Model model) {
        log.info("Request validate a new patient");
        if (result.hasErrors()) {
            log.error("Error(s) in the form");
            return "addPatient";
        }
        try {
            patientService.addPatient(patient);
        } catch (PatientException e) {
            return "redirect:/patients?error=" + e.getMessage();
        }
        return "redirect:/patients";
    }

    @GetMapping("patient/update/{id}")
    public String showUpdatePatientPage(@PathVariable("id") long id, Model model) {
        try {
            Patient patient = patientService.findPatientById(id);
            model.addAttribute("patient", patient);
            return "updatePatient";
        } catch (PatientException e) {
            return "redirect:/patients?error=" + e.getMessage();
        }
    }

    @PostMapping("patient/update/{id}")
    public String updatePatient(@PathVariable("id") long id, @Valid Patient patient, BindingResult result, Model model) {
        log.info("Request update a patient");
        if (result.hasErrors()) {
            log.error("Error(s) in the form");
            return "updatePatient";
        }
        //Continue
        return "redirect:/patients";
    }
}
