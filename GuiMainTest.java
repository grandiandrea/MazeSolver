import java.util.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GuiMainTest{
	public static void main(String[] args) throws IOException{
		BufferedImage image = ImageIO.read(new File("maze.png"));
		BufferedImage image1 = ImageIO.read(new File("saved.png"));
		new Gui1(image, image1);
	}
}