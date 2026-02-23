package com.lucasdev.medication.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Date dateBirth;
    private String bloodType;
    private String healthPlan;
    private Double weight;
    private Double height;
    private String phoneNumber;

    @OneToMany(mappedBy = "id.user")
    private Set<UserAllergy> allergies = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Medicine> medicines = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Consultation> consultations = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, String password, Date dateBirth, String bloodType, String healthPlan, Double weight, Double height, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateBirth = dateBirth;
        this.bloodType = bloodType;
        this.healthPlan = healthPlan;
        this.weight = weight;
        this.height = height;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getHealthPlan() {
        return healthPlan;
    }

    public void setHealthPlan(String healthPlan) {
        this.healthPlan = healthPlan;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<UserAllergy> getAllergies() {
        return allergies;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
