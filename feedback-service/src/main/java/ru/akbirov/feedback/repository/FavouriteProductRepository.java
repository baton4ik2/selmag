package ru.akbirov.feedback.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akbirov.feedback.entity.FavouriteProduct;

import java.util.UUID;

public interface FavouriteProductRepository extends ReactiveCrudRepository<FavouriteProduct, UUID> {


    Flux<FavouriteProduct> findAllByUserId(String userId);

    Mono<FavouriteProduct> findByProductIdAndUserId(int productId, String userId);

    Mono<Void> deleteByProductIdAndUserId(int productId, String userId);



}
