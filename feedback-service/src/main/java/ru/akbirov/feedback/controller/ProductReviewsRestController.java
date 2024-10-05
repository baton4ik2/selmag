package ru.akbirov.feedback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akbirov.feedback.controller.payload.NewProductReviewPayload;
import ru.akbirov.feedback.entity.ProductReview;
import ru.akbirov.feedback.service.ProductReviewsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("feedback-api/product-reviews")
public class ProductReviewsRestController {

    private final ProductReviewsService productReviewsService;

    //private final ReactiveMongoTemplate reactiveMongoTemplate;

    @GetMapping("by-product-id/{productId:\\d+}")
    public Flux<ProductReview> findProductReviewsByProductId(@PathVariable("productId") int productId) {
        return this.productReviewsService.findProductReviewsByProduct(productId);
    }

    /*@GetMapping("by-product-id/{productId:\\d+}")
    public Flux<ProductReview> findProductsReviewsByProductId(@PathVariable("productId") int productId) {
        return this.reactiveMongoTemplate
                .find(Query.query(Criteria.where("productId").is(productId)),
                        ProductReview.class);
    }*/

    @PostMapping
    public Mono<ResponseEntity<ProductReview>> createProductReview(
            Mono<JwtAuthenticationToken> authenticationTokenMono,
            @Valid @RequestBody Mono<NewProductReviewPayload> monoPayload,
            UriComponentsBuilder uriComponentsBuilder) {
        return authenticationTokenMono.flatMap(token -> monoPayload
                .flatMap(payload -> this.productReviewsService
                        .createProductReview(payload.productId(), payload.rating(),
                                payload.review(), token.getToken().getSubject())))
                .map(productReview -> ResponseEntity
                        .created(uriComponentsBuilder.replacePath("/feedback-api/product-reviews/{id}")
                                .build(productReview.getId()))
                        .body(productReview));
    }


}
