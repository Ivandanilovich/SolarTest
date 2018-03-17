package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.math.Vector2;


public class PlanetVirtual extends SBody {
    public Vector2 f, a, v;

    public PlanetVirtual(Vector2 pos, float radius,float m, Vector2 velocity) {
        this.v = velocity;
        a = new Vector2(0, 0);
        f = new Vector2(0, 0);

        this.mass=m;
        this.pos = pos;
        this.radius = radius;
    }

    public void applyForce(Vector2 f, float dTime) {

        a.x = f.x / mass;
        a.y = f.y / mass;

        v.x += a.x * dTime;
        v.y += a.y * dTime;

        pos.x += v.x * dTime + a.x * dTime * dTime * 0.5f;
        pos.y += v.y * dTime + a.y * dTime * dTime * 0.5f;
    }


}
