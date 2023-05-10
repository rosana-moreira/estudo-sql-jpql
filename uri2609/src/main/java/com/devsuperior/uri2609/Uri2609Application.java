package com.devsuperior.uri2609;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

    @Autowired
    private CategoryRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Uri2609Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<CategorySumProjection> list = repository.search1();
        List<CategorySumDTO> result1 = list.stream().map(x -> new CategorySumDTO(x)).collect(Collectors.toList());
        System.out.println("\n*** Resultado Sql Raiz: ");
        for (CategorySumDTO obj : result1) {
            System.out.println(obj);
        }
        List<CategorySumDTO> list2 = repository.search2();

        System.out.println("\n*** Resultado jpql : ");
        for (CategorySumDTO obj : list2) {
            System.out.println(obj);
        }

    }
}
