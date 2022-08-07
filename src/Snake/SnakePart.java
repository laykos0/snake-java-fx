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

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/* Class representing a single snake part */
public class SnakePart {

    Rectangle partOfSnake;

    public SnakePart() {
        int sideLength = 20;
        partOfSnake = new Rectangle();
        partOfSnake.setWidth(sideLength);
        partOfSnake.setHeight(sideLength);
        partOfSnake.setFill(Color.SPRINGGREEN);
        partOfSnake.setStroke(Color.BLACK);
    }
    public Rectangle getPartOfSnake(){
        return partOfSnake;
    }

    /* Returns snake part coordinates */
    public double getPartOfSnakeX(){ return partOfSnake.getTranslateX();}
    public double getPartOfSnakeY(){ return partOfSnake.getTranslateY(); }

    /* Sets snake part coordinates */
    public void setPartOfSnakeX(double newX){ partOfSnake.setTranslateX(newX); }
    public void setPartOfSnakeY(double newY){ partOfSnake.setTranslateY(newY); }
}
