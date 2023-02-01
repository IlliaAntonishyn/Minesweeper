
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartPanel extends JPanel implements ActionListener{

	private JLabel label1, label2, label3, warning1, warning2;
	private JTextField text1, text2, text3;
	private JButton button;
	
	private int maxWidth;
	private int maxHeight;
	private int maxBombs;
	
	static final int startPanelHeight = 190;
	static final int startPanelWidth = 340;
	
	public StartPanel() {		
		this.setSize(startPanelWidth, startPanelHeight);
		this.setLayout(null);
	
		maxHeight = Main.screenSize.height / 64 - 3;
		maxWidth = Main.screenSize.width / 64 - 2;
		maxBombs = maxHeight * maxWidth / 5;
		
		label1 = new JLabel("Insert Height:");
		label1.setLocation(20, 20);
		label1.setSize(220, 30);
		label1.setFont(new Font("Arial", Font.PLAIN, 20));
		label1.setForeground(Color.black);
		label1.setBackground(Color.LIGHT_GRAY);
		label1.setOpaque(true);
		this.add(label1);
		
		label2 = new JLabel("Insert Width:");
		label2.setLocation(20, 60);
		label2.setSize(220, 30);
		label2.setFont(new Font("Arial", Font.PLAIN, 20));
		label2.setForeground(Color.black);
		label2.setBackground(Color.LIGHT_GRAY);
		label2.setOpaque(true);
		this.add(label2);
		
		label3 = new JLabel("Insert Bomb Count:");
		label3.setLocation(20, 100);
		label3.setSize(220, 30);
		label3.setFont(new Font("Arial", Font.PLAIN, 20));
		label3.setForeground(Color.black);
		label3.setBackground(Color.LIGHT_GRAY);
		label3.setOpaque(true);
		this.add(label3);
		
		text1 = new JTextField("0");
		text1.setLocation(240, 20);
		text1.setSize(80, 30);
		text1.setFont(new Font("Arial", Font.BOLD, 20));
		text1.setBackground(Color.black);
		text1.setForeground(Color.red);
		text1.setHorizontalAlignment(JTextField.CENTER);
		this.add(text1);

		text2 = new JTextField("0");
		text2.setLocation(240, 60);
		text2.setSize(80, 30);
		text2.setFont(new Font("Arial", Font.BOLD, 20));
		text2.setBackground(Color.black);
		text2.setForeground(Color.red);
		text2.setHorizontalAlignment(JTextField.CENTER);
		this.add(text2);
		
		text3 = new JTextField("0");
		text3.setLocation(240, 100);
		text3.setSize(80, 30);
		text3.setFont(new Font("Arial", Font.BOLD, 20));
		text3.setBackground(Color.black);
		text3.setForeground(Color.red);
		text3.setHorizontalAlignment(JTextField.CENTER);
		this.add(text3);
		
		button = new JButton("Insert");
		button.setLocation(230, 140);
		button.setSize(90, 30);
		button.setFont(new Font("Arial", Font.PLAIN, 20));
		button.addActionListener(this);
		this.add(button);
		
		warning1 = new JLabel("P.S. Height {3 - " + maxHeight + "}");
		warning1.setLocation(20, 145);
		warning1.setSize(300, 10);
		warning1.setFont(new Font("Arial", Font.ITALIC, 10));
		this.add(warning1);
		
		warning2 = new JLabel("Width {6 - " + maxWidth + "} Bombs {1 - " + maxBombs + "}");
		warning2.setLocation(20, 160);
		warning2.setSize(300, 10);
		warning2.setFont(new Font("Arial", Font.ITALIC, 10));
		this.add(warning2);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Integer.parseInt(text1.getText()) < 3) {
			Main.fieldHeight = 3;
		}else if(Integer.parseInt(text1.getText()) > maxHeight) {
			Main.fieldHeight = maxHeight;
		}else {
			Main.fieldHeight = Integer.parseInt(text1.getText());
		}
		
		if(Integer.parseInt(text2.getText()) < 6) {
			Main.fieldWidth = 6;
		}else if(Integer.parseInt(text2.getText()) > maxWidth) {
			Main.fieldWidth = maxWidth;
		}else {
			Main.fieldWidth = Integer.parseInt(text2.getText());
		}
		
		if(Integer.parseInt(text3.getText()) < 1) {
			Main.bombCount = 1;
		}else if(Integer.parseInt(text3.getText()) > maxBombs) {
			Main.bombCount = maxBombs;
		}else {
			Main.bombCount = Integer.parseInt(text3.getText());
		}
		
		Main.frame.remove(Main.startPanel);
		Main.createField();
	}

}
