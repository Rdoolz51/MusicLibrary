package com.karendiscord.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int id;

    @Column(name = "rating_stars")
    private int stars;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "rating_owner")
    private User owner;

    public Rating(int stars, User owner) {
        this.stars = stars;
        this.owner = owner;
    }
}
