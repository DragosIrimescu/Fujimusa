package com.dragos.game.tiles.blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.dragos.game.util.Vector2f;
import com.dragos.game.util.AABB;
public abstract class Block {
    protected int w;
    protected int h;

    protected BufferedImage img;
    protected Vector2f pos;

    public Block(BufferedImage img, Vector2f pos, int w, int h){
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public abstract boolean update(AABB p);
    public abstract boolean isInside(AABB p);
    public void render(Graphics2D g){
        g.drawImage(img, (int) pos.getWorldVar().x,(int) pos.getWorldVar().y,w,h,null);
    }
}
