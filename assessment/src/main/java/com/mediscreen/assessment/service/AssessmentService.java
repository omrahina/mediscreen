package com.mediscreen.assessment.service;

import com.mediscreen.assessment.beans.EnumGenderBean;
import com.mediscreen.assessment.beans.NoteBean;
import com.mediscreen.assessment.beans.PatientBean;
import com.mediscreen.assessment.data.DiabetesTriggers;
import com.mediscreen.assessment.dto.PatientAssessmentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AssessmentService {

    public PatientAssessmentDto assess(PatientBean patient, List<NoteBean> notes) {
        PatientAssessmentDto assessment = new PatientAssessmentDto(patient, "None");
        if (CollectionUtils.isEmpty(notes)) {
            return assessment;
        }
        int nbTriggers = countTriggers(notes.stream()
                .map(NoteBean::getNote)
                .collect(Collectors.toList()))
                .size();
        int patientAge = calculateAge(patient.getDateOfBirth());

        switch (nbTriggers) {
            case 0:
            case 1:
                assessment.setDiabetesAssessment("None");
                break;
            case 2:
                if (patientAge > 30) {
                    assessment.setDiabetesAssessment("Borderline");
                }
                break;

            case 3:
                if ((patientAge < 30) && (patient.getGender() == EnumGenderBean.M)) {
                    assessment.setDiabetesAssessment("In Danger");
                }
                break;

            case 4:
                if ((patientAge < 30) && (patient.getGender() == EnumGenderBean.F)) {
                    assessment.setDiabetesAssessment("In Danger");
                }
                break;

            case 5:
                if ((patientAge < 30) && (patient.getGender() == EnumGenderBean.M)) {
                    assessment.setDiabetesAssessment("Early onset");
                }
                break;

            case 6:
                if (patientAge > 30) {
                    assessment.setDiabetesAssessment("In Danger");
                }
                break;

            case 7:
                if ((patientAge < 30) && (patient.getGender() == EnumGenderBean.F)) {
                    assessment.setDiabetesAssessment("Early onset");
                }
                break;

            default:
                if (patientAge > 30) {
                    assessment.setDiabetesAssessment("Early onset");
                }
                break;
        }
        return assessment;
    }

    private Map<String, Long> countTriggers(List<String> notes) {
        List<String> triggers = DiabetesTriggers.getTriggers();
        return notes.stream()
                .map(note -> note.replaceAll("\\p{Punct}", "").split("\\s+"))
                .flatMap(Arrays::stream)
                .filter(word -> triggers.contains(word.toLowerCase()))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

}
