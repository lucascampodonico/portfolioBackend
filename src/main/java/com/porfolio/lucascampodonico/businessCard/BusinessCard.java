package com.porfolio.lucascampodonico.businessCard;

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
public class BusinessCard {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String name;

    private String residence;

    private String city;

    @NotBlank
    private String age;

    private String linkedin;

    private String github;

    private String gitlab;

    private String twitter;

    private String imageUrl;



}
