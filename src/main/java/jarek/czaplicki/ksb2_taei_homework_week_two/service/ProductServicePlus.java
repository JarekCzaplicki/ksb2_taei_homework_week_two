package jarek.czaplicki.ksb2_taei_homework_week_two.service;

import jarek.czaplicki.ksb2_taei_homework_week_two.Hellper.CreateProducts;
import jarek.czaplicki.ksb2_taei_homework_week_two.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Profile("Plus")
public class ProductServicePlus{

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


    @EventListener(ApplicationReadyEvent.class)
    public void totalPrice() {
        List<Product> products = createProducts.create(5);
        for (Product product : products) {
            summaryPrice+= product.getPrice();
        }
        summaryPrice = Math.round(summaryPrice * 100.0) / 100.0;
        summaryVatPrice = Math.round((summaryPrice + (summaryPrice*vatTax)) * 100.0) / 100.0;
        System.out.println(messageSource.getMessage("price_of_all_products", new Object[]{summaryPrice}, Locale.forLanguageTag(locale)));
        System.out.println(messageSource.getMessage("price_of_all_products_plus_vat", new Object[]{summaryVatPrice}, Locale.forLanguageTag(locale)));
    }
}
