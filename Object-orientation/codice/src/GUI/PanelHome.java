package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.LezioneDAO;
import ImplementazioniPostgresDAO.GetLezioniDAO;
import Modelli.Lezione;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class PanelHome extends JPanel {

	private int id_lezione;

	private String nome_lezione = null;

	private ArrayList<Lezione> lista_lezione = new ArrayList<Lezione>();

	DefaultTableModel modelLezioni = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	String headersLezioni[] = { "Nome", "Descrizione", "Durata", "Data", "id_lezione", "corso_appartenenza"};

	public PanelHome(String id_utente) {
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 481, 592);
		setLayout(null);
		
		JLabel lblLabelHomeP = new JLabel("");
		lblLabelHomeP.setIcon(new ImageIcon(PanelHome.class.getResource("/Immagini/homeee.png")));
		lblLabelHomeP.setBounds(10, 11, 468, 511);
		add(lblLabelHomeP);
		modelLezioni.setColumnIdentifiers(headersLezioni);
	}
}
