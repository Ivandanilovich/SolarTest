package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.ivandanilovich.game.cosmos.PlanetReal;
import com.ivandanilovich.game.cosmos.PlanetVirtual;
import com.ivandanilovich.game.cosmos.Space;
import com.ivandanilovich.game.cosmos.Star;


public class Test2 implements Screen {

    private final float Width = Gdx.graphics.getWidth();
    private final float Height = Gdx.graphics.getHeight();
    ShapeRenderer shapeRendererLine;
    float x0, y0, x1, y1;
    private Core core;
    private Space space;
    private boolean isDrawLine = false;
    private float velConst  = 0.01f;

    public Test2(Core Core) {
        core = Core;

        InputProcessor inputProcessor = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                x0 = screenX;
                y0 = Height - screenY;
                x1 = x0;
                y1 = y0;
                isDrawLine = true;
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                isDrawLine = false;
                space.addPlanetReal(new PlanetReal(new Vector2(x0, y0), 10, 100,
                        new Vector2((x0 - x1) * velConst, (y0 - y1) * velConst),40));
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                x1 = screenX;
                y1 = Height - screenY;
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        };
        Gdx.input.setInputProcessor(inputProcessor);

        ShapeRenderer shapeRendererSpase = new ShapeRenderer();
        space = new Space(shapeRendererSpase);

        space.addStar(new Star(new Vector2(500, 500), 20, 5000));
//        space.addStar(new Star(new Vector2(800, 300), 20, 1000));
//        space.addStar(new Star(new Vector2(0, 0), 20, 1000));

        shapeRendererLine = new ShapeRenderer();
    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        space.renderRealPlanet(delta * 50);///////////////////////////

        if (isDrawLine) {
            shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);
            shapeRendererLine.line(x0, y0, x1, y1);
            shapeRendererLine.circle(x0, y0, 10);
            shapeRendererLine.end();
        }
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        core.dispose();
    }
}

