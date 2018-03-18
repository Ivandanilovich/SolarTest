package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.ivandanilovich.game.cosmos.PlanetVirtual;
import com.ivandanilovich.game.cosmos.Space;
import com.ivandanilovich.game.cosmos.Star;


public class Test2 implements Screen {

    private final float Width = Gdx.graphics.getWidth();
    private final float Height = Gdx.graphics.getHeight();
    private Core core;
    private ShapeRenderer shapeRendererSpase;
    private Space space;

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
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                space.addPlanet(new PlanetVirtual(new Vector2(screenX,Height-screenY),10,100,new Vector2(0,0)));
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
            public boolean scrolled(int amount) {
                return false;
            }
        };
        Gdx.input.setInputProcessor(inputProcessor);

        shapeRendererSpase = new ShapeRenderer();
        space = new Space(shapeRendererSpase);

        space.addStar(new Star(new Vector2(500,500),20,1000));
        space.addStar(new Star(new Vector2(800,300),20,1000));
        space.addStar(new Star(new Vector2(0,0),20,1000));

    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        space.render(delta * 40);
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

