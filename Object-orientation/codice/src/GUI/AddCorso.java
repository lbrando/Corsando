package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DAO.CorsoDAO;
import ImplementazioniPostgresDAO.GetCorsoDAO;
import Modelli.Corso;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class AddCorso extends JDialog {

	private int mouseX, mouseY;
	private JTextField corsoName_field;

	private JTextField corsoTematica_field;
	private JTextField corsoDescrizione_field;
	
	//COSTRUTTORE DELLA CLASSE
	public AddCorso(String idutente) {
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 0, 47));
		JDialog addCorsoDialog = new JDialog();
		addCorsoDialog.getContentPane().setBackground(Color.WHITE);
		addCorsoDialog.setResizable(false);
		addCorsoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addCorsoDialog.setTitle("Aggiungi Corso.");
		addCorsoDialog.setUndecorated(true);
		addCorsoDialog.setBounds(100, 100, 525, 413);
		addCorsoDialog.getContentPane().setLayout(null);
		addCorsoDialog.setVisible(true);
		contentPanel.setBounds(0, 0, 525, 413);
		contentPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
		addCorsoDialog.getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel draggablePanel = new JPanel();
			draggablePanel.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					addCorsoDialog.setLocation(addCorsoDialog.getX() + e.getX() - mouseX, addCorsoDialog.getY() + e.getY() - mouseY);
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
			draggablePanel.setBounds(0, 0, 525, 20);
			contentPanel.add(draggablePanel);
			{
				JLabel exitButton = new JLabel("");
				exitButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/closered.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
				exitButton.setBounds(495, 0, 30, 20);
				draggablePanel.add(exitButton);
				exitButton.setHorizontalAlignment(SwingConstants.CENTER);
				exitButton.setForeground(Color.WHITE);
				exitButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
							addCorsoDialog.dispose();
						}
				});
				exitButton.setFont(new Font("Arial", Font.BOLD, 16));
			}
		}
		{
			JPanel corsoName_panel = new JPanel();
			corsoName_panel.setLayout(null);
			corsoName_panel.setBorder(null);
			corsoName_panel.setBackground(new Color(0, 0, 47));
			corsoName_panel.setBounds(115, 90, 309, 53);
			contentPanel.add(corsoName_panel);
			{
				corsoName_field = new JTextField();
				corsoName_field.setFont(new Font("Arial", Font.PLAIN, 16));
				corsoName_field.setColumns(10);
				corsoName_field.setBounds(10, 11, 215, 35);
				corsoName_panel.add(corsoName_field);
			}
			{
				JLabel corsoName_Label = new JLabel("Nome");
				corsoName_Label.setForeground(new Color(255, 255, 255));
				corsoName_Label.setFont(new Font("Arial", Font.BOLD, 12));
				corsoName_Label.setBounds(230, 0, 69, 53);
				corsoName_panel.add(corsoName_Label);
				corsoName_Label.setHorizontalTextPosition(SwingConstants.CENTER);
				corsoName_Label.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		{
			JPanel corsoTematica_panel = new JPanel();
			corsoTematica_panel.setLayout(null);
			corsoTematica_panel.setBorder(null);
			corsoTematica_panel.setBackground(new Color(0, 0, 47));
			corsoTematica_panel.setBounds(115, 144, 309, 53);
			contentPanel.add(corsoTematica_panel);
			{
				corsoTematica_field = new JTextField();
				corsoTematica_field.setFont(new Font("Arial", Font.PLAIN, 16));
				corsoTematica_field.setColumns(10);
				corsoTematica_field.setBounds(10, 11, 215, 35);
				corsoTematica_panel.add(corsoTematica_field);
			}
			{
				JLabel corsoTematica_Label = new JLabel("Tematica");
				corsoTematica_Label.setForeground(new Color(255, 255, 255));
				corsoTematica_Label.setBounds(235, 0, 64, 53);
				corsoTematica_panel.add(corsoTematica_Label);
				corsoTematica_Label.setFont(new Font("Arial", Font.BOLD, 12));
				corsoTematica_Label.setHorizontalTextPosition(SwingConstants.CENTER);
				corsoTematica_Label.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		
		JPanel corsoNumeroLez_panel = new JPanel();
		corsoNumeroLez_panel.setForeground(new Color(0, 0, 47));
		corsoNumeroLez_panel.setLayout(null);
		corsoNumeroLez_panel.setBorder(null);
		corsoNumeroLez_panel.setBackground(new Color(0, 0, 47));
		corsoNumeroLez_panel.setBounds(115, 254, 309, 53);
		contentPanel.add(corsoNumeroLez_panel);
		
		JLabel corsoNumeroLez_Label = new JLabel("Numero ");
		corsoNumeroLez_Label.setForeground(new Color(255, 255, 255));
		corsoNumeroLez_Label.setHorizontalTextPosition(SwingConstants.CENTER);
		corsoNumeroLez_Label.setHorizontalAlignment(SwingConstants.CENTER);
		corsoNumeroLez_Label.setFont(new Font("Arial", Font.BOLD, 12));
		corsoNumeroLez_Label.setBounds(235, 0, 68, 53);
		corsoNumeroLez_panel.add(corsoNumeroLez_Label);
		
		JSpinner spinner_NumeroLez = new JSpinner();
		spinner_NumeroLez.setBounds(21, 12, 172, 26);
		corsoNumeroLez_panel.add(spinner_NumeroLez);
		
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(null);
		panel_Title.setBorder(null);
		panel_Title.setBackground(new Color(0, 0, 47));
		panel_Title.setBounds(94, 21, 347, 57);
		contentPanel.add(panel_Title);
		
		JLabel lblAggiungiCorso = new JLabel("Aggiungi corso");
		lblAggiungiCorso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAggiungiCorso.setForeground(Color.WHITE);
		lblAggiungiCorso.setFont(new Font("Arial", Font.ITALIC, 31));
		lblAggiungiCorso.setBounds(8, 10, 331, 35);
		panel_Title.add(lblAggiungiCorso);
		{
						
						JButton okButton = new JButton("Aggiungi");
						okButton.setForeground(Color.BLACK);
						okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
						okButton.setBackground(Color.WHITE);
						okButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								okButton.setBackground(Color.DARK_GRAY);
							}
							@Override
							public void mouseExited(MouseEvent e) {
								okButton.setBackground(Color.WHITE);	
							}
						});
						okButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String nome = corsoName_field.getText();
								String tematica = corsoTematica_field.getText();
								String descrizione = corsoDescrizione_field.getText();
								int numerolezioni = spinner_NumeroLez.getComponentCount();
								
								boolean ok;
								
								CorsoDAO newcorso = new GetCorsoDAO();

								ok = newcorso.inserisciCorso(idutente, nome, tematica, descrizione, numerolezioni);
								
								if(ok == true) {
									JOptionPane.showMessageDialog(null, "Corso creato con successo!.");
									addCorsoDialog.dispose();
								}
							}
						});
						okButton.setBounds(204, 337, 141, 47);
						okButton.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
						contentPanel.add(okButton);
						okButton.setActionCommand("OK");
						getRootPane().setDefaultButton(okButton);
					}
		
		JPanel corsoDescrizione_panel = new JPanel();
		corsoDescrizione_panel.setLayout(null);
		corsoDescrizione_panel.setBorder(null);
		corsoDescrizione_panel.setBackground(new Color(0, 0, 47));
		corsoDescrizione_panel.setBounds(115, 198, 309, 53);
		contentPanel.add(corsoDescrizione_panel);
		
		corsoDescrizione_field = new JTextField();
		corsoDescrizione_field.setFont(new Font("Arial", Font.PLAIN, 16));
		corsoDescrizione_field.setColumns(10);
		corsoDescrizione_field.setBounds(10, 11, 215, 35);
		corsoDescrizione_panel.add(corsoDescrizione_field);
		
		JLabel corsoDescrizione_Label = new JLabel("Descrizione");
		corsoDescrizione_Label.setForeground(new Color(255, 255, 255));
		corsoDescrizione_Label.setHorizontalTextPosition(SwingConstants.CENTER);
		corsoDescrizione_Label.setHorizontalAlignment(SwingConstants.CENTER);
		corsoDescrizione_Label.setFont(new Font("Arial", Font.BOLD, 12));
		corsoDescrizione_Label.setBounds(235, 0, 68, 53);
		corsoDescrizione_panel.add(corsoDescrizione_Label);
		
		
	}
}
