package com.porfolio.lucascampodonico.employment;

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
public class Employment {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String nameEmployment;

    private String DateFrom;

    private String DateTo;

    @NotBlank
    private String description;
    

    public Employment(@NotBlank String nameEmployment, String dateFrom, String dateTo,
            @NotBlank String description) {
        this.nameEmployment = nameEmployment;
        this.DateFrom = dateFrom;
        this.DateTo = dateTo;
        this.description = description;
    }

}
