package com.dragos.game.entity;

import com.dragos.game.graphics.Sprite;
import com.dragos.game.states.PlayState;
import com.dragos.game.util.KeyHandler;
import com.dragos.game.util.MouseHandler;
import com.dragos.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity {


    public Player(Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);
        acc = 1.5f;
        maxSpeed = 2f;
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);
    }

    public void move() {
        if(up){
            dy -= acc;
            if(dy < -maxSpeed){
                 dy = -maxSpeed;
            }
            //dy += acc;
        }else{
            if(dy < 0){
                 dy += deacc;
                 if(dy > 0){
                      dy = 0;
                 }
            }

        }
        if(down){
            dy += acc;
            if(dy > maxSpeed){
                dy = maxSpeed;
            }
        }else{
            if(dy > 0){
                dy -= deacc;
                if(dy < 0){
                    dy = 0;
                }
            }
        }
        if(left){
            dx -= acc;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }else{
            if(dx < 0){
                dx += deacc;
                if(dx > 0){
                    dx = 0;
                }
            }
        }
        if(right){
            dx += acc;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }else{
            if(dx > 0){
                dx -= deacc;
                if(dx < 0){
                    dx = 0;
                }
            }
        }
    }

    public void update() {
        super.update();
        move();
        if(!bounds.collisionTile(dx,0)) {
            PlayState.map.x += dx;
            pos.x += dx;
        }
        if(!bounds.collisionTile(0,dy)) {
            PlayState.map.y += dy;
            pos.y += dy;
        }
     pos.x += dx; // Mișcare pe axa X
        pos.y += dy; // Mișcare pe axa Y

        // Aplicăm forța de gravitație
        dy += 0.5; // Puteți ajusta valoarea pentru a controla forța de gravitație

        // Logica de coliziune pentru sol
        // Exemplu: nivelul solului este la 200 pe axa Y
        if (pos.y >= groundLevel) {
            // Dacă personajul a atins sau depășit nivelul solului, opriți săritura și aliniați-l la nivelul solului
            dy = 0; // Resetați viteza pe axa Y la 0 pentru a opri săritura
            pos.y = groundLevel; // Asigurați-vă că personajul este aliniat cu nivelul solului
        }
    }
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.blue);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getxOffset()), (int) (pos.getWorldVar().y + bounds.getyOffset()),(int) bounds.getWidth(),(int) bounds.getHetight());
        g.drawImage(ani.getImage(),(int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y),size, size, null);
    }

    public void input(MouseHandler mouse, KeyHandler key) {

       if(mouse.getButton() == 1){
           System.out.println("Player: " + pos.x + "," + pos.y);
       }

        if(key.up.down){
            up = true;
        }else {up = false;}
        if(key.down.down){
            down = true;
        }else {down = false;}
        if(key.left.down){
            left = true;
        }else {left = false;}
        if(key.right.down){
            right = true;
        }else {right = false;}



    }
}
