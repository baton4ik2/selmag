package ru.akbirov.feedback.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akbirov.feedback.entity.FavouriteProduct;

public interface FavouriteProductsService {

    Mono<FavouriteProduct> addProductToFavourites(int productId, String userId);

    Mono<Void> removeProductFromFavourites(int productId, String userId);

    Mono<FavouriteProduct> findFavouriteProductByProduct(int productId, String userId);

    Flux<FavouriteProduct> findFavouriteProducts(String userId);
}
