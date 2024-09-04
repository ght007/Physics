package io.eisaatif.physicssimapp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * Extras such as javadocs and special static functions
 */
public final class Extras {
    public static final int WIDTH = 900, HEIGHT = 500;
    public static final float ASPECT_RATIO = WIDTH / HEIGHT;
    public static final float SCALE = 0.01f;
    public static final LinkedList<Integer> numberKeys = new LinkedList<>();
    public static final BitmapFont font = new BitmapFont();
    public static final float TIME_STEP = 0.00016f;
    public static final float G = 6.6f * 100;
    public static DecimalFormat df = new DecimalFormat("###.##");

    /**
     * Converts the mouse screen coords to coords in the world
     *
     * @param viewport Viewport: viewport to .unproject() coords onto
     * @return Vector2: The mouse position in world units
     */
    public static Vector2 getMousePos(Viewport viewport) {
        Vector3 mouseOnScreen = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Vector3 mouseOnWorld3D = viewport.unproject(mouseOnScreen);
        return new Vector2(mouseOnWorld3D.x, mouseOnWorld3D.y);
    }

    /**
     * Inserts a string into another string at an index specified at function call
     *
     * @param originalString     String: string to be modified
     * @param stringToBeInserted String: string which is inserted at an index
     * @param index              int: index to insert the string at the specific pos
     * @return
     */
    public static String insertString(String originalString, String stringToBeInserted, int index) {

        String newString = "";
        if(originalString.equals("")) {
            return stringToBeInserted;
        }
        for(int i = -1; i < originalString.length(); i++) {
            try {
                newString += originalString.charAt(i);
            } catch(StringIndexOutOfBoundsException ignored) {

            }

            if(i == index) {
                newString += stringToBeInserted;
            }
        }
        return newString;
    }

    /**
     * @param string String: the string whose char is to be removed
     * @param index  int: the index of the String to reference a char that is to be removed
     * @return String: the final string with the removed char
     * @throws RuntimeException
     */
    public static String removeChar(String string, int index) throws RuntimeException {
        if(index < 0 || index >= string.length()) {
            throw new RuntimeException("String length must be bigger than 0.");
        }
        String start = string.substring(0, index);
        String end = string.substring(index + 1);

        return start + end;
    }

    public static float getTextWidth(BitmapFont font, String text) { // Used to update the text width
        GlyphLayout layout = new GlyphLayout(font, text);
        return layout.width;
    }

    public static float getTextHeight(BitmapFont font, String text) { // used to update the text height
        GlyphLayout layout = new GlyphLayout(font, text);
        return layout.height;
    }

    public static void initNumberKeys() {
        numberKeys.add(Input.Keys.NUM_1);
        numberKeys.add(Input.Keys.NUM_2);
        numberKeys.add(Input.Keys.NUM_3);
        numberKeys.add(Input.Keys.NUM_4);
        numberKeys.add(Input.Keys.NUM_5);
        numberKeys.add(Input.Keys.NUM_6);
        numberKeys.add(Input.Keys.NUM_7);
        numberKeys.add(Input.Keys.NUM_8);
        numberKeys.add(Input.Keys.NUM_9);
        numberKeys.add(Input.Keys.NUM_0);

        numberKeys.add(Input.Keys.NUMPAD_1);
        numberKeys.add(Input.Keys.NUMPAD_2);
        numberKeys.add(Input.Keys.NUMPAD_3);
        numberKeys.add(Input.Keys.NUMPAD_4);
        numberKeys.add(Input.Keys.NUMPAD_5);
        numberKeys.add(Input.Keys.NUMPAD_6);
        numberKeys.add(Input.Keys.NUMPAD_7);
        numberKeys.add(Input.Keys.NUMPAD_8);
        numberKeys.add(Input.Keys.NUMPAD_9);
        numberKeys.add(Input.Keys.NUMPAD_0);

        numberKeys.add(Input.Keys.PERIOD);
    }

    public static LinkedList<Integer> getNumberKeys() {
        return numberKeys;
    }

    public static BitmapFont getFont() {
        return font;
    }
}
