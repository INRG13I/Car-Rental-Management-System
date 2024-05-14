package com.example.eu;

import Domain.Product;
import Repository.AbstractRepository;
import Repository.BinaryRepository;
import Repository.MemoryRepository;
import Service.Service;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;


public class HelloApplication extends Application {
    TextField idTextField = new TextField();
    TextField brandTextField = new TextField();

    TextField nameTextField = new TextField();

    TextField priceTextField = new TextField();
    TextField quantityTextField = new TextField();

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {


        AbstractRepository<Product> productRepo;

        java.util.Properties p = new java.util.Properties();

        try {
            p.load(new FileReader("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String repo = p.getProperty("Repository");
        String product = p.getProperty("Product");

        switch (repo) {
            case "binary":
                productRepo = new BinaryRepository<>(product);
                break;
            default:
                productRepo = new MemoryRepository<>();
        }

        Service productService = new Service(productRepo);
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        VBox mainVerticalBox = new VBox();
        mainVerticalBox.setPadding(new Insets(10));

        ObservableList<Product> products = FXCollections.observableArrayList(productService.getAllProducts());
        ListView<Product> listView = new ListView<Product>(products);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Product product = listView.getSelectionModel().getSelectedItem();
                idTextField.setText(Integer.toString(product.getId()));
                brandTextField.setText(product.getBrand());
                nameTextField.setText(product.getName());
                priceTextField.setText(Integer.toString(product.getPrice()));
                quantityTextField.setText(Integer.toString(product.getQuantity()));

            }
        });
        mainVerticalBox.getChildren().add(listView);

        GridPane productsGridPane = new GridPane();

        Label idLabel = new Label();
        idLabel.setText("Id: ");
        idLabel.setPadding(new Insets(10, 5, 10, 0));

        Label brandLabel = new Label();
        brandLabel.setText("Brand:");
        brandLabel.setPadding(new Insets(10, 5, 10, 0));

        Label nameLabel = new Label();
        nameLabel.setText("Name:");
        nameLabel.setPadding(new Insets(10, 5, 10, 0));


        Label priceLabel = new Label();
        priceLabel.setText("Price:");
        priceLabel.setPadding(new Insets(10, 5, 10, 0));

        Label quantityLabel = new Label();
        quantityLabel.setText("Quantity:");
        quantityLabel.setPadding(new Insets(10, 5, 10, 0));


        productsGridPane.add(idLabel, 0, 0);
        productsGridPane.add(idTextField, 0, 1);
        productsGridPane.add(brandLabel, 1, 0);
        productsGridPane.add(brandTextField, 1, 1);
        productsGridPane.add(nameLabel, 2, 0);
        productsGridPane.add(nameTextField, 2, 1);
        productsGridPane.add(priceLabel, 3, 0);
        productsGridPane.add(priceTextField, 3, 1);
        productsGridPane.add(quantityLabel, 4, 0);
        productsGridPane.add(quantityTextField, 4, 1);
        mainVerticalBox.getChildren().add(productsGridPane);

        HBox buttonsHorizontalBox = new HBox();
        mainVerticalBox.getChildren().add(buttonsHorizontalBox);

        Button addButton = new Button("Add product");
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String brand = brandTextField.getText();
                    String name = nameTextField.getText();
                    int price = Integer.parseInt(priceTextField.getText());
                    int quantity = Integer.parseInt(quantityTextField.getText());
                    productService.addProduct(new Product(id, brand, name, price, quantity));
                    products.setAll(productService.getAllProducts());
                } catch (Exception e) {
                    Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
                    errorPopUp.setTitle("Error");
                    errorPopUp.setContentText(e.getMessage());
                    errorPopUp.show();
                }
            }
        });
        buttonsHorizontalBox.getChildren().add(addButton);

        Button updateButton = new Button("Update product");
        updateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String brand = brandTextField.getText();
                    String name = nameTextField.getText();
                    int price = Integer.parseInt(priceTextField.getText());
                    int quantity = Integer.parseInt(quantityTextField.getText());
                    productService.updateProduct(id, new Product(id, brand, name, price, quantity));
                    products.setAll(productService.getAllProducts());
                } catch (Exception e) {
                    Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
                    errorPopUp.setTitle("Error");
                    errorPopUp.setContentText(e.getMessage());
                    errorPopUp.show();
                }
            }
        });
        buttonsHorizontalBox.getChildren().add(updateButton);

        Button deleteButton = new Button("Delete product");
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    productService.deleteProduct(id);
                    products.setAll(productService.getAllProducts());
                } catch (Exception e) {
                    Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
                    errorPopUp.setTitle("Error");
                    errorPopUp.setContentText(e.getMessage());
                    errorPopUp.show();
                }
            }
        });
        buttonsHorizontalBox.getChildren().add(deleteButton);


    }
}