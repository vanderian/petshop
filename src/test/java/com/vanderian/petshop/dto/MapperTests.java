package com.vanderian.petshop.dto;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import sk.vanderian.petshop.dto.CategoryCreate;
import sk.vanderian.petshop.dto.OrderCreate;
import sk.vanderian.petshop.dto.OrderItemCreate;
import sk.vanderian.petshop.dto.ProductCreate;
import sk.vanderian.petshop.entity.Category;
import sk.vanderian.petshop.entity.Order;
import sk.vanderian.petshop.entity.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapperTests {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void testProductCreate() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setName("product");
        productCreate.setPrice(BigDecimal.valueOf(123));
        productCreate.setDescription("product description");
        productCreate.setCategoryIds(Collections.singletonList(1L));
        productCreate.setGalleryUrls(Collections.singletonList("url1"));

        Product product = modelMapper.map(productCreate, Product.class);
        assert product.getName().equals(productCreate.getName());
        assert product.getPrice().equals(productCreate.getPrice());
        assert product.getDescription().equals(productCreate.getDescription());
        assert product.getCategories().iterator().next().getId() == productCreate.getCategoryIds().get(0);
        assert product.getGallery().iterator().next().getUrl().equals(productCreate.getGalleryUrls().get(0));
    }

    @Test
    public void testCategoryCreate() {
        CategoryCreate categoryCreate = new CategoryCreate();
        categoryCreate.setName("category");

        Category category = modelMapper.map(categoryCreate, Category.class);
        assert category.getId() == 0;
        assert category.getName().equals(categoryCreate.getName());
    }

    @Test
    public void testOrderCreate() {
        OrderCreate orderCreate = new OrderCreate();
        List<OrderItemCreate> items = Stream.of(1, 2, 3, 4, 5).map(it -> new OrderItemCreate(it, it)).collect(Collectors.toList());
        orderCreate.setOrders(items);

        Order order = modelMapper.map(orderCreate, Order.class);

        assert order.getOrders().size() == items.size();
    }

//    and so on...

}
