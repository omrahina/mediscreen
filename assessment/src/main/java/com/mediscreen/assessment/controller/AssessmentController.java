package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.dto.PatientAssessmentDto;
import com.mediscreen.assessment.proxies.PatientHistoryMicroserviceProxy;
import com.mediscreen.assessment.proxies.PatientMicroserviceProxy;
import com.mediscreen.assessment.service.AssessmentService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class AssessmentController {

    private final AssessmentService assessmentService;

    private final PatientHistoryMicroserviceProxy historyProxy;

    private final PatientMicroserviceProxy patientProxy;


    public AssessmentController(AssessmentService assessmentService, PatientHistoryMicroserviceProxy historyProxy, PatientMicroserviceProxy patientProxy) {
        this.assessmentService = assessmentService;
        this.historyProxy = historyProxy;
        this.patientProxy = patientProxy;
    }

    @GetMapping("/assess/{id}")
    public ResponseEntity<String> assessPatient(@PathVariable("id") long id) {
        try {
            PatientAssessmentDto assessment = assessmentService.assess(patientProxy.getPatient(id), historyProxy.viewPatientHistory(id));
            return new ResponseEntity<>(assessment.toString(), HttpStatus.OK) ;
        } catch (FeignException e) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
