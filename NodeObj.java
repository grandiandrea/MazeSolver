public class NodeObj{
  Pole north, south, east, west;
  int x,y;
  public NodeObj(Pole n,Pole s,Pole e,Pole w ,int xi,int yi){
    north = n;
    south = s;
    east = e;
    west = w;
    x = xi;
    y = yi;
  }
  public Pole UpdateE(Pole e){
    east = e;
    return e;
  }
  public Pole UpdateW(Pole w){
    west = w;
    return w;
  }
  public Pole UpdateS(Pole s){
    south = s;
    return s;
  }
  public Pole UpdateN(Pole n){
    north = n;
    return n;
  }

}
