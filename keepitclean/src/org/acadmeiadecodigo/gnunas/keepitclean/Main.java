package org.acadmeiadecodigo.gnunas.keepitclean;

import org.acadmeiadecodigo.gnunas.keepitclean.characters.Cat;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){


        Sound sound = new Sound("/resources/Sound/ArcadeKid.wav");
        sound.play(true);

        try {

            Thread.sleep(100);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        try {
            Game game = new Game();

            game.init();




            game.start();

        }
        catch (InterruptedException interruptedException){
            System.out.println(interruptedException.getMessage());
        }
    }
}
