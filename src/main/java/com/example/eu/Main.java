package com.example.eu;

import Domain.Product;
import Repository.AbstractRepository;
import Repository.BinaryRepository;
import Repository.MemoryRepository;
import Service.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {


        try {
            new File("config.properties").createNewFile();
        } catch (IOException ioException) {

        }

        java.util.Properties p = new java.util.Properties();

        p.load(new FileReader("config.properties"));

        String repo = p.getProperty("Repository");
        String car = p.getProperty("Car");
        String rent = p.getProperty("Rent");


        AbstractRepository<Product> productRepository = null;

        switch (repo) {
            case "binary":
                productRepository = new BinaryRepository<>(car);
                break;

            default:
                productRepository = new MemoryRepository<>();
        }

        Service service = new Service(productRepository);


        //consoleInterface.run();
    }
}