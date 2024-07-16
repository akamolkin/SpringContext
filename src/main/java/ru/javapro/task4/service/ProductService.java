package ru.javapro.task4.service;

import org.springframework.stereotype.Service;
import ru.javapro.task4.dto.ProductDto;
import ru.javapro.task4.dto.ProductListRespDto;
import ru.javapro.task4.dto.AppRespDto;
import ru.javapro.task4.dto.UserDto;
import ru.javapro.task4.entity.Product;
import ru.javapro.task4.entity.User;
import ru.javapro.task4.exception.BadReqException;
import ru.javapro.task4.exception.NotFoundException;

import java.util.List;

@Service
public class ProductService {
    private final ProductDto productDto;
    private final UserDto userDto;

    public ProductService(ProductDto productDto, UserDto userDto) {
        this.productDto = productDto;
        this.userDto = userDto;
    }

    public ProductListRespDto getAllByUserId(long userId){
        User user = userDto.findById(userId);
        if (user == null) throw new BadReqException("User not found with id " + userId);

        List<Product> productList = productDto.findAllByUserId(userId);
        return new ProductListRespDto(productList);
    }

    public Product getProductById(long productId){
        Product product = productDto.findById(productId);
        if (product == null) throw new NotFoundException("Product not found with id " + productId);
        return product;
    }

    public AppRespDto addProduct(Product product) {
        User user = userDto.findById(product.getUserId());
        if (user == null) throw new BadReqException("User not found with id " + product.getUserId());
        int result = productDto.add(product);
        return new AppRespDto(result,"Product added");
    }
}
