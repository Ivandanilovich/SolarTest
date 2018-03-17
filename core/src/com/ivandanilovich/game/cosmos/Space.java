package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.Gdx;
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
        float fx, fy;
        float e = 0.0000001f;

        float dx = Math.abs(star.pos.x - planet.pos.x);
        fx = G * ((star.mass * planet.mass) / (dx * dx));
        if (star.pos.x < planet.pos.x)
            fx *= -1;
        if (dx < e && dx > -e)
            fx = 0;
        if (fx == Float.NaN)
            fx = 0;


        float dy = Math.abs(star.pos.y - planet.pos.y);
        fy = G * ((star.mass * planet.mass) / (dy * dy));
        if (star.pos.y < planet.pos.y)
            fy *= -1;
        if (dy < e && dy > -e)
            fy = 0;
        if (fy == Float.NaN)
            fy = 0;

//        Gdx.app.log("ForceX", "" + fx);
        return new Vector2(fx, fy);
    }


    public void render(float delta) {

        for (PlanetVirtual planet : planets) {
            if (planet==null) continue;
            Vector2 f = new Vector2(0, 0);

            boolean flag = false;

            for (Star star : stars) {
                Vector2 myf = getF(star, planet);
                f.add(myf);

                flag = planet.isNeedDestroy(star);

                Gdx.app.log("F", f+" "+flag);

                if (flag)
                    break;
            }
            if (flag) {
//                planet//destroy
                planets.remove(planet);
            } else {
                planet.applyForce(f, delta);
            }
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
