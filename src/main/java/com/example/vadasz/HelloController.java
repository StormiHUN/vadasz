package com.example.vadasz;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {

    public Pane pane;
    public Label shots;
    public Label foxes;

    ImageView[][] t = new ImageView[16][32];
    String[][] tn = new String[16][32];

    int shots_taken = 0;
    int shots_hit = 0;
    int foxes_dead = 0;
    int foxes_alive = 0;
    int foxes_at_start = 0;

    public void initialize(){

        for(int y = 0;y < 16; y++){
            for(int x = 0; x < 32; x++){
                t[y][x] = new ImageView();
                tn[y][x] = "tree.png";
                if(Math.random()<=0.1){
                    tn[y][x] = "fox.png";
                    foxes_alive++;
                    foxes_at_start++;
                }
                t[y][x].setImage(new Image(getClass().getResourceAsStream("icons/dark.png")));
                t[y][x].setTranslateX(10+x*48);
                t[y][x].setTranslateY(10+y*48);
                int finalY = y;
                int finalX = x;
                t[y][x].setOnMouseEntered(mouseEvent -> reveal(finalY,finalX,"reveal"));
                t[y][x].setOnMouseExited(mouseEvent -> reveal(finalY,finalX,"hide"));

                pane.getChildren().add(t[y][x]);
            }
        }

        foxes.setText(String.format("%d/%d róka",foxes_alive,foxes_at_start));

    }

    public void reveal(int y, int x, String type){
        if(!type.equals("hide")){
            for(int dy = -1; dy < 2; dy++){
                for(int dx = -2; dx < 3; dx++){
                    try {
                        t[y + dy][x + dx].setImage(new Image(getClass().getResourceAsStream("icons/" + tn[y + dy][x + dx])));
                    }catch(Exception e){}
                }
            }
            for(int dy = -2; dy < 5; dy+=4){
                for(int dx = -1; dx < 2; dx++){
                    try{
                        t[y + dy][x + dx].setImage(new Image(getClass().getResourceAsStream("icons/" + tn[y + dy][x + dx])));
                    }catch (Exception e){}
                }
            }
        }else{
            for(int dy = -1; dy < 2; dy++){
                for(int dx = -2; dx < 3; dx++){
                    try {
                        t[y + dy][x + dx].setImage(new Image(getClass().getResourceAsStream("icons/dark.png")));
                    }catch(Exception e){}
                }
            }
            for(int dy = -2; dy < 5; dy+=4){for(int dx = -1; dx < 2; dx++){
                try{
                    t[y + dy][x + dx].setImage(new Image(getClass().getResourceAsStream("icons/dark.png")));
                }catch (Exception e){}
            }
            }
        }
    }

}