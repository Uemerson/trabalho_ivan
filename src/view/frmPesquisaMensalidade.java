package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.DAOMensalidade;
import tableModel.MensalidadeTableModel;

public class frmPesquisaMensalidade extends JInternalFrame
		implements ActionListener, InternalFrameListener, DocumentListener, KeyListener {
	private JTable tbTabelaMensalidade;

	private static frmPesquisaMensalidade singleton = null;
	private JScrollPane srpPainelTabela;
	private JTextField txtBuscarPor;
	private MensalidadeTableModel mensalidadeTableModel;
	private JRadioButton rdbtnFiltrarPorRegistro;
	private JRadioButton rdbtnFiltrarPorSerie;
	private JRadioButton rdbtnFiltrarPorPagamento;
	private JButton btnConfirma;

	public static frmPesquisaMensalidade getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaMensalidade();
		}

		return singleton;
	}
	
	public static void setInstance(frmPesquisaMensalidade estado) {
		singleton = estado;
	}
	
	public frmPesquisaMensalidade() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Mensalidade");
		setBounds(100, 100, 719, 396);
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 103, 685, 213);
		getContentPane().add(srpPainelTabela);

		mensalidadeTableModel = new MensalidadeTableModel();
		mensalidadeTableModel.addListaDeMensalidades(new DAOMensalidade().listaMensalidade());

		tbTabelaMensalidade = new JTable();
		tbTabelaMensalidade.setModel(mensalidadeTableModel);
		tbTabelaMensalidade.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mensalidadeTableModel);
		tbTabelaMensalidade.setRowSorter(sorter);

		srpPainelTabela.setViewportView(tbTabelaMensalidade);

		btnConfirma = new JButton("Abrir cadastro de mensalidade");
		btnConfirma.addActionListener(this);
		btnConfirma.setBounds(478, 327, 217, 28);
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

		rdbtnFiltrarPorSerie = new JRadioButton("Filtrar por s\u00E9rie");
		rdbtnFiltrarPorSerie.addActionListener(this);
		rdbtnFiltrarPorSerie.setBounds(144, 13, 108, 23);
		pnlFiltros.add(rdbtnFiltrarPorSerie);

		rdbtnFiltrarPorPagamento = new JRadioButton("Filtrar por forma de pagamento");
		rdbtnFiltrarPorPagamento.addActionListener(this);
		rdbtnFiltrarPorPagamento.setBounds(254, 13, 193, 23);
		pnlFiltros.add(rdbtnFiltrarPorPagamento);

		btngpGrupoFiltros.add(rdbtnFiltrarPorSerie);
		btngpGrupoFiltros.add(rdbtnFiltrarPorPagamento);
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
		System.out.println("Atualizando dados mensalidade");
		txtBuscarPor.setText(null);
		tbTabelaMensalidade.setRowSorter(null);
		mensalidadeTableModel.limpar();
		mensalidadeTableModel.addListaDeMensalidades(new DAOMensalidade().listaMensalidade());

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mensalidadeTableModel);
		tbTabelaMensalidade.setRowSorter(sorter);

		// tbTabelaFuncionario.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == rdbtnFiltrarPorPagamento) {
				rdbtnFiltrarPorLogin_click();
			} else if (e.getSource() == rdbtnFiltrarPorSerie) {
				rdbtnFiltrarPorNome_click();
			} else if (e.getSource() == rdbtnFiltrarPorRegistro) {
				rdbtnFiltrarPorRegistro_click();
			} else if (e.getSource() == btnConfirma) {
				btnConfirma_click();
			} 
		} catch (Exception ex) {
			//System.out.println(ex.getMessage());
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
		if (btnConfirma.getText() == "Abrir cadastro de mensalidade") {
		
			if (tbTabelaMensalidade.getSelectedRow() > -1) {
				frmCadastroMensalidade.getInstance();
	
				if (frmCadastroMensalidade.getInstance().isVisible()) {
					frmCadastroMensalidade.getInstance().preencheCadastro(new DAOMensalidade().buscaMensalidade(mensalidadeTableModel.getMensalidade(tbTabelaMensalidade.getSelectedRow()).getRegistro()));
					frmCadastroMensalidade.getInstance().setSelected(true);
				} else {
	
					frmCadastroMensalidade.getInstance().setVisible(true);
					frmMenu.getInstance().getDskPrincipal().add(frmCadastroMensalidade.getInstance());
					frmCadastroMensalidade.getInstance().setSelected(true);
					frmCadastroMensalidade.getInstance().preencheCadastro(new DAOMensalidade().buscaMensalidade(mensalidadeTableModel.getMensalidade(tbTabelaMensalidade.getSelectedRow()).getRegistro()));
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de mensalidade!",
						"Sistema", JOptionPane.ERROR_MESSAGE);
			}
		
		} else if (btnConfirma.getText() == "Abrir cadastro de matrícula") {
		
			if (tbTabelaMensalidade.getSelectedRow() > -1) {
				frmCadastroMatricula.getInstance();
	
				if (frmCadastroMatricula.getInstance().isVisible()) {
					frmCadastroMatricula.getInstance().preencheMensalidade(new DAOMensalidade().buscaMensalidade(mensalidadeTableModel.getMensalidade(tbTabelaMensalidade.getSelectedRow()).getRegistro()));
					frmCadastroMatricula.getInstance().setSelected(true);
				} else {
	
					frmCadastroMatricula.getInstance().setVisible(true);
					frmMenu.getInstance().getDskPrincipal().add(frmCadastroMatricula.getInstance());
					frmCadastroMatricula.getInstance().setSelected(true);
					frmCadastroMatricula.getInstance().preencheMensalidade(new DAOMensalidade().buscaMensalidade(mensalidadeTableModel.getMensalidade(tbTabelaMensalidade.getSelectedRow()).getRegistro()));
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de mensalidade!",
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
			tbTabelaMensalidade.setRowSorter(null);
			mensalidadeTableModel.limpar();

			if (rdbtnFiltrarPorRegistro.isSelected())
				mensalidadeTableModel.addListaDeMensalidades(
						new DAOMensalidade().listaMensalidade(Integer.parseInt(txtBuscarPor.getText())));
			else if (rdbtnFiltrarPorSerie.isSelected())
				mensalidadeTableModel
						.addListaDeMensalidades(new DAOMensalidade().listaMensalidadeSerie(txtBuscarPor.getText()));
			else if (rdbtnFiltrarPorPagamento.isSelected())
				mensalidadeTableModel.addListaDeMensalidades(new DAOMensalidade().listaMensalidadePagamento(txtBuscarPor.getText()));

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mensalidadeTableModel);
			tbTabelaMensalidade.setRowSorter(sorter);
		} else {
			tbTabelaMensalidade.setRowSorter(null);
			mensalidadeTableModel.limpar();
			mensalidadeTableModel.addListaDeMensalidades(new DAOMensalidade().listaMensalidade());

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mensalidadeTableModel);
			tbTabelaMensalidade.setRowSorter(sorter);
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
