package ru.akbirov.customer.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akbirov.customer.entity.FavouriteProduct;
import ru.akbirov.customer.entity.ProductReview;

import java.util.Optional;

public interface FavouriteProductsClient {

    Flux<FavouriteProduct> findFavouriteProducts();

    Mono<FavouriteProduct> findFavouriteProductByProductId(int productId);

    Mono<FavouriteProduct> addProductToFavourites(int productId);

    Mono<Void> removeProductFromFavourites(int productId);



}
