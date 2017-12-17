import java.util.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Loader {
	public static void main(String[] args) throws IOException{
		String fileN;
		if(args.length != 0)
			fileN = args[0];
		else
			fileN = "maze.png";
		BufferedImage image = ImageIO.read(new File(fileN));
		byte[][] pixels = new byte[image.getWidth()][];

		for(int y = 0; y < image.getHeight(); y++){
		  pixels[y] = new byte[image.getWidth()];
		  for(int x = 0; x < image.getWidth(); x++){
		    pixels[y][x] = (byte) (image.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
		  }
		}
		/*for(int y = 0; y < pixels.length; y++){
			System.out.printf("\n");
			for(int x = 0; x < pixels[0].length; x++){
				System.out.print(" "+pixels[y][x]);
			}
		}*/
		Graphics2D out = image.createGraphics();

		Maze labir = new Maze(pixels);
		labir.Scanner();
		labir.Lister();

		System.out.println("pathfinding initialized");
		Dijkstra test = new Dijkstra(labir);
		test.sort();
		System.out.println("pathfinding completed ");
		//labir.print();
		System.out.println("ImageWriter starting, please wait");
		ImageWriter writer = new ImageWriter(out, test.trace, labir);
		System.out.println("ImageWriter created, starting ImageIO");
		ImageIO.write(image, "png", writer.outputfile);
		System.out.println("ImageIO done. Starting UI");
		Ui(fileN);
  }
  public static void Ui(String fileN) throws IOException{
	System.out.println("UI: opening original lab image");
  	BufferedImage image = ImageIO.read(new File(fileN));
	System.out.println("UI: opening resulting lab image");
	BufferedImage image1 = ImageIO.read(new File("saved.png"));
	System.out.println("UI: image loading completed, starting interface");
	new Gui1(image, image1);
  }
}
