package org.acadmeiadecodigo.gnunas.keepitclean.characters;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.acadmeiadecodigo.gnunas.keepitclean.Direction;
import org.acadmeiadecodigo.gnunas.keepitclean.Game;

public class KeyboardPlayerHandler implements KeyboardHandler {

    Keyboard kb;
    private Direction direction;
    private boolean moving;
    private boolean interacting;
    private int[] keyList;
    private boolean[] pressedKeys = new boolean[6];
    private Player player;


    public KeyboardPlayerHandler(Player player, Direction direction, int up,int down,int left,int right,int space,int q){
        this.direction =direction;
        moving = false;
        interacting = false;
        kb = new Keyboard(this);
        keyList= new int[]{up,down,left,right,space,q};
        this.player = player;

    }

    public boolean isInteracting() {
        return interacting;
    }

    public boolean isMoving() {
        return moving;
    }


    public void loadKboardConfig(){

        KeyboardEvent pressedUp = new KeyboardEvent();
        pressedUp.setKey(keyList[0]);
        pressedUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(pressedUp);

        KeyboardEvent releasedUp = new KeyboardEvent();
        releasedUp.setKey(keyList[0]);
        releasedUp.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(releasedUp);


        KeyboardEvent pressedDown = new KeyboardEvent();
        pressedDown.setKey(keyList[1]);
        pressedDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(pressedDown);


        KeyboardEvent releasedDown = new KeyboardEvent();
        releasedDown.setKey(keyList[1]);
        releasedDown.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(releasedDown);



        KeyboardEvent pressedLeft = new KeyboardEvent();
        pressedLeft.setKey(keyList[2]);
        pressedLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(pressedLeft);



        KeyboardEvent releasedLeft = new KeyboardEvent();
        releasedLeft.setKey(keyList[2]);
        releasedLeft.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(releasedLeft);



        KeyboardEvent pressedRight = new KeyboardEvent();
        pressedRight.setKey(keyList[3]);
        pressedRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(pressedRight);



        KeyboardEvent releasedRight = new KeyboardEvent();
        releasedRight.setKey(keyList[3]);
        releasedRight.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(releasedRight);

        KeyboardEvent pressedSpace = new KeyboardEvent();
        pressedSpace.setKey(keyList[4]);
        pressedSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(pressedSpace);

        KeyboardEvent releasedSpace = new KeyboardEvent();
        releasedSpace.setKey(keyList[4]);
        releasedSpace.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(releasedSpace);

        KeyboardEvent pressedQ = new KeyboardEvent();
        pressedQ.setKey(keyList[5]);
        pressedQ.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(pressedQ);



    }

    private Direction moveNewDirection(){

        if(pressedKeys[0]){
            return Direction.UP;
        }

        if(pressedKeys[1]){
            return Direction.DOWN;
        }

        if(pressedKeys[2]){
            return Direction.LEFT;
        }

        if(pressedKeys[3]){
            return Direction.RIGHT;
        }

        return Direction.SIT;

    }


    public Direction getDirection() {
        return direction;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        moving = true;

        if(keyboardEvent.getKey()==keyList[0]){
            pressedKeys[0]=true;
        }

        if(keyboardEvent.getKey()==keyList[1]){
            pressedKeys[1]=true;
        }

        if(keyboardEvent.getKey()==keyList[2]){
            pressedKeys[2]= true;
        }
        if(keyboardEvent.getKey()==keyList[3]){
            pressedKeys[3]=true;
        }

        if(keyboardEvent.getKey()==keyList[4]){
            pressedKeys[4]=true;
            player.setInteracting(true);
            System.out.println(player.isInteracting());
        }
        if(keyboardEvent.getKey()==keyList[5]){
            pressedKeys[5] = true;
            System.exit(0);
        }


        direction = moveNewDirection();
        player.checkMovement();

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        moving = false;

        if(keyboardEvent.getKey()==keyList[0]){
            pressedKeys[0]=false;
        }

        if(keyboardEvent.getKey()==keyList[1]){
            pressedKeys[1]=false;
        }

        if(keyboardEvent.getKey()==keyList[2]){
            pressedKeys[2]= false;
        }
        if(keyboardEvent.getKey()==keyList[3]){
            pressedKeys[3]=false;
        }

        if(keyboardEvent.getKey()==keyList[4]){
            player.setInteracting(false);
        }

        if(direction== Direction.SIT){
            moving = false;
        }
    }

}
