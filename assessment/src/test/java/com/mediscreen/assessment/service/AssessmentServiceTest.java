package com.mediscreen.assessment.service;

import com.mediscreen.assessment.beans.EnumGenderBean;
import com.mediscreen.assessment.beans.NoteBean;
import com.mediscreen.assessment.beans.PatientBean;
import com.mediscreen.assessment.dto.PatientAssessmentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
public class AssessmentServiceTest {

    @InjectMocks
    private AssessmentService assessmentService;

    @Test
    public void should_assess_empty_noteList() {
        PatientAssessmentDto assessment = assessmentService.assess(new PatientBean(), Collections.emptyList());

        assertEquals("None", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_assess_ko_missing_gender() {
        List<NoteBean> notes = buildNoteList(new String[] {"cholestérol"});
        PatientBean patientBean = new PatientBean();
        patientBean.setDateOfBirth(LocalDate.of(2010, 2, 2));

        PatientAssessmentDto assessment = assessmentService.assess(patientBean, notes);

        assertEquals("diabetes assessment is not available (gender missing)", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_assess_ok_borderline() {
        List<NoteBean> notes = buildNoteList(new String[] {"cholestérol", "anormal"});
        PatientBean patientBean = new PatientBean();
        patientBean.setDateOfBirth(LocalDate.of(1991, 2, 2));
        patientBean.setGender(EnumGenderBean.M);

        PatientAssessmentDto assessment = assessmentService.assess(patientBean, notes);

        assertEquals("Borderline", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_assess_ok_danger_M() {
        List<NoteBean> notes = buildNoteList(new String[] {"cholestérol", "anormal", "rechute"});
        PatientBean patientBean = new PatientBean();
        patientBean.setDateOfBirth(LocalDate.of(2000, 2, 2));
        patientBean.setGender(EnumGenderBean.M);

        PatientAssessmentDto assessment = assessmentService.assess(patientBean, notes);

        assertEquals("In Danger", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_assess_ok_danger_F() {
        List<NoteBean> notes = buildNoteList(new String[] {"cholestérol", "anormal", "rechute", "anticorps"});
        PatientBean patientBean = new PatientBean();
        patientBean.setDateOfBirth(LocalDate.of(2000, 2, 2));
        patientBean.setGender(EnumGenderBean.F);

        PatientAssessmentDto assessment = assessmentService.assess(patientBean, notes);

        assertEquals("In Danger", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_assess_ok_early_onset_M() {
        List<NoteBean> notes = buildNoteList(new String[] {"cholestérol", "anormal", "rechute", "anticorps", "vertige"});
        PatientBean patientBean = new PatientBean();
        patientBean.setDateOfBirth(LocalDate.of(2000, 2, 2));
        patientBean.setGender(EnumGenderBean.M);

        PatientAssessmentDto assessment = assessmentService.assess(patientBean, notes);

        assertEquals("Early onset", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_assess_ok_danger() {
        List<NoteBean> notes = buildNoteList(new String[] {"cholestérol", "anormal", "rechute", "anticorps",
                "vertige", "poids"});
        PatientBean patientBean = new PatientBean();
        patientBean.setDateOfBirth(LocalDate.of(1991, 2, 2));
        patientBean.setGender(EnumGenderBean.M);

        PatientAssessmentDto assessment = assessmentService.assess(patientBean, notes);

        assertEquals("In Danger", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_assess_ok_early_onset_F() {
        List<NoteBean> notes = buildNoteList(new String[] {"cholestérol", "anormal", "rechute", "anticorps",
                "vertige", "poids", "taille"});
        PatientBean patientBean = new PatientBean();
        patientBean.setDateOfBirth(LocalDate.of(2000, 2, 2));
        patientBean.setGender(EnumGenderBean.F);

        PatientAssessmentDto assessment = assessmentService.assess(patientBean, notes);

        assertEquals("Early onset", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_assess_ok_early_onset() {
        List<NoteBean> notes = buildNoteList(new String[] {"cholestérol", "anormal", "rechute", "anticorps",
                "vertige", "poids", "taille", "microalbumine", "réaction"});
        PatientBean patientBean = new PatientBean();
        patientBean.setDateOfBirth(LocalDate.of(1991, 2, 2));
        patientBean.setGender(EnumGenderBean.M);

        PatientAssessmentDto assessment = assessmentService.assess(patientBean, notes);

        assertEquals("Early onset", assessment.getDiabetesAssessment());
    }

    @Test
    public void should_countTriggers() {
        Map<String, Long> result = assessmentService.countTriggers(List.of("cholestérol", "anormal", "cholestérol"));

        assertEquals(2, result.size());
        assertTrue(result.containsKey("anormal"));
    }

    private List<NoteBean> buildNoteList(String[] triggers) {
        NoteBean noteBean = new NoteBean();
        noteBean.setNote("Note with " + Arrays.toString(triggers));
        return List.of(noteBean);
    }

}