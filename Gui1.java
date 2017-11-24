import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
public class Gui1 extends JFrame{
	public Gui1(BufferedImage Image1, BufferedImage Image2){
		JFrame guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Maze Solver GUI");
		guiFrame.setSize(650,680);
		guiFrame.setLocationRelativeTo(null);

		//final JPanel comboPanel = new	JPanel();
		iconPanel comboPanel = new iconPanel(Image1);
		comboPanel.setAlt(Image2);
		JButton Button1 = new JButton( "Premi per cambiare");
		Button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				//comboPanel.setVisible(!comboPanel.isVisible());
				//comboPanel.buff = image2;
				comboPanel.imgS();
			}
		});

		guiFrame.add(comboPanel, BorderLayout.NORTH);
		guiFrame.add(Button1,BorderLayout.SOUTH);
		guiFrame.setVisible(true);
	}

	
}
class iconPanel extends JPanel{
	BufferedImage buff;
	BufferedImage alt;
	public iconPanel(BufferedImage buff){
		this.buff = buff;
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buff, 0, 0, getWidth(), getHeight(), this);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
    public void setP(BufferedImage pic) {
    	this.buff = pic;
    	repaint();
	}
	public void setAlt(BufferedImage pic) {
    	this.alt = pic;
	}
	public void imgS(){
		BufferedImage temp = buff;
		this.buff = this.alt;
		this.alt = temp;
		repaint();
	}
}