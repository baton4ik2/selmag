package ru.akbirov.catalogue.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.akbirov.catalogue.entity.Product;


public interface ProductRepository extends CrudRepository<Product, Integer> {

    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);
}
