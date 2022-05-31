package com.esgi.jeeproject.vibecritical.domain.UserRating;


import javax.persistence.*;

@Entity
public class UserRating {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
