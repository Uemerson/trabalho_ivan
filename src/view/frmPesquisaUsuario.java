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
import dao.DAOUsuario;
import dao.DAOUsuario;
import model.Responsavel;
import tableModel.FuncionarioTableModel;
import tableModel.ResponsavelTableModel;
import tableModel.UsuarioTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JRadioButton;

public class frmPesquisaUsuario extends JInternalFrame
		implements ActionListener, InternalFrameListener, DocumentListener, KeyListener {
	private JTable tbTabelaUsuario;

	private static frmPesquisaUsuario singleton = null;
	private JScrollPane srpPainelTabela;
	private JTextField txtBuscarPor;
	private UsuarioTableModel usuarioTableModel;
	private JRadioButton rdbtnFiltrarPorRegistro;
	private JRadioButton rdbtnFiltrarPorNome;
	private JRadioButton rdbtnFiltrarPorLogin;
	private JButton btnConfirma;

	public static frmPesquisaUsuario getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaUsuario();
		}

		return singleton;
	}
	
	public static void setInstance(frmPesquisaUsuario estado) {
		singleton = estado;
	}

	public frmPesquisaUsuario() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Usu\u00E1rio");
		setBounds(100, 100, 719, 396);
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 103, 685, 213);
		getContentPane().add(srpPainelTabela);

		usuarioTableModel = new UsuarioTableModel();
		usuarioTableModel.addListaDeUsuarios(new DAOUsuario().listaUsuarios());

		tbTabelaUsuario = new JTable();
		tbTabelaUsuario.setModel(usuarioTableModel);
		tbTabelaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(usuarioTableModel);
		tbTabelaUsuario.setRowSorter(sorter);

		srpPainelTabela.setViewportView(tbTabelaUsuario);

		btnConfirma = new JButton("Abrir cadastro de usu\u00E1rio");
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

		rdbtnFiltrarPorNome = new JRadioButton("Filtrar por funcionario");
		rdbtnFiltrarPorNome.addActionListener(this);
		rdbtnFiltrarPorNome.setBounds(144, 13, 139, 23);
		pnlFiltros.add(rdbtnFiltrarPorNome);

		rdbtnFiltrarPorLogin = new JRadioButton("Filtrar por login");
		rdbtnFiltrarPorLogin.addActionListener(this);
		rdbtnFiltrarPorLogin.setBounds(285, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorLogin);

		btngpGrupoFiltros.add(rdbtnFiltrarPorNome);
		btngpGrupoFiltros.add(rdbtnFiltrarPorLogin);
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
		System.out.println("Atualizando dados usuario");
		txtBuscarPor.setText(null);
		tbTabelaUsuario.setRowSorter(null);
		usuarioTableModel.limpar();
		usuarioTableModel.addListaDeUsuarios(new DAOUsuario().listaUsuarios());

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(usuarioTableModel);
		tbTabelaUsuario.setRowSorter(sorter);

		// tbTabelaFuncionario.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == rdbtnFiltrarPorLogin) {
				rdbtnFiltrarPorLogin_click();
			} else if (e.getSource() == rdbtnFiltrarPorNome) {
				rdbtnFiltrarPorNome_click();
			} else if (e.getSource() == rdbtnFiltrarPorRegistro) {
				rdbtnFiltrarPorRegistro_click();
			} else if (e.getSource() == btnConfirma) {
				btnConfirma_click();
			} 
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação, tente novamente!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void rdbtnFiltrarPorLogin_click() {
		txtBuscarPor.setText(null);
		txtBuscarPor.requestFocus();
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
		
		if (usuarioTableModel.getUsuario(tbTabelaUsuario.getSelectedRow()).getLogin().equals("Administrador")) {
			JOptionPane.showMessageDialog(this, "Esse usuário não pode ser editado e nem excluido!", "Sistema", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (btnConfirma.getText() == "Abrir cadastro de usuário") {
		
			if (tbTabelaUsuario.getSelectedRow() > -1) {
				frmCadastroUsuario.getInstance();
	
				if (frmCadastroUsuario.getInstance().isVisible()) {
					frmCadastroUsuario.getInstance().preencheCadastro(new DAOUsuario().buscaUsuario(usuarioTableModel.getUsuario(tbTabelaUsuario.getSelectedRow()).getRegistro()));
					frmCadastroUsuario.getInstance().setSelected(true);
				} else {
	
					frmCadastroUsuario.getInstance().setVisible(true);
					frmMenu.getInstance().getDskPrincipal().add(frmCadastroUsuario.getInstance());
					frmCadastroUsuario.getInstance().setSelected(true);
					frmCadastroUsuario.getInstance().preencheCadastro(new DAOUsuario().buscaUsuario(usuarioTableModel.getUsuario(tbTabelaUsuario.getSelectedRow()).getRegistro()));
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de usuario!",
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
			tbTabelaUsuario.setRowSorter(null);
			usuarioTableModel.limpar();

			if (rdbtnFiltrarPorRegistro.isSelected())
				usuarioTableModel.addListaDeUsuarios(
						new DAOUsuario().listaUsuarios(Integer.parseInt(txtBuscarPor.getText())));
			else if (rdbtnFiltrarPorNome.isSelected())
				usuarioTableModel
						.addListaDeUsuarios(new DAOUsuario().listaUsuariosNome(txtBuscarPor.getText()));
			else if (rdbtnFiltrarPorLogin.isSelected())
				usuarioTableModel.addListaDeUsuarios(new DAOUsuario().listaUsuariosLogin(txtBuscarPor.getText()));

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(usuarioTableModel);
			tbTabelaUsuario.setRowSorter(sorter);
		} else {
			tbTabelaUsuario.setRowSorter(null);
			usuarioTableModel.limpar();
			usuarioTableModel.addListaDeUsuarios(new DAOUsuario().listaUsuarios());

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(usuarioTableModel);
			tbTabelaUsuario.setRowSorter(sorter);
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
