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

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.util.ArrayList;

/* Utility class: GUI setup */
public class Setup {

    /* Creates snake food */
    public static void createFood(Rectangle snakeFood, AnchorPane primaryLayout){
        snakeFood.setWidth(20);
        snakeFood.setHeight(20);
        snakeFood.setFill(Color.RED);
        snakeFood.setStroke(Color.BLACK);
        snakeFood.setTranslateX(300);
        snakeFood.setTranslateY(200);
        primaryLayout.getChildren().add(snakeFood);
    }

    /* Creates snake model */
    public static void createSnake(ArrayList<SnakePart> snakeModel, AnchorPane primaryLayout){
        for (int x = 0; x < 7; x++){
            snakeModel.add(new SnakePart());
            snakeModel.get(x).setPartOfSnakeX(80 + x*20);
            snakeModel.get(x).setPartOfSnakeY(200);
            primaryLayout.getChildren().add(snakeModel.get(x).getPartOfSnake());
        }
    }

    /* Creates game field */
    public static void createField(Rectangle gameField, AnchorPane primaryLayout){
        gameField.setWidth(360);
        gameField.setHeight(360);
        gameField.setTranslateX(20);
        gameField.setTranslateY(20);
        gameField.setFill(Color.WHITE);
        gameField.setStroke(Color.BLACK);
        primaryLayout.getChildren().add(gameField);
    }

    /* Creates score tracker */
    public static void createScore(Label score, AnchorPane primaryLayout) {
        score.setTranslateX(300);
        score.setTranslateY(0);
        score.setText("Score: 0");
        score.setFont(new Font("Comic Sans MS", 15));
        primaryLayout.getChildren().add(score);
    }

    /* Creates end score display */
    public static void createEndScore(Rectangle scoreField, Label endScore, Label endMessage, AnchorPane secondaryLayout) {
        createField(scoreField, secondaryLayout);
        endScore.setTranslateX(160);
        endScore.setTranslateY(120);
        endScore.setFont(new Font("Comic Sans MS", 22));
        endMessage.setTranslateX(55);
        endMessage.setTranslateY(270);
        endMessage.setText("Press ENTER to restart");
        endMessage.setFont(new Font("Comic Sans MS", 26));
        secondaryLayout.getChildren().add(endScore);
        secondaryLayout.getChildren().add(endMessage);
        endMessage.setUnderline(true);
    }
}
