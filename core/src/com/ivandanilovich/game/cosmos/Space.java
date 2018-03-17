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

  /*  private Vector2 getF(Star star, PlanetVirtual planet) {
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
*/
    private float getR(Vector2 v1, Vector2 v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }


    private Vector2 getFAngle(Star star, PlanetVirtual planet) {
        float e = 0.0000001f;

        float r = getR(star.pos, planet.pos);

        float f = G * ((star.mass * planet.mass) / (r * r));

        float dx = star.pos.x - planet.pos.x;
        float dy = star.pos.y - planet.pos.y;

        float angle = (float) Math.atan(dy / dx);


//        if(dx==0 && dy>0){
//            angle=90;
//        }
//
        if(dx<0){
            f*=-1;
        }

        return new Vector2(f, angle);
    }


    public void render(float delta) {

        PlanetVirtual planetDel = null;
        for (PlanetVirtual planet : planets) {
            Vector2 f = new Vector2(0, 0);
            for (Star star : stars) {
                Vector2 myf = getFAngle(star, planet);
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
