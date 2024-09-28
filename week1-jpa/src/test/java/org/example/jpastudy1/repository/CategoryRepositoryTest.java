package org.example.jpastudy1.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Transactional
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void test() {

        //Given
        Category category1 = Category.builder()
                .type("양식")
                .description("데이트")
                .build();

        Category category2 = Category.builder()
                .type("한식")
                .description("한국인의 정")
                .build();

        // When
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        // Then
        List<Category> categories = categoryRepository.findAll();
        Assertions.assertThat(categories).hasSize(2);
        Assertions.assertThat(categories.get(0).getType()).isEqualTo("양식");
        Assertions.assertThat(categories.get(0).getDescription()).isEqualTo("데이트");

    }

    @DisplayName("Description을 이용한 조회")
    @Test
    void findByDescription() {

        //Given
        Category category1 = Category.builder()
                .type("양식")
                .description("데이트")
                .build();

        Category category2 = Category.builder()
                .type("한식")
                .description("한국인의 정")
                .build();
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        // When
        Category result1 = categoryRepository.findByDescription("철가방");
        Category result2 = categoryRepository.findByDescription("데이트");

        // Then
        Assertions.assertThat(result1).isNull();
        Assertions.assertThat(result2).isNotNull();
        Assertions.assertThat(result2.getType()).isEqualTo("양식");
    }


    @DisplayName("description과 type를 이용한 조회")
    @Test
    void findByTypeAndDescription() {

        //Given
        Category category1 = Category.builder()
                .type("양식")
                .description("데이트")
                .build();

        Category category2 = Category.builder()
                .type("한식")
                .description("한국인의 정")
                .build();

        Category category3 = Category.builder()
                .type("중식")
                .description("철가방")
                .build();

        Category category4 = Category.builder()
                .type("미식")
                .description("축구ㅋㅋ")
                .build();

        categoryRepository.saveAll(List.of(category1, category2, category3, category4));

        // When
        Category result1 = categoryRepository.findByTypeAndDescription("양식", "데이트");
        Category result2 = categoryRepository.findByTypeAndDescription("중식", "데이트"); // null
        Category result3 = categoryRepository.findByTypeAndDescription("미식", "축구ㅋㅋ");

        // Then
        Assertions.assertThat(result1.getType()).isEqualTo("양식");
        Assertions.assertThat(result2).isNull();
        Assertions.assertThat(result3.getDescription()).isEqualTo("축구ㅋㅋ");
    }
}
