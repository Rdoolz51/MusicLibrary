package com.karendiscord.models;


import javax.persistence.*;

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

}
