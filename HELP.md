# Homework for the second week of the course

### Variable configuration

Inside the file `application.properties`, set the value of the variable to one of three:
- `start` - The basic functionality that each store has is the "START" variant. It allows you to add products to the basket (the product stores the name and price), and on their basis, display the price of all products in the console window. 
- `plus` - The "PLUS" package additionally allows adding VAT to the resulting price. The VAT rate is included in the configuration file.
- `pro` - Apart from calculating the tax, the "PRO" package also has the option of calculating a discount, the value of which is included in the configuration file.

When launched, the application adds any 5 products with a random price (in the range of PLN 50-300) and displays their total price.  