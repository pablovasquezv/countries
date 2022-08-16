package com.word.countries.models;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Pablo
 *
 */

@Data
@Entity
@Table(name = "city")
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_city")
    private Long id_city;

    @NotEmpty(message = "¡El campo name_city no debe ser vacío!")
    @Size(min = 2, max = 255, message = "¡El campo name_city no debe ser entre 4 y 255 carácteres!")
    @Column(name = "name_city")
    private String name_city;

    @NotEmpty(message = "¡El campo country_code no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo country_code no debe ser entre 4 y 255 carácteres!")
    @Column(name = "country_code_city")
    private String country_code_city;

    @NotEmpty(message = "¡El campo district no debe ser vacío!")
    @Size(min = 2, max = 255, message = "¡El campo district no debe ser entre 4 y 255 carácteres!")
    @Column(name = "district_city")
    private String district_city;

    @NotNull(message= "¡El campo population_city no debe ser vacio!")
    @Min(value = 0, message = "¡El campo population_city debe ser mayor a 0!")
    @Column(name = "population_city")
    private Integer population_city;

    // <----- Relationships ----->
    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @NotNull(message = "¡El id del País no debe ser vacío!")
    @JoinColumn(name="country_id")
    private Country country;


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
