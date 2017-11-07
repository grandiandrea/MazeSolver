import java.util.ArrayList;
public class Maze{
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";
byte[][] lab;
ArrayList<NodeObj> nodes = new ArrayList<NodeObj>();
int count = 1;
boolean flag = true;
  public Maze(byte[][] o){
    lab = o;
  }
  ArrayList<NodeObj> Scanner(){
    for(int i = 0; i < lab[0].length; i++){
      if(lab[0][i] == 0){
        nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), i,0));
      }
    }
    for(int y = 1; y < lab.length -1; y++){
      for(int x = 0; x < lab[0].length -1; x++){
        if(lab[y][x] == 0){
          if(lab[y][x -1] == 1 && lab[y -1][x] == 1){
            nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
          }
          else if(lab[y +1][x] == 1 && lab[y][x +1] == 1){
            nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
          }
          else if(lab[y][x -1] == 1 && lab[y +1][x] == 1){
            nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
          }
          else if(lab[y -1][x] == 1 && lab[y][x +1] == 1){
            nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
          }
          else if(lab[y -1][x -1] == 1 && lab[y -1][x +1] == 1 && lab[y +1][x +1] == 1 && lab[y +1][x -1] == 1){
            if(lab[y][x -1] == 1 && lab[y][x +1] == 0){
              nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
            }
            else if(lab[y][x +1] == 1 && lab[y][x -1] == 0){
              nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
            }
            else if(lab[y -1][x] == 1 && lab[y +1][x] == 0){
              nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
            }
            else if(lab[y +1][x] == 1 && lab[y -1][x] == 0){
              nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
            }
            else if(lab[y][x +1] == 0 && lab[y][x -1] == 0 && lab[y +1][x] == 0 && lab[y -1][x] == 0){
              nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), x,y));
            }
          }

        }
      }
    }
    for(int i = 0; i < lab[0].length; i++){
      if(lab[lab.length-1][i] == 0){
        nodes.add(new NodeObj(new Pole(0,0), new Pole(0,0), new Pole(0,0), new Pole(0,0), i,lab.length-1));
      }
    }
    return nodes;
  }
  ArrayList<NodeObj> Lister(){
    boolean f = true;
    for(int y = 0; y < lab.length; y++){
      for(int x = 0; x < lab[0].length; x++){
        if(lab[y][x] == 0)
        for(int i = 0; i < nodes.size(); i++){
          if(nodes.get(i).x == x && nodes.get(i).y == y){
            //Controlla a destra
            f = true;
            for(int xi = x +1; xi < lab[0].length && lab[y][xi] == 0 && f == true; xi++){
              //Controlla i nodi a destra
              for(int ii = i+1; ii < nodes.size(); ii++){
                if(nodes.get(ii).x == xi && nodes.get(ii).y == y){
                  nodes.get(i).UpdateE(new Pole(xi - x, ii));
                  nodes.get(ii).UpdateW(new Pole(xi - x, i));
                  f = false;
                  break;
                }
              }
            }
            //Controlla in basso
            f = true;
            for(int yi = y +1; yi < lab.length && lab[yi][x] == 0 && f == true; yi++){
              //Controlla i nodi in basso
              for(int ii = i+1; ii < nodes.size(); ii++){
                if(nodes.get(ii).y == yi && nodes.get(ii).x == x){
                  nodes.get(i).UpdateS(new Pole(yi - y, ii));
                  nodes.get(ii).UpdateN(new Pole(yi - y, i));
                  f = false;
                  break;
                }
              }
            }
            break;
          }
        }

      }
    }
    return nodes;
  }
  void print(){
    for(int y = 0; y < lab.length; y++){
			System.out.printf("\n");
			for(int x = 0; x < lab[0].length; x++){
        flag = true;
        for(int i = 0; i < nodes.size(); i++){
          if(nodes.get(i).x == x && nodes.get(i).y == y){
            System.out.print(ANSI_GREEN+"n"+ANSI_RESET+" ");
            flag = false;
          }
        }
        if(flag != false){
          if(lab[y][x] == 1)
          System.out.print(ANSI_BLACK+lab[y][x]+ANSI_RESET+" ");
          else
          System.out.print(lab[y][x]+ANSI_RESET+" ");
        }
			}
		}
    System.out.printf("\n\n\n");
    for(int y = 0; y < lab.length; y++){
			System.out.printf("\n\n");
			for(int x = 0; x < lab[0].length; x++){
        flag = true;
        for(int i = 0; i < nodes.size(); i++){
          if(nodes.get(i).x == x && nodes.get(i).y == y){
            System.out.print(ANSI_GREEN+i+ANSI_RESET+" ");
            flag = false;
          }
        }
        if(flag != false){
          if(lab[y][x] == 1)
          System.out.print(ANSI_BLACK+lab[y][x]+ANSI_RESET+" ");
          else
          System.out.print(lab[y][x]+ANSI_RESET+" ");
        }
			}
		}
  }

}
