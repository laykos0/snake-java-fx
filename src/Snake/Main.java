/*
# Copyright (C) 2022 laykos0 <laykos0@protonmail.com>
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as published by
# the Free Software Foundation; version 3 of the License.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
*/
package Snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import static Snake.Setup.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Random randomizer = new Random();

        /* Game elements */
        Rectangle gameField = new Rectangle();
        Rectangle scoreField = new Rectangle();
        Rectangle snakeFood = new Rectangle();
        ArrayList<SnakePart> snakeParts = new ArrayList<>();

        /* Text elements */
        Label score = new Label();
        Label endScore = new Label();
        Label endMessage = new Label();

        /* Layout elements */
        AnchorPane primaryLayout = new AnchorPane();
        AnchorPane secondaryLayout = new AnchorPane();
        Scene primaryScene = new Scene(primaryLayout,400,400);
        Scene secondaryScene = new Scene(secondaryLayout,400,400);

        /* Snake parameters */
        final int snakePartSize = 20;
        final String[] direction = {"Right"};

        /* Adjusts GUI elements */
        createField(gameField, primaryLayout);
        createFood(snakeFood, primaryLayout);
        createSnake(snakeParts, primaryLayout);
        createScore(score, primaryLayout);
        createEndScore(scoreField, endScore, endMessage, secondaryLayout);

        /* Controls game animation */
        AnimationTimer gameAnimation = new AnimationTimer() {

            private long lastUpdate; /* Tracks when handle() was last called */
            private double savedX, savedY; /* Tracks coordinates for new part */
            private int playerScore = 0; /* Tracks player score */
            private boolean foodOnSnake = true; /* Tracks possibility to generate food */

            @Override
            public void start() {
                lastUpdate = System.nanoTime();
                super.start();
            }

            /* Adds new part at the end of snake */
            private void addNewPart(double newPartX, double newPartY){
                SnakePart newPart = new SnakePart();
                newPart.setPartOfSnakeX(newPartX);
                newPart.setPartOfSnakeY(newPartY);
                snakeParts.add(newPart);
                primaryLayout.getChildren().add(snakeParts.get(snakeParts.size() - 1).getPartOfSnake());
            }

            /* Generates snake food at a random location */
            private void generateFood(){
                boolean foodGenerated = false;
                while (!foodGenerated) {
                    int randomFoodX = randomizer.nextInt(16) + 2;
                    int randomFoodY = randomizer.nextInt(16) + 2;
                    for (SnakePart temporarySnakePart : snakeParts) {
                        foodOnSnake = temporarySnakePart.getPartOfSnake().getBoundsInParent().contains(20 * randomFoodX, 20 * randomFoodY);
                        if (foodOnSnake) { break; }
                    }
                    if (!foodOnSnake) {
                        snakeFood.setTranslateX(20 * randomFoodX);
                        snakeFood.setTranslateY(20 * randomFoodY);
                        foodGenerated = true;
                    }
                }
            }

            /* Main game loop */
            @Override
            public void handle(long now) {

                /* Controls game speed */
                if (now - lastUpdate >= 75000000){

                    /* Handles snake movement and stores coordinates for new part */
                    switch(direction[0]){
                        case "Up" -> {
                            savedX = snakeParts.get(snakeParts.size() - 1).getPartOfSnakeX();
                            savedY = snakeParts.get(snakeParts.size() - 1).getPartOfSnakeY() - snakePartSize;
                            snakeParts.get(0).setPartOfSnakeY(snakeParts.get(snakeParts.size() - 1).getPartOfSnakeY() - snakePartSize);
                            snakeParts.get(0).setPartOfSnakeX(snakeParts.get(snakeParts.size() - 1).getPartOfSnakeX());
                        }
                        case "Down" -> {
                            savedX = snakeParts.get(snakeParts.size() - 1).getPartOfSnakeX();
                            savedY = snakeParts.get(snakeParts.size() - 1).getPartOfSnakeY() + snakePartSize;
                            snakeParts.get(0).setPartOfSnakeY(snakeParts.get(snakeParts.size() - 1).getPartOfSnakeY() + snakePartSize);
                            snakeParts.get(0).setPartOfSnakeX(snakeParts.get(snakeParts.size() - 1).getPartOfSnakeX());
                        }
                        case "Left" -> {
                            savedX = snakeParts.get(snakeParts.size() - 1).getPartOfSnakeX() - snakePartSize;
                            savedY = snakeParts.get(snakeParts.size() - 1).getPartOfSnakeY();
                            snakeParts.get(0).setPartOfSnakeY(snakeParts.get(snakeParts.size() - 1).getPartOfSnakeY());
                            snakeParts.get(0).setPartOfSnakeX(snakeParts.get(snakeParts.size() - 1).getPartOfSnakeX() - snakePartSize);
                        }
                        case "Right" -> {
                            savedX = snakeParts.get(snakeParts.size() - 1).getPartOfSnakeX() + snakePartSize;
                            savedY = snakeParts.get(snakeParts.size() - 1).getPartOfSnakeY();
                            snakeParts.get(0).setPartOfSnakeY(snakeParts.get(snakeParts.size() - 1).getPartOfSnakeY());
                            snakeParts.get(0).setPartOfSnakeX(snakeParts.get(snakeParts.size() - 1).getPartOfSnakeX() + snakePartSize) ;
                        }
                    }

                    /* Moves snake tail to the first position */
                    SnakePart temporarySnakePart = snakeParts.get(0);
                    snakeParts.remove(0);
                    snakeParts.add(temporarySnakePart);

                    /* Checks for collision with snake model */
                    for (int x = 0; x < snakeParts.size() - 1; x++){
                      if (snakeParts.get(x).partOfSnake.getBoundsInParent().contains(snakeParts.get(snakeParts.size() - 1).partOfSnake.getBoundsInParent())){
                          endScore.setText("Score: " + playerScore);
                          playerScore = 0;
                          primaryStage.setScene(secondaryScene);
                          stop();
                      }
                    }

                    /* Checks for collision with game field */
                    if (!(gameField.getBoundsInParent().contains(snakeParts.get(snakeParts.size() - 1).partOfSnake.getBoundsInParent()))) {
                        endScore.setText("Score: " + playerScore);
                        playerScore = 0;
                        primaryStage.setScene(secondaryScene);
                        stop();
                    }

                    /* Checks for collision with food */
                    if (snakeParts.get(snakeParts.size() - 1).partOfSnake.getBoundsInParent().contains(snakeFood.getBoundsInParent())){
                        playerScore++;
                        score.setText("Score: " + playerScore);
                        addNewPart(savedX, savedY);
                        generateFood();
                    }

                    lastUpdate = now;
                }
            }
        };
        gameAnimation.start();

        /* Handles user input (primaryScene) */
        primaryScene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case UP, W -> { if (!direction[0].equals("Down")) { direction[0] = "Up";} }
                case DOWN, S -> { if (!direction[0].equals("Up")) { direction[0] = "Down";} }
                case LEFT, A -> { if (!direction[0].equals("Right")) { direction[0] = "Left";} }
                case RIGHT, D -> { if (!direction[0].equals("Left")) { direction[0] = "Right";} }
            }
        });

        /* Handles user input (secondaryScene) */
        secondaryScene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.ENTER) { /* Restarts game on 'ENTER' key press */
                primaryLayout.getChildren().clear();
                snakeParts.clear();
                direction[0] = "Right";
                createField(gameField, primaryLayout);
                createSnake(snakeParts,primaryLayout);
                createFood(snakeFood, primaryLayout);
                createScore(score, primaryLayout);
                primaryStage.setScene(primaryScene);
                gameAnimation.start();
            }
        });

        primaryStage.setScene(primaryScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

