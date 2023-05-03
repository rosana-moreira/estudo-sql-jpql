package com.devsuperior.uri2621;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

    @Autowired
    private ProductRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Uri2621Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<ProductMinProjection> list = repository.search1(10, 20, "P");
        List<ProductMinDTO> result1 = list.stream().map(x -> new ProductMinDTO(x)).collect(Collectors.toList());
        System.out.println("\n*** Resultado Sql Raiz: ");
        for (ProductMinDTO obj : result1) {
            System.out.println(obj);
        }
        List<ProductMinDTO> list2 = repository.search2(10, 20, "P");

        System.out.println("\n*** Resultado jpql : ");
        for (ProductMinDTO obj : list2) {
            System.out.println(obj);
        }
    }
}
