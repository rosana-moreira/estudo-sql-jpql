package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = " select products.name " +
            "  from products " +
            "  inner join providers " +
            "  on products.id_providers  = providers.id " +
            "  where products.amount  " +
            "  between :min and :max " +
            "  and providers.name like concat(:name, '%')")
    List<ProductMinProjection> search1(Integer min, Integer max, String name);

    @Query(" select new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name)" +
            "  from Product obj " +
            "  where obj.amount  " +
            "  between :min and :max " +
            "  and obj.provider.name like concat(:name, '%')")
    List<ProductMinDTO> search2(Integer min, Integer max, String name);

}
