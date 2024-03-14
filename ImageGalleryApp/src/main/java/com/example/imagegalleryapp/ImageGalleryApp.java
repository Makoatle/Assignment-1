package com.example.imagegalleryapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ImageGalleryApp extends Application {


    private ImageView fullSizeImageView;
    private Image[] images;
    private int currentIndex;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Gallery");

        // Load images
        images = new Image[]{

        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image1.jpg"))),
        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image2.jpg"))),
        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image3.jpg"))),
        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image4.jpg"))),
        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image5.jpg"))),
        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image6.jpg"))),
        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image7.jpg"))),
        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image8.jpg")))
        };

        // Create thumbnail grid
        GridPane thumbnailGrid = new GridPane();
        for (int i = 0; i < images.length; i++) {
            ImageView thumbnailImageView = new ImageView(images[i]);
            thumbnailImageView.setFitWidth(100);
            thumbnailImageView.setFitHeight(100);
            int finalI = i;
            thumbnailImageView.setOnMouseClicked(event -> showFullSizeImage(finalI));
            thumbnailGrid.add(thumbnailImageView, i % 3, i / 3);
        }

        // Create full size image view
        fullSizeImageView = new ImageView();
        fullSizeImageView.setPreserveRatio(true);
        fullSizeImageView.setFitWidth(500);
        fullSizeImageView.setFitHeight(500);

        // Create navigation buttons
        Button prevButton = new Button("Previous");
        prevButton.setOnAction(event -> showPreviousImage());
        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> showNextImage());

        // Stack pane for full size image and navigation buttons
        StackPane fullSizePane = new StackPane(fullSizeImageView);
        fullSizePane.getChildren().addAll(prevButton, nextButton);
        StackPane.setAlignment(prevButton, javafx.geometry.Pos.CENTER_LEFT);
        StackPane.setAlignment(nextButton, javafx.geometry.Pos.CENTER_RIGHT);

        // Border pane for layout
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(thumbnailGrid);
        borderPane.setCenter(fullSizePane);

        // Create and set scene
        Scene scene = new Scene(borderPane, 800, 600);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css"), "styles.css resource not found").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showFullSizeImage(int index) {
        fullSizeImageView.setImage(images[index]);
        currentIndex = index;
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % images.length;
        fullSizeImageView.setImage(images[currentIndex]);
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        fullSizeImageView.setImage(images[currentIndex]);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
