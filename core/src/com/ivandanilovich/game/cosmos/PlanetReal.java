package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class PlanetReal extends PlanetVirtual {

    private final float Width = Gdx.graphics.getWidth();
    private final float Height = Gdx.graphics.getHeight();
    public float orbit;

    public PlanetReal(Vector2 pos, float radius, float m, Vector2 velocity, float orbit) {
        super(pos, radius, m, velocity);
        this.orbit = orbit;
    }

    @Override
    public void draw() {
        super.draw();
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(pos.x,pos.y,orbit);
        shapeRenderer.setColor(Color.WHITE);
    }

    public boolean isNeedDestroyBounds() {
        int N=1000;
        return pos.x > Width+N || pos.x < 0-N || pos.y < 0-N || pos.y > Height+N;
    }
}
