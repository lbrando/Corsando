package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import DAO.UtenteDAO;
import ImplementazioniPostgresDAO.GetUtenteDAO;

import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class RegistrationGUI {

	int mouseX, mouseY;

	private JTextField usernameField;

	private JTextField cognomeField;

	private JTextField codiceField;

	//COSTRUTTORE DELLA CLASSE REGISTRATIONGUI
	public RegistrationGUI(){
		JFrame RegistrationFrame = new JFrame();
		RegistrationFrame.setUndecorated(true);
		RegistrationFrame.setResizable(false);
		RegistrationFrame.setTitle("Registration");
		RegistrationFrame.setVisible(true);
		RegistrationFrame.setBounds(100, 100, 568, 624);
		RegistrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		RegistrationFrame.getContentPane().setLayout(null);
		
		JPanel draggablePanel = new JPanel();
		draggablePanel.setBounds(0, 0, 568, 20);
		RegistrationFrame.getContentPane().add(draggablePanel);
		draggablePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				RegistrationFrame.setLocation(RegistrationFrame.getX() + e.getX() - mouseX, RegistrationFrame.getY() + e.getY() - mouseY);
			}
		});
		draggablePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		draggablePanel.setLayout(null);
		draggablePanel.setBackground(new Color(0, 0, 47));
		
		JLabel exitButton = new JLabel("");
		exitButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/closered.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		exitButton.setHorizontalAlignment(SwingConstants.CENTER);
		exitButton.setForeground(Color.WHITE);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Sei sicuro di voler uscire?", "Conferma", JOptionPane.YES_NO_OPTION) == 0) {
					RegistrationFrame.dispose();
				}
			}
		});
		exitButton.setFont(new Font("Arial", Font.BOLD, 16));
		exitButton.setBounds(541, -3, 27, 24);
		draggablePanel.add(exitButton);
		
		JLabel minimizeButton = new JLabel("");
		minimizeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationFrame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		minimizeButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/minimize.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		minimizeButton.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeButton.setForeground(Color.WHITE);
		minimizeButton.setFont(new Font("Arial", Font.BOLD, 16));
		minimizeButton.setBounds(512, -3, 30, 27);
		draggablePanel.add(minimizeButton);
		
		JPanel panelRegistration = new JPanel();
		panelRegistration.setBorder(new LineBorder(new Color(0, 0, 47), 2, true));
		panelRegistration.setBackground(new Color(255, 255, 255));
		panelRegistration.setBounds(0, 20, 568, 604);
		RegistrationFrame.getContentPane().add(panelRegistration);
		panelRegistration.setLayout(null);
		
		JPanel UsernamePanel = new JPanel();
		UsernamePanel.setLayout(null);
		UsernamePanel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		UsernamePanel.setBackground(Color.WHITE);
		UsernamePanel.setBounds(122, 222, 309, 53);
		panelRegistration.add(UsernamePanel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameField.setColumns(10);
		usernameField.setBounds(10, 11, 215, 35);
		UsernamePanel.add(usernameField);
		
		JLabel usernameLabel = new JLabel("Nome");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(225, 2, 68, 53);
		UsernamePanel.add(usernameLabel);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JPanel CognomePanel = new JPanel();
		CognomePanel.setLayout(null);
		CognomePanel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		CognomePanel.setBackground(Color.WHITE);
		CognomePanel.setBounds(122, 298, 309, 53);
		panelRegistration.add(CognomePanel);
		
		cognomeField = new JTextField();
		cognomeField.setFont(new Font("Arial", Font.PLAIN, 16));
		cognomeField.setBounds(10, 11, 215, 35);
		CognomePanel.add(cognomeField);
		
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cognomeLabel.setBounds(225, 2, 68, 53);
		CognomePanel.add(cognomeLabel);
		cognomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JPanel CodicePanel = new JPanel();
		CodicePanel.setLayout(null);
		CodicePanel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		CodicePanel.setBackground(Color.WHITE);
		CodicePanel.setBounds(122, 370, 309, 53);
		panelRegistration.add(CodicePanel);
		
		codiceField = new JTextField();
		codiceField.setFont(new Font("Arial", Font.PLAIN, 16));
		codiceField.setColumns(10);
		codiceField.setBounds(10, 11, 215, 35);
		CodicePanel.add(codiceField);
		
		JLabel codiceLabel = new JLabel("Codice");
		codiceLabel.setBounds(235, 4, 46, 53);
		CodicePanel.add(codiceLabel);
		codiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		codiceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

		JButton Register_Button = new JButton("REGISTRATI");
		Register_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String eccoUs = usernameField.getText();
				String eccoCogn = cognomeField.getText();
				String eccoCodice = codiceField.getText();
		
				boolean ok;
					
				UtenteDAO registrazione = new GetUtenteDAO();
				ok = registrazione.registerInDB(eccoUs, eccoCogn, eccoCodice);
				
				if(ok == true) {
					JOptionPane.showMessageDialog(null, "Registrazione effettuata con successo!.");
					RegistrationFrame.dispose();
				}
			}
		});
		
		Register_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Register_Button.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Register_Button.setBackground(Color.WHITE);	
			}
		});
		Register_Button.setForeground(new Color(0, 0, 0));
		Register_Button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Register_Button.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		Register_Button.setBackground(Color.WHITE);
		Register_Button.setBounds(169, 510, 214, 62);
		panelRegistration.add(Register_Button);
		
		JLabel lblRegistrationP = new JLabel("");
		lblRegistrationP.setIcon(new ImageIcon(RegistrationGUI.class.getResource("/Immagini/registrazione.png")));
		lblRegistrationP.setBounds(31, 11, 506, 401);
		panelRegistration.add(lblRegistrationP);
	}
}
