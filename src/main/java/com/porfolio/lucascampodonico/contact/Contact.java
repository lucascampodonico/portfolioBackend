package com.porfolio.lucascampodonico.contact;

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
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String nameContact;

    private Date DateFrom;

    private Date DateTo;

    @NotBlank
    private String description;
    

    public Contact(@NotBlank String nameContact, Date dateFrom, Date dateTo,
            @NotBlank String description) {
        this.nameContact = nameContact;
        this.DateFrom = dateFrom;
        this.DateTo = dateTo;
        this.description = description;
    }



    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
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
