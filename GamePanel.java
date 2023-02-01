
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GamePanel extends JPanel implements MouseListener, ActionListener{
		
	private BufferedImage img;
	private BufferedImage[] sprites;
	
	private JButton reset;
	private JButton newGame;
	private JLabel text;
	private JLabel bombsLeft;

	public GamePanel() {
		
		this.setSize(Main.fieldWidth * 64, Main.fieldHeight * 64 + 64);
		this.setLayout(null);
		
		importImages();
		
		this.addMouseListener(this);
		
		reset = new JButton("Reset Game");
		reset.setLocation(64, 7);
		reset.setSize((Main.fieldWidth * 64 - 78) / 2, 50);
		reset.setFont(new Font("Arial", Font.PLAIN, 20));
		reset.addActionListener(this);
		this.add(reset);
		
		newGame = new JButton("New Game");
		newGame.setLocation(71 + (Main.fieldWidth * 64 - 78) / 2, 7);
		newGame.setSize((Main.fieldWidth * 64 - 78) / 2, 50);
		newGame.setFont(new Font("Arial", Font.PLAIN, 20));
		newGame.addActionListener(this);
		this.add(newGame);
		
		bombsLeft = new JLabel(String.valueOf(Main.displayBombs));
		bombsLeft.setLocation(7, 7);
		bombsLeft.setSize(50, 50);
		bombsLeft.setFont(new Font("Arial", Font.BOLD, 30));
		bombsLeft.setHorizontalAlignment(JLabel.CENTER);
		bombsLeft.setBackground(Color.black);
		bombsLeft.setOpaque(true);
		bombsLeft.setForeground(Color.red);
		this.add(bombsLeft);
		
		text = new JLabel();
		text.setLocation(0, 64 + (Main.fieldHeight * 64 - 50) / 2);
		text.setSize(Main.fieldWidth * 64, 50);
		text.setFont(new Font("Arial", Font.PLAIN, 40));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBackground(Color.LIGHT_GRAY);
		text.setOpaque(true);
		text.setForeground(Color.black);
		text.setVisible(false);
		this.add(text);
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
						
		for(int i = 0; i < Main.fieldHeight; i++) {
			for(int j = 0; j < Main.fieldWidth; j++) {
				if(Main.screen[i][j] == 1) {
					g.drawImage(sprites[10], j * 64, i * 64 + 64, this);
				}else if(Main.screen[i][j] == 0) {
					g.drawImage(sprites[Main.field[i][j]], j * 64, i * 64 + 64, this);
				}else if(Main.screen[i][j] == 3) {
					g.drawImage(sprites[12], j * 64, i * 64 + 64, this);
				}else if(Main.screen[i][j] == 2) {
					g.drawImage(sprites[11], j * 64, i * 64 + 64, this);
				}
			}
		}
		
		if(!Main.inGame) {
			if(Main.victory) {
				g.setColor(new Color(0, 255, 0, 127));
				text.setText("You Won");
			}else if(Main.defeat) {
				g.setColor(new Color(255, 0, 0, 127));
				text.setText("You Lost");
			}
			g.fillRect(0, 64, Main.fieldWidth * 64, Main.fieldHeight * 64);
			text.setVisible(true);
		}

		bombsLeft.setText(String.valueOf(Main.displayBombs));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(Main.inGame) {
			if(SwingUtilities.isLeftMouseButton(e)) {
				Main.updateField(e.getX() / 64, e.getY() / 64 - 1, 0);
			}else if(SwingUtilities.isRightMouseButton(e)) {
				Main.updateField(e.getX() / 64, e.getY() / 64 - 1, 1);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Main.frame.remove(Main.gamePanel);
		SwingUtilities.updateComponentTreeUI(Main.frame);
		
		if(e.getSource() == reset) {
			Main.createField();
		}else if(e.getSource() == newGame) {
			Main.insertParameters();
		}
	}
	
	private void importImages() {
		InputStream is = getClass().getResourceAsStream("MinesweeperTextures.png");
		
		try {
			img = ImageIO.read(is);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		sprites = new BufferedImage[13];
		for(int i = 0; i < 13; i++) {
			sprites[i] = img.getSubimage(i * 64, 0, 64, 64);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
