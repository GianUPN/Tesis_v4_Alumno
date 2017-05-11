package com.giancarlo.tesis.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.giancarlo.tesis.Screens.PlayScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlo on 07/05/2017.
 */

public class Enemy_Demon extends Personaje {

    public static List<Enemy_Demon> instancias = new ArrayList<Enemy_Demon>();

    public Enemy_Demon(PlayScreen screen, float x, float y,float rectX, float rectY) {
        super(screen, x, y,rectX,rectY);
        setAnimaciones();
        current_anim = parado;
        instancias.add(this);
    }
    public void setAnimaciones( ){ // HAY QUE HACER HERENCIA DE ESTA CLASE CON NOMBRE PERSONAJES

        int x= 0;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i=1;i<=8;i++){
            frames.add(new TextureRegion(atlas.findRegion("demon01"), x, 15, 16, 15));
            x+=18;
        }
        this.parado = new Animation(1 / 10f, frames);//5 frames por segundo
        setBounds(0, 0, 18, 15);

    }
}
