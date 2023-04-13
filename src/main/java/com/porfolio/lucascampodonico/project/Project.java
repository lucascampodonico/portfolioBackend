package com.porfolio.lucascampodonico.project;

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
public class Project {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String nameProject;

    @NotBlank
    private String imageUrl;
    

    public Project(@NotBlank String nameProject,
            @NotBlank String imageUrl) {
        this.nameProject = nameProject;
        this.imageUrl = imageUrl;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    

    
    

}
