package com.mediscreen.assessment.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteBean {

    private String id;

    private Long patientId;

    private String fullName;

    private String note;
}
