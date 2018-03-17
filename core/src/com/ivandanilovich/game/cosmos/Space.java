package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Space {
    public float G = 1f;
    ArrayList<Star> stars = new ArrayList<Star>();
    ArrayList<PlanetVirtual> planets = new ArrayList<PlanetVirtual>();
    ShapeRenderer shapeRenderer;

    public Space(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public void addPlanet(PlanetVirtual p) {
        p.setShapeRenderer(shapeRenderer);
        planets.add(p);
    }

    public void addStar(Star star) {
        star.setShapeRenderer(shapeRenderer);
        stars.add(star);
    }

    private Vector2 getF(Star star, PlanetVirtual planet) {

        float dx = Math.abs(star.pos.x - planet.pos.x);
        float dy = Math.abs(star.pos.y - planet.pos.y);

        float fx, fy;

        fx = G * ((star.mass * planet.mass) / (dx * dx));
        fy = G * ((star.mass * planet.mass) / (dy * dy));

        if (dx < 0.0005)
            fx = 0;
        if (dy < 0.0005)
            fy = 0;

        if(fx==Float.NaN){
            fx=0;
        }
        if(fy==Float.NaN){
            fy=0;
        }

        return new Vector2(fx, fy);
    }

    private float getR(Vector2 v1, Vector2 v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }

    public void render(float delta) {

        for (PlanetVirtual pl : planets) {
            Vector2 f = new Vector2(0, 0);
            for (Star st : stars) {
                f.add(getF(st, pl));
            }
            pl.applyForce(f, delta);
        }

        draw();
    }

    private void draw() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (PlanetVirtual pl : planets) {
            pl.draw();
        }
        for (Star st : stars) {
            st.draw();
        }
        shapeRenderer.end();
    }
}
