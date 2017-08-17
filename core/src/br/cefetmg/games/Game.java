package br.cefetmg.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A classe principal de uma aplicação LibGDX deve herdar de ApplicationAdapter,
 * ou então de Game (específico para o caso quando há várias telas diferentes no
 * jogo - splash, menu, jogo etc.).
 * 
 * Um ApplicationAdapter possui alguns métodos que normalmente nós
 * sobrecarregamos: - create(): é invocado quando o jogo é criado. - dispose():
 * é invocado quando o jogo é finalizado. - render(): é invocado muitas vezes
 * por segundo.
 * 
 * @author fegemo
 */
public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private Sprite goomba;
	private Texture[] mapLevelsTextures;

	float goombaSpeed, goombaX, goombaY;

	/**
	 * No método create colocamos código de inicialização do jogo. Por exemplo,
	 * carregamos texturas, sons e outros recursos. Aqui também instanciamos e
	 * posicionamos os elementos que compõem a cena do jogo, como personagens,
	 * inimigos, o mapa etc.
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		mapLevelsTextures = new Texture[2];
		mapLevelsTextures[0] = new Texture("map-level-1.png");
		mapLevelsTextures[1] = new Texture("map-level-2.png");

		goomba = new Sprite(new Texture("goomba.png"));
		goombaSpeed = 50.0f;
		goombaX = 30.0f;
		goombaY = 10.0f;

		// cor de fundo da tela: branco
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	/**
	 * No método dispose nós desfazemos dos recursos que estávamos usando. Por
	 * exemplo, texturas, sons e outras coisas. É interessante implementar o dispose
	 * próximo ao create porque praticamente tudo o que está no create precisa estar
	 * no dispose.
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

	/**
	 * No método render desenhamos tudo o que faz parte da cena do jogo. É aqui que,
	 * inicialmente, limpamos a tela e então mandamos desenhar a cena. Este método é
	 * chamado muitas vezes por segundo.
	 */
	@Override
	public void render() {
		// apaga a tela, para desenharmos de novo
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// chamando o método update(), que atualiza a lógica do jogo.
		// passa para o método quanto tempo se passou desde a última vez
		// que renderizamos
		update(Gdx.graphics.getDeltaTime());

		batch.begin();
		// desenhos são realizados aqui
		batch.draw(mapLevelsTextures[0], 0, 0);
		batch.draw(goomba, (int) goombaX, (int) goombaY);
		batch.draw(mapLevelsTextures[1], 0, 0);
		batch.end();
	}

	/**
	 * Na LibGDX, não existe um método update() que seja separado do render(),
	 * portanto, é uma boa prática criar um update() e chamá-lo de dentro do render.
	 *
	 * A ideia é colocar aqui tudo relacionado à atualização da lógica do jogo e, no
	 * render, apenas comandos referentes ao desenho da cena.
	 *
	 * @param delta
	 *            o tempo que passou desde o último "quadro".
	 */
	public void update(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		float deltaX = 0.0f, deltaY = 0.0f;
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			deltaX -= Gdx.graphics.getDeltaTime() * goombaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			deltaX += Gdx.graphics.getDeltaTime() * goombaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			deltaY += Gdx.graphics.getDeltaTime() * goombaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			deltaY -= Gdx.graphics.getDeltaTime() * goombaSpeed;
		}

		float newGoombaX = goombaX + deltaX;
		float newGoombaY = goombaY + deltaY;
		if (newGoombaX >= 0.0f && newGoombaX <= mapLevelsTextures[0].getWidth() - goomba.getWidth()) {
			goombaX = newGoombaX;
		}
		if (newGoombaY >= 0.0f && newGoombaY <= mapLevelsTextures[0].getHeight() - goomba.getHeight()) {
			goombaY = newGoombaY;
		}
	}

}
