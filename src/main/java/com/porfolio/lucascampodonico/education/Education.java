package com.porfolio.lucascampodonico.education;

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
public class Education {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String nameEducation;

    private Date DateFrom;

    private Date DateTo;

    @NotBlank
    private String description;
    

    public Education(@NotBlank String nameEducation, Date dateFrom, Date dateTo,
            @NotBlank String description) {
        this.nameEducation = nameEducation;
        this.DateFrom = dateFrom;
        this.DateTo = dateTo;
        this.description = description;
    }



    public String getNameEducation() {
        return nameEducation;
    }

    public void setNameEducation(String nameEducation) {
        this.nameEducation = nameEducation;
    }

    public Date getDateFrom() {
        return this.DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return this.DateTo;
    }

    public void setDateTo(Date dateTo) {
        this.DateTo = dateTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    

    
    

}
