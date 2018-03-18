


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

    private float getR(Vector2 v1, Vector2 v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }


    private Vector2 getForceWithAngle(Star star, PlanetVirtual planet) {

        float r = getR(star.pos, planet.pos);
        float f = G * ((star.mass * planet.mass) / (r * r));

        float dx = star.pos.x - planet.pos.x;
        float dy = star.pos.y - planet.pos.y;

        float angle = (float) Math.atan(dy / dx);

        if(dx<0){
            f=-f;
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
        for (PlanetVirtual planet : planets) {
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
            planets.remove(planetDel);


        draw();
    }

    private void draw() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (PlanetVirtual pl : planets) {
            pl.draw();
        }
        for (Star st : stars) {
            st.draw();
        }
        shapeRenderer.end();
    }
}



