package com.ivandanilovich.game;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;

import java.util.Timer;
import java.util.TimerTask;

import box2dLight.PointLight;


public class Test implements Screen {

    private final float Width = Gdx.graphics.getWidth();
    private final float Height = Gdx.graphics.getHeight();
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer box2DDebugRenderer;
    private Core core;

    public Test(Core Core) {
        core = Core;
        camera = new OrthographicCamera();
        float cameraHeight = 10;
        float WdivH = Width / Height;
        float cameraWidth = cameraHeight * WdivH;
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        world = new World(new Vector2(0, -9.8f), false);
        box2DDebugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        GetBox2dBody getBox2dBody = new GetBox2dBody();
        getBox2dBody.createBox(0, 0, 10, 1, false);
        getBox2dBody.createChain(new Vector2(5, 5), new Vector2(5, 6), true);
        getBox2dBody.createCircle(5, 50, 0.5f, true);

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


        Body a = getBox2dBody.createCircle(5, 50, 0.5f, true);
        Body b = getBox2dBody.createCircle(5, 50, 0.5f, true);

        PulleyJoint p = new PulleyJoint(world,2);
       // p.

    }


    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        box2DDebugRenderer.render(world, camera.combined);
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

    private class GetBox2dBody {
        Body createBox(float x, float y, float w, float h, boolean isDinamic) {
            Body body;
            BodyDef bodyDef = new BodyDef();
            if (isDinamic) bodyDef.type = BodyDef.BodyType.DynamicBody;
            else bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(x, y);
            bodyDef.fixedRotation = false;
            body = world.createBody(bodyDef);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(w, h);
            body.createFixture(shape, 1.0f);
            return body;
        }

        Body createCircle(float x, float y, float r, boolean isDinamic) {
            Body body;
            BodyDef bodyDef = new BodyDef();
            if (isDinamic) bodyDef.type = BodyDef.BodyType.DynamicBody;
            else bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(x, y);
            bodyDef.fixedRotation = false;
            body = world.createBody(bodyDef);

            CircleShape shape = new CircleShape();
            shape.setRadius(r);
            body.createFixture(shape, 1);

            return body;
        }

        public Body createChain(Vector2 v1, Vector2 v2, boolean isDinamic) {

            BodyDef def = new BodyDef();
            def.type = BodyDef.BodyType.DynamicBody;
            Body cb = world.createBody(def);

            ChainShape chainShape = new ChainShape();
            chainShape.createChain(new Vector2[] {v1,v2});

            cb.createFixture(chainShape, 1);
            return cb;
            /*
            ChainShape chainShape = new ChainShape();
            chainShape.createChain(new Vector2[] {v1,v2});
            BodyDef chainBodyDef = new BodyDef();

            if (isDinamic) chainBodyDef.type = BodyDef.BodyType.DynamicBody;
            else chainBodyDef.type = BodyDef.BodyType.StaticBody;

            chainBodyDef.fixedRotation=false;

            Body chainBody = world.createBody(chainBodyDef);
            chainBody.createFixture(chainShape, 1);
//            FixtureDef circleFixture = new FixtureDef();
//            circleFixture.density = 0.10f;
//            circleFixture.friction = 0.1f;
//            circleFixture.restitution = 0.1f;
            return chainBody;*/
        }
    }
}
