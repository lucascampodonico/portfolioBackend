package com.porfolio.lucascampodonico.education;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Education {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String nameEducation;

    private String DateFrom;

    private String DateTo;

    @NotBlank
    private String description;
    

    public Education(@NotBlank String nameEducation, String dateFrom, String dateTo,
            @NotBlank String description) {
        this.nameEducation = nameEducation;
        this.DateFrom = dateFrom;
        this.DateTo = dateTo;
        this.description = description;
    }
 

}
