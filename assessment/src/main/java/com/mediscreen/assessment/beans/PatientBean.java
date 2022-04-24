package com.mediscreen.assessment.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
public class PatientBean {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private EnumGenderBean gender;

    private String homeAddress;

    private String phone;

    @Override
    public String toString() {
        return "Patient:" +
                " " + firstName +
                " " + lastName +
                " (age " + Period.between(dateOfBirth, LocalDate.now()).getYears() + ") ";
    }
}
