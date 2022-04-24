package com.mediscreen.mediscreen.controller;

import com.mediscreen.mediscreen.beans.NoteBean;
import com.mediscreen.mediscreen.beans.PatientBean;
import com.mediscreen.mediscreen.proxies.AssessmentMicroserviceProxy;
import com.mediscreen.mediscreen.proxies.PatientHistoryMicroserviceProxy;
import com.mediscreen.mediscreen.proxies.PatientMicroserviceProxy;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
public class MediscreenPatientHistoryController {

    private final PatientHistoryMicroserviceProxy historyProxy;

    private final PatientMicroserviceProxy patientProxy;

    private final AssessmentMicroserviceProxy assessmentProxy;

    public MediscreenPatientHistoryController(PatientHistoryMicroserviceProxy historyProxy, PatientMicroserviceProxy patientProxy, AssessmentMicroserviceProxy assessmentProxy) {
        this.historyProxy = historyProxy;
        this.patientProxy = patientProxy;
        this.assessmentProxy = assessmentProxy;
    }

    @GetMapping("/history/{patientId}")
    public String viewPatientHistory(@PathVariable("patientId") long id, @RequestParam(name = "error", required = false) String error, Model model) {
        try {
            PatientBean patient = patientProxy.getPatient(id);
            model.addAttribute("patientId", patient.getId());
            model.addAttribute("notes", historyProxy.viewPatientHistory(id));
            if (patient.getDateOfBirth() != null) {
                model.addAttribute("risk", assessmentProxy.assessPatient(id));
            } else {
                model.addAttribute("risk", patient.getFirstName() + " " + patient.getLastName()+ " diabetes assessment is not available (age missing)");
            }
        } catch (FeignException e) {
            error = e.getMessage();
        }
        if (error != null){
            model.addAttribute("error", error);
        }
        return "notes";
    }

    @GetMapping("/history/add/{patientId}")
    public String showAddNotePage(@PathVariable("patientId") long id, Model model) {
        try {
            PatientBean patient = patientProxy.getPatient(id);
            model.addAttribute("note", new NoteBean(id, patient.getFirstName() + " " + patient.getLastName()));
            return "addNote";
        } catch (FeignException e) {
            return "redirect:/history/{patientId}?error=" + e.getMessage();
        }
    }

    @PostMapping("/history/add/{patientId}")
    public String addNote(@Valid @ModelAttribute("note") NoteBean note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("Error(s) in the form");
            return "addNote";
        }
        try {
            historyProxy.addNote(note);
        } catch (FeignException e) {
            return "redirect:/history/{patientId}?error=" + e.getMessage();
        }
        return "redirect:/history/{patientId}";
    }

    @GetMapping("/history/update/{patientId}/{id}")
    public String showUpdateNotePage(@PathVariable("patientId") long patientId, @PathVariable("id") String noteId, Model model) {
        try {
            model.addAttribute("note", historyProxy.getNote(noteId));
            return "updateNote";
        } catch (FeignException e) {
            return "redirect:/history/{patientId}?error=" + e.getMessage();
        }
    }

    @PostMapping("/history/update/{patientId}/{id}")
    public String updateNote(@Valid @ModelAttribute("note") NoteBean note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("Error(s) in the form");
            return "updateNote";
        }
        try {
            historyProxy.updateNote(note.getId(), note);
        } catch (FeignException e) {
            return "redirect:/history/{patientId}?error=" + e.getMessage();
        }
        return "redirect:/history/{patientId}";
    }
}
