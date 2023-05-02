package com.devsuperior.uri2602;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {
@Autowired
private CustomerRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CustomerMinProjection> list = repository.search1("RS");
		List<CustomerMinDTO> result1 = list.stream().map(x-> new CustomerMinDTO(x)).collect(Collectors.toList());
		System.out.println("\n*** Resultado Sql Raiz: ");
		for(CustomerMinDTO obj : result1){
			System.out.println(obj);
		}
		List<CustomerMinDTO> list2 = repository.search2("RS");

		System.out.println("\n*** Resultado jpql : ");
		for(CustomerMinDTO obj : list2){
			System.out.println(obj);
		}


	}
}
