package com.movingsquare;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class MovingSquare extends ApplicationAdapter {
    private Texture square;
    private Sound gameStart;

    private OrthographicCamera camera;

    private SpriteBatch batch;

    private Rectangle squareBox;

    @Override
    public void create() {
        square = new Texture(Gdx.files.internal("square.png"));
        gameStart = Gdx.audio.newSound(Gdx.files.internal("gameStart.wav"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        batch = new SpriteBatch();

        squareBox = new Rectangle();
        squareBox.x = 800 / 2 - 64 / 2;
        squareBox.y = 300;
        squareBox.width = 64;
        squareBox.height = 64;

        //play this sound on game startup
        gameStart.play();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(square, squareBox.x, squareBox.y);
        batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 mousePos = new Vector3();
            mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePos);
            squareBox.x = mousePos.x;
            squareBox.y = mousePos.y;
        }
    }
    @Override
    public void dispose() {
        square.dispose();
        gameStart.dispose();
        batch.dispose();

    }
}
