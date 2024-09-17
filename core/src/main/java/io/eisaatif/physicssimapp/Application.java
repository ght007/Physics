package io.eisaatif.physicssimapp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.eisaatif.physicssimapp.UI.Extras;

public class Application extends Game {

    public SpriteBatch batch;
    public ShapeRenderer sr;
    public BitmapFont font;

    public void create() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        sr.setAutoShapeType(true);
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        sr.dispose();
        font.dispose();
        Extras.getFont().dispose();
    }

}
