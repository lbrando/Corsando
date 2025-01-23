package GUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import DAO.LezioneDAO;
import ImplementazioniPostgresDAO.GetLezioniDAO;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Timestamp;
import java.time.LocalTime;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RimuoviLezione extends JDialog {
 
	private int mouseX, mouseY;

	private Timestamp ts = new Timestamp(System.currentTimeMillis());


	public RimuoviLezione(String id_utente, int id_lezione, String nome_lezione, int grandezza_corso) {
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 0, 47));
		JDialog rimuoviLezione_panel = new JDialog();
		rimuoviLezione_panel.getContentPane().setBackground(Color.WHITE);
		rimuoviLezione_panel.setResizable(false);
		rimuoviLezione_panel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		rimuoviLezione_panel.setTitle("Aggiungi Corso.");
		rimuoviLezione_panel.setUndecorated(true);
		rimuoviLezione_panel.setBounds(100, 100, 450, 300);
		rimuoviLezione_panel.getContentPane().setLayout(null);
		rimuoviLezione_panel.setVisible(true);
		contentPanel.setBounds(0, 0, 450, 300);
		contentPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
		rimuoviLezione_panel.getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel draggablePanel = new JPanel();
			draggablePanel.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					rimuoviLezione_panel.setLocation(rimuoviLezione_panel.getX() + e.getX() - mouseX, rimuoviLezione_panel.getY() + e.getY() - mouseY);
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
			draggablePanel.setBounds(0, 0, 450, 20);
			contentPanel.add(draggablePanel);
			{
				JLabel exitButton = new JLabel("");
				exitButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/closered.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
				exitButton.setBounds(420, 0, 30, 20);
				draggablePanel.add(exitButton);
				exitButton.setHorizontalAlignment(SwingConstants.CENTER);
				exitButton.setForeground(Color.WHITE);
				exitButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
							rimuoviLezione_panel.dispose();
						}
				});
				exitButton.setFont(new Font("Arial", Font.BOLD, 16));
			}
		}
		
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(null);
		panel_Title.setBorder(null);
		panel_Title.setBackground(new Color(0, 0, 47));
		panel_Title.setBounds(52, 22, 347, 57);
		contentPanel.add(panel_Title);
		
		JLabel lblTitle = new JLabel("Lezione: '"+ nome_lezione + "'");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 29));
		lblTitle.setBounds(8, 10, 331, 35);
		panel_Title.add(lblTitle);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				boolean ok;
				
				LezioneDAO t = new GetLezioniDAO();
				System.out.println("Rimuovere:" + id_lezione);
				ok = t.rimuoviLezione(id_lezione);
				
				if(ok == true) {
					JOptionPane.showMessageDialog(null, "La lezione '"+ nome_lezione +"' e' stata rimossa dal corso.");
					rimuoviLezione_panel.dispose();
				}
			}
		});
		btnRimuovi.setToolTipText("Rimuovi la lezione dal corso.");
		btnRimuovi.setBorder(new LineBorder(Color.BLACK, 2, true));
		btnRimuovi.setBackground(Color.WHITE);
		btnRimuovi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRimuovi.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRimuovi.setBackground(Color.WHITE);	
			}
		});
		btnRimuovi.setBounds(231, 172, 168, 40);
		contentPanel.add(btnRimuovi);
		
		JLabel lblEliminareDefinitivamenteLa = new JLabel("Eliminare definitivamente la lezione?");
		lblEliminareDefinitivamenteLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminareDefinitivamenteLa.setForeground(Color.WHITE);
		lblEliminareDefinitivamenteLa.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEliminareDefinitivamenteLa.setBounds(32, 90, 389, 35);
		contentPanel.add(lblEliminareDefinitivamenteLa);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setToolTipText("Annulla rimuovi la lezione dal corso.");
		btnAnnulla.setBorder(new LineBorder(Color.BLACK, 2, true));
		btnAnnulla.setBackground(Color.WHITE);
		btnAnnulla.setBounds(36, 172, 168, 40);
		contentPanel.add(btnAnnulla);
	}
}
