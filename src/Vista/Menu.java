package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.BibliotecaController;
import Excepciones.CampoObligatorioException;
import Excepciones.IsbnException;
import Modelo.Libro;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Menu extends JFrame {

	private JPanel contentPane,panel;
	private ImageIcon icon;
	private JButton btnAdd,btnEdit,btnDelete,btnSave,btnUndo;
	private JPanel panel_1,panel_2;
	private JTextField textIdLibro;
	private JTextField textTitulo;
	private JTextField textAutor;
	private JTextField textEditorial;
	private JTextField textIsbn;
	private JTextField textFecha;
	private JLabel lblIdLibros,lblTitulo,lblAutor,lblEditorial,lblIsbn,lblFecha,lblFecha2;
	private JCheckBox chckbxPrestado;
	private JButton btnBeginning,btnBackward,btnForward,btnEnd;
	private JPanel panel_3;
	private JTable tableBiblioteca;
	private JScrollPane scrollPane;
	private DefaultTableModel dtm;
	private List<Libro> listaLibros;
	private String titulos[]={"idLibro","Titulo","Autor","Editorial","Isbn","FechaPrestamo","Prestado"};
	private String columnaslibro[];
	private JTextField textConsulta;
	private JLabel lblConsulta;
	private JComboBox comboBox;
	private JButton btnFiltrar;
	private int index;
	private Libro libro;
	private JFrame errorMessage;
	private BibliotecaController bc;
	
	public Menu() throws NumberFormatException, IOException, ParseException, CampoObligatorioException, IsbnException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1156, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		bc=new BibliotecaController();
		this.listaLibros=bc.mostrarLibros();
		index=0;
		
		definirVentana();
		definirEventosNavegacion();
		definirEventosMantenimiento();
		setGrid(listaLibros);
		setBotonesNavegacion(index,listaLibros);
		setLibro(index,listaLibros);
		setVisible(true);
	}

	private void definirEventosMantenimiento() {
		// TODO Auto-generated method stub
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnUndo.setEnabled(true);
				
				textTitulo.setText("");
				textTitulo.setEditable(true);
				textAutor.setText("");
				textAutor.setEditable(true);
				textEditorial.setText("");
				textEditorial.setEditable(true);
				textIsbn.setText("");
				textIsbn.setEditable(true);
				textFecha.setText("");
				textFecha.setEditable(true);
				chckbxPrestado.setSelected(false);
				chckbxPrestado.setEnabled(true);
				btnBeginning.setEnabled(false);
				btnBackward.setEnabled(false);
				btnEnd.setEnabled(false);
				btnForward.setEnabled(false);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo=textTitulo.getText(),autor=textAutor.getText(),editorial=textEditorial.getText(),isbn=textIsbn.getText(),
						fecha=textFecha.getText(),prestado=chckbxPrestado.isSelected()+"";
				try {
					bc.agregar(isbn, titulo, autor, editorial, fecha, fecha, prestado);
				} catch (NumberFormatException | ParseException | CampoObligatorioException | IsbnException l) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(errorMessage,l.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libro=null;
				btnAdd.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnUndo.setEnabled(false);
				
				textTitulo.setText(listaLibros.get(index).getTitulo());
				textTitulo.setEditable(false);
				textAutor.setText(listaLibros.get(index).getAutor());
				textAutor.setEditable(false);
				textEditorial.setText(listaLibros.get(index).getEditorial());
				textEditorial.setEditable(false);
				textIsbn.setText(listaLibros.get(index).getIsbn());
				textIsbn.setEditable(false);
				textFecha.setText(listaLibros.get(index).getFechaRegistro().toString());
				textFecha.setEditable(false);
				chckbxPrestado.setSelected(listaLibros.get(index).isPrestado());
				chckbxPrestado.setEnabled(rootPaneCheckingEnabled);
				setBotonesNavegacion(index, listaLibros);
			}
		});
	}

	private void setLibro(int index, List<Libro> listaLibros) {
		// TODO Auto-generated method stub
		textTitulo.setText(listaLibros.get(index).getTitulo());
		textAutor.setText(listaLibros.get(index).getAutor());
		textEditorial.setText(listaLibros.get(index).getEditorial());
		textIsbn.setText(listaLibros.get(index).getIsbn());
		textFecha.setText(listaLibros.get(index).getFechaRegistro().toString());
		chckbxPrestado.setSelected(listaLibros.get(index).isPrestado()); 
	}

	private void setBotonesNavegacion(int index,List<Libro> lista) {
		// TODO Auto-generated method stub
		if (index==0) {
			btnBeginning.setEnabled(false);
			btnBackward.setEnabled(false);
			btnEnd.setEnabled(true);
			btnForward.setEnabled(true);
		} else if (index>0 && index<lista.size()-1) {
			btnBeginning.setEnabled(true);
			btnBackward.setEnabled(true);
			btnEnd.setEnabled(true);
			btnForward.setEnabled(true);
		} else {
			btnForward.setEnabled(false);
			btnEnd.setEnabled(false);
			btnBeginning.setEnabled(true);
			btnBackward.setEnabled(true);
		}
	
	}

	private void setGrid(List<Libro> listaLibros) {
		// TODO Auto-generated method stub
		for (Libro lib:listaLibros) {
			this.columnaslibro=lib.toString().split(",");
			dtm.addRow(this.columnaslibro);
		}
	}

	private void definirEventosNavegacion() {
		// TODO Auto-generated method stub
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index=listaLibros.size()-1;
				setBotonesNavegacion(index,listaLibros);
				setLibro(index,listaLibros);
			}
		});
		btnBeginning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index=0;
				setBotonesNavegacion(index,listaLibros);
				setLibro(index,listaLibros);
			}
		});
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				setBotonesNavegacion(index,listaLibros);
				setLibro(index,listaLibros);
			}
		});
		btnBackward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				setBotonesNavegacion(index,listaLibros);
				setLibro(index,listaLibros);
			}
		});
	}

	private void definirVentana() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 255), 2), "Mantenimiento Libros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(27, 26, 271, 89);
		contentPane.add(panel);
		panel.setLayout(null);
		
		icon=new ImageIcon("img/add.png");
		btnAdd = new JButton("",icon);
		btnAdd.setBounds(10, 23, 40, 40);
		panel.add(btnAdd);
		icon=null;
		
		icon=new ImageIcon("img/edit.png");
		btnEdit = new JButton("",icon);
		btnEdit.setBounds(60, 23, 40, 40);
		panel.add(btnEdit);
		icon=null;
		
		icon=new ImageIcon("img/delete.png");
		btnDelete = new JButton("",icon);
		btnDelete.setBounds(110, 23, 40, 40);
		panel.add(btnDelete);
		icon=null;
		
		icon=new ImageIcon("img/save.png");
		btnSave = new JButton("",icon);
		btnSave.setEnabled(false);
		btnSave.setBounds(160, 23, 40, 40);
		panel.add(btnSave);
		icon=null;
		
		icon=new ImageIcon("img/undo.png");
		btnUndo = new JButton("",icon);
		btnUndo.setEnabled(false);
		btnUndo.setBounds(210, 23, 40, 40);
		panel.add(btnUndo);
		icon=null;
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 255), 2), "Libros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(27, 137, 321, 241);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblIdLibros = new JLabel("IdLibro");
		lblIdLibros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdLibros.setBounds(24, 30, 70, 20);
		panel_1.add(lblIdLibros);
		
		textIdLibro = new JTextField();
		textIdLibro.setEditable(false);
		textIdLibro.setBounds(104, 30, 40, 20);
		panel_1.add(textIdLibro);
		textIdLibro.setColumns(10);
		
		lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitulo.setBounds(24, 61, 70, 20);
		panel_1.add(lblTitulo);
		
		textTitulo = new JTextField();
		textTitulo.setEditable(false);
		textTitulo.setColumns(10);
		textTitulo.setBounds(104, 61, 195, 20);
		panel_1.add(textTitulo);
		
		lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAutor.setBounds(24, 92, 70, 20);
		panel_1.add(lblAutor);
		
		textAutor = new JTextField();
		textAutor.setEditable(false);
		textAutor.setColumns(10);
		textAutor.setBounds(104, 92, 195, 20);
		panel_1.add(textAutor);
		
		lblEditorial = new JLabel("Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEditorial.setBounds(24, 123, 70, 20);
		panel_1.add(lblEditorial);
		
		textEditorial = new JTextField();
		textEditorial.setEditable(false);
		textEditorial.setColumns(10);
		textEditorial.setBounds(104, 123, 195, 20);
		panel_1.add(textEditorial);
		
		lblIsbn = new JLabel("Isbn");
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIsbn.setBounds(24, 154, 70, 20);
		panel_1.add(lblIsbn);
		
		textIsbn = new JTextField();
		textIsbn.setEditable(false);
		textIsbn.setColumns(10);
		textIsbn.setBounds(104, 154, 195, 20);
		panel_1.add(textIsbn);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(24, 185, 70, 20);
		panel_1.add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setEditable(false);
		textFecha.setColumns(10);
		textFecha.setBounds(104, 185, 109, 20);
		panel_1.add(textFecha);
		
		lblFecha2 = new JLabel("aaaa-MM-dd");
		lblFecha2.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha2.setBounds(223, 185, 76, 20);
		panel_1.add(lblFecha2);
		
		chckbxPrestado = new JCheckBox("Prestado");
		chckbxPrestado.setEnabled(false);
		chckbxPrestado.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxPrestado.setBounds(24, 211, 76, 23);
		panel_1.add(chckbxPrestado);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 255), 2), "Navegador", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_2.setBounds(86, 389, 212, 80);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		icon=new ImageIcon("img/beginning.png");
		btnBeginning = new JButton("", icon);
		btnBeginning.setBounds(10, 29, 40, 40);
		panel_2.add(btnBeginning);
		icon=null;
		
		icon=new ImageIcon("img/backward.png");
		btnBackward = new JButton("", icon);
		btnBackward.setBounds(60, 29, 40, 40);
		panel_2.add(btnBackward);
		icon=null;
		
		icon=new ImageIcon("img/forward.png");
		btnForward = new JButton("", icon);
		btnForward.setBounds(110, 29, 40, 40);
		panel_2.add(btnForward);
		icon=null;
		
		icon=new ImageIcon("img/end.png");
		btnEnd = new JButton("", icon);
		btnEnd.setBounds(160, 29, 40, 40);
		panel_2.add(btnEnd);
		
		panel_3 = new JPanel();
		panel_3.setBounds(386, 94, 721, 333);
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		dtm=new DefaultTableModel();
		tableBiblioteca = new JTable(dtm);
		scrollPane.setViewportView(tableBiblioteca);
		
		lblConsulta = new JLabel("Consulta");
		lblConsulta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsulta.setBounds(386, 26, 82, 21);
		contentPane.add(lblConsulta);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Isbn", "Titulo", "Autor", "Editorial", "Fecha", "Prestado"}));
		comboBox.setBounds(386, 46, 82, 21);
		contentPane.add(comboBox);
		
		textConsulta = new JTextField();
		textConsulta.setBounds(478, 46, 329, 21);
		contentPane.add(textConsulta);
		textConsulta.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(821, 45, 89, 22);
		contentPane.add(btnFiltrar);
		dtm.setColumnIdentifiers(titulos);
		
		this.errorMessage=this;
	}
}
