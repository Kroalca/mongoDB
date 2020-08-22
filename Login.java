package login;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login{
    public static void main(String[] args) {
        new MainLogin();
    }
}

class MainLogin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	boolean  entra=false;
	JButton register;
	JButton entry;
	static JTextField userName;
	static JPasswordField password;


	public MainLogin(){
        ImageIcon imageIcon = new ImageIcon("./src/login/logomasaje.png");
        Image image = imageIcon.getImage();
        setIconImage(image);
        setTitle("Art Thai Massage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setSize(400,180);
        setLocationRelativeTo(null);
        setResizable(false);
        components();
        setVisible(true);
    }
	
	

    private void components(){
        userName = new JTextField(15);
        password = new JPasswordField(15);
        password.setEchoChar('*');
        register = new JButton("Nuevo Usuario");
		register.addActionListener(this);
        entry = new JButton("Entrar");
		entry.addActionListener(this);
        grid(new JLabel("User:"),0,0);
        grid(userName,2,0);
        grid(new JLabel("Password:"),0,2);
        grid(password,2,2);
        grid(register,0,4);
        grid(entry,2,4);
        grid(new JLabel(" "),1,0);
        grid(new JLabel(" "),1,1);
        grid(new JLabel(" "),1,3);
    }

    private void grid(Component component, int x, int y){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        add(component, gridBagConstraints);
    }
    

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==entry) {
			if(comprobar()) {
				new Masajes();
			}else {
				JOptionPane.showMessageDialog(null, "Error 404: El usuario o contraseña no coinciden", "Error 404: Usuario no valido", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getSource()==register) {
			new AddUsers();
		}
	}
    public boolean comprobar() {
    	ConectoMongo c = new ConectoMongo();
		FindIterable<Document> iterable = c.bd().getCollection("masajistas").find();	
		entra=false;
		
		 iterable.forEach(new Block<Document>() {
	            @Override
	            public void apply(final Document document) {
	            	Masajista m = new Masajista(document);
	            	if ((m.getDni().equalsIgnoreCase(userName.getText().trim())) && (m.getContraseña().equals(pass(GetPassword())))) {
	            		entra=true;
	            		ConectoMongo.user=userName.getText().trim();
	            	}
	            }
	        }); 
		 return this.entra;
    }
    
    public static String GetPassword() {
    	char[] pass = password.getPassword();
    	String password = "";
    	
    	for (int i=0 ; i<pass.length;i++) {
    		password = password + String.valueOf(pass[i]);
    	}
    	
    	return password.trim();
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

