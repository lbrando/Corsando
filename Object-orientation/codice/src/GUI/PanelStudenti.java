package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.CorsoDAO;
import DAO.LezioneDAO;
import DAO.StudenteDAO;
import ImplementazioniPostgresDAO.GetCorsoDAO;
import ImplementazioniPostgresDAO.GetLezioniDAO;
import ImplementazioniPostgresDAO.GetStudentiDAO;
import Modelli.Corso;
import Modelli.Lezione;
import Modelli.Studente;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelStudenti extends JPanel {

	private JTextField searchField;

	private int id_lezione;

	private String nome_lezione = null;

	private ArrayList<Studente> list = new ArrayList<Studente>();

	DefaultTableModel modelTable = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	String headers[] = { "Nome", "Cognome", "Matricola"};

	private JTable table = new JTable();

	//COSTRUTTORE
	public PanelStudenti(String id_utente) {
		setBackground(new Color(0, 0, 47));
		setBounds(0, 0, 481, 592);
		setLayout(null);
				
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(null);
		panel_Title.setBorder(null);
		panel_Title.setBackground(new Color(0, 0, 47));
		panel_Title.setBounds(68, 0, 347, 57);
		add(panel_Title);
		
		JLabel lblGestStud = new JLabel("Gestione studenti");
		lblGestStud.setBackground(new Color(255, 255, 255));
		lblGestStud.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestStud.setForeground(new Color(255, 255, 255));
		lblGestStud.setFont(new Font("Arial", Font.ITALIC, 31));
		lblGestStud.setBounds(8, 10, 331, 35);
		panel_Title.add(lblGestStud);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBackground(new Color(0, 0, 47));
		searchPanel.setBounds(2, 60, 477, 180);
		add(searchPanel);
		
		searchField = new JTextField();
		searchField.setBorder(new LineBorder(Color.BLACK, 2, true));
		searchField.setBounds(150, 75, 176, 35);
		searchPanel.add(searchField);
		searchField.setColumns(10);
		
		JComboBox tipoQueryBox = new JComboBox();
		tipoQueryBox.setBorder(new LineBorder(Color.BLACK, 2, true));
		tipoQueryBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Nome Studente", "Corso di appartenenza"}));
		tipoQueryBox.setBounds(150, 20, 176, 53);
		searchPanel.add(tipoQueryBox);
		
		JButton searchButton = new JButton("Ricerca");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchButton.setForeground(Color.BLACK);
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchButton.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				searchButton.setBackground(Color.WHITE);	
			}
		});
		searchButton.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int box = tipoQueryBox.getSelectedIndex();
				String nome = searchField.getText(); 
				
				StudenteDAO t = new GetStudentiDAO();
				
				int grandezza;
				
				switch (box) {
				case 1:

				list = t.ricercaStudenteNome(nome);
				
				grandezza = list.size();
				
				table = new JTable();
				modelTable.setRowCount(0);
				for (int i = 0; i < grandezza; i++) {
					modelTable.addRow(new Object[] { String.valueOf(list.get(i).getNomeStudente()),
							String.valueOf(list.get(i).getCognomeStudente()),
							String.valueOf(list.get(i).getMatricola())});
					}
				
				break;
				case 2:
					
					list = t.ricercaStudenteCorso(nome);
					
					grandezza = list.size();
					
					table = new JTable();
					modelTable.setRowCount(0);
					for (int i = 0; i < grandezza; i++) {
						modelTable.addRow(new Object[] { String.valueOf(list.get(i).getNomeStudente()),
								String.valueOf(list.get(i).getCognomeStudente()),
								String.valueOf(list.get(i).getMatricola())
						});
						}
					
				break;
				default:
				JOptionPane.showMessageDialog(null, "Non hai selezionato un tipo di ricerca, selezionane uno.");
				}
			}
		});
		
		modelTable.setColumnIdentifiers(headers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setBorder(new LineBorder(Color.GRAY, 2));
		scrollPane.setBounds(2, 241, 477, 344);
		add(scrollPane);
		scrollPane.setViewportView(table);
		
		table.setGridColor(Color.BLACK);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(modelTable);
		table.setRowHeight(45);
		
		searchButton.setBounds(188, 122, 98, 29);
		searchPanel.add(searchButton);
	}
}
