package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Space {
    public float G = 1f;
    ArrayList<Star> stars = new ArrayList<Star>();
    ArrayList<PlanetVirtual> planetVirtuals = new ArrayList<PlanetVirtual>();
    ArrayList<PlanetReal> planetReals = new ArrayList<PlanetReal>();

    Vector2[] forces;

    ShapeRenderer shapeRenderer;

    public Space(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;


        forces = new Vector2[100];
    }

    public void addPlanet(PlanetVirtual p) {
        p.setShapeRenderer(shapeRenderer);
        planetVirtuals.add(p);
    }

    public void addPlanetReal(PlanetReal p) {
        p.setShapeRenderer(shapeRenderer);
        planetReals.add(p);
    }

    public void addStar(Star star) {
        star.setShapeRenderer(shapeRenderer);
        stars.add(star);
    }

    private float getR(Vector2 v1, Vector2 v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }


    private Vector2 getForceWithAngle(Star star, PlanetVirtual planet) {

        float r = getR(star.pos, planet.pos);
        float f = G * ((star.mass * planet.mass) / (r * r));

        Gdx.app.log("R",r+"");

        float dx = star.pos.x - planet.pos.x;
        float dy = star.pos.y - planet.pos.y;

        float angle = (float) Math.atan(dy / dx);

        if (dx < 0) {
            f = -f;
        }

        return new Vector2(f, angle);
    }

    public Vector2 FA2FxFy(Vector2 FA) {
        float fx = (float) (FA.x * Math.cos(FA.y));
        float fy = (float) (FA.x * Math.sin(FA.y));
        return new Vector2(fx, fy);
    }


    public void render(float delta) {

        PlanetVirtual planetDel = null;
        for (PlanetVirtual planet : planetVirtuals) {
            Vector2 f = new Vector2(0, 0);
            for (Star star : stars) {
                Vector2 myf = FA2FxFy(getForceWithAngle(star, planet));
                f.add(myf);
                if (planet.isNeedDestroy(star)) {
                    planetDel = planet;
                }
            }
            planet.applyForce(f, delta);
        }

        if (planetDel != null)
            planetVirtuals.remove(planetDel);
        draw();
    }

    public void renderRealPlanet(float delta) {
        PlanetReal planetDel = null;
        int i = 0;

        for (PlanetReal planet1 : planetReals) {
            Vector2 f = new Vector2(0, 0);
            for (PlanetReal planet2 : planetReals) {
                Vector2 myf = new Vector2(0, 0);
                if (planet1 != planet2)
                    myf = FA2FxFy(getForceWithAngleReal(planet1, planet2));
                f.add(myf);
            }
            ///
            for (Star star : stars) {
                Vector2 myf = new Vector2(0, 0);
                myf = FA2FxFy(getForceWithAngle(star, planet1));
                f.add(myf);

                if(planet1.isNeedDestroy(star)){
                    planetDel=planet1;
                }
            }
            ///
            forces[i++] = new Vector2(f);
            if (planet1.isNeedDestroyBounds()) {
                planetDel = planet1;
            }
        }

        int j = 0;
        for (PlanetReal pl : planetReals) {
            pl.applyForce(forces[j++], delta);
        }
        if (planetDel != null) {
            planetReals.remove(planetDel);
        }
        draw();
    }

    private Vector2 getForceWithAngleReal(PlanetReal planet1, PlanetReal planet2) {

        float r = getR(planet2.pos, planet1.pos);
        float f = (float) (G * ((planet2.mass * planet1.mass) / (Math.pow(r, 2))));

        float dx = planet2.pos.x - planet1.pos.x;
        float dy = planet2.pos.y - planet1.pos.y;

        float angle = (float) Math.atan(dy / dx);

        if (dx < 0) {
            f = -f;
        }

        if (r < planet1.orbit) {
            f = -f;
        }

        return new Vector2(f, angle);
    }

    private void draw() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (PlanetVirtual pl : planetVirtuals) {
            pl.draw();
        }
        for (Star st : stars) {
            st.draw();
        }
        for (PlanetReal pr : planetReals) {
            pr.draw();
        }
        shapeRenderer.end();
    }
}



