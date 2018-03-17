package com.ivandanilovich.game.cosmos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Star extends SBody {

    public Star(Vector2 p, float r, float m) {
        this.pos = p;
        this.radius = r;
        this.mass = m;
        this.color = Color.YELLOW;
    }
}
