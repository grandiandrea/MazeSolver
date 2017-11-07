import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.*;

public class ImageWriter{
  Graphics2D image;
  ArrayList<Nodo> list;
  ArrayList<Wrap> coords = new ArrayList<Wrap>();
  Maze maze;
  File outputfile = new File("saved.png");
  BasicStroke bs1 = new BasicStroke(1);
  public ImageWriter(Graphics2D in, ArrayList<Nodo> lista, Maze lab){
    image = in;
    list = lista;
    maze = lab;
    write();
  }
  public void write(){
    for(int i = list.size()-1; i >= 0; i--){
      for( NodeObj temp : maze.nodes){
        if(list.get(i).id == maze.nodes.indexOf(temp)){
          coords.add(new Wrap(temp.x, temp.y));
          break;
        }
      }
    }
    Color startColor = Color.red;
    Color endColor = Color.blue;
    int startX = 1, startY = 1, endX = maze.lab[0].length -1, endY = maze.lab.length -1;
    GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
    image.setStroke(bs1);
    image.setPaint(gradient);
    for(int i = 0; i < coords.size() -1; i++){
      image.drawLine(coords.get(i).x, coords.get(i).y, coords.get(i+1).x, coords.get(i+1).y);
    }
  }

}
class Wrap{
  int x;
  int y;
  Wrap(int xi, int yi){
    x = xi;
    y = yi;
  }
}
