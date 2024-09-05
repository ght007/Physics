package io.eisaatif.physicssimapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Button {
    private String text = "";

    private float x;
    private float y;

    private float radius;
    private float width;
    private float height;
    private float initR, initWidth, initHeight;
    private float initX, initY;

    private float textWidth;
    private float textHeight;

    private float enlargementFactor = 1.11f;

    private int borderThickness = 6;
    private int buttonOfChoice = 0;

    private boolean isCircular = false;
    private boolean isRectangular = false;
    private boolean isImage = false;

    private boolean hasBorder = false;
    private boolean borderOnHover = false;
    private boolean enlargeOnHover = true;
    private boolean enlargeOnClick = false;
    private boolean colourChangeOnHover = false;
    private boolean isClick = false;
    private boolean isHover = false;

    private Color initColour = Color.WHITE;
    private Color colour = Color.WHITE;
    private Color colourChanged = Color.LIGHT_GRAY;
    private Color borderColour = Color.BLACK;
    private Color textColour = Color.BLACK;

    private Texture image;

    private Vector2 textPos = new Vector2(0, 0);
    private BitmapFont font = new BitmapFont();


    /**
     * @param x      float: x position of the button
     * @param y      float: y position of the button
     * @param width  float: width of the button
     * @param height float: height of the button
     */
    public Button(float x, float y, float width, float height) {
        isRectangular = true;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.initWidth = width;
        this.initHeight = height;
        this.initR = 0;
        this.initX = this.x;
        this.initY = this.y;
        textPos = new Vector2(this.x + this.width / 2 - textWidth / 2, this.y + this.height / 2 + textHeight / 2);
    }

    /**
     * @param x float: x position of the button
     * @param y float: y position of the button
     * @param r float: radius of the button
     */
    public Button(float x, float y, float r) {
        isCircular = true;

        this.x = x;
        this.y = y;
        this.radius = r;

        this.initR = r;
        this.initWidth = 0;
        this.initHeight = 0;
        this.initX = this.x;
        this.initY = this.y;
    }

    /**
     * Doesn't support enlargement due to pixelation of images.
     * Preferred way to enlarge: set the image to a copy of itself that is bigger
     *
     * @param x     float: x position of the button
     * @param y     float: y position of the button
     * @param image Texture: texture which is drawn in place of the button
     */
    public Button(float x, float y, Texture image) {
        isImage = true;

        this.x = x;
        this.y = y;
        this.radius = -1;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();

        this.initR = -1;
        this.initWidth = image.getWidth();
        this.initHeight = image.getHeight();
        this.initX = this.x;
        this.initY = this.y;

        setHasBorder(false);
        setEnlargeOnHover(false);
    }

    /**
     * Returns whether the button is hovered over or not
     *
     * @param mousePos Vector2: mouse position to see if the button contains the vector in its area
     * @return boolean: whether the button is hovered over or not.
     */
    public boolean isHoveredOn(Vector2 mousePos) {
        /*
            Different for each type of button
         */
        if (isCircular) {
            Circle self = new Circle(this.x, this.y, this.radius);
            if (self.contains(mousePos)) {
                return true;
            }
        }

        if (isRectangular) {
            if (mousePos.x > this.x && mousePos.x < this.x + this.width) {
                if (mousePos.y > this.y && mousePos.y < this.y + this.height) {
                    return true;
                }
            }
        }

        if (isImage) {
            if (mousePos.x > this.x && mousePos.x < this.x + this.width) {
                return mousePos.y > this.y && mousePos.y < this.y + this.getHeight();
            }
        }
        return false;
    }

    /**
     * Returns whether the button was clicked on by checking if it
     * was being hovered on and the mouse was clicked.
     *
     * @param mousePos Vector2: mouse position for .isHoveredOn()
     * @return boolean: returns whether button was clicked on.
     */
    public boolean isJustClickedOn(Vector2 mousePos) {
        if (isHoveredOn(mousePos)) {
            if (InputManager.isButtonJustDown(buttonOfChoice)) {
                isClick = true;
            }
        } else {
            isClick = false;
        }
        return isClick && InputManager.isButtonJustUp(buttonOfChoice);
    }

    public boolean isHeldClickedOn(Vector2 mousePos) {
        if (isHoveredOn(mousePos)) {
            isHover = true;
        }
        if(InputManager.isButtonUp(buttonOfChoice)) {
            isHover = false;
        }
        return (isHover && InputManager.isButtonDown(buttonOfChoice));
    }

    /**
     * Updates the button when hovered on.
     * If the button is circular, the radius enlarges and the position is
     * modified to make sure that the button is in the same spot despite
     * a different size. If it's an image or a rectangle, then the width
     * and height are enlarged.
     * If colourChangeOnHover is true, then the colour changes to colourChanged
     *
     * @param mousePos Vector2: mouse position for .isHoveredOn()
     */
    public void updateOnHover(Vector2 mousePos) {
        if (enlargeOnHover && isHoveredOn(mousePos)) {
            if (isCircular) {
                this.radius = initR * enlargementFactor;
            }

            if (isRectangular) {
                this.width = initWidth * enlargementFactor;
                this.height = initHeight * enlargementFactor;
                float dx = this.width - initWidth;
                float dy = this.height - initHeight;
                this.x = initX - dx / 2;
                this.y = initY - dy / 2;
            }
        } else if (isHoveredOn(mousePos)) {
            if (colourChangeOnHover) {
                colour = colourChanged;
            }
        } else {
            if(!enlargeOnClick && isHeldClickedOn(mousePos)){
                this.radius = initR;
                this.width = initWidth;
                this.height = initHeight;
                this.x = initX;
                this.y = initY;
                colour = initColour;
            }
        }
        if (borderOnHover && isHoveredOn(mousePos)) {
            hasBorder = true;
        } else if (borderOnHover) {
            hasBorder = false;
        }

    }

    public void updateOnClick(Vector2 mousePos) {
        if (enlargeOnClick && isHeldClickedOn(mousePos)) {
            if (isCircular) {
                this.radius = initR * enlargementFactor;
            }

            if (isRectangular) {
                this.width = initWidth * enlargementFactor;
                this.height = initHeight * enlargementFactor;
                float dx = this.width - initWidth;
                float dy = this.height - initHeight;
                this.x = initX - dx / 2;
                this.y = initY - dy / 2;

            }

        } else if (!isHeldClickedOn(mousePos)) {
            this.radius = initR;
            this.width = initWidth;
            this.height = initHeight;
            this.x = initX;
            this.y = initY;
            colour = initColour;
        }
    }

    /**
     * @param sr       ShapeRenderer: used for drawing the button
     * @param batch    SpriteBatch: used for drawing the text
     * @param mousePos Vector2: used for .updateOnHover()
     */
    public void render(ShapeRenderer sr, SpriteBatch batch, Vector2 mousePos) {
        updateOnClick(mousePos);
        updateOnHover(mousePos);
        if (isCircular) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(borderColour);

            if (hasBorder) {
                sr.circle(this.x, this.y, this.radius + borderThickness);
            }
            sr.setColor(colour);
            sr.circle(this.x, this.y, this.radius);
            sr.end();
            batch.begin();
            font.setColor(textColour);
            font.draw(batch, text, textPos.x, textPos.y);
            batch.end();

        } else if (isRectangular) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(borderColour);
            if (hasBorder)
                sr.rect(this.x - borderThickness, this.y - borderThickness, this.width + borderThickness * 2, this.height + borderThickness * 2);
            sr.setColor(colour);
            sr.rect(this.x, this.y, this.width, this.height);
            sr.end();
            batch.begin();
            font.setColor(textColour);
            font.draw(batch, text, textPos.x, textPos.y);
            batch.end();

        } else if (isImage) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(colour);
            if (!colour.equals(new Color(0, 0, 0, 0))) {
                sr.rect(this.x, this.y, this.width, this.height);
            }
            sr.end();
            batch.begin();
            batch.draw(image, this.x + this.width / 2 - image.getWidth() / 2f, this.y + this.height / 2 - image.getHeight() / 2f);
            batch.end();
        }
    }

    public void render(ShapeRenderer sr, SpriteBatch batch) {
        if (isCircular) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(borderColour);

            if (hasBorder) {
                sr.circle(this.x, this.y, this.radius + borderThickness);
            }
            sr.setColor(colour);
            sr.circle(this.x, this.y, this.radius);
            sr.end();
            batch.begin();
            font.setColor(textColour);
            font.draw(batch, text, textPos.x, textPos.y);
            batch.end();

        } else if (isRectangular) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(borderColour);
            if (hasBorder)
                sr.rect(this.x - borderThickness, this.y - borderThickness, this.width + borderThickness * 2, this.height + borderThickness * 2);
            sr.setColor(colour);
            sr.rect(this.x, this.y, this.width, this.height);
            sr.end();
            batch.begin();
            font.setColor(textColour);
            font.draw(batch, text, textPos.x, textPos.y);
            batch.end();

        } else if (isImage) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(colour);
            if (!colour.equals(new Color(0, 0, 0, 0))) {
                sr.rect(this.x, this.y, this.width, this.height);
            }
            sr.end();
            batch.begin();
            batch.draw(image, this.x + this.width / 2 - image.getWidth() / 2f, this.y + this.height / 2 - image.getHeight() / 2f);
            batch.end();
        }
    }

    /**
     * Disposes all disposables.
     */
    public void dispose() {
        if (isImage) {
            image.dispose();
        }
        font.dispose();

    }

    /*

    ENCAPSULATION:

    */

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.initX = x;
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.initY = y;
        this.y = y;

    }

    public float getWidth() {
        if (isRectangular || isImage) {
            return width;
        } else if (isCircular) {
            return this.radius * 2;
        } else {
            throw new RuntimeException("Field width is not available for circular labels");
        }
    }

    public void setWidth(float width) {
        if (isRectangular || isImage) {
            this.initWidth = width;
            this.width = width;
        } else {
            throw new RuntimeException("Field width is not available for circular labels");
        }
    }

    public float getHeight() {
        if (isRectangular || isImage) {
            return height;
        } else if (isCircular) {
            return this.radius * 2;
        } else {

            throw new RuntimeException("Field height is not available for circular labels");
        }
    }

    public void setHeight(float height) {
        if (isRectangular || isImage) {
            this.initHeight = height;
            this.height = height;
        } else {
            throw new RuntimeException("Field height is not available for circular labels");
        }

    }

    public float getRadius() {
        if (isCircular) {
            return radius;
        } else {
            throw new RuntimeException("Field radius not available for rectangular or custom sprite labels.");
        }
    }

    public void setRadius(float radius) {
        if (isCircular) {
            this.radius = radius;
        } else {
            throw new RuntimeException("Field radius not available for rectangular or custom sprite labels.");
        }
    }

    public int getButtonOfChoice() {
        return buttonOfChoice;
    }

    public void setButtonOfChoice(int buttonOfChoice) {
        this.buttonOfChoice = buttonOfChoice;
    }

    public boolean getHasBorder() {
        return hasBorder;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text, BitmapFont font) {
        this.font.dispose();
        this.text = text;
        this.font = font;

        GlyphLayout layout = new GlyphLayout(font, text);
        textWidth = layout.width;
        textHeight = layout.height;

        if (isRectangular) {
            textPos = new Vector2(this.x + this.width / 2 - textWidth / 2, this.y + this.height / 2 + textHeight / 2);
        } else if (isCircular) {
            textPos = new Vector2(this.x - textWidth / 2, this.y + textHeight / 2);
        }
    }

    public void format(String path) throws FileNotFoundException {
        // Will customise the button to its extent with text file
        File file = new File(path);
        Scanner s = new Scanner(file);

    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
    }

    public Color getTextColour() {
        return textColour;
    }

    public void setTextColour(Color colour) {
        this.textColour = colour;
    }

    public boolean getEnlargeOnHover() {
        return enlargeOnHover;
    }

    public void setEnlargeOnHover(boolean enlargeOnHover) {
        this.enlargeOnHover = enlargeOnHover;
    }

    public void setBorderOnHover(boolean borderOnHover) {
        this.borderOnHover = borderOnHover;
    }

    public float getEnlargementFactor() {
        return enlargementFactor;
    }

    public void setEnlargementFactor(float enlargementFactor) {
        this.enlargementFactor = enlargementFactor;
    }

    public Color getColourChanged() {
        return colourChanged;
    }

    public void setColourChanged(Color colourChanged) {
        this.colourChanged = colourChanged;
    }

    public boolean getColourChangeOnHover() {
        return colourChangeOnHover;
    }

    public void setColourChangeOnHover(boolean colourChangeOnHover) {
        this.colourChangeOnHover = colourChangeOnHover;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.initColour = colour;
        this.colour = colour;
    }

    public boolean isCircular() {
        return isCircular;
    }

    public boolean isRectangular() {
        return isRectangular;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Color getBorderColour() {
        return this.borderColour;
    }

    public void setBorderColour(Color colour) {
        this.borderColour = colour;
    }

    public boolean getEnlargeOnClick() {
        return enlargeOnClick;
    }

    public void setEnlargeOnClick(boolean enlargeOnClick) {
        this.enlargeOnClick = enlargeOnClick;
    }
}
