package com.giancarlo.tesis.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.giancarlo.tesis.Scenes.Question;
import com.giancarlo.tesis.Screens.PlayScreen;

/**
 * Created by Giancarlo on 23/08/2016.
 */
public class PersonajePrincipal extends Sprite{
    public World world;
    public Body body;
    private TextureRegion personaje_parado;
    private Animation caminando_abajo;
    private Animation caminando_arriba;
    private Animation caminando_der;
    private Animation caminando_izq;
    private TextureAtlas atlas;
    private Animation current_anim;
    private float statetimer;
    private PlayScreen screen;
    private Integer contador=0;

    public PersonajePrincipal(World world, PlayScreen screen, String sexo){
        //super(screen.getAtlas().findRegion("personaje_f"));
        this.world = world;
        this.screen = screen;
        definePersonaje1();
        atlas = new TextureAtlas("Personajes.pack");

        personaje_parado = new TextureRegion(atlas.findRegion("personaje_f"),0,0,17,22);
        setBounds(0, 0, 16, 21);
        setPosition(body.getPosition().x, body.getPosition().y);
        //setRegion(new Texture("chomp3.png"));
        setAnim_caminando_abajo(sexo);
        statetimer = 0;

        setRegion(personaje_parado);
        current_anim = caminando_abajo;
    }
    public void update(float dt)
    {
        setPosition(body.getPosition().x - getWidth() / 2f,
                body.getPosition().y - getHeight() / 2f);
        setRegion(getFrame(dt));
        //COLISIONES
        for(int i=0;i<Enemy_Demon.instancias.size();i++){
            if(this.getBoundingRectangle().overlaps(Enemy_Demon.instancias.get(i).getBoundingRectangle()) &&
                screen.state== PlayScreen.State.Running)
            {
                screen.state = PlayScreen.State.Question;
                Gdx.app.log("Collision","ok");
                Question.personaje_pregunta=i;
                screen.currentScore = new Question(screen.game.batch,screen,screen.preguntas.get(contador));
                contador++;
            }
        }

    }
    public TextureRegion getFrame(float dt){
        statetimer+= Gdx.graphics.getDeltaTime();
        return current_anim.getKeyFrame(statetimer,true);

    }
    public  void definePersonaje1(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(600,600);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(7,9);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
    public void setAnim_caminando_abajo(String sexo){
        if (sexo.equals("M")) {
            Array<TextureRegion> frames = new Array<TextureRegion>();
            frames.add(new TextureRegion(atlas.findRegion("personaje_m"), 0, 20, 15, 21));
            frames.add(new TextureRegion(atlas.findRegion("personaje_m"), 0, 40, 15, 21));
            caminando_abajo = new Animation(1 / 5f, frames);//5 frames por segundo
            frames = new Array<TextureRegion>();
            frames.add(new TextureRegion(atlas.findRegion("personaje_m"), 15, 20, 15, 21));
            frames.add(new TextureRegion(atlas.findRegion("personaje_m"), 15, 40, 15, 21));
            caminando_arriba = new Animation(1 / 5f, frames);
            frames = new Array<TextureRegion>();
            frames.add(new TextureRegion(atlas.findRegion("personaje_m"), 30, 20, 15, 21));
            frames.add(new TextureRegion(atlas.findRegion("personaje_m"), 30, 40, 15, 21));
            caminando_izq = new Animation(1 / 5f, frames);
            frames = new Array<TextureRegion>();
            TextureRegion tx = new TextureRegion(atlas.findRegion("personaje_m"), 30, 20, 15, 21);
            tx.flip(true,false);
            frames.add(tx);
            tx = new TextureRegion(atlas.findRegion("personaje_m"), 30, 40, 15, 21);
            tx.flip(true,false);
            frames.add(tx);
            caminando_der = new Animation(1 / 5f, frames);
        }else
        if (sexo.equals("F")){
            Array<TextureRegion> frames = new Array<TextureRegion>();
            frames.add(new TextureRegion(atlas.findRegion("personaje_f"), 0, 21, 17, 22));
            frames.add(new TextureRegion(atlas.findRegion("personaje_f"), 0, 43, 17, 22));
            caminando_abajo = new Animation(1 / 5f, frames);
        }


    }
    public void change_Anim(int anim){
        switch (anim){
            case 1: current_anim = caminando_abajo;
                return;
            case 2: current_anim = caminando_arriba;
                return;
            case 3: current_anim = caminando_der;
                return;
            case 4: current_anim = caminando_izq;
                return;
        }
    }
}
