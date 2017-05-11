package com.giancarlo.tesis.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giancarlo.tesis.Scenes.CurrentScore;
import com.giancarlo.tesis.Sprites.Personaje;
import com.giancarlo.tesis.Sprites.PersonajePrincipal;
import com.giancarlo.tesis.Sprites.*;
import com.giancarlo.tesis.TesisMain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlo on 19/08/2016.
 */
public class PlayScreen implements Screen {

    private TesisMain game;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private CurrentScore currentScore;
    private SpriteCache cache;
    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;
    //Box2D variables
    public World world;
    private Box2DDebugRenderer b2dr;
    //personajes
    PersonajePrincipal personaje1;
    Personaje enemigo;
    private TextureAtlas atlas;
    public enum State{
        Running, Paused, Question
    }
    private List<Personaje> list_personajes;

    public State state = State.Running;

    public PlayScreen(TesisMain game){
        this.game = game;
        gamecam = new OrthographicCamera();
        atlas = new TextureAtlas("Personajes.pack");
        //Fitviewport para mantener el aspecto original de la pantalla
        viewport = new FitViewport(TesisMain.V_WIDTH,TesisMain.V_HEIGHT, gamecam);
        //una escena del screen donde se visualiza el puntaje
        currentScore = new CurrentScore(game.batch,this);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1-2.tmx");
        //brindarle el mapa al renderizador de mapas y de paso darle mas Caché
        renderer = new OrthoCachedTiledMapRenderer(map,1,5000);
        //inicializar camara con posiciones
        gamecam.position.set(600,600,0);
        // Box2d incializacion
        world = new World(new Vector2(0,0),true);//AQUI SE AGREGA LA GRAVEDAD
        b2dr = new Box2DDebugRenderer();
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get("arboles").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rectangle.getX()+rectangle.getWidth()/2,rectangle.getY()+rectangle.getHeight()/2);

            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth()/2,rectangle.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
        for(MapObject object : map.getLayers().get("casas").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rectangle.getX()+rectangle.getWidth()/2,rectangle.getY()+rectangle.getHeight()/2);

            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth()/2,rectangle.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
        list_personajes = new ArrayList<Personaje>();
        for(MapObject object : map.getLayers().get("personajes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            enemigo = new Enemy_Demon(this,rectangle.getX(),rectangle.getY(),5,5);
            list_personajes.add(enemigo);
        }

        /*
        cache.beginCache();
        cache.add(list_personajes);
        cache.
        cache.end();
        */
        personaje1= new PersonajePrincipal(world,this,"M");

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            personaje1.body.applyLinearImpulse(new Vector2(0,94f),personaje1.body.getWorldCenter(),true);
            personaje1.change_Anim(2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            personaje1.body.applyLinearImpulse(new Vector2(94f,0),personaje1.body.getWorldCenter(),true);
            personaje1.change_Anim(3);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            personaje1.body.applyLinearImpulse(new Vector2(-94f,0),personaje1.body.getWorldCenter(),true);
            personaje1.change_Anim(4);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            personaje1.body.applyLinearImpulse(new Vector2(0,-94f),personaje1.body.getWorldCenter(),true);
            personaje1.change_Anim(1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            state = State.Running;
        }
        //&& personaje1.body.getLinearVelocity().x <= 1
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            state = State.Paused;
        }

    }

    public void update(float dt){
        //handleInput(dt);
        world.step(1 / 60f, 6, 2);
        gamecam.position.x = personaje1.body.getPosition().x;
        gamecam.position.y = personaje1.body.getPosition().y;
        personaje1.body.setLinearVelocity(new Vector2(0, 0));
        personaje1.update(dt);

        for(int i=0;i<list_personajes.size();i++){
            list_personajes.get(i).update(dt,personaje1.getBoundingRectangle());
        }

        //chicaNaranja.update(dt,personaje1.getBoundingRectangle());
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {

        update(delta);
        //renderizar mapa con alpha
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        //rendirizar Box2d debug lines
        //b2dr.render(world, gamecam.combined);
        //b2dr.SHAPE_STATIC.set(0, 0, 0, 1);
        //matriz de proyeccion
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        personaje1.draw(game.batch);
        for(int i=0;i<Enemy_Demon.instancias.size();i++){
            Enemy_Demon.instancias.get(i).draw(game.batch);
        }
        game.batch.end();
        //matriz del stage
        switch(state){
            case Running:
                handleInput(delta);

                break;
            case Paused:
                //don't update
                game.batch.setProjectionMatrix(currentScore.stage.getCamera().combined);
                currentScore.stage.draw();
                break;
            case Question:

                game.batch.setProjectionMatrix(currentScore.stage.getCamera().combined);
                currentScore.stage.draw();
                break;
        }

    }
    public World getWorld(){
        return world;
    }
    public TextureAtlas getAtlas(){
        return atlas;
    }
    @Override
    public void resize(int width, int height) {
        gamecam.update();
        viewport.update(width,height);
        currentScore.stage.getViewport().update(width, height, true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        b2dr.dispose();
        world.dispose();
        currentScore.dispose();
    }

}
