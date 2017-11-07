/*
Si analizza i qualunque nodo != collegato allo 0; Poi vengono inseriti in una lista, la lista viene eseguita
e vengono trovati per esempio tutti i nodi collegati al un nodo, allora quel nodo viene flaggato in quanto non attivo;
viene ripetuto tutto in modo ricorsivo fino a floodare il labirinto. Quando un nodo collegato viene analizzato e la
somma delle distanze e' minore di quella che gia' aveva in lista, viene aggiornata. Si somma la distanza  e si indica il nodo precedente

*/
import java.util.ArrayList;

public class Dijkstra{
  Maze maze;
  int count;
  ArrayList<Nodo> trace = new ArrayList<Nodo>();
  ArrayList<Nodo> nodi = new ArrayList<Nodo>();
  ArrayList<Nodo> que = new ArrayList<Nodo>();

  public Dijkstra(Maze labir){
    maze = labir;
    nodi.add(new Nodo(0,0,true,0));
    nodiAdder(0, 0);
    queAdder();
    //mi serve una funzione che prende tutti gli elementi collegati e li aggiunge in coda in ordine crescente di distanza

  }
  public void sort(){
    System.out.println("sort() initialized");
    do{
      while(que.size() != 0){
        Nodo temp = deepCopy(queGetMin());
        nodi.add(temp);
        System.out.println("Finding and adding to nodi all the nodes close to temp id:" +temp.id);
        nodiAdder( temp.id, temp.path);
      }
      queAdder();
    }while(que.size() != 0);
    System.out.println("sort() completed");
    System.out.println("trace initialized");
    nodiTrace(maze.nodes.size() - 1);
    //return null;
  }
  public void nodiTrace(int n){

    for(Nodo temp : nodi){
      if(temp.id == n){
        trace.add(temp);
        if(temp.path != 0){
          nodiTrace(temp.path);
          System.out.println("Path of temp.id: "+temp.id+ " found throug temp.path:"+temp.path);
        }
        else{
          trace.add(nodi.get(0));
          System.out.println("Start found");
        }
        break;
      }
    }

    //return null;
  }
  public Nodo deepCopy(Nodo inpt){
    Nodo out = new Nodo(inpt.dist, inpt.path, inpt.flag, inpt.id);
    return out;
  }
  public void nodiAdder(int n, int p){
    int d = 0;
    for(Nodo temp : nodi){
      if(temp.id == n){
        temp.flag = false;
        d = temp.dist;
      }
    }
    if(maze.nodes.get(n).south.id != 0 && maze.nodes.get(n).south.id != p){
      addNcomp(getDist(maze.nodes.get(n).south.id,n) + d, n, true, maze.nodes.get(n).south.id);
    }
    if(maze.nodes.get(n).east.id != 0 && maze.nodes.get(n).east.id != p){
      addNcomp(getDist(maze.nodes.get(n).east.id,n) + d, n, true, maze.nodes.get(n).east.id);
    }
    if(maze.nodes.get(n).west.id != 0 && maze.nodes.get(n).west.id != p){
      addNcomp(getDist(maze.nodes.get(n).west.id,n) + d, n, true, maze.nodes.get(n).west.id);
    }
    if(maze.nodes.get(n).north.id != 0 && maze.nodes.get(n).north.id != p){
      addNcomp(getDist(maze.nodes.get(n).north.id,n) + d, n, true, maze.nodes.get(n).north.id);
    }
    //return null;
  }
  public void queAdder(){
    for(int i = 0; i < nodi.size(); i++){
      if(nodi.get(i).flag == true){
        System.out.println("Adding to que the node with id:" +nodi.get(i).id);
        que.add(deepCopy(nodi.get(i)));
      }
    }
    //return null;
  }
  public void addNcomp(int d, int p, boolean f,int i){
    Nodo inpt = new Nodo(d,p,f,i);
    boolean flag = false;
    for(Nodo temp : nodi){
      if(inpt.id == temp.id){
        flag = true;
        if(inpt.dist < temp.dist){
          System.out.println("Updating shortest path to id:" +temp.id+ " through pathid: "+inpt.path+ " with new distance of:" +inpt.dist);
          temp.path = inpt.path;
          temp.dist = inpt.dist;
        }
        break;
      }
    }
    if(flag == false){
      System.out.println("Adding to nodi the Nodo id: "+inpt.id);
      nodi.add(inpt);
    }


    //return void;
  }
  public Nodo queGetMin(){
    Nodo min = que.get(0);
    for(int i = 0; i < que.size(); i++){
      if(que.get(i).dist < min.dist){
        min = que.get(i);
      }
    }
    int ind = que.indexOf(min);
    min = deepCopy(que.get(ind));
    que.remove(ind);
    //min.flag = true;
    System.out.println("Found the element with the shortest distance in que with id:" +min.id);
    return min;
  }

  public int getDist(int n, int p){
    //Bisogna trovare nella lista un nodo che ha l'id uguale alla path di n;
    int dist = 0;
    if(maze.nodes.get(n).south.id == p){
      dist = maze.nodes.get(n).south.distance;
    }
    else if(maze.nodes.get(n).east.id == p){
      dist = maze.nodes.get(n).east.distance;
    }
    else if(maze.nodes.get(n).west.id == p){
      dist = maze.nodes.get(n).west.distance;
    }
    else if(maze.nodes.get(n).north.id == p){
      dist = maze.nodes.get(n).north.distance;
    }
    return dist;
  }

}
class Nodo{
  int dist;
  int path;
  boolean flag;
  int id;
  int graphId;
  public Nodo(int d, int p, boolean f,int i){
    dist = d;
    path = p;
    flag = f;
    id = i;
  }
}
