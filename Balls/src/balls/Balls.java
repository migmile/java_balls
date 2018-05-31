/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author migmile
 */
public class Balls extends Application {
  static int pos_x=0;  
  B b1=new B(new Rect(1,1,203,152));
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
       
        Group root = new Group();
        Canvas canva = new Canvas(500,275);
        GraphicsContext cont=canva.getGraphicsContext2D();

        drawPict(cont);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                AnimationTimer at;
                at = new AnimationTimer(){
                    
                    
                    @Override
                    public void handle(long now) {
                        try {
                            changePict(cont);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Balls.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                at.start();
            }
        });
        
        
        root.getChildren().add(canva);
        root.getChildren().add(btn);        
        Scene scene = new Scene(root, 500, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    protected void drawPict(GraphicsContext cont) {
        cont.setFill(Color.BLUE);
        cont.fillOval(b1.x, b1.y, 5, 5);
    }

    private void changePict(GraphicsContext cont) throws InterruptedException {

          Thread.sleep(500);
            b1.move();
            drawPict(cont);       

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
    
    class Rect{
        int _xl=0;
        int _yt=0;
        int _xr=0;
        int _yb=0;
    public Rect(int xl,int yt,int xr,int yb){
        _xl=xl;
        _yt=yt;
        _xr=xr;
        _yb=yb;
        };    
    }
    
    class B{
        Rect _bounds;
        int x;
        int y;
        float dx;
        float dy;        
        public B(Rect bounds){
            _bounds=bounds;
            x=(bounds._xl+bounds._xr)/2;
            y=(bounds._yt+bounds._yb)/2;            
            dx=(bounds._xl+bounds._xr)/10;
            dy=(bounds._yt+bounds._yb)/10;            
        }
        public void move(){
            int wind=(int)(Math.random()*10.);
            System.out.println(wind);
         x+=dx+wind;
         y+=dy+wind;
         if (x>_bounds._xr){dx=-dx;x=_bounds._xr;}
         if (y>_bounds._yb){dy=-dy;y=_bounds._yb;}
         if (x<_bounds._xl){dx=-dy;x=_bounds._xl;}
         if (y<_bounds._yt){dy=-dy;y=_bounds._yt;}

        }
    }
    
}
