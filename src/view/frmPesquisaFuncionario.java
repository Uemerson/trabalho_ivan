package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controler.FuncoesGlobais;
import dao.DAOFuncionario;
import tableModel.FuncionarioTableModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class frmPesquisaFuncionario extends JInternalFrame implements DocumentListener, ActionListener, InternalFrameListener, KeyListener {
	private JTable tbTabelaFuncionario;
	private JButton btnConfirma;

	private static frmPesquisaFuncionario singleton = null;
	private JPanel pnlFiltros;
	private JScrollPane srpPainelTabela;
	private JRadioButton rdbtnFiltrarPorCargo;
	private JRadioButton rdbtnFiltrarPorRegistro;
	private JRadioButton rdbtnFiltrarPorNome;
	private JRadioButton rdbtnFiltrarPorCpf;
	private JTextField txtBuscarPor;
	private JLabel lblBuscarPor;
	private FuncionarioTableModel funcionarioTableModel;
	
	public static frmPesquisaFuncionario getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaFuncionario();
		}

		return singleton;
	}

	public frmPesquisaFuncionario() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Funcion\u00E1rio");
		setBounds(100, 100, 808, 515);
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		pnlFiltros = new JPanel();
		pnlFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiltros.setBounds(10, 11, 772, 84);
		getContentPane().add(pnlFiltros);
		pnlFiltros.setLayout(null);

		ButtonGroup btngpGrupoFiltros = new ButtonGroup();

		rdbtnFiltrarPorRegistro = new JRadioButton("Filtrar por registro");
		rdbtnFiltrarPorRegistro.addActionListener(this);
		rdbtnFiltrarPorRegistro.setSelected(true);
		rdbtnFiltrarPorRegistro.setBounds(10, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorRegistro);

		rdbtnFiltrarPorNome = new JRadioButton("Filtrar por nome");
		rdbtnFiltrarPorNome.addActionListener(this);
		rdbtnFiltrarPorNome.setBounds(144, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorNome);

		rdbtnFiltrarPorCpf = new JRadioButton("Filtrar por CPF");
		rdbtnFiltrarPorCpf.addActionListener(this);
		rdbtnFiltrarPorCpf.setBounds(266, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorCpf);

		rdbtnFiltrarPorCargo = new JRadioButton("Filtrar por cargo");
		rdbtnFiltrarPorCargo.addActionListener(this);
		rdbtnFiltrarPorCargo.setBounds(387, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorCargo);

		btngpGrupoFiltros.add(rdbtnFiltrarPorNome);
		btngpGrupoFiltros.add(rdbtnFiltrarPorCpf);
		btngpGrupoFiltros.add(rdbtnFiltrarPorRegistro);
		btngpGrupoFiltros.add(rdbtnFiltrarPorCargo);

		lblBuscarPor = new JLabel("Buscar por registro");
		lblBuscarPor.setBounds(10, 50, 122, 14);
		pnlFiltros.add(lblBuscarPor);

		txtBuscarPor = new JTextField();
		txtBuscarPor.addKeyListener(this);
		txtBuscarPor.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscarPor.setBounds(144, 43, 365, 28);
		pnlFiltros.add(txtBuscarPor);
		txtBuscarPor.setColumns(10);
		txtBuscarPor.getDocument().addDocumentListener(this);
		
		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 107, 772, 334);
		getContentPane().add(srpPainelTabela);

		// Preenche a Table com a lista de usuarios
		tbTabelaFuncionario = new JTable();
		tbTabelaFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		funcionarioTableModel = new FuncionarioTableModel();
		funcionarioTableModel.addListaDeFuncionarios(new DAOFuncionario().listaFuncionario());
		tbTabelaFuncionario.setModel(funcionarioTableModel);
		srpPainelTabela.setViewportView(tbTabelaFuncionario);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(funcionarioTableModel);
		tbTabelaFuncionario.setRowSorter(sorter);
		
		btnConfirma = new JButton("Confirma");
		btnConfirma.addActionListener(this);
		btnConfirma.setBounds(578, 446, 204, 28);
		btnConfirma.setVisible(false);
		getContentPane().add(btnConfirma);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtBuscarPor.requestFocus();
			}
		});
	}

	public void focusLost(FocusEvent e) {
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

	private void txtBuscarPor_documentUpdate() throws SQLException {

		if (!txtBuscarPor.getText().isEmpty()) {
			tbTabelaFuncionario.setRowSorter(null);
			funcionarioTableModel.limpar();
			
			if (rdbtnFiltrarPorRegistro.isSelected())
				funcionarioTableModel.addListaDeFuncionarios(new DAOFuncionario().listaFuncionario(Integer.parseInt(txtBuscarPor.getText())));
			else if (rdbtnFiltrarPorNome.isSelected())
				funcionarioTableModel.addListaDeFuncionarios(new DAOFuncionario().listaFuncionarioNome(txtBuscarPor.getText()));
			else if (rdbtnFiltrarPorCargo.isSelected())
				funcionarioTableModel.addListaDeFuncionarios(new DAOFuncionario().listaFuncionarioCargo(txtBuscarPor.getText()));
			else if (rdbtnFiltrarPorCpf.isSelected())
				funcionarioTableModel.addListaDeFuncionarios(new DAOFuncionario().listaFuncionarioCPF(txtBuscarPor.getText()));
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(funcionarioTableModel);
			tbTabelaFuncionario.setRowSorter(sorter);
		}else {
			tbTabelaFuncionario.setRowSorter(null);
			funcionarioTableModel.limpar();
			funcionarioTableModel.addListaDeFuncionarios(new DAOFuncionario().listaFuncionario());
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(funcionarioTableModel);
			tbTabelaFuncionario.setRowSorter(sorter);
		}
		//tbTabelaFuncionario.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == rdbtnFiltrarPorCargo) {
				rdbtnFiltrarPorCargo_click();
			} else if (e.getSource() == rdbtnFiltrarPorRegistro) {
				rdbtnFiltrarPorRegistro_click();
			} else if (e.getSource() == rdbtnFiltrarPorCpf) {
				rdbtnFiltrarPorCPF_click();
			} else if (e.getSource() == rdbtnFiltrarPorNome) {
				rdbtnFiltrarPorNome_click();
			} else if (e.getSource() == btnConfirma) {
				btnConfirma_click();
			} 
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void btnConfirma_click() throws ParseException, SQLException, PropertyVetoException {
		if (btnConfirma.getText().equals("Abrir cadastro de funcionario")) {
			if (tbTabelaFuncionario.getSelectedRow() > -1) {
				frmCadastroFuncionario.getInstance();
	
				if (frmCadastroFuncionario.getInstance().isVisible()) {
					frmCadastroFuncionario.getInstance().preencheCadastro(new DAOFuncionario()
							.buscaFuncionario(funcionarioTableModel.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro()));
					frmCadastroFuncionario.getInstance().setSelected(true);
				} else {
	
					frmCadastroFuncionario.getInstance().setVisible(true);
					frmMenu.getFrmMenu().getDskPrincipal().add(frmCadastroFuncionario.getInstance());
					frmCadastroFuncionario.getInstance().setSelected(true);
					frmCadastroFuncionario.getInstance().preencheCadastro(new DAOFuncionario()
							.buscaFuncionario(funcionarioTableModel.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro()));
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de funcionario!",
						"Sistema", JOptionPane.ERROR_MESSAGE);
			}
		}else if (btnConfirma.getText() == "Abrir cadastro de usuário") {
			if (tbTabelaFuncionario.getSelectedRow() > -1) {
	
				frmCadastroUsuario.getInstance();
	
				if (frmCadastroUsuario.getInstance().isVisible()) {
					frmCadastroUsuario.getInstance().preencheFuncionario(funcionarioTableModel.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro());
					frmCadastroUsuario.getInstance().setSelected(true);
				} else {
					frmCadastroUsuario.getInstance().setVisible(true);
					frmMenu.getFrmMenu().getDskPrincipal().add(frmCadastroUsuario.getInstance());
					frmCadastroUsuario.getInstance().setSelected(true);
					frmCadastroUsuario.getInstance().preencheFuncionario(funcionarioTableModel.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro());
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de usuário!",
						"Sistema", JOptionPane.ERROR_MESSAGE);
			}
		}else if (btnConfirma.getText() == "Abrir cadastro de matrícula") {
			
			if (tbTabelaFuncionario.getSelectedRow() > -1) {
				
				frmCadastroMatricula.getInstance();
	
				if (frmCadastroMatricula.getInstance().isVisible()) {
					frmCadastroMatricula.getInstance().preencheSecretario(new DAOFuncionario().buscaFuncionario(funcionarioTableModel.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro()));
					frmCadastroMatricula.getInstance().setSelected(true);
				} else {
					frmCadastroMatricula.getInstance().setVisible(true);
					frmMenu.getFrmMenu().getDskPrincipal().add(frmCadastroMatricula.getInstance());
					frmCadastroMatricula.getInstance().setSelected(true);
					frmCadastroMatricula.getInstance().preencheSecretario(new DAOFuncionario().buscaFuncionario(funcionarioTableModel.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro()));
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de usuário!",
						"Sistema", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	private void rdbtnFiltrarPorCargo_click() {
		txtBuscarPor.setText(null);
		txtBuscarPor.requestFocus();
	}
	
	private void rdbtnFiltrarPorNome_click() {
		txtBuscarPor.setText(null);
		txtBuscarPor.requestFocus();
	}
	
	private void rdbtnFiltrarPorCPF_click() {
		txtBuscarPor.setText(null);
		txtBuscarPor.requestFocus();
	}
	
	private void rdbtnFiltrarPorRegistro_click() {
		txtBuscarPor.setText(null);
		txtBuscarPor.requestFocus();
	}
	
	// Atualiza os dados dos funcionarios cadastrados
	public void atualizaDados() throws SQLException {
		System.out.println("Atualizando dados funcionario");
		txtBuscarPor.setText(null);
		tbTabelaFuncionario.setRowSorter(null);
		funcionarioTableModel.limpar();
		funcionarioTableModel.addListaDeFuncionarios(new DAOFuncionario().listaFuncionario());
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(funcionarioTableModel);
		tbTabelaFuncionario.setRowSorter(sorter);
		
		//tbTabelaFuncionario.setModel(model);
		FuncoesGlobais.limpaCampos(pnlFiltros);
	}

	public JButton getBtnConfirma() {
		return btnConfirma;
	}

	public void setBtnConfirma(JButton btnConfirma) {
		this.btnConfirma = btnConfirma;
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}
	
	//Limpa buffer da memoria
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
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
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
