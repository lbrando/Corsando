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
import ImplementazioniPostgresDAO.GetCorsoDAO;
import ImplementazioniPostgresDAO.GetLezioniDAO;
import Modelli.Corso;
import Modelli.Lezione;

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

public class PanelSearch extends JPanel {

	private JTextField searchField;

	private int id_lezione;

	private String nome_lezione = null;

	private ArrayList<Corso> list = new ArrayList<Corso>();

	DefaultTableModel modelTable = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	String headers[] = { "Nome", "Descrizione", "Tematica", "Numero Lezioni"};

	private JTable table = new JTable();
	public PanelSearch(String id_utente) {
		setBackground(new Color(0, 0, 47));
		setBounds(0, 0, 481, 592);
		setLayout(null);
		
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(null);
		panel_Title.setBorder(null);
		panel_Title.setBackground(new Color(0, 0, 47));
		panel_Title.setBounds(68, 0, 347, 57);
		add(panel_Title);
		
		JLabel lblRicercaCorsi = new JLabel("Ricerca corsi");
		lblRicercaCorsi.setHorizontalAlignment(SwingConstants.CENTER);
		lblRicercaCorsi.setForeground(Color.WHITE);
		lblRicercaCorsi.setFont(new Font("Arial", Font.ITALIC, 31));
		lblRicercaCorsi.setBounds(8, 10, 331, 35);
		panel_Title.add(lblRicercaCorsi);
		
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
		tipoQueryBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Nome corso", "Tematica"}));
		tipoQueryBox.setBounds(150, 20, 176, 53);
		searchPanel.add(tipoQueryBox);
		
		JButton searchButton = new JButton("Ricerca");
		searchButton.setBackground(new Color(255, 255, 255));
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
		searchButton.setBorder(new LineBorder(Color.BLACK, 2, true));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int box = tipoQueryBox.getSelectedIndex();
				String nome = searchField.getText(); 
				
				CorsoDAO t = new GetCorsoDAO();
				
				int grandezza;
				
				switch (box) {
				case 1:

				list = t.ricercaCorsoNome(nome, id_utente);
				
				grandezza = list.size();
				
				table = new JTable();
				modelTable.setRowCount(0);
				for (int i = 0; i < grandezza; i++) {
					modelTable.addRow(new Object[] { String.valueOf(list.get(i).getNomeCorso()),
							String.valueOf(list.get(i).getDescrizione()),
							String.valueOf(list.get(i).getTematica()),
							String.valueOf(list.get(i).getNumeroLezioni())
					});
					}
				
				break;
				case 2:
					
					list = t.ricercaCorsoTematica(nome, id_utente);
					
					grandezza = list.size();
					
					table = new JTable();
					modelTable.setRowCount(0);
					for (int i = 0; i < grandezza; i++) {
						modelTable.addRow(new Object[] { String.valueOf(list.get(i).getNomeCorso()),
								String.valueOf(list.get(i).getDescrizione()),
								String.valueOf(list.get(i).getTematica()),
								String.valueOf(list.get(i).getNumeroLezioni())
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
