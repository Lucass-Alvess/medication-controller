package com.lucasdev.medication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_user_allergy")
public class UserAllergy {

    @EmbeddedId
    private UserAllergyPK id = new UserAllergyPK();

    @Column(columnDefinition = "TEXT")
    private String observation;

    public UserAllergy() {
    }

    public UserAllergy(String observation) {
        this.observation = observation;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAllergy that = (UserAllergy) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
