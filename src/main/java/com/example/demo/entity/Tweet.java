package com.example.demo.entity;

import jakarta.persistence.*;
import lombok .*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Tweets", schema = "fsweb")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tweet_name")
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
}
