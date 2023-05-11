package com.porfolio.lucascampodonico.employment;

import java.sql.Date;

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



    public String getNameEmployment() {
        return nameEmployment;
    }

    public void setNameEmployment(String nameEmployment) {
        this.nameEmployment = nameEmployment;
    }

    public String getDateFrom() {
        return this.DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.DateFrom = dateFrom;
    }

    public String getDateTo() {
        return this.DateTo;
    }

    public void setDateTo(String dateTo) {
        this.DateTo = dateTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    

    
    

}
