package com.dragos.game.tiles.blocks;

import com.dragos.game.util.AABB;
import com.dragos.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;


public class HoleBlock extends Block{
    public HoleBlock(BufferedImage img, Vector2f pos, int w, int h){
        super(img,pos,w,h);
    }

    public boolean update(AABB p) {
       if(isInside(p)) {
           System.out.println("I am a hole");
       }
        return false;
    }

    public boolean isInside(AABB p){

        if(p.getPos().x + p.getxOffset() < pos.x) return false;
        if(p.getPos().y + p.getyOffset() < pos.y) return false;
        if(w + pos.x < p.getWidth() + (p.getPos().x + p.getxOffset())) return false;
        if(h + pos.y < p.getHetight() + (p.getPos().y + p.getyOffset())) return false;
            return true;
    }



    public void render(Graphics2D g){
        super.render(g);
        g.setColor(Color.green);
        g.drawRect((int) pos.getWorldVar().x,(int) pos.getWorldVar().y,w,h);
    }

}
