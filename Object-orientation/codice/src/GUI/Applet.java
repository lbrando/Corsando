package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import Modelli.Utente;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.border.LineBorder;
import GUI.FrontGUI;
import java.awt.event.MouseMotionAdapter;

public class Applet {

	private PanelHome paneHome;
	
	private PanelLibrary paneLibrary;

	private PanelSearch paneSearch;
	
	private PanelStudenti paneStudents;

	private int mouseX, mouseY;

	public Applet(String uname) {
		Utente utente = new Utente(uname);
		
		JFrame AppWindow = new JFrame();
		AppWindow.setResizable(false);
		AppWindow.setUndecorated(true);
		AppWindow.getContentPane().setBackground(new Color(0, 0, 47));
		AppWindow.setTitle("Corsando");
		AppWindow.setVisible(true);
		AppWindow.setBounds(100, 100, 823, 616);
		AppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AppWindow.getContentPane().setLayout(null);
		
		paneHome = new PanelHome(utente.getCodice());
		paneHome.setBorder(new LineBorder(Color.BLACK, 2, true));
		paneHome.setBounds(0, 0, 486, 588);
		paneLibrary = new PanelLibrary(utente.getUsername(), utente.getCodice());
		paneLibrary.setBorder(new LineBorder(Color.BLACK, 2, true));
		paneLibrary.setBounds(0, 0, 486, 588);
		paneSearch = new PanelSearch(utente.getCodice());
		paneSearch.setBorder(new LineBorder(Color.BLACK, 2, true));
		paneSearch.setBounds(0, 0, 486, 588);
		paneStudents = new PanelStudenti(utente.getCodice());
		paneStudents.setBorder(new LineBorder(Color.BLACK, 2, true));
		paneStudents.setBounds(0, 0, 486, 588);
		
		
		JPanel draggablePanel = new JPanel();
		draggablePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				AppWindow.setLocation(AppWindow.getX() + e.getX() - mouseX, AppWindow.getY() + e.getY() - mouseY);
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
		draggablePanel.setBounds(-2, 0, 825, 20);
		AppWindow.getContentPane().add(draggablePanel);
		
		JLabel exitButton = new JLabel("");
		exitButton.setBounds(798, -2, 27, 24);
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
		
		exitButton.setHorizontalAlignment(SwingConstants.CENTER);
		exitButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel minimizeButton = new JLabel("");
		minimizeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AppWindow.setExtendedState(JFrame.ICONIFIED);
			}
		});
		minimizeButton.setBounds(769, -3, 30, 27);
		draggablePanel.add(minimizeButton);
		minimizeButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/minimize.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		minimizeButton.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeButton.setForeground(Color.WHITE);
		minimizeButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		JPanel panel_Menu = new JPanel();
		panel_Menu.setBackground(new Color(0, 0, 47));
		panel_Menu.setBounds(0, 0, 327, 616);
		AppWindow.getContentPane().add(panel_Menu);
		panel_Menu.setLayout(null);
		
		JPanel panel_Home = new JPanel();
		panel_Home.addMouseListener(new PanelButtonMouseAdapter(panel_Home) {
			@Override
			public void mouseReleased(MouseEvent e) {
				menuClicked(paneHome);
			}
		});
		panel_Home.setBounds(0, 251, 327, 65);
		panel_Menu.add(panel_Home);
		panel_Home.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		panel_Home.setBackground(new Color(255, 255, 255));
		panel_Home.setLayout(null);
		
		JLabel homeLabel = new JLabel("HOME");
		homeLabel.setBounds(0, 0, 327, 65);
		panel_Home.add(homeLabel);
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground(new Color(0, 0, 47));
		homeLabel.setFont(new Font("Arial", Font.BOLD, 27));
		
		JPanel panel_Library = new JPanel();
		panel_Library.addMouseListener(new PanelButtonMouseAdapter(panel_Library){
			@Override
			public void mouseReleased(MouseEvent e) {
				menuClicked(paneLibrary);
			}
		});
		panel_Library.setBounds(0, 314, 327, 65);
		panel_Menu.add(panel_Library);
		panel_Library.setBackground(new Color(255, 255, 255));
		panel_Library.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		panel_Library.setLayout(null);
		
		JLabel libraryLabel = new JLabel("LIBRARY");
		libraryLabel.setBounds(0, 0, 327, 65);
		panel_Library.add(libraryLabel);
		libraryLabel.setBackground(new Color(255, 255, 255));
		libraryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		libraryLabel.setForeground(new Color(0, 0, 47));
		libraryLabel.setFont(new Font("Arial", Font.BOLD, 27));
		
		JPanel panel_Search = new JPanel();
		panel_Search.addMouseListener(new PanelButtonMouseAdapter(panel_Search){
			@Override
			public void mouseReleased(MouseEvent e) {
				menuClicked(paneSearch);
			}
		});
		panel_Search.setBounds(0, 441, 327, 65);
		panel_Menu.add(panel_Search);
		panel_Search.setBackground(new Color(255, 255, 255));
		panel_Search.setBorder(new LineBorder(new Color(0, 64, 128)));
		panel_Search.setLayout(null);
		
		JLabel searchLabel = new JLabel("SEARCH");
		searchLabel.setBounds(0, 0, 327, 65);
		panel_Search.add(searchLabel);
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setForeground(new Color(0, 0, 47));
		searchLabel.setFont(new Font("Arial", Font.BOLD, 27));

		
		JPanel paneMainContent = new JPanel();
		paneMainContent.setBounds(332, 24, 486, 588);
		AppWindow.getContentPane().add(paneMainContent);
		paneMainContent.setLayout(null);
		
		paneMainContent.add(paneHome);
		paneMainContent.add(paneHome);
		paneMainContent.add(paneLibrary);
		paneMainContent.add(paneSearch);
		paneMainContent.add(paneStudents);
		
		menuClicked(paneHome);
		
		JPanel panel_welcome = new JPanel();
		panel_welcome.setBorder(null);
		panel_welcome.setBackground(new Color(0, 0, 47));
		panel_welcome.setBounds(0, 225, 327, 28);
		panel_Menu.add(panel_welcome);
		panel_welcome.setLayout(null);
		
		
		JLabel welcomeLabel = new JLabel("Benvenuto:");
		welcomeLabel.setBounds(10, -19, 101, 68);
		panel_welcome.add(welcomeLabel);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setBackground(Color.WHITE);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel userLabel = new JLabel(utente.getUsername());
		userLabel.setForeground(new Color(255, 255, 255));
		userLabel.setBounds(98, -4, 106, 39);
		panel_welcome.add(userLabel);
		userLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel creditLabel = new JLabel("App delivered by: Lucia Brando");
		creditLabel.setBounds(0, 572, 327, 44);
		panel_Menu.add(creditLabel);
		creditLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditLabel.setForeground(Color.WHITE);
		creditLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_SignOut = new JPanel();
		panel_SignOut.setBounds(10, 541, 86, 36);
		panel_Menu.add(panel_SignOut);
		panel_SignOut.addMouseListener(new PanelButtonMouseAdapter(panel_SignOut) {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(JOptionPane.showConfirmDialog(null, "Sei sicuro di voler uscire?", "Conferma", JOptionPane.YES_NO_OPTION) == 0) {
					FrontGUI FrontGUI = new FrontGUI();
					AppWindow.setVisible(false);
					AppWindow.dispose();	
				}
			}
		});
		panel_SignOut.setBackground(new Color(255, 255, 255));
		panel_SignOut.setBorder(null);
		panel_SignOut.setLayout(null);
		
		JLabel signOutLabel = new JLabel("SIGN OUT");
		signOutLabel.setBounds(0, 0, 86, 36);
		panel_SignOut.add(signOutLabel);
		signOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signOutLabel.setForeground(new Color(0, 0, 47));
		signOutLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
		JPanel panel_Iscritti = new JPanel();
		panel_Iscritti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				menuClicked(paneStudents);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_Iscritti.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panel_Iscritti.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_Iscritti.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_Iscritti.setBackground(Color.WHITE);
			}
		});
		panel_Iscritti.setLayout(null);
		panel_Iscritti.setBorder(new LineBorder(new Color(0, 64, 128)));
		panel_Iscritti.setBackground(Color.WHITE);
		panel_Iscritti.setBounds(0, 376, 327, 65);
		panel_Menu.add(panel_Iscritti);
		
		JLabel lblIscritti = new JLabel("ISCRITTI");
		lblIscritti.setHorizontalAlignment(SwingConstants.CENTER);
		lblIscritti.setForeground(new Color(0, 0, 47));
		lblIscritti.setFont(new Font("Arial", Font.BOLD, 27));
		lblIscritti.setBounds(0, 0, 327, 65);
		panel_Iscritti.add(lblIscritti);
	}
	
	public void menuClicked(JPanel panel) {
		paneHome.setVisible(false);
		paneLibrary.setVisible(false);
		paneSearch.setVisible(false);
		paneStudents.setVisible(false);
		panel.setVisible(true);
	}

	public class PanelButtonMouseAdapter extends MouseAdapter{

		JPanel panel;

		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel=panel;
		}
	
		@Override
		public void mouseClicked(MouseEvent e) {
			panel.setBackground(Color.LIGHT_GRAY);
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(Color.WHITE);	
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(Color.LIGHT_GRAY);
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(Color.LIGHT_GRAY);
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(Color.WHITE);
		}
	}
}
