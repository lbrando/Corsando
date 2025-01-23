package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DAO.LibreriaDAO;
import DAO.CorsoDAO;
import DAO.LezioneDAO;
import ImplementazioniPostgresDAO.GetLezioniDAO;
import ImplementazioniPostgresDAO.GetLibreriaDAO;
import ImplementazioniPostgresDAO.GetCorsoDAO;
import Modelli.Libreria;
import Modelli.Corso;
import Modelli.Lezione;
import Modelli.Utente;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import java.awt.Component;
import javax.swing.DebugGraphics;


public class PanelLibrary extends JPanel {
	
	private int index_f;
	private String index;
	
	private Object obj_cors;
	
	private int id_corso;
	
	private Libreria libs;
	
	
	private ArrayList<Corso> lista_corso = new ArrayList<Corso>();
	
	
	private ArrayList<Lezione> list = new ArrayList<Lezione>();
	
	
	DefaultTableModel modelTable = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	
	String headersCorsi[] = { "Nome", "Tematica", "Descrizione", "Lezioni"};
	
	
	private JTable tableCorsi = new JTable();
	
	
	
	DefaultTableModel modelTableLezioni = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	
	String headersLezioni[] = {  "Nome", "Durata", "Data", "Descrizione"};
	
	
	private JTable tableLezioni = new JTable();
	

	public PanelLibrary(String username, String id_utente) {
		Utente u = new Utente(username);
		
		setBackground(new Color(0, 0, 47));
		setBounds(0, 0, 481, 592);
		setLayout(null);
				
		JPanel panel_Title = new JPanel();
		panel_Title.setForeground(new Color(0, 0, 47));
		panel_Title.setBackground(new Color(0, 0, 47));
		panel_Title.setBorder(new LineBorder(new Color(0, 0, 47), 2, true));
		panel_Title.setBounds(68, 0, 347, 57);
		add(panel_Title);
		panel_Title.setLayout(null);
		
		JLabel labelBenvenuto = new JLabel("Libreria di "+ username);
		labelBenvenuto.setBounds(10, 11, 331, 35);
		panel_Title.add(labelBenvenuto);
		labelBenvenuto.setForeground(Color.WHITE);
		labelBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		labelBenvenuto.setFont(new Font("Arial", Font.ITALIC, 31));
		
		JPanel plusPanel = new JPanel();
		plusPanel.setToolTipText("Aggiungi Corso.");
		plusPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				plusPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				plusPanel.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				plusPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				plusPanel.setBackground(Color.WHITE);
				AddCorso addcorso = new AddCorso(id_utente);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		plusPanel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		plusPanel.setBackground(new Color(255, 255, 255));
		plusPanel.setBounds(433, 72, 38, 37);
		add(plusPanel);
		plusPanel.setLayout(null);
		
		JLabel plusLabel_Lezione = new JLabel("");
		plusLabel_Lezione.setBounds(0, 0, 38, 37);
		plusPanel.add(plusLabel_Lezione);
		plusLabel_Lezione.setHorizontalAlignment(SwingConstants.CENTER);
		plusLabel_Lezione.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/plus.png")).getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
		
		JPanel corsoPanel = new JPanel();
		corsoPanel.setBackground(Color.GRAY);
		corsoPanel.setBounds(2, 111, 477, 473);
		add(corsoPanel);
		corsoPanel.setLayout(null);
		
		JPanel refreshPanelCorso = new JPanel();
		refreshPanelCorso.setToolTipText("Refresh Corsi.");
		refreshPanelCorso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				refreshPanelCorso.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				refreshPanelCorso.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				refreshPanelCorso.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				refreshPanelCorso.setBackground(Color.WHITE);
				
	        	LezioneDAO t = new GetLezioniDAO();
	            int id_corso_loc = id_corso;
	      
	            list = t.ritornaLezioni(id_corso_loc);
		        
					modelTableLezioni.setRowCount(0);
					for (int i = 0; i < list.size(); i++) {
						modelTableLezioni.addRow(new Object[] { String.valueOf(list.get(i).getNomeLezione()),
								String.valueOf(list.get(i).getDurataLezione()),
								String.valueOf(list.get(i).getDataLezione()),
								String.valueOf(list.get(i).getDescrizioneLezione())});
						}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		refreshPanelCorso.setLayout(null);
		refreshPanelCorso.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		refreshPanelCorso.setBackground(new Color(255, 255, 255));
		refreshPanelCorso.setBounds(396, 72, 38, 37);
		add(refreshPanelCorso);
		
		JLabel refreshLabelPl = new JLabel("");
		refreshLabelPl.setBounds(0, 0, 38, 37);
		refreshPanelCorso.add(refreshLabelPl);
		refreshLabelPl.setHorizontalAlignment(SwingConstants.CENTER);
		refreshLabelPl.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/refreshing.png")).getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
		refreshPanelCorso.setVisible(false);
		
		JPanel refreshPanel = new JPanel();
		refreshPanel.setToolTipText("Refresh Libreria.");
		refreshPanel.setLayout(null);
		refreshPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
		refreshPanel.setBackground(Color.GRAY);
		refreshPanel.setBounds(396, 72, 38, 37);
		add(refreshPanel);
		refreshPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				refreshPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				refreshPanel.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				refreshPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				refreshPanel.setBackground(Color.WHITE);
				LibreriaDAO l = new GetLibreriaDAO();
				
				libs = l.ritornaLibreria(u.getCodice());
				lista_corso = libs.getCorso();
				
				modelTable.setRowCount(0);
				for (int i = 0; i < lista_corso.size(); i++) {
					modelTable.addRow(new Object[] { String.valueOf(lista_corso.get(i).getNomeCorso()),
							String.valueOf(lista_corso.get(i).getTematica()),
							String.valueOf(lista_corso.get(i).getDescrizione()),
							String.valueOf(lista_corso.get(i).getNumeroLezioni())});
					}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		
		JPanel downloadLibPanel = new JPanel();
		downloadLibPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				downloadLibPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				downloadLibPanel.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				downloadLibPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				downloadLibPanel.setBackground(Color.WHITE);
				LibreriaDAO l = new GetLibreriaDAO();
				
				libs = l.ritornaLibreria(u.getCodice());
				System.out.println(u.getCodice());
				lista_corso = libs.getCorso();
				
				modelTable.setRowCount(0);
				for (int i = 0; i < lista_corso.size(); i++) {
					modelTable.addRow(new Object[] { String.valueOf(lista_corso.get(i).getNomeCorso()),
							String.valueOf(lista_corso.get(i).getTematica()),
							String.valueOf(lista_corso.get(i).getDescrizione()),
							String.valueOf(lista_corso.get(i).getNumeroLezioni())});
					}
			}
		});
		downloadLibPanel.setLayout(null);
		downloadLibPanel.setToolTipText("Avvia libreria.");
		downloadLibPanel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		downloadLibPanel.setBackground(new Color(255, 255, 255));
		downloadLibPanel.setBounds(219, 72, 38, 37);
		add(downloadLibPanel);
		
		JLabel downloadLabel = new JLabel("");
		downloadLabel.setBounds(0, 0, 38, 37);
		downloadLibPanel.add(downloadLabel);
		downloadLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/power.png")).getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
		downloadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		modelTable.setColumnIdentifiers(headersCorsi);
		modelTableLezioni.setColumnIdentifiers(headersLezioni);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new LineBorder(new Color(0, 64, 128), 2));
		scrollPane.setBounds(0, 0, 477, 473);
		corsoPanel.add(scrollPane);
		scrollPane.setViewportView(tableCorsi);
		
		JScrollPane scrollPaneTracce = new JScrollPane();
		scrollPaneTracce.getViewport().setBackground(Color.WHITE);
		scrollPaneTracce.setBackground(Color.GRAY);
		scrollPaneTracce.setBorder(new LineBorder(Color.GRAY, 2));
		scrollPaneTracce.setBounds(0, 0, 477, 473);
		corsoPanel.add(scrollPaneTracce);
		scrollPaneTracce.setViewportView(tableLezioni); 
		
		JPanel plusPanel_Lezione = new JPanel();
		plusPanel_Lezione.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					plusPanel_Lezione.setBackground(Color.LIGHT_GRAY);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					plusPanel_Lezione.setBackground(Color.WHITE);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					plusPanel_Lezione.setBackground(Color.LIGHT_GRAY);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					
					AddLezione addLezione = new AddLezione(id_corso);
					plusPanel_Lezione.setBackground(Color.WHITE);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
		});
		plusPanel_Lezione.setLayout(null);
		plusPanel_Lezione.setToolTipText("Aggiungi Corso.");
		plusPanel_Lezione.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		plusPanel_Lezione.setBackground(new Color(255, 255, 255));
		plusPanel_Lezione.setBounds(359, 72, 38, 37);
		plusPanel_Lezione.setVisible(false);
		
		add(plusPanel_Lezione);
		
		JLabel plusLabel = new JLabel("");
		plusLabel.setBounds(0, 0, 38, 37);
		plusPanel_Lezione.add(plusLabel);
		plusLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/plus.png")).getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
		plusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel backPanel = new JPanel();
		backPanel.setLayout(null);
		backPanel.setToolTipText("Torna alla Libreria.");
		backPanel.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		backPanel.setBackground(new Color(255, 255, 255));
		backPanel.setBounds(4, 72, 82, 37);
		add(backPanel);
		backPanel.setVisible(false);
		
		JLabel lblIndietro = new JLabel("");
		lblIndietro.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/arrow.png")).getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
		lblIndietro.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIndietro.setBounds(0, 0, 82, 37);
		backPanel.add(lblIndietro);
		backPanel.setVisible(false);
		
		JPanel binPanel = new JPanel();
		binPanel.setToolTipText("Elimina Corso.");
		binPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				binPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				binPanel.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				binPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				binPanel.setBackground(Color.WHITE);
				
				if(JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il corso selezionato?", "Conferma", JOptionPane.YES_NO_OPTION) == 0) {
				
						lista_corso.remove(index_f);
			            System.out.println("" + index_f);
						
						modelTable.setRowCount(0);
						for (int i = 0; i < lista_corso.size(); i++) {
							modelTable.addRow(new Object[] { String.valueOf(lista_corso.get(i).getNomeCorso()),
									String.valueOf(lista_corso.get(i).getTematica()),
									String.valueOf(lista_corso.get(i).getDescrizione()),
									String.valueOf(lista_corso.get(i).getNumeroLezioni())});
							}
						
						JOptionPane.showMessageDialog(null, "Corso eliminato con successo.");
						
						scrollPaneTracce.setVisible(false);
						backPanel.setVisible(false);
						plusPanel_Lezione.setVisible(false);
						refreshPanelCorso.setVisible(false);
						binPanel.setVisible(false);
			            scrollPane.setVisible(true);
			            refreshPanel.setVisible(true);
			            plusPanel.setVisible(true);
			            downloadLibPanel.setVisible(true);
					
				}	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		binPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
		binPanel.setBackground(Color.GRAY);
		binPanel.setBounds(433, 72, 38, 37);
		add(binPanel);
		binPanel.setLayout(null);
		binPanel.setVisible(false);
		
		JLabel binLabel = new JLabel("");
		binLabel.setBounds(0, 0, 38, 37);
		binPanel.add(binLabel);
		binLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/bin.png")).getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
		binLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		backPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				backPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backPanel.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				backPanel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				backPanel.setBackground(Color.WHITE);
				
				scrollPaneTracce.setVisible(false);
				backPanel.setVisible(false);
				
				refreshPanelCorso.setVisible(false);
				binPanel.setVisible(false);
				
				plusPanel_Lezione.setVisible(false);
				
	            scrollPane.setVisible(true);
	            refreshPanel.setVisible(true);
	            plusPanel.setVisible(true);
	            downloadLibPanel.setVisible(true);
			}
		});
		
		tableCorsi.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table = (JTable) mouseEvent.getSource();
		        Point punto = mouseEvent.getPoint();
		        int righe = table.rowAtPoint(punto);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		            
		        	LezioneDAO t = new GetLezioniDAO();
		            Object obj = GetData(table, table.getSelectedRow(), 0);
		            index_f = table.getSelectedRow();
		            
		            id_corso = lista_corso.get(table.getSelectedRow()).getIDCorso();
		            list = t.ritornaLezioni(id_corso);
		            
			            scrollPane.setVisible(false);
			            refreshPanel.setVisible(false);
			            plusPanel.setVisible(false);
			            downloadLibPanel.setVisible(false);
			            
						modelTableLezioni.setRowCount(0);
						for (int i = 0; i < list.size(); i++) {
							modelTableLezioni.addRow(new Object[] { String.valueOf(list.get(i).getNomeLezione()),
									String.valueOf(list.get(i).getDurataLezione()),
									String.valueOf(list.get(i).getDataLezione()),
									String.valueOf(list.get(i).getDescrizioneLezione())});
							}
						
						refreshPanelCorso.setVisible(true);
						backPanel.setVisible(true);
			            scrollPaneTracce.setVisible(true);
			            binPanel.setVisible(true);
			            plusPanel_Lezione.setVisible(true);
			            
			            if(list.size() == 0) {
							JOptionPane.showMessageDialog(null, "Il corso"+ obj +" e' vuoto");
						}
		        }
		    }
		});
		
		
		tableLezioni.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		    	
		        JTable table = (JTable) mouseEvent.getSource();
		        JTable table_corsi = (JTable) mouseEvent.getSource();
		        Point punto = mouseEvent.getPoint();
		        int righe = table.rowAtPoint(punto);
		        
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		            
		            Object obj = GetData(table, table.getSelectedRow(), 0);
		            
		            int id_lezione = list.get(table.getSelectedRow()).getIdLezione();
		            int grandezza_corso = lista_corso.size();
		            
		            RimuoviLezione paneltrack = new RimuoviLezione(id_utente, id_lezione, obj.toString(), grandezza_corso);
		}}});
		
		
		tableLezioni.setGridColor(Color.BLACK);
		tableLezioni.setFont(new Font("Arial", Font.PLAIN, 14));
		tableLezioni.setForeground(Color.BLACK);
		tableLezioni.setBackground(Color.WHITE);
		tableLezioni.setShowVerticalLines(false);
		tableLezioni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLezioni.setModel(modelTableLezioni);
		tableLezioni.setRowHeight(45);
		
		
		tableCorsi.setGridColor(Color.BLACK);
		tableCorsi.setFont(new Font("Arial", Font.PLAIN, 14));
		tableCorsi.setForeground(Color.BLACK);
		tableCorsi.setBackground(Color.WHITE);
		tableCorsi.setShowVerticalLines(false);
		tableCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCorsi.setModel(modelTable);
		tableCorsi.setRowHeight(45);
		
		JLabel refreshLabel = new JLabel("");
		refreshLabel.setBounds(0, 0, 38, 37);
		refreshPanel.add(refreshLabel);
		refreshLabel.setHorizontalAlignment(SwingConstants.CENTER);
		refreshLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Immagini/refreshing.png")).getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH)));

	}

	
	
 	public Object GetData(JTable table, int row_index, int col_index){
		  return table.getModel().getValueAt(row_index, col_index);
		  }
}
