package com.mediscreen.assessment.dto;

import com.mediscreen.assessment.beans.PatientBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientAssessmentDto {

    private PatientBean patient;
    private String diabetesAssessment;

    public PatientAssessmentDto(PatientBean patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return patient + "diabetes assessment is: " + diabetesAssessment;
    }
}
