package com.ybytu.yalotengo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    @Column(name = "item_condition", nullable = false, length = 50)
    private String itemCondition;

    @Column(name = "img_url", nullable = false, length = 300)
    private String imgUrl;

    @Column(name = "fruits_required", nullable = false)
    private Integer fruitsRequired;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Item(String title, String description, String type, String condition, String img_url, Integer fruits_required){
        this.title = title;
        this.description = description;
        this.type = type;
        this.itemCondition = itemCondition;
        this.imgUrl = imgUrl;
        this.fruitsRequired = fruitsRequired;
    }
}
