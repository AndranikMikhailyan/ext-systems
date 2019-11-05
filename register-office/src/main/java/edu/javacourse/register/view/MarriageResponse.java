package edu.javacourse.register.view;

import java.io.Serializable;
import java.time.LocalDate;

public class MarriageResponse implements Serializable {

    private String husbandSureName;
    private String husbandGivenName;
    private String husbandPatronymic;
    private LocalDate husbandDateOfBirth;
    private String hupbandPassportSeria;
    private String hupbandPassportNumber;
    private LocalDate husbandPassportIssueDate;
    private String wifeSureName;
    private String wifeGivenName;
    private String wifePatronymic;
    private LocalDate wifeDateOfBirth;
    private String wifePassportSeria;
    private String wifePassportNumber;
    private LocalDate wifePassportIssueDate;

    private String marriageCertificateNumber;
    private LocalDate marriageCertificateDate;
}
