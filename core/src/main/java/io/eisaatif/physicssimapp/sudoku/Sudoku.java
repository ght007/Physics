package io.eisaatif.physicssimapp.sudoku;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.eisaatif.physicssimapp.Application;
import io.eisaatif.physicssimapp.UI.Extras;

public class Sudoku implements Screen {
    private Application application;
    private Viewport viewport;

    public Sudoku(Application application) {
        this.application = application;
        this.viewport = new FitViewport(Extras.WIDTH, Extras.HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.18f, 0.17f, 0.19f, 0);
        application.batch.setProjectionMatrix(viewport.getCamera().combined);
        application.sr.setProjectionMatrix(viewport.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
