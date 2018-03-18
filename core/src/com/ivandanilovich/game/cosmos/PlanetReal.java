package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class PlanetReal extends PlanetVirtual{

    public float orbit;

    public PlanetReal(Vector2 pos, float radius, float m, Vector2 velocity, float orbit) {
        super(pos, radius, m, velocity);
        this.orbit=orbit;
    }


    @Override
    public void draw() {
        super.draw();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.YELLOW);
//        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(pos.x,pos.y,orbit);
        shapeRenderer.setColor(Color.WHITE);
//        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.end();
    }
}
