package org.acadmeiadecodigo.gnunas.keepitclean;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.acadmeiadecodigo.gnunas.keepitclean.objects.*;

import java.util.LinkedList;

public class Field {
    private int height;
    private int width;
    public static final int PADDING=10;
    private int offsetX;
    private int offsetY;
    private LinkedList<GameObject> objects;
    private Rectangle screen;
    private Picture background;

    public Field(int width, int height){
        objects = new LinkedList<>();
        this.height = height;
        this.width = width;
        offsetX = (width / 8) + PADDING;
        offsetY = (height / 8) + PADDING;
        fillObjList();
    }

    //Instancia os GameObjects desta sala e guarda-os num array de GameObjects
    private void fillObjList(){

        objects.add(new Table(696 + offsetX,206 + offsetY, "Table","resources/objects/DirtTable.png"));
        objects.add(new Bookshelf(89 + offsetX,35 + offsetY,"Bookshelf","resources/objects/BookShelf.png"));
        objects.add(new Couch(92 + offsetX, 200 + offsetY, "Couch" , "resources/objects/DirtCouch.png"));
        objects.add(new Plant(480 + offsetX, 42 + offsetY, "Plant" ,"resources/objects/DriedPlant.png"));
        objects.add(new Plant(640 + offsetX, 42 + offsetY, "Plant2", "resources/objects/DriedPlant.png"));
        objects.add(new Trash(900 + offsetX, 450 + offsetY, "Trash", "resources/objects/DirtTrash.png"));
        objects.add(new Weed((int) (170 + (Math.random() * (960 - 100))), 510,"resources/GameObject/weed.png"));

    }

    //Desenha o fundo, sala e os objetos contidos no array
    public void draw(){
       screen = new Rectangle(PADDING,PADDING,width,height);
       screen.setColor(Color.BLACK);
       screen.fill();

       background = new Picture(offsetX,offsetY,"resources/livingRoom (1).png");
       background.draw();

       for (GameObject go : objects){
           go.draw();
       }

    }

    // para podermos fazer delete das pictures eventualmente
    public void deleteScene(){
        screen.delete();
        background.delete();
        for (GameObject go : objects){
            go.delete();
        }

    }

    public Picture getBackground() {
        return background;
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }


}
