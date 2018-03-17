package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;
import com.badlogic.gdx.utils.TimeUtils;
import com.ivandanilovich.game.cosmos.PlanetVirtual;
import com.ivandanilovich.game.cosmos.Space;
import com.ivandanilovich.game.cosmos.Star;


public class Test2 implements Screen {

    private final float Width = Gdx.graphics.getWidth();
    private final float Height = Gdx.graphics.getHeight();
    private PlanetVirtual planet;
    private Core core;
    private ShapeRenderer shapeRenderer;

    private Space space;


    public Test2(Core Core) {
        core = Core;

        InputProcessor inputProcessor = new InputProcessor() {

            //region input
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == 4) {
                }
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
            //endregion
        };
        Gdx.input.setInputProcessor(inputProcessor);

        shapeRenderer = new ShapeRenderer();

        space=new Space(shapeRenderer);

        Star star = new Star(new Vector2(500,200),50,1000);

        planet = new PlanetVirtual(new Vector2(100, 100), 40,1, new Vector2(0, 0));

        space.addPlanet(planet);
        space.addStar(star);
    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        space.render(delta*100);
        Gdx.app.log("TAG", planet.pos+" ");

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