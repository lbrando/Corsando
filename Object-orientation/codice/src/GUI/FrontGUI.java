package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import DAO.UtenteDAO;
import GUI.Applet.PanelButtonMouseAdapter;
import ImplementazioniPostgresDAO.GetUtenteDAO;

import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class FrontGUI {

	private JTextField Username_Field;

	private JTextField Cognome_Field;

	private int mouseX, mouseY;
	private JTextField Codice_Field;
	
//COSTRUTTORE
	public FrontGUI() {
		JFrame LogInWindow = new JFrame();
		LogInWindow.setUndecorated(true);
		LogInWindow.setResizable(false);
		LogInWindow.getContentPane().setBackground(Color.GRAY);
		LogInWindow.setForeground(Color.WHITE);
		LogInWindow.setBackground(new Color(255, 255, 255));
		LogInWindow.setVisible(true);
		LogInWindow.setTitle("Corsando");
		LogInWindow.setBounds(100, 100, 774, 488);
		LogInWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LogInWindow.getContentPane().setLayout(null);
		BorderFactory.createLineBorder(Color.black);
		
		JPanel draggablePanel = new JPanel();
		draggablePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				LogInWindow.setLocation(LogInWindow.getX() + e.getX() - mouseX, LogInWindow.getY() + e.getY() - mouseY);
			}
		});
		draggablePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		draggablePanel.setBackground(new Color(0, 0, 47));
		draggablePanel.setBounds(0, 0, 774, 20);
		LogInWindow.getContentPane().add(draggablePanel);
		draggablePanel.setLayout(null);
		
		JLabel exitButton = new JLabel("");
		exitButton.setBounds(744, -3, 30, 27);
		draggablePanel.add(exitButton);
		exitButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/closered.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		exitButton.setForeground(Color.WHITE);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Sei sicuro di voler uscire?", "Conferma", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		exitButton.setFont(new Font("Arial", Font.BOLD, 16));
		exitButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel minimizeButton = new JLabel("");
		minimizeButton.setBackground(new Color(0, 0, 128));
		minimizeButton.setBounds(717, -3, 30, 27);
		draggablePanel.add(minimizeButton);
		minimizeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LogInWindow.setExtendedState(JFrame.ICONIFIED);
			}
		});
		minimizeButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/minimize.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		minimizeButton.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeButton.setForeground(Color.WHITE);
		minimizeButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		JPanel LogInScreen = new JPanel();
		LogInScreen.setBackground(new Color(255, 255, 255));
		LogInScreen.setBorder(new LineBorder(Color.BLACK, 2));
		LogInScreen.setBounds(0, 19, 774, 469);
		LogInWindow.getContentPane().add(LogInScreen);
		LogInScreen.setLayout(null);
		
		JPanel User_panel = new JPanel();
		User_panel.setBackground(Color.WHITE);
		User_panel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		User_panel.setBounds(234, 164, 309, 53);
		LogInScreen.add(User_panel);
		User_panel.setLayout(null);
		
		Username_Field = new JTextField();
		Username_Field.setFont(new Font("Arial", Font.PLAIN, 16));
		Username_Field.setBounds(10, 11, 215, 35);
		User_panel.add(Username_Field);
		Username_Field.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Nome");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		usernameLabel.setBounds(235, 0, 68, 53);
		User_panel.add(usernameLabel);
		
		JPanel Cognome_panel = new JPanel();
		Cognome_panel.setBackground(Color.WHITE);
		Cognome_panel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		Cognome_panel.setBounds(234, 228, 309, 53);
		LogInScreen.add(Cognome_panel);
		Cognome_panel.setLayout(null);
		
		Cognome_Field = new JTextField();
		Cognome_Field.setFont(new Font("Arial", Font.PLAIN, 16));
		Cognome_Field.setBounds(10, 11, 215, 35);
		Cognome_panel.add(Cognome_Field);
		
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cognomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		cognomeLabel.setBounds(235, 0, 68, 53);
		Cognome_panel.add(cognomeLabel);
		
		
		JPanel Iscriviti_panel = new JPanel();
		Iscriviti_panel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		Iscriviti_panel.addMouseListener(new PanelButtonMouseAdapter(Iscriviti_panel) {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationGUI registrationPage = new RegistrationGUI();
			}
		});
		Iscriviti_panel.setFont(new Font("Arial", Font.PLAIN, 12));
		Iscriviti_panel.setBounds(22, 428, 133, 30);
		Iscriviti_panel.setBackground(Color.WHITE);
		LogInScreen.add(Iscriviti_panel);
		Iscriviti_panel.setLayout(null);
		
		JLabel iscrizioneLabel = new JLabel("Registrati qui");
		iscrizioneLabel.setBounds(0, 0, 133, 30);
		Iscriviti_panel.add(iscrizioneLabel);
		iscrizioneLabel.setForeground(Color.BLACK);
		iscrizioneLabel.setBackground(new Color(255, 255, 255));
		iscrizioneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iscrizioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton LogIn_Button = new JButton("Accedi");
		LogIn_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LogIn_Button.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				LogIn_Button.setBackground(Color.WHITE);	
			}
		});
		LogIn_Button.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		LogIn_Button.setBounds(615, 428, 133, 30);
		LogInScreen.add(LogIn_Button);
		LogIn_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = Username_Field.getText();
				String psd = Cognome_Field.getText();
				String codice = Codice_Field.getText();
				boolean ok;
				
				UtenteDAO login = new GetUtenteDAO();
				ok = login.logInDB(uname, psd, codice);
				
				if(ok == true) {
					JOptionPane.showMessageDialog(null, "Log in effettuato con successo!.");
					LogInWindow.setVisible(false);
					Applet mainPage = new Applet(uname);
				}else {
					JOptionPane.showMessageDialog(null, "Log in non riuscito, ritenta o registrati se non l'hai ancora fatto.");
				}
			}
		});
		
		LogIn_Button.setBackground(new Color(255, 255, 255));
		LogIn_Button.setForeground(new Color(0, 0, 0));
		LogIn_Button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel creditLabel = new JLabel("Totally delivered by: Lucia Brando");
		creditLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditLabel.setForeground(Color.WHITE);
		creditLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		creditLabel.setBounds(40, 11, 327, 44);
		LogInScreen.add(creditLabel);
		
		JPanel Codice_panel = new JPanel();
		Codice_panel.setLayout(null);
		Codice_panel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		Codice_panel.setBackground(Color.WHITE);
		Codice_panel.setBounds(234, 292, 309, 53);
		LogInScreen.add(Codice_panel);
		
		Codice_Field = new JTextField();
		Codice_Field.setFont(new Font("Arial", Font.PLAIN, 16));
		Codice_Field.setBounds(10, 11, 215, 35);
		Codice_panel.add(Codice_Field);
		
		JLabel codiceLabel = new JLabel("Codice");
		codiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		codiceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		codiceLabel.setBounds(235, 0, 74, 53);
		Codice_panel.add(codiceLabel);
		
		JLabel lblLoginP = new JLabel("");
		lblLoginP.setIcon(new ImageIcon(FrontGUI.class.getResource("/Immagini/logocorsando.png")));
		lblLoginP.setBounds(132, 41, 551, 206);
		LogInScreen.add(lblLoginP);
		
	}

	public class PanelButtonMouseAdapter extends MouseAdapter{

		JPanel panel;

		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel=panel;
		}
		
	
		@Override
		public void mouseClicked(MouseEvent e) {
			panel.setBackground(Color.DARK_GRAY);
		}
		
	
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(Color.WHITE);	
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(Color.DARK_GRAY);
		}
		

		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(Color.LIGHT_GRAY);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(Color.DARK_GRAY);
		}
	}
}
