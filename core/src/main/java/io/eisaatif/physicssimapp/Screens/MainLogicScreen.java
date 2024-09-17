package io.eisaatif.physicssimapp.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.eisaatif.physicssimapp.Application;
import io.eisaatif.physicssimapp.UI.Button;
import io.eisaatif.physicssimapp.UI.Extras;
import io.eisaatif.physicssimapp.sudoku.Sudoku;

import static io.eisaatif.physicssimapp.UI.Extras.*;

public class MainLogicScreen implements Screen {
    private Application application;
    private Viewport viewport;

    private Button sudoku;

    public MainLogicScreen(Application application) {
        this.application = application;
        this.viewport = new FitViewport(WIDTH, HEIGHT);

        sudoku = new Button(-100 + WIDTH/4, -50 + HEIGHT/4, 200, 100);
        sudoku.setHasBorder(true);
        sudoku.setBorderThickness(2);
        sudoku.setText("Open Sudoku", Extras.getFont());
        sudoku.setTextColour(Color.WHITE);
        sudoku.setColour(Color.DARK_GRAY);
        sudoku.setBorderColour(new Color(0.0f, 0.7f, 0.0f, 0.0f));
        sudoku.setEnlargeOnClick(true);
        sudoku.setEnlargeOnHover(false);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.18f, 0.17f, 0.19f, 0);
        application.batch.setProjectionMatrix(viewport.getCamera().combined);
        application.sr.setProjectionMatrix(viewport.getCamera().combined);

        sudoku.render(this.application.sr, this.application.batch);

        if(sudoku.isJustClickedOn(getMousePos(viewport))) {
            this.application.setScreen(new Sudoku(application));
        }
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
        sudoku.dispose();
    }
}
