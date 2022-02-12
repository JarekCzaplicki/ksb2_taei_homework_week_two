package jarek.czaplicki.ksb2_taei_homework_week_two.service.impl;

import jarek.czaplicki.ksb2_taei_homework_week_two.hellper.CreateProducts;
import jarek.czaplicki.ksb2_taei_homework_week_two.model.Product;
import jarek.czaplicki.ksb2_taei_homework_week_two.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Profile("plus")
public class ProductServicePlus implements ProductService {

    @Value("${vatTax}")
    private Double vatTax;

    @Value("${locale}")
    private String locale;

    private Double summaryPrice = 0.0;
    private Double summaryVatPrice = 0.0;

    private final CreateProducts createProducts;
    private final MessageSource messageSource;

    @Autowired
    public ProductServicePlus(CreateProducts createProducts, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.createProducts = createProducts;
    }

    @Override
    public void printInfo() {
        List<Product> products = createProducts.create(5);
        for (Product product : products) {
            summaryPrice += product.getPrice();
        }
        summaryPrice = Math.round(summaryPrice * 100.0) / 100.0;
        summaryVatPrice = Math.round((summaryPrice + (summaryPrice * vatTax)) * 100.0) / 100.0;
        System.out.println(messageSource.getMessage("price_of_all_products", new Object[]{summaryPrice}, Locale.forLanguageTag(locale)));
        System.out.println(messageSource.getMessage("price_of_all_products_plus_vat", new Object[]{summaryVatPrice}, Locale.forLanguageTag(locale)));
    }
}
