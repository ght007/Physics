package io.eisaatif.physicssimapp.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import io.eisaatif.physicssimapp.Application;

public class MainMathScreen implements Screen {
    private Application application;

    public MainMathScreen(Application application) {
        this.application = application;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.18f, 0.17f, 0.19f, 0);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
