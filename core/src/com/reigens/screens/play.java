package com.reigens.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.reigens.controlers.InputControler;

/**
 * Created by Rich on 8/10/2014.
 */
public class play implements Screen {
    private final float TIMESTEP = 1 / 60f;
    private final int VELOCITYINTERATIONS = 8, POSITIONITERATIONS = 3;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Body box;
    private float speed = 150;
    private Vector2 movement = new Vector2();


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(TIMESTEP, VELOCITYINTERATIONS, POSITIONITERATIONS);
        box.applyForceToCenter(movement, true);

        debugRenderer.render(world, camera.combined);

        camera.position.set(box.getPosition().x, box.getPosition().y, 0);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 25;
        camera.viewportHeight = height / 25;
        camera.update();
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(new InputControler() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode)
                {
                    case Input.Keys.ESCAPE:
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new Levels());
                        break;
                    case Input.Keys.W:
                        movement.y = speed;
                        break;
                    case Input.Keys.A:
                        movement.x = -speed;
                        break;
                    case Input.Keys.S:
                        movement.y = -speed;
                        break;
                    case Input.Keys.D:
                        movement.x = speed;
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode)
                {
                    case Input.Keys.W:
                    case Input.Keys.S:
                        movement.x = -0;
                        break;
                    case Input.Keys.A:
                    case Input.Keys.D:
                        movement.y = -0;
                        break;
                }
                return true;
            }
        });

        //Body Definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 10);

        //Ball Shape
        CircleShape shape = new CircleShape();
        shape.setRadius(.5f);


        //Fixture Definition
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .75f;

        world.createBody(bodyDef).createFixture(fixtureDef);

        shape.dispose();


        //Ground
        //Body Definition
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        //Ground Shape
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(-50, 0), new Vector2(50, 0)});


        //Fixture Definition
        fixtureDef.shape = groundShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;

        world.createBody(bodyDef).createFixture(fixtureDef);

        groundShape.dispose();

        //Box
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(2.25f, 10);

        //box shape
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(.5f, 1);

        //box fixture definition
        fixtureDef.shape = boxShape;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 5;

        box = world.createBody(bodyDef);
        box.createFixture(fixtureDef);

        boxShape.dispose();


    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }
}
