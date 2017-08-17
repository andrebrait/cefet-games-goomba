package br.cefetmg.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Goomba {

	private float goombaSpeed = 20.0f;
	private float goombaX, goombaY;

	private Sprite goombaSprite;

	public Goomba(Texture goombaTexture) {
		this.goombaSprite = new Sprite(goombaTexture);
	}

	public void render(SpriteBatch batch) {
		batch.draw(goombaSprite, (int) goombaX, (int) goombaY);
	}

	public void update(float delta, Texture map) {
		float deltaX = 0.0f, deltaY = 0.0f;
		float totalSpeed = delta * goombaSpeed;
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			deltaX -= totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			deltaX += totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			deltaY += totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			deltaY -= totalSpeed;
		}

		float newGoombaX = goombaX + deltaX;
		float newGoombaY = goombaY + deltaY;
		if (newGoombaX >= 0.0f && newGoombaX <= map.getWidth() - goombaSprite.getWidth()) {
			goombaX = newGoombaX;
		}
		if (newGoombaY >= 0.0f && newGoombaY <= map.getHeight() - goombaSprite.getHeight()) {
			goombaY = newGoombaY;
		}
	}

}
