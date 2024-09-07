import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class Main2 {
	public static void main(String[] args) {
		JFrame framee = new JFrame("weather app");
		framee.setSize(250, 300);

		framee.setIconImage(new ImageIcon("C:/Users/ashug/OneDrive/Pictures/weather_app.png").getImage());
		framee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon backgroundI = new ImageIcon("C:/Users/ashug/Downloads/WhatsApp Image 2024-09-06 at 9.56.59 PM.jpeg");
		BackgroundPanel panell = new BackgroundPanel(backgroundI.getImage());

		framee.add(panell);
		place(panell);
		framee.setVisible(true);
	}

	public static void place(BackgroundPanel panel){
		panel.setLayout(null);

		JLabel userlabel = new JLabel("city");
		userlabel.setBounds(30,12,80,30);
		userlabel.setFont(new Font("Arial", Font.BOLD,18) );
		panel.add(userlabel);

		JTextField usertext = new JTextField(20);
		usertext.setBounds(100,15,100,25);
		usertext.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel.add(usertext);

		JButton loginbutton = new JButton("Get weather");
		loginbutton.setBounds(40,90,150,30);
		loginbutton.setBackground(Color.ORANGE);
		loginbutton.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel.add(loginbutton);

		JTextArea resultarea = new JTextArea();
		resultarea.setBounds(15, 155, 200, 50);
		resultarea.setOpaque(false);
		resultarea.setFont(new Font("Arial", Font.BOLD, 14) );
		panel.add(resultarea);

		loginbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String city = usertext.getText();
				String response = weatherapp.getWeatherData(city);

				if(response != null){
					resultarea.setOpaque(true);
					resultarea.setText(weatherapp.parseWeatherData(response));

					String weatherDscp = weatherapp.getWeatherData(city);
					String weatherCondtion = weatherapp.weatherDiscp(weatherDscp);

					switch (weatherCondtion){
						case "clear sky":
							panel.setBackgroundImage(new ImageIcon("C:/Users/ashug/Downloads/WhatsApp Image 2024-09-07 at 10.02.22 PM.jpeg").getImage() );
							break;

						case "overcast clouds":
							panel.setBackgroundImage(new ImageIcon("C:/Users/ashug/OneDrive/Pictures/overcast.jpg").getImage() );
							break;

						case "light rain":
							panel.setBackgroundImage(new ImageIcon("C:/Users/ashug/OneDrive/Pictures/light rain.jpg").getImage() );
							break;

						case "moderate rain":
							panel.setBackgroundImage(new ImageIcon("C:/Users/ashug/OneDrive/Pictures/moderate rain.jpg").getImage() );
							break;

						case "scattered clouds":
							panel.setBackgroundImage(new ImageIcon("C:/Users/ashug/OneDrive/Pictures/scattered clouds.jpg").getImage() );
							break;

						case "broken clouds":
							panel.setBackgroundImage(new ImageIcon("C:/Users/ashug/OneDrive/Pictures/scattered clouds.jpg").getImage() );
							break;

						case "mist":
							panel.setBackgroundImage(new ImageIcon("C:/Users/ashug/OneDrive/Pictures/mist.jpg").getImage() );
							break;

						default:
							panel.setBackgroundImage(new ImageIcon("C:/Users/ashug/Downloads/WhatsApp Image 2024-09-06 at 9.56.59 PM.jpeg").getImage());
					}
				}
				else{
					resultarea.setText("could not reterive data");
				}
			}
		});
	}
}

class BackgroundPanel extends JPanel{
	private Image backgroundImage;

	public BackgroundPanel(Image imageB){
		this.backgroundImage = imageB;
		setLayout(null);
		Main2.place(this);
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		if(backgroundImage != null){
		  g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// repaint background
	public void setBackgroundImage(Image newImage){
		this.backgroundImage = newImage;
		repaint();
	}
}
