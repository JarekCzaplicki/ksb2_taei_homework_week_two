package jarek.czaplicki.ksb2_taei_homework_week_two.service;

import jarek.czaplicki.ksb2_taei_homework_week_two.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceMain {
    private ProductService productService;

    public ProductServiceMain(ProductService productService) {
        this.productService = productService;
        productService.printInfo();
    }
}
