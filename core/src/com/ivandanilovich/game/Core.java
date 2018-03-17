package com.ivandanilovich.game;
//SSS
import com.badlogic.gdx.Game;

public class Core extends Game {

	public void create() {
		this.setScreen(new Test2(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
	}
}
