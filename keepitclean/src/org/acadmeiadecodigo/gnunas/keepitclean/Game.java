package org.acadmeiadecodigo.gnunas.keepitclean;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.acadmeiadecodigo.gnunas.keepitclean.characters.Cat;
import org.acadmeiadecodigo.gnunas.keepitclean.characters.Player;

import javax.management.StringValueExp;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Game {

    private Level level;
    private static Player player;
    private Cat cat;
    private static Text txt;
    public static GameState currentState;
    public static SelectedOption currentOption;
    private static int score;


    public Game() {

        currentState = GameState.MAINMENU;
        currentOption = SelectedOption.PLAY;

    }

    public void showScore() {
        txt = new Text(100, 50, "0");
        txt.grow(20, 50);
        txt.setColor(Color.RED);
        txt.draw();
    }


    public static void updateScore(int value) {
        score += value;
        txt.setText(String.valueOf(score));
    }


    public void init() throws InterruptedException {
        //showClock();
        Picture menu = new Picture(0, 0, "menus/menus.png");
        menu.draw();
        Rectangle arrow = new Rectangle(430, 270, 64, 64);
        arrow.fill();
        SelectedOption current = currentOption;
        GameController menuController = new GameController();

        while (currentState == GameState.MAINMENU || currentState == GameState.INSTRUCTIONS) {

            if (currentOption != current) {

                if (currentOption == SelectedOption.PLAY) {
                    arrow.delete();
                    arrow = new Rectangle(430, 270, 64, 64);
                    arrow.fill();
                }
                if (currentOption == SelectedOption.INSTRUCTIONS) {
                    arrow.delete();
                    arrow = new Rectangle(430, 360, 64, 64);
                    arrow.fill();
                }
                if (currentOption == SelectedOption.QUIT) {
                    arrow.delete();
                    arrow = new Rectangle(430, 450, 64, 64);
                    arrow.fill();
                }

            }

            current = currentOption;

            Thread.sleep(50);
        }

        menu.delete();

        /*while(!gameOver){
            play game here
        }*/

    }

    public void start() {

        final long TOTAL_TIME = TimeUnit.MINUTES.toMillis(1);
        final long WAITING_TIME = TimeUnit.SECONDS.toMillis(1);

        level = new Level();
        player = new Player(level);
        cat = new Cat(level);
        showScore();

        Text clockTxt = new Text(1200, 50, "0:0");
        clockTxt.grow(20, 50);
        clockTxt.setColor(Color.WHITE);
        clockTxt.draw();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private int executions = 120;

            @Override
            public void run() {

                int minutes = (executions / 60);
                int seconds = (executions % 60);

                clockTxt.setText(String.format("%d:%02d", minutes, seconds));


                System.out.println(minutes + ":" + seconds);

                if (executions == 0) {
                    timer.cancel();
                }
                executions--;

            }
        }, 0, WAITING_TIME);


        try {
            cat.move();
        } catch (
                InterruptedException interruptedException) {
            System.out.println(interruptedException.getMessage());
        }
    }


    public static void exit() {
        System.exit(0);
    }

    public enum SelectedOption {
        PLAY,
        INSTRUCTIONS,
        QUIT
    }
}



