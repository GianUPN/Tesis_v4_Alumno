package com.giancarlo.tesis.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.giancarlo.tesis.Screens.PlayScreen;
import com.giancarlo.tesis.TesisMain;

/**
 * Created by Giancarlo on 30/09/2016.
 */
public class Personaje extends Sprite {
    public World world;
    public Body body;
    public Animation caminando_abajo;
    public Animation caminando_arriba;
    public Animation caminando_der;
    public Animation caminando_izq;
    public Animation parado;
    public TextureAtlas atlas;
    public Animation current_anim;
    public float statetimer;
    public Personaje(PlayScreen screen, float x, float y, float rectX, float rectY){

        this.world = screen.getWorld();
        definePersonaje(x,y,rectX,rectY);
        this.atlas = screen.getAtlas();
        setBounds(0, 0, 16, 21);
        setPosition(body.getPosition().x, body.getPosition().y);
        statetimer = 0;
    }
    public void update(float dt,Rectangle rectangle)
    {
        setPosition(body.getPosition().x - getWidth() / 2f,
        body.getPosition().y - getHeight() / 2f);
        setRegion(getFrame(dt));

    }

    public TextureRegion getFrame(float dt){
        statetimer+= Gdx.graphics.getDeltaTime();
        return current_anim.getKeyFrame(statetimer,true);

    }
    public  void definePersonaje(float x, float y, float rectX, float rectY){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rectX,rectY);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    public void setAnimaciones( ){}
}
