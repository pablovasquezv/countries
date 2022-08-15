package com.word.countries.models;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Pablo
 *
 */

@Data
@Entity
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_country")
    private Long id_country;

    @NotEmpty(message = "¡El campo code_country no debe ser vacío!")
    @Size(min = 2, max = 255, message = "¡El campo code_country debe ser entre 4 y 255 carácteres!")
    @Column(name = "code_country")
    private String code_country;

    @NotEmpty(message = "¡El campo name_country no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo name_country no debe ser entre 4 y 255 carácteres!")
    @Column(name = "name_country")
    private String name_country;

    @NotEmpty(message = "¡El campo continent_country no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo continent_country no debe ser entre 4 y 255 carácteres!")
    @Column(name = "continent_country")
    private String continent_country;

    @NotEmpty(message = "¡El campo region_country no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo region_country no debe ser entre 4 y 255 carácteres!")
    @Column(name = "region_country")
    private String region_country;

    @NotNull(message= "El campo surface_area_country debe ser vacío")
    @Min(value = 0, message = "¡El campo surface_area_country debe ser mayor a 0!")
    @Column(name = "surface_area_country")
    private Double surface_area_country;

    @NotNull(message= "El campo indep_year_country debe ser vacío")
    @Min(value = 0, message = "¡El campo surface_area_city debe ser mayor a 0!")
    @Column(name = "indep_year_country")
    private Short indep_year_country;

    @NotNull(message= "El campo population_country debe ser vacío")
    @Min(value = 0, message = "¡El campo population_country debe ser mayor a 0!")
    @Column(name = "population_country")
    private Integer population_country;

    @NotNull(message= "El campo life_expectancy_country debe ser vacío")
    @Min(value = 0, message = "¡El campo life_expectancy_country debe ser mayor a 0!")
    @Column(name = "life_expectancy_country")
    private Double life_expectancy_country;

    @NotNull(message= "El campo gnp_country debe ser vacío")
    @Min(value = 0, message = "¡El campo gnp_country debe ser mayor a 0!")
    @Column(name = "gnp_country")
    private Double gnp_country;

    @NotNull(message= "El campo gnp_old_country debe ser vacío")
    @Min(value = 0, message = "¡El campo gnp_old_country debe ser mayor a 0!")
    @Column(name = "gnp_old_country")
    private Double gnp_old_country;

    @NotEmpty(message = "¡El campo local_name_country no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo local_name_country no debe ser entre 4 y 255 carácteres!")
    @Column(name = "local_name_country")
    private String local_name_country;

    @NotEmpty(message = "¡El campo government_form_country no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo government_form_country no debe ser entre 4 y 255 carácteres!")
    @Column(name = "government_form_country")
    private String government_form_country;

    @NotEmpty(message = "¡El campo head_of_state_country no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo head_of_state_country no debe ser entre 4 y 255 carácteres!")
    @Column(name = "head_of_state_country")
    private String head_of_state_country;

    @NotNull(message= "El campo capital_country debe ser vacío")
    @Min(value = 0, message = "¡El campo capital_country debe ser mayor a 0!")
    @Column(name = "capital_country")
    private Integer capital_country;

    @NotEmpty(message = "¡El campo head_of_state_country no debe ser vacío!")
    @Size(min = 2, max = 255, message = "¡El campo head_of_state_country no debe ser entre 4 y 255 carácteres!")
    @Column(name = "head_of_state_code2")
    private String head_of_state_code2;

    // <----- Relationships ----->
    // cities
    @OneToMany(mappedBy="country", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<City> cities;

    @OneToMany(mappedBy="country", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<Language> languages;

    // This will not allow the createdAt column to be updated after creation
    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;


    // other getters and setters removed for brevitycopy
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
