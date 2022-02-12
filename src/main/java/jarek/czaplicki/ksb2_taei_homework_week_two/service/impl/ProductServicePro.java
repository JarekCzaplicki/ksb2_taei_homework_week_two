package jarek.czaplicki.ksb2_taei_homework_week_two.service.impl;

import jarek.czaplicki.ksb2_taei_homework_week_two.Hellper.CreateProducts;
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
@Profile("pro")
public class ProductServicePro implements ProductService {
    @Value("${discountRate}")
    private Double discount;

    @Value("${vatRate}")
    private Double vatTax;

    @Value("${locale}")
    private String locale;

    private Double summaryPrice = 0.0;
    private Double summaryVatPrice = 0.0;
    private Double summaryPriceDiscount = 0.0;

    private final CreateProducts createProducts;
    private final MessageSource messageSource;

    @Autowired
    public ProductServicePro(CreateProducts createProducts, MessageSource messageSource) {
        this.createProducts = createProducts;
        this.messageSource = messageSource;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void totalPrice() {
//        List<Product> products = createProducts.create(5);
//        for (Product product : products) {
//            summaryPrice += product.getPrice();
//        }
//        summaryPrice = Math.round(summaryPrice * 100.0) / 100.0;
//        summaryPriceDiscount = Math.round((summaryPrice - (summaryPrice * discount)) * 100.0) / 100.0;
//        summaryVatPrice = Math.round((summaryPriceDiscount + (summaryPriceDiscount * vatTax)) * 100.0) / 100.0;
//        System.out.println(messageSource.getMessage("price_of_all_products", new Object[]{summaryPrice}, Locale.forLanguageTag(locale)));
//        System.out.println(messageSource.getMessage("price_of_all_products_after_discount", new Object[]{summaryPriceDiscount}, Locale.forLanguageTag(locale)));
//        System.out.println(messageSource.getMessage("price_of_all_products_after_discount_plus_vat", new Object[]{summaryVatPrice}, Locale.forLanguageTag(locale)));
//    }

    @Override
    public void printInfo() {
        List<Product> products = createProducts.create(5);
        for (Product product : products) {
            summaryPrice += product.getPrice();
        }
        summaryPrice = Math.round(summaryPrice * 100.0) / 100.0;
        summaryPriceDiscount = Math.round((summaryPrice - (summaryPrice * discount)) * 100.0) / 100.0;
        summaryVatPrice = Math.round((summaryPriceDiscount + (summaryPriceDiscount * vatTax)) * 100.0) / 100.0;
        System.out.println(messageSource.getMessage("price_of_all_products", new Object[]{summaryPrice}, Locale.forLanguageTag(locale)));
        System.out.println(messageSource.getMessage("price_of_all_products_after_discount", new Object[]{summaryPriceDiscount}, Locale.forLanguageTag(locale)));
        System.out.println(messageSource.getMessage("price_of_all_products_after_discount_plus_vat", new Object[]{summaryVatPrice}, Locale.forLanguageTag(locale)));
    }
}
