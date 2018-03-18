package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class SBody {

    public Vector2 pos;
    public float mass;
    public Color color = Color.WHITE;
    public float radius;
    public ShapeRenderer shapeRenderer;

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public void draw() {
        shapeRenderer.circle(pos.x, pos.y, radius);
    }
}