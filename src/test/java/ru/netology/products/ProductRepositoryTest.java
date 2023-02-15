package ru.netology.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product product1 = new Book(1, "Book1", 400, "Author1");
    Product product2 = new Smartphone(2, "Smartphone1", 70000, "Manufacturer1");
    Product product3 = new Book(3, "Book2", 1200, "Author2");
    Product product4 = new Smartphone(4, "Smartphone2", 20000, "Manufacturer2");
    Product product5 = new Book(5, "Book3", 450, "Author2");

    @BeforeEach
    public void save() {
        repository.save(product1);
        repository.save(product2);
        repository.save(product3);
        repository.save(product4);
        repository.save(product5);
    }

    @Test
    public void shouldFindAll() {
        Product[] expected = new Product[]{product1, product2, product3, product4, product5};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveLastProduct() {

        repository.removeById(5);

        Product[] expected = new Product[]{product1, product2, product3, product4};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllBooks() {
        Product[] expected = new Product[]{product1, product3, product5};
        Product[] actual = manager.searchBy("Book");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByName() {
        Product[] expected = new Product[]{product2};
        Product[] actual = manager.searchBy("Smartphone1");

        Assertions.assertArrayEquals(expected, actual);
    }
}
