package com.dragos.game.entity;

import com.dragos.game.graphics.Animation;
import com.dragos.game.graphics.Sprite;
import com.dragos.game.util.AABB;
import com.dragos.game.util.KeyHandler;
import com.dragos.game.util.MouseHandler;
import com.dragos.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private final int UP = 1;
    private final int UP_RIGHT = 1;
    private final int UP_LEFT = 6;
    private final int DOWN_RIGHT =5;
    private final int DOWN_LEFT = 7;
    private final int DOWN = 5;

    private final int IDLE = 0;
    private final int RevIDLE = 4;

    private final int RIGHT = 2;
    private final int LEFT = 3;
    protected int currentAnimation;
    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 2f;
    protected float acc = 1.5f;
    protected float deacc = 0.1f;

    protected AABB hitBounds;
    protected AABB bounds;
    float groundLevel = 520;

    public Entity(Sprite sprite, Vector2f orgin, int size) {
        this.sprite = sprite;
        pos = orgin;
        this.size = size;

        bounds = new AABB(orgin, size, size);
        hitBounds = new AABB(new Vector2f(orgin.x + (int)(size / 2), orgin.y), size, size);
        pos = new Vector2f(orgin.x, orgin.y);

        ani = new Animation();
        setAnimation(IDLE,sprite.getSpriteArray(IDLE), 10);
        setAnimation(IDLE,sprite.getSpriteArray(IDLE), 10);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSize(int i) { size = i; }
    public void setMaxSpeed(float f) { maxSpeed = f; }
    public void setAcc(float f) { acc = f;}
    public void setDeacc(float f) { deacc = f;}

    public AABB getBounds() { return bounds; }

    public int getSize() {return size;}
    public Animation getAnimation() {return ani;}
    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void animate() {
        if (up) {
            if (right) {
                if (currentAnimation != UP_RIGHT || ani.getDelay() == -1) {
                    setAnimation(UP_RIGHT, sprite.getSpriteArray(UP_RIGHT), 5);
                }
            } else if (left) {
                if (currentAnimation != UP_LEFT || ani.getDelay() == -1) {
                    setAnimation(UP_LEFT, sprite.getSpriteArray(UP_LEFT), 5);
                }
            } else {
                if (currentAnimation != UP || ani.getDelay() == -1) {
                    setAnimation(UP, sprite.getSpriteArray(UP), 5);
                }
            }
        } else if (down) {
            if (right) {
                if (currentAnimation != DOWN_RIGHT || ani.getDelay() == -1) {
                    setAnimation(DOWN_RIGHT, sprite.getSpriteArray(DOWN_RIGHT), 5);
                }
            } else if (left) {
                if (currentAnimation != DOWN_LEFT || ani.getDelay() == -1) {
                    setAnimation(DOWN_LEFT, sprite.getSpriteArray(DOWN_LEFT), 5);
                }
            } else {
                if (currentAnimation != DOWN || ani.getDelay() == -1) {
                    setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
                }
            }
        } else if (right) {
            if (currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        } else if (left) {
            if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else {
            if (currentAnimation != IDLE || ani.getDelay() == -1) {

                if (currentAnimation == RIGHT) {

                    setAnimation(IDLE, sprite.getSpriteArray(IDLE), 10);
                } else {

                    setAnimation(RevIDLE, sprite.getSpriteArray(RevIDLE), 10);
                }
            }
        }
        /*if (pos.y < groundLevel && !up) {
            if (left) {
                setAnimation(DOWN_LEFT, sprite.getSpriteArray(DOWN_LEFT), 5);
            } else {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }*/
    }



    private void setHitBoxDirection() {
        if(up) {
            hitBounds.setYOffset(-size / 2);
            hitBounds.setXOffset( -size / 2);
        }
        else if(down) {
            hitBounds.setYOffset(size / 2);
            hitBounds.setXOffset(-size / 2);
        }
        else if(left) {
            hitBounds.setXOffset(-size);
            hitBounds.setYOffset(0);
        }
        else if(right) {
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(0);
        }
    }

    public void update() {

        animate();


        setHitBoxDirection();


        ani.update();



    }


    public abstract void render(Graphics2D g);
    public void input(KeyHandler key, MouseHandler mouse) {

    }




}
