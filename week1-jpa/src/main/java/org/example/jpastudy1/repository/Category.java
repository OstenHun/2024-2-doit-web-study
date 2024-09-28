package org.example.jpastudy1.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private String type;

    private String description;

    @Builder
    public Category(String description, String type) {
        this.description = description;
        this.type = type;
    }
}
