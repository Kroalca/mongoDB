package login;

import javax.swing.*;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Masajes  extends JFrame implements ActionListener{
   
	private static final long serialVersionUID = 1L;
	 JButton addmassage,addclient,refresh;
	 String[] colum = {"Cod_Masaje","Fecha","DNI_Cliente"};
	 static int i;
	 static  int column = 0;
     JTable table = new JTable(CrearMatriz(),colum);
	 

	public Masajes(){
        ImageIcon imageIcon = new ImageIcon("./src/login/logomasaje.png");
        Image image = imageIcon.getImage();
        setIconImage(image);
        setTitle("Art Thai Massage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(650,380);
        setLocationRelativeTo(null);
        setResizable(false);
        components();
        setVisible(true);
    }

    private void components(){
        add(west(), BorderLayout.WEST);
        add(north(), BorderLayout.NORTH);
        add(south(), BorderLayout.SOUTH);
        add(center(), BorderLayout.CENTER);
    }

    private JPanel north(){
        JPanel north = new JPanel();
        JLabel title = new JLabel("Art Thai Massage");
        north.add(title);
        return north;
    }

    private JPanel south(){
        JPanel south = new JPanel();
        addmassage = new JButton("Masaje");
		addmassage.addActionListener(this);
        addclient = new JButton("Cliente");
		addclient.addActionListener(this);
        refresh = new JButton("Actualizar");
		refresh.addActionListener(this);
        south.add(addmassage);
        south.add(addclient);
        south.add(refresh);
        return south;
    }

    private JPanel west(){
        JPanel west = new JPanel();
        JLabel label = new JLabel();
        label.setSize(30,30);
        label.setIcon(new ImageIcon("./src/login/logo pequeño.png"));
        west.add(label);
        return west;
    }

    private JScrollPane center(){
    	//DefaultTableModel model = new DefaultTableModel();
        JPanel center = new JPanel();
        table.setEnabled(false);
        JScrollPane adjust = new JScrollPane(table);
        adjust.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        center.add(adjust);
        JScrollPane scrollPane = new JScrollPane(center);
        return scrollPane;
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==addmassage) {
			new addmasajes();
		}
		
		if (e.getSource()==addclient) {
			new addclient();
		}
		
		if (e.getSource()==refresh) {
			dispose();
			new Masajes();
	
		}
	}
    
    public int ContarDatos() {
    	column=0;
    	ConectoMongo c = new ConectoMongo();
		FindIterable<Document> iterable = c.bd().getCollection("masajes").find();	
		
		 iterable.forEach(new Block<Document>() {
	            @Override
	            public void apply(final Document document) {
	            	Masaje m = new Masaje(document);
	            	if (m.getDnimasajista().equals(ConectoMongo.user)) {
	            		column ++;
	            	}
	            }
	        });
		 return column;
    }
    
    public String[][] CrearMatriz(){
    	String[][] datos = new String[ContarDatos()][3];
    	i=0;
    	
    		ConectoMongo c = new ConectoMongo();
    		FindIterable<Document> iterable = c.bd().getCollection("masajes").find();	
    		
    		 iterable.forEach(new Block<Document>() {
    	            @Override
    	            public void apply(final Document document) {
    	            	Masaje m = new Masaje(document);
    	            	if (m.getDnimasajista().equals(ConectoMongo.user)) {
    	            			datos[i][0] = m.getCodmasaje();
    	            			datos[i][1] = m.getFecha();
    	            			datos[i][2] = m.getDnicliente();
    	            			i++;
    	            		}
    	            }
    	        });
		 return datos;

    }
    

}
