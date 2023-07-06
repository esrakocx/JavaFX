package com.example.movidleguessinggame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoviedleGame extends Application {

    private TilePane tilePane;
    private TextField guessText;
    private int chanceOfGuess;
    private Movie currentMovie;


    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Moviedle");
        stage.setResizable(false);

        guessText = new TextField();
        guessText.setPrefWidth(350);
        guessText.setPrefHeight(30);

        Button guessBtn = new Button("Guess");
        guessBtn.setPrefWidth(70);
        guessBtn.setPrefHeight(30);
        guessBtn.setOnAction(e -> handleGuess());

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(guessText, guessBtn);

        tilePane = new TilePane();
        tilePane.setHgap(30);
        tilePane.setVgap(20);
        tilePane.setPrefColumns(5);
        tilePane.setPadding(new Insets(5));

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(hbox, tilePane);

        Scene scene = new Scene(root, 1100, 700);
        stage.setScene(scene);
        stage.show();

        startGame();
    }

    private void startGame(){
        List<Movie> movies = CSVReader("src/main/resources/imdb_top_250.csv");

        Random random = new Random();
        int rndIndex = random.nextInt(movies.size());
        currentMovie = movies.get(rndIndex);
        chanceOfGuess = 5;

        tilePane.getChildren().clear();

        for(int i = 0; i < 6; i++)
            tilePane.getChildren().add(createTiles("", false));
    }

    private StackPane createTiles(String tileValue, boolean isMatch){
        StackPane tile = new StackPane();
        Label label = new Label(tileValue);
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
        tile.getChildren().add(label);

        tile.setAlignment(Pos.CENTER);
        tile.setMinWidth(150);
        tile.setMinHeight(70);

        if(isMatch)
            tile.setStyle("-fx-background-color: green; -fx-padding: 5px");
        else
            tile.setStyle("-fx-background-color: red; -fx-padding: 5px");

        return tile;
    }

    private List<Movie> CSVReader(String path){
        List<Movie> movies = new ArrayList<>();
        String line;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"ISO-8859-1"));){
            while ((line = br.readLine()) != null){
                String[] movieInfo = line.split(";");
                Movie movie = new Movie(movieInfo[0], movieInfo[1], movieInfo[2], movieInfo[3], movieInfo[4], movieInfo[5]);
                movies.add(movie);
            }
        }
        catch (IOException E){
            E.printStackTrace();
        }
        return movies;
    }

    private Movie findMovie(String title){
        List<Movie> movies = CSVReader("src/main/resources/imdb_top_250.csv");
        for(Movie movie : movies){
            if(movie.getTitle().equalsIgnoreCase(title))
                return movie;
        }
        return null;
    }

    private void showPopUp(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void handleGuess(){
        String userGuess = guessText.getText();
        guessText.clear();

        Movie guessedMovie = findMovie(userGuess);
        if(guessedMovie != null){
            boolean isTitleMatch = guessedMovie.getTitle().equalsIgnoreCase(currentMovie.getTitle());
            boolean isYearMatch = guessedMovie.getYear().equalsIgnoreCase(currentMovie.getYear());
            boolean isGenreMatch = guessedMovie.getGenre().equalsIgnoreCase(currentMovie.getGenre());
            boolean isOriginMatch = guessedMovie.getOrigin().equalsIgnoreCase(currentMovie.getOrigin());
            boolean isDirectorMatch = guessedMovie.getDirector().equalsIgnoreCase(currentMovie.getDirector());
            boolean isStarMatch = guessedMovie.getStar().equalsIgnoreCase(currentMovie.getStar());

            tilePane.getChildren().add(createTiles(guessedMovie.getTitle(), isTitleMatch));
            tilePane.getChildren().add(createTiles(guessedMovie.getYear(), isYearMatch));
            tilePane.getChildren().add(createTiles(guessedMovie.getGenre(), isGenreMatch));
            tilePane.getChildren().add(createTiles(guessedMovie.getOrigin(), isOriginMatch));
            tilePane.getChildren().add(createTiles(guessedMovie.getDirector(), isDirectorMatch));
            tilePane.getChildren().add(createTiles(guessedMovie.getStar(), isStarMatch));

            if(isTitleMatch && isYearMatch && isGenreMatch && isOriginMatch && isDirectorMatch && isStarMatch){
                showPopUp("You Win!");
                startGame();
            }
            else{
                chanceOfGuess--;
                if(chanceOfGuess == 0){
                    showPopUp("Game Over! You no longer have a chance to guess...\nThe movie was: " + currentMovie.getTitle());
                    startGame();
                }
            }
        }
        else
            showPopUp("Invalid movie! Try again.");
    }

    public static void main(String[] args) {
        launch();
    }
}