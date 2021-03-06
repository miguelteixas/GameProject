package org.acadmeiadecodigo.gnunas.keepitclean;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.acadmeiadecodigo.gnunas.keepitclean.characters.Cat;
import org.acadmeiadecodigo.gnunas.keepitclean.characters.Player;

import javax.management.StringValueExp;
import java.security.cert.TrustAnchor;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Game {

    private Level level;
    private static Player player;
    private Cat cat;
    private static Text txt;
    public static GameState currentState;
    public static SelectedOption currentOption ;
    protected int timeLeft;
    private Timer timer;
    private GameController menuController = new GameController();
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

    public void init() throws InterruptedException{
        Picture menu = new Picture(10,10, "resources/menus/menu.png");
        menu.draw();
        Picture heart = new Picture(30,245,"resources/menus/heart.png");
        heart.draw();

        SelectedOption current = currentOption;

        while (currentState == GameState.MAINMENU || currentState == GameState.INSTRUCTIONS) {

            if(GameState.INSTRUCTIONS == currentState){
                instructions();
                Thread.sleep(7000);
                currentState = GameState.MAINMENU;
                currentOption= SelectedOption.PLAY;
               init();
            }

            if(currentOption != current){

                if (currentOption == SelectedOption.PLAY){

                    heart.delete();

                    heart = new Picture(30,245,"resources/menus/heart.png");
                    heart.draw();
                }
                if (currentOption == SelectedOption.INSTRUCTIONS){

                    heart.delete();
                    heart = new Picture(30,340,"resources/menus/heart.png");
                    heart.draw();
                }
                if (currentOption == SelectedOption.QUIT){

                    heart.delete();
                    heart = new Picture(30,440,"resources/menus/heart.png");
                    heart.draw();
                }
            }
            current = currentOption;

            Thread.sleep(50);
        }
        heart.delete();

        menu.delete();
    }

        public void instructions () {

            Picture instructions = new Picture(10, 10, "resources/menus/instructions.png");
            instructions.draw();
        }

        public void winGame(){
            Picture winGameScreen = new Picture(170,100, "resources/menus/gameWon.png");
            winGameScreen.draw();

            cat.setStopCat(true);
            timer.cancel();

            Text score = new Text(500,50,"Score");
            score.draw();

        }

        public void gameOver(){
            Picture gameOver = new Picture(170,100,"resources/menus/gameLost.png");
            gameOver.draw();
            try{
                cat.setStopCat(true);
                Thread.sleep(6000);
                System.exit(0);
            }catch (InterruptedException interruptedException){
                System.out.println(interruptedException.getMessage());
            }
        }

        public void plot () throws InterruptedException {

            Rectangle fundo = new Rectangle(10, 10, 1280, 720);
            fundo.setColor(Color.BLACK);
            fundo.fill();
            Picture menu = new Picture(170, 100, "resources/menus/tinder.png");

            menu.draw();
            Thread.sleep(1000);
            menu.delete();

            Picture menu2 = new Picture(170, 100, "resources/menus/superlike.png");
            menu2.draw();
            Thread.sleep(1000);
            menu2.delete();

            Picture menu3 = new Picture(170, 100, "resources/menus/answer.png");
            menu3.draw();
            Thread.sleep(6000);
            menu3.delete();

            Picture menu4 = new Picture(170, 100, "resources/menus/oops.png");
            menu4.draw();
            Thread.sleep(3000);
            menu4.delete();
        }


        public void start () {
            final long TOTAL_TIME = TimeUnit.MINUTES.toMillis(1);
            final long WAITING_TIME = TimeUnit.SECONDS.toMillis(1);

            level = new Level(this);
            player = new Player(level);
            cat = new Cat(level);
            showScore();

            Text clockTxt = new Text(1200, 50, "0:0");
            clockTxt.grow(20, 50);
            clockTxt.setColor(Color.WHITE);
            clockTxt.draw();

            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                 int executions = 60;

                @Override
                public void run() {

                    int minutes = (executions / 60);
                    int seconds = (executions % 60);

                    clockTxt.setText(String.format("%d:%02d", minutes, seconds));


                    if (executions == 0) {
                        gameOver();
                        timer.cancel();
                    }
                    executions--;
                    timeLeft = executions;
                }
            }, 0, WAITING_TIME);

            try {
                cat.move();
            } catch (
                    InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage());
            }
        }


        public static void exit () {
            System.exit(0);
        }

        public enum SelectedOption {
            PLAY,
            INSTRUCTIONS,
            QUIT
        }
}




