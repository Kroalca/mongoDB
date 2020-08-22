package login;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class addclient extends JFrame implements ActionListener  {
	
	private static final long serialVersionUID = 1L;
	JLabel etidni, etinombre,etiapellido,etitlf,eticontraseña;
	static JTextField texdni, texnombre,texapellido,textlf;
	static JPasswordField password;
	JButton boton,boton2;
	static JCalendar calendario;
	JFrame frame;
	JPanel panel;
	
	public addclient() {
		
		etidni = new JLabel("DNI:");
		etidni.setBounds(10, 20, 80, 20);
		
		etinombre = new JLabel("Nombre:");
		etinombre.setBounds(10, 50, 80, 20);
		
		etiapellido = new JLabel("Apellidos:");
		etiapellido.setBounds(10, 80, 80, 20);
		
		etitlf = new JLabel("Telefono:");
		etitlf.setBounds(10, 140, 80, 20);
		
		eticontraseña = new JLabel("Contraseña:");
		eticontraseña.setBounds(10, 110, 80, 20);
		
		texdni = new JTextField();
		texdni.setBounds(100, 20, 80, 20);
		
		texnombre = new JTextField();
		texnombre.setBounds(100, 50, 80, 20);
		
		texapellido = new JTextField();
		texapellido.setBounds(100, 80, 80, 20);
		
		textlf = new JTextField();
		textlf.setBounds(100, 140, 80, 20);
		
		password = new JPasswordField();
		password.setBounds(100, 110, 80, 20);
		
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
		panel.add(etinombre);
		panel.add(etidni);
		panel.add(etiapellido);
		panel.add(etitlf);
		panel.add(eticontraseña);
		panel.add(calendario);
		panel.add(texnombre);
		panel.add(texdni);
		panel.add(texapellido);
		panel.add(textlf);
		panel.add(password);
		panel.add(boton);
		panel.add(boton2);
		frame.add(panel);
		
		frame.setTitle("Crear Cliente");
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
			texdni.setText("");
			texnombre.setText("");
			texapellido.setText("");
			textlf.setText("");
			password.setText("");
		}
		
		if (e.getSource()==boton2) {
			frame.dispose();
		}
	}
	
	
	public static String GetPassword() {
    	char[] pass = password.getPassword();
    	String password = "";
    	
    	for (int i=0 ; i<pass.length;i++) {
    		password = password + String.valueOf(pass[i]);
    	}
    	
    	return password.trim();
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
		Cliente cl = new Cliente(texdni.getText(),texnombre.getText(),texapellido.getText(), textlf.getText() ,GetFecha(String.valueOf(calendario.getDate())),pass(GetPassword()));
		ConectoMongo c = new ConectoMongo();
		c.bd().getCollection("cliente").insertOne(cl.TCliente());

	}
	
	public static String pass(String password) {
		String key = "92AE31A79FEEB2A3"; 
		StringEncrypt e = new StringEncrypt();

		try {
			String ClaveEncriptada=e.encrypt(key, password);
			return ClaveEncriptada;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return password;
		
	}
	
}
