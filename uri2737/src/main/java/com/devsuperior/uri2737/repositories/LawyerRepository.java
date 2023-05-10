package com.devsuperior.uri2737.repositories;

import com.devsuperior.uri2737.dto.LawyerMinDTO;
import com.devsuperior.uri2737.projections.LawyerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2737.entities.Lawyer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    @Query(nativeQuery = true, value = "(select name, customers_number  as customersNumber" +
            "from lawyers " +
            "order by customers_number " +
            "desc limit 1) " +
            "union all " +
            "(select name, customers_number " +
            "from lawyers  " +
            "order by customers_number  " +
            "asc limit 1) " +
            "union all " +
            "(select 'Average', round(avg(customers_number)) " +
            "from lawyers)")
    List<LawyerMinProjection> search1();

}
