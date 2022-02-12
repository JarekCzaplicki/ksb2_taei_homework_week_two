package jarek.czaplicki.ksb2_taei_homework_week_two.hellper;

import jarek.czaplicki.ksb2_taei_homework_week_two.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateProducts {
    private static final Double MIN_PRICE = 50.0;
    private static final Double MAX_PRICE = 300.0;

    List<Product> listOfProducts = new ArrayList<>();

    public List<Product> create(int numberOfProducts) {
        for (int i = 1; i <= numberOfProducts; i++) {
            listOfProducts.add(new Product("Product_" + i, generatePrize()));
        }
        return listOfProducts;
    }

    private Double generatePrize() {
        return Math.random() * (MAX_PRICE - MIN_PRICE) + MIN_PRICE;
    }
}
