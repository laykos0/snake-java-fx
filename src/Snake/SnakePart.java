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
