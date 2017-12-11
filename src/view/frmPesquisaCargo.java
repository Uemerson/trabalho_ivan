package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import controler.ComboKeyHandler;
import controler.FuncoesGlobais;
import dao.DAOCargo;
import dao.DAOFuncionario;
import dao.DAOCargo;
import dao.DAOCargo;
import model.Responsavel;
import tableModel.CargoTableModel;
import tableModel.FuncionarioTableModel;
import tableModel.ResponsavelTableModel;
import tableModel.CargoTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JRadioButton;

public class frmPesquisaCargo extends JInternalFrame
		implements ActionListener, InternalFrameListener, DocumentListener, KeyListener {
	private JTable tbTabelaCargo;

	private static frmPesquisaCargo singleton = null;
	private JScrollPane srpPainelTabela;
	private JTextField txtBuscarPor;
	private CargoTableModel cargoTableModel;
	private JRadioButton rdbtnFiltrarPorRegistro;
	private JRadioButton rdbtnFiltrarPorNome;
	private JButton btnConfirma;

	public static frmPesquisaCargo getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaCargo();
		}

		return singleton;
	}
	
	public static void setInstance(frmPesquisaCargo estado) {
		singleton = estado;
	}
	
	public frmPesquisaCargo() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Cargo");
		setBounds(100, 100, 719, 396);
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 103, 685, 213);
		getContentPane().add(srpPainelTabela);

		cargoTableModel = new CargoTableModel();
		cargoTableModel.addListaDeCargos(new DAOCargo().listaCargos());

		tbTabelaCargo = new JTable();
		tbTabelaCargo.setModel(cargoTableModel);
		tbTabelaCargo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(cargoTableModel);
		tbTabelaCargo.setRowSorter(sorter);

		srpPainelTabela.setViewportView(tbTabelaCargo);

		btnConfirma = new JButton("Abrir cadastro de cargo");
		btnConfirma.addActionListener(this);
		btnConfirma.setBounds(500, 327, 195, 28);
		btnConfirma.setVisible(false);
		getContentPane().add(btnConfirma);

		JPanel pnlFiltros = new JPanel();
		pnlFiltros.setLayout(null);
		pnlFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiltros.setBounds(6, 6, 689, 84);
		getContentPane().add(pnlFiltros);

		ButtonGroup btngpGrupoFiltros = new ButtonGroup();

		rdbtnFiltrarPorRegistro = new JRadioButton("Filtrar por registro");
		rdbtnFiltrarPorRegistro.addActionListener(this);
		rdbtnFiltrarPorRegistro.setSelected(true);
		rdbtnFiltrarPorRegistro.setBounds(10, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorRegistro);

		rdbtnFiltrarPorNome = new JRadioButton("Filtrar por nome");
		rdbtnFiltrarPorNome.addActionListener(this);
		rdbtnFiltrarPorNome.setBounds(144, 13, 139, 23);
		pnlFiltros.add(rdbtnFiltrarPorNome);

		btngpGrupoFiltros.add(rdbtnFiltrarPorNome);
		btngpGrupoFiltros.add(rdbtnFiltrarPorRegistro);

		JLabel lblBuscar = new JLabel("Buscar por registro");
		lblBuscar.setBounds(10, 50, 122, 14);
		pnlFiltros.add(lblBuscar);

		txtBuscarPor = new JTextField();
		txtBuscarPor.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscarPor.setColumns(10);
		txtBuscarPor.setBounds(144, 43, 365, 28);
		txtBuscarPor.getDocument().addDocumentListener(this);
		txtBuscarPor.addKeyListener(this);
		pnlFiltros.add(txtBuscarPor);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtBuscarPor.requestFocus();
			}
		});
	}

	// Atualiza os dados dos funcionarios cadastrados
	public void atualizaDados() throws SQLException {
		System.out.println("Atualizando dados cargo");
		txtBuscarPor.setText(null);
		tbTabelaCargo.setRowSorter(null);
		cargoTableModel.limpar();
		cargoTableModel.addListaDeCargos(new DAOCargo().listaCargos());

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(cargoTableModel);
		tbTabelaCargo.setRowSorter(sorter);

		// tbTabelaFuncionario.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			
			if (e.getSource() == rdbtnFiltrarPorNome) {
				rdbtnFiltrarPorNome_click();
			} else if (e.getSource() == rdbtnFiltrarPorRegistro) {
				rdbtnFiltrarPorRegistro_click();
			} else if (e.getSource() == btnConfirma) {
				btnConfirma_click();
			} 
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void rdbtnFiltrarPorNome_click() {
		txtBuscarPor.setText(null);
		txtBuscarPor.requestFocus();
	}
	
	private void rdbtnFiltrarPorRegistro_click() {
		txtBuscarPor.setText(null);
		txtBuscarPor.requestFocus();
	}
	
	private void btnConfirma_click() throws ParseException, SQLException, PropertyVetoException {
		if (btnConfirma.getText() == "Abrir cadastro de cargo") {
		
			if (tbTabelaCargo.getSelectedRow() > -1) {
				frmCadastroCargo.getInstance();
	
				if (frmCadastroCargo.getInstance().isVisible()) {
					frmCadastroCargo.getInstance().preencheCadastro(new DAOCargo().buscaCargo(cargoTableModel.getCargo(tbTabelaCargo.getSelectedRow()).getRegistro()));
					frmCadastroCargo.getInstance().setSelected(true);
				} else {
	
					frmCadastroCargo.getInstance().setVisible(true);
					frmMenu.getInstance().getDskPrincipal().add(frmCadastroCargo.getInstance());
					frmCadastroCargo.getInstance().setSelected(true);
					frmCadastroCargo.getInstance().preencheCadastro(new DAOCargo().buscaCargo(cargoTableModel.getCargo(tbTabelaCargo.getSelectedRow()).getRegistro()));
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de cargo!",
						"Sistema", JOptionPane.ERROR_MESSAGE);
			}
		
		}
	}

	public JButton getBtnConfirma() {
		return btnConfirma;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		try {
			if (e.getDocument() == txtBuscarPor.getDocument()) {
				txtBuscarPor_documentUpdate();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void txtBuscarPor_documentUpdate() throws SQLException {
		
		if (!txtBuscarPor.getText().isEmpty()) {
			tbTabelaCargo.setRowSorter(null);
			cargoTableModel.limpar();

			if (rdbtnFiltrarPorRegistro.isSelected())
				cargoTableModel.addListaDeCargos(
						new DAOCargo().listaCargos(Integer.parseInt(txtBuscarPor.getText())));
			else if (rdbtnFiltrarPorNome.isSelected())
				cargoTableModel
						.addListaDeCargos(new DAOCargo().listaCargosNome(txtBuscarPor.getText()));
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(cargoTableModel);
			tbTabelaCargo.setRowSorter(sorter);
		} else {
			tbTabelaCargo.setRowSorter(null);
			cargoTableModel.limpar();
			cargoTableModel.addListaDeCargos(new DAOCargo().listaCargos());

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(cargoTableModel);
			tbTabelaCargo.setRowSorter(sorter);
		}
		// tbTabelaFuncionario.setModel(model);
		  
		 
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		try {
			if (e.getDocument() == txtBuscarPor.getDocument()) {
				txtBuscarPor_documentUpdate();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}

	// Limpa buffer da memoria
	public void internalFrameClosed(InternalFrameEvent e) {
		this.singleton = null;
	}

	public void internalFrameClosing(InternalFrameEvent e) {
	}

	public void internalFrameDeactivated(InternalFrameEvent e) {
	}

	public void internalFrameDeiconified(InternalFrameEvent e) {
	}

	public void internalFrameIconified(InternalFrameEvent e) {
	}

	public void internalFrameOpened(InternalFrameEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtBuscarPor) {
			String caracteres = "0123456789";

			if (txtBuscarPor.getText().length() >= 10 && rdbtnFiltrarPorRegistro.isSelected())
				e.consume();

			if (!caracteres.contains(e.getKeyChar() + "") && rdbtnFiltrarPorRegistro.isSelected())
				e.consume();

		}
	}

}
