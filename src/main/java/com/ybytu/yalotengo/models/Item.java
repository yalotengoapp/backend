package com.ybytu.yalotengo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", nullable = false, length = 800)
    private String description;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    @Column(name = "condition", nullable = false, length = 50)
    private String condition;

    @Column(name = "img_url", nullable = false, length = 300)
    private String img_url;

    @Column(name = "fruits_required", nullable = false, length = 50)
    private Integer fruits_required;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
