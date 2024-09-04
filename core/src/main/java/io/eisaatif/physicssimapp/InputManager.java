package io.eisaatif.physicssimapp;

import com.badlogic.gdx.InputProcessor;

/**
 * InputProcessor to process more complicated input than Gdx.input.XXX can
 */
public class InputManager implements InputProcessor {
    private static final boolean[] justPressedButtons = new boolean[5];
    private static final boolean[] justLetGoButtons = new boolean[5];
    private static final boolean[] justPressedKeys = new boolean[255];
    private static final boolean[] justLetGoKeys = new boolean[255];

    private static String keyValue;

    private static Integer keycodeOfKeyDown;
    private static Integer keycodeOfKeyUp = -1;

    private static boolean isKeyUp = false;
    private static boolean isKeyDown = false;

    private static Integer keycodeOfButtonDown;
    private static Integer keycodeOfButtonUp = -1;

    private static boolean isButtonUp = false;
    private static boolean isButtonDown = false;

    private static boolean isKeyJustDown = false;
    private static boolean justTouched;
    private static boolean justLetGo;
    private static boolean justPressed;
    private static boolean justUp;

    /**
     * @return String: the value which was typed i.e. for the key Input.Keys.A, it would be 'a'
     */
    public static String getKeyTyped() {
        return keyValue;
    }

    /**
     * @return int: the keycode for the key that was pressed.
     */
    public static int getKeyPressed() {
        return keycodeOfKeyDown;
    }

    /**
     * @return int: the keycode for the key that was most recently pressed but is not anymore.
     */
    public static int getKeyLetGo() {
        return keycodeOfKeyUp;
    }

    /**
     * @return int: the keycode for the button that was pressed.
     */
    public static int getButtonPressed() {
        return keycodeOfButtonDown;
    }

    /**
     * @return int: the keycode for the button that was most recently pressed but is not anymore.
     */
    public static int getButtonLetGo() {
        return keycodeOfButtonUp;
    }

    /**
     * @return boolean: whether any key is pressed.
     */
    public static boolean isKeyDown() {
        return isKeyDown;
    }

    /**
     * @return boolean: whether any button is pressed.
     */
    public static boolean isButtonDown() {
        return isButtonDown;
    }

    /**
     * @return boolean: whether any key is not up (must have been pressed to be able to be up)
     */
    public static boolean isKeyUp() {
        return isKeyUp;
    }

    /**
     * @return boolean: whether any button is not up (must have been pressed to be able to be up)
     */
    public static boolean isButtonUp() {
        return isButtonUp;
    }

    /**
     * @return boolean: whether a specific key is pressed.
     */
    public static boolean isKeyDown(int keycode) {
        return isKeyDown && getKeyPressed() == keycode;
    }

    /**
     * @return boolean: whether a specific button is pressed.
     */
    public static boolean isButtonDown(int button) {
        return isButtonDown && getButtonPressed() == button;
    }

    /**
     * @return boolean: whether a specific key is not up (must have been pressed to be able to be up)
     */
    public static boolean isKeyUp(int keycode) {
        return isKeyUp && getKeyLetGo() == keycode;
    }

    /**
     * @return boolean: whether a specific button is not up (must have been pressed to be able to be up)
     */
    public static boolean isButtonUp(int button) {
        return isButtonUp && getButtonLetGo() == button;
    }

    /**
     * @return boolean: whether any key is just pressed.
     */
    public static boolean isKeyJustDown() {
        for(boolean b : justPressedKeys) {
            if(b) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return boolean: whether any button is just pressed.
     */
    public static boolean isButtonJustDown() {
        for(boolean b : justPressedButtons) {
            if(b) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return boolean: whether any key is just up (must have been pressed to be able to be up)
     */
    public static boolean isKeyJustUp() {
        for(boolean b : justLetGoKeys) {
            if(b) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return boolean: whether any button is just up (must have been pressed to be able to be up)
     */
    public static boolean isButtonJustUp() {
        for(boolean b : justLetGoButtons) {
            if(b) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return boolean: whether a specific key is just pressed.
     */
    public static boolean isKeyJustDown(int keycode) {
        return justPressedKeys[keycode];
    }

    /**
     * @return boolean: whether a specific button is just pressed.
     */
    public static boolean isButtonJustDown(int button) {
        return justPressedButtons[button];
    }

    /**
     * @return boolean: whether a specific key is just up (must have been pressed to be able to be up)
     */
    public static boolean isKeyJustUp(int keycode) {
        return justLetGoKeys[keycode];
    }

    /**
     * @return boolean: whether a specific button is just up (must have been pressed to be able to be up)
     */
    public static boolean isButtonJustUp(int button) {
        return justLetGoButtons[button];
    }

    public static void update() {
        if(justTouched) {
            justTouched = false;
            for(int i = 0; i < justPressedButtons.length; i++) {
                justPressedButtons[i] = false;
            }
        }

        if(justLetGo) {
            justLetGo = false;
            for(int i = 0; i < justLetGoButtons.length; i++) {
                justLetGoButtons[i] = false;
            }
        }

        if(justPressed) {
            justPressed = false;
            for(int i = 0; i < justPressedKeys.length; i++) {
                justPressedKeys[i] = false;
            }
        }

        if(justUp) {
            justUp = false;
            for(int i = 0; i < justLetGoKeys.length; i++) {
                justLetGoKeys[i] = false;
            }
        }
    }

    /**
     * Used to get the keycode of the key pressed without knowing which key it was.
     *
     * @param keycode int: the keycode of the key that was pressed.
     */
    @Override
    public boolean keyDown(int keycode) {
        isKeyDown = true;
        isKeyUp = false;

        justPressedKeys[keycode] = true;
        justLetGoKeys[keycode] = false;
        justPressed = true;
        justUp = false;

        keycodeOfKeyDown = keycode;
        keycodeOfKeyUp = null;
        return false;
    }

    /**
     * Used to get the keycode of the key let go without knowing which key it was.
     * Also sets the key value to "" to avoid the key value being the same as a key that was pressed
     * but is not anymore.
     *
     * @param keycode int: the keycode of the key that was let go
     */
    @Override
    public boolean keyUp(int keycode) {
        isKeyUp = true;
        isKeyDown = false;
        keyValue = "";

        justPressedKeys[keycode] = false;
        justLetGoKeys[keycode] = true;
        justPressed = false;
        justUp = true;

        keycodeOfKeyUp = keycode;
        keycodeOfKeyDown = null;
        return false;
    }

    /**
     * Sets the keyValue to the key that was typed without knowing which key it was.
     *
     * @param character char: the key that was typed
     */
    @Override
    public boolean keyTyped(char character) {
        isKeyDown = true;
        if(!isKeyJustDown) {
            isKeyJustDown = true;
        }
        isKeyUp = false;
        keyValue = "" + character;
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        isButtonDown = true;
        isButtonUp = false;

        justPressedButtons[button] = true;
        justLetGoButtons[button] = false;
        justTouched = true;
        justLetGo = false;

        keycodeOfButtonDown = button;
        keycodeOfButtonUp = null;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isButtonUp = true;
        isButtonDown = false;

        justPressedButtons[button] = false;
        justLetGoButtons[button] = true;
        justTouched = false;
        justLetGo = true;

        keycodeOfButtonUp = button;
        keycodeOfButtonDown = null;
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
