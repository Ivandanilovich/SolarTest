package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


public class PlanetVirtual extends SBody {
    public Vector2 f, a, v;

    public PlanetVirtual(Vector2 pos, float radius, float m, Vector2 velocity) {
        this.v = velocity;
        a = new Vector2(0, 0);
        f = new Vector2(0, 0);

        this.mass = m;
        this.pos = pos;
        this.radius = radius;
    }

    public void applyForce(Vector2 f, float dTime) {

        a.x = (float) (f.x*Math.cos(f.y) / mass);
        v.x += a.x * dTime;
        pos.x += v.x * dTime + a.x * dTime * dTime * 0.5f;

        a.y = (float) (f.x*Math.sin(f.y) / mass);
        v.y += a.y * dTime;
        pos.y += v.y * dTime + a.y * dTime * dTime * 0.5f;

        Gdx.app.log("a",a+"");
        Gdx.app.log("v",v+"");
        Gdx.app.log("pos",pos+"");
    }

    public void applyForceAngle(Vector2 f,float dTime){

    }

    private float getR(Vector2 v1, Vector2 v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }


    public boolean isNeedDestroy(Star star) {
        return getR(pos, star.pos) <= star.radius + radius;
    }
}
