package com.mediscreen.mediscreen.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class NoteBean {

    private String id;

    private Long patientId;

    private String fullName;

    @NotBlank(message = "Note is mandatory")
    private String note;

    public NoteBean(Long patientId, String fullName) {
        this.patientId = patientId;
        this.fullName = fullName;
    }
}
