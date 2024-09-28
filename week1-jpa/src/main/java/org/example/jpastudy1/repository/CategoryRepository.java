package org.example.jpastudy1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // select * from Category where description = ${description}
    Category findByDescription(String description);

    // select * from Category where type = ? and description = ?
    Category findByTypeAndDescription(String type, String description);
}
