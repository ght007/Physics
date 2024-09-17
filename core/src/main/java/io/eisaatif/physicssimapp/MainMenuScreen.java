package io.eisaatif.physicssimapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.eisaatif.physicssimapp.Screens.MainCompSciScreen;
import io.eisaatif.physicssimapp.Screens.MainLogicScreen;
import io.eisaatif.physicssimapp.Screens.MainMathScreen;
import io.eisaatif.physicssimapp.Screens.MainPhysicsScreen;
import io.eisaatif.physicssimapp.UI.Button;
import io.eisaatif.physicssimapp.UI.Extras;
import io.eisaatif.physicssimapp.UI.InputManager;

import static io.eisaatif.physicssimapp.UI.Extras.*;

public class MainMenuScreen implements Screen {
    private Button math;
    private Button compsci;
    private Button logic;
    private Button physics;
    private final Application application;
    private final Viewport viewport;

    public MainMenuScreen(Application application) {
        this.application = application;
        viewport = new FitViewport(WIDTH, HEIGHT);
        InputManager inputProcessor = new InputManager();
        Gdx.input.setInputProcessor(inputProcessor);

        math = new Button(-100 - WIDTH/4, -50 - HEIGHT/4, 200, 100);
        math.setHasBorder(true);
        math.setBorderThickness(2);
        math.setText("Open Math", Extras.getFont());
        math.setTextColour(Color.WHITE);
        math.setColour(Color.DARK_GRAY);
        math.setBorderColour(new Color(0.0f, 0.7f, 0.0f, 0.0f));
        math.setEnlargeOnClick(true);
        math.setEnlargeOnHover(false);

        compsci = new Button(-100 - WIDTH/4, -50 + HEIGHT/4, 200, 100);
        compsci.setHasBorder(true);
        compsci.setBorderThickness(2);
        compsci.setText("Open Comp Sci", Extras.getFont());
        compsci.setTextColour(Color.WHITE);
        compsci.setColour(Color.DARK_GRAY);
        compsci.setBorderColour(new Color(0.0f, 0.7f, 0.0f, 0.0f));
        compsci.setEnlargeOnClick(true);
        compsci.setEnlargeOnHover(false);

        physics = new Button(-100 + WIDTH/4, -50 + HEIGHT/4, 200, 100);
        physics.setHasBorder(true);
        physics.setBorderThickness(2);
        physics.setText("Open Physics", Extras.getFont());
        physics.setTextColour(Color.WHITE);
        physics.setColour(Color.DARK_GRAY);
        physics.setBorderColour(new Color(0.0f, 0.7f, 0.0f, 0.0f));
        physics.setEnlargeOnClick(true);
        physics.setEnlargeOnHover(false);

        logic = new Button(-100 + WIDTH/4, -50 - HEIGHT/4, 200, 100);
        logic.setHasBorder(true);
        logic.setBorderThickness(2);
        logic.setText("Open Logic", Extras.getFont());
        logic.setTextColour(Color.WHITE);
        logic.setColour(Color.DARK_GRAY);
        logic.setBorderColour(new Color(0.0f, 0.7f, 0.0f, 0.0f));
        logic.setEnlargeOnClick(true);
        logic.setEnlargeOnHover(false);
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
        compsci.render(application.sr, application.batch, getMousePos(viewport));
        physics.render(application.sr, application.batch, getMousePos(viewport));
        logic.render(application.sr, application.batch, getMousePos(viewport));

        if(math.isJustClickedOn(getMousePos(viewport))) {
            this.application.setScreen(new MainMathScreen(application));
        }

        if(logic.isJustClickedOn(getMousePos(viewport))) {
            this.application.setScreen(new MainLogicScreen(application));
        }

        if(compsci.isJustClickedOn(getMousePos(viewport))) {
            this.application.setScreen(new MainCompSciScreen(application));
        }

        if(physics.isJustClickedOn(getMousePos(viewport))) {
            this.application.setScreen(new MainPhysicsScreen(application));
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
        logic.dispose();
        physics.dispose();
        compsci.dispose();
    }
}
