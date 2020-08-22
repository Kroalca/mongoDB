package login;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.toedter.calendar.JCalendar;

public class addmasajes extends JFrame implements ActionListener  {
	
	private static final long serialVersionUID = 1L;
	JLabel eticodmasaje;
	JLabel eticl;
	JLabel etima;
	static JTextField textcodmasaje;
	static JPasswordField password;
	JButton boton,boton2;
	static JCalendar calendario;
	JFrame frame;
	JPanel panel;
	static JComboBox<String> dnis,dnima;
	public addmasajes() {
		
		eticodmasaje = new JLabel("Cod. Masaje:");
		eticodmasaje.setBounds(10, 30, 80, 20);
		
		eticl = new JLabel("DNI Cliente");
		eticl.setBounds(10, 60, 80, 20);
		
		etima = new JLabel("DNI Masajista:");
		etima.setBounds(10, 90, 80, 20);
	
		textcodmasaje = new JTextField();
		textcodmasaje.setBounds(100, 30, 80, 20);
		
		dnis = new JComboBox<String>();
		dnis.setBounds(100, 60, 90, 20);
		adddniclienete();
		
		dnima = new JComboBox<String>();
		dnima.setBounds(100, 90, 90, 20);
		adddnimasajista();
		
		boton = new JButton("Insertar");
		boton.setBounds(100, 220, 80, 20);
		boton.addActionListener(this);
		
		boton2 = new JButton("Salir");
		boton2.setBounds(300, 220, 80, 20);
		boton2.addActionListener(this);
		
		calendario = new JCalendar();
		calendario.setBounds(220, 20, 250, 200);
		
		frame = new JFrame();
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(dnis);
		panel.add(dnima);
		panel.add(eticodmasaje);
		panel.add(eticl);
		panel.add(etima);
		panel.add(calendario);
		panel.add(textcodmasaje);
		panel.add(boton);
		panel.add(boton2);
		frame.add(panel);
		
		frame.setTitle("Crear Masaje");
		ImageIcon ImageIcon = new ImageIcon("./src/login/logomasaje.png");
        Image image = ImageIcon.getImage();
        frame.setIconImage(image);
        frame.setResizable(false);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==boton) {
			insertar();
			textcodmasaje.setText("");
			
		}
		
		if (e.getSource()==boton2) {
			frame.dispose();
		}
	}
	
	
	public static String GetFecha(String fecha) {
		String[] todo = fecha.split(" ");
		
		switch (todo[1]) {
        case "Jan":  todo[1] = "01";
                 break;
        case "Feb": todo[1] = "02";
                 break;
        case "Mar":  todo[1] = "03";
                 break;
        case "Apr":  todo[1] = "04";
                 break;
        case "May":  todo[1] = "05";
                 break;
        case "Jun":  todo[1] = "06";
                 break;
        case "Jul":  todo[1] = "07";
                 break;
        case "Aug":  todo[1] = "08";
                 break;
        case "Sep":  todo[1] = "09";
                 break;
        case "Oct": todo[1] = "10";
                 break;
        case "Nov": todo[1] = "11";
                 break;
        case "Dec": todo[1] = "12";
                 break;
    }
		
		String ffinal = todo[5] + "/" + todo[1] + "/" + todo[2];
		return ffinal;
	}
	
	public static void insertar() {
		Masaje ma = new Masaje(textcodmasaje.getText(),GetFecha(String.valueOf(calendario.getDate())),dnis.getSelectedItem().toString(),dnima.getSelectedItem().toString());
		ConectoMongo c = new ConectoMongo();
		c.bd().getCollection("masajes").insertOne(ma.TMasaje());

	}
	
	public void adddniclienete() {
		ConectoMongo c = new ConectoMongo();
		FindIterable<Document> iterable = c.bd().getCollection("cliente").find();		
		
		 iterable.forEach(new Block<Document>() {
	            @Override
	            public void apply(final Document document) {
	            	Cliente cli = new Cliente(document);
	            	dnis.addItem(cli.getDni());
	            }
	        }); 
	}
	
	public void adddnimasajista() {
		ConectoMongo c = new ConectoMongo();
		FindIterable<Document> iterable = c.bd().getCollection("masajistas").find();		
		
		 iterable.forEach(new Block<Document>() {
	            @Override
	            public void apply(final Document document) {
	            	Masajista ma = new Masajista(document);
	            	if (!ma.getDni().equalsIgnoreCase("root")) {
	            		dnima.addItem(ma.getDni());
	            	}
	            }
	        }); 
	}
	
	
}
