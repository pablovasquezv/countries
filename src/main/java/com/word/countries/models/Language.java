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
@Table(name = "language")
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_language")
    private Long id_language;

    @NotEmpty(message = "¡El campo country_code_language no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo country_code_language no debe ser entre 4 y 255 carácteres!")
    @Column(name = "country_code_language")
    private String country_code_language;

    @NotEmpty(message = "¡El campo name_language no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo name_language no debe ser entre 4 y 255 carácteres!")
    @Column(name = "name_language")
    private String name_language;

    @NotEmpty(message = "¡El campo is_official_language no debe ser vacío!")
    @Size(min = 4, max = 255, message = "¡El campo is_official_language no debe ser entre 4 y 255 carácteres!")
    @Column(name = "is_official_language")
    private String is_official_language;

    @NotNull(message= "El campo percentage_language debe ser vacío")
    @Min(value = 0, message = "¡El campo percentage_language debe ser mayor a 0!")
    @Column(name = "percentage_language")
    private Double percentage_language;

    // <----- Relationships ----->
    @JsonIgnore
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
