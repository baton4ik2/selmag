package ru.akbirov.customer.client.payload;

public record NewProductReviewPayload(Integer productId, Integer rating, String review) {
}
