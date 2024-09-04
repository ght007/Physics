package io.eisaatif.physicssimapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static io.eisaatif.physicssimapp.Extras.*;

public class MainMenuScreen implements Screen {
    private Button math;
    private final Application application;
    private final Viewport viewport;

    public MainMenuScreen(Application application) {
        this.application = application;
        viewport = new FitViewport(WIDTH, HEIGHT);
        InputManager inputProcessor = new InputManager();
        Gdx.input.setInputProcessor(inputProcessor);

        math = new Button(-150, -50, 300, 100);
        math.setHasBorder(true);
        math.setBorderThickness(2);
        math.setText("Open Math", Extras.getFont());
        math.setTextColour(Color.WHITE);
        math.setColour(Color.DARK_GRAY);
        math.setBorderColour(new Color(0.0f, 0.7f, 0.0f, 0.0f));
        math.setEnlargeOnClick(true);
        math.setEnlargeOnHover(false);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.18f, 0.17f, 0.19f, 0);
        application.batch.setProjectionMatrix(viewport.getCamera().combined);
        application.sr.setProjectionMatrix(viewport.getCamera().combined);

        math.render(application.sr, application.batch, getMousePos(viewport));

        if(math.isJustClickedOn(getMousePos(viewport))) {
            this.application.setScreen(new MainMathScreen(application));
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
        math.dispose();
    }
}
