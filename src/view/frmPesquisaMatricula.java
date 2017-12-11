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

import dao.DAOMatricula;
import tableModel.MatriculaTableModel;

public class frmPesquisaMatricula extends JInternalFrame
		implements ActionListener, InternalFrameListener, DocumentListener, KeyListener {
	private JTable tbTabelaMatricula;

	private static frmPesquisaMatricula singleton = null;
	private JScrollPane srpPainelTabela;
	private JTextField txtBuscarPor;
	private MatriculaTableModel matriculaTableModel;
	private JRadioButton rdbtnFiltrarPorRegistro;
	private JRadioButton rdbtnFiltrarPorAluno;
	private JRadioButton rdbtnFiltrarPorResponsavel;
	private JButton btnConfirma;

	public static frmPesquisaMatricula getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaMatricula();
		}

		return singleton;
	}
	
	public static void setInstance(frmPesquisaMatricula estado) {
		singleton = estado;
	}
	
	public frmPesquisaMatricula() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Matr\u00EDcula");
		setBounds(100, 100, 719, 396);
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 103, 685, 213);
		getContentPane().add(srpPainelTabela);

		matriculaTableModel = new MatriculaTableModel();
		matriculaTableModel.addListaDeMatriculas(new DAOMatricula().listaMatricula());

		tbTabelaMatricula = new JTable();
		tbTabelaMatricula.setModel(matriculaTableModel);
		tbTabelaMatricula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(matriculaTableModel);
		tbTabelaMatricula.setRowSorter(sorter);

		srpPainelTabela.setViewportView(tbTabelaMatricula);

		btnConfirma = new JButton("Abrir cadastro de matr\u00EDcula");
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

		rdbtnFiltrarPorAluno = new JRadioButton("Filtrar por aluno");
		rdbtnFiltrarPorAluno.addActionListener(this);
		rdbtnFiltrarPorAluno.setBounds(144, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorAluno);

		rdbtnFiltrarPorResponsavel = new JRadioButton("Filtrar por Respons\u00E1vel");
		rdbtnFiltrarPorResponsavel.addActionListener(this);
		rdbtnFiltrarPorResponsavel.setBounds(266, 13, 157, 23);
		pnlFiltros.add(rdbtnFiltrarPorResponsavel);

		btngpGrupoFiltros.add(rdbtnFiltrarPorAluno);
		btngpGrupoFiltros.add(rdbtnFiltrarPorResponsavel);
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
		System.out.println("Atualizando dados aluno");
		txtBuscarPor.setText(null);
		tbTabelaMatricula.setRowSorter(null);
		matriculaTableModel.limpar();
		matriculaTableModel.addListaDeMatriculas(new DAOMatricula().listaMatricula());

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(matriculaTableModel);
		tbTabelaMatricula.setRowSorter(sorter);

		// tbTabelaFuncionario.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == rdbtnFiltrarPorRegistro) {
				rdbtnFiltrarPorRegistro_click();
			} else if (e.getSource() == rdbtnFiltrarPorResponsavel) {
				rdbtnFiltrarPorCPF_click();
			} else if (e.getSource() == rdbtnFiltrarPorAluno) {
				rdbtnFiltrarPorNome_click();
			} else if (e.getSource() == btnConfirma) {
				btnConfirma_click();
			} 
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
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

	private void btnConfirma_click() throws ParseException, SQLException, PropertyVetoException {
		
		if (btnConfirma.getText().equals("Abrir cadastro de matrícula")) {
			
			if (tbTabelaMatricula.getSelectedRow() > -1) {
				frmCadastroMatricula.getInstance();
	
				if (frmCadastroMatricula.getInstance().isVisible()) {
					frmCadastroMatricula.getInstance().preencheCadastro(new DAOMatricula().buscaMatricula(matriculaTableModel.getMatricula(tbTabelaMatricula.getSelectedRow()).getRegistro()));
					frmCadastroMatricula.getInstance().setSelected(true);
				} else {
	
					frmCadastroMatricula.getInstance().setVisible(true);
					frmMenu.getInstance().getDskPrincipal().add(frmCadastroMatricula.getInstance());
					frmCadastroMatricula.getInstance().setSelected(true);
					frmCadastroMatricula.getInstance().preencheCadastro(new DAOMatricula()
							.buscaMatricula(matriculaTableModel.getMatricula(tbTabelaMatricula.getSelectedRow()).getRegistro()));
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de matrícula!",
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
			tbTabelaMatricula.setRowSorter(null);
			matriculaTableModel.limpar();

			if (rdbtnFiltrarPorRegistro.isSelected())
				matriculaTableModel.addListaDeMatriculas(
						new DAOMatricula().listaMatricula(Integer.parseInt(txtBuscarPor.getText())));
			else if (rdbtnFiltrarPorAluno.isSelected())
				matriculaTableModel
						.addListaDeMatriculas(new DAOMatricula().listaMatriculaAluno(txtBuscarPor.getText()));
			else if (rdbtnFiltrarPorResponsavel.isSelected())
				matriculaTableModel
						.addListaDeMatriculas(new DAOMatricula().listaMatriculaResponsavel(txtBuscarPor.getText()));

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(matriculaTableModel);
			tbTabelaMatricula.setRowSorter(sorter);
		} else {
			tbTabelaMatricula.setRowSorter(null);
			matriculaTableModel.limpar();
			matriculaTableModel.addListaDeMatriculas(new DAOMatricula().listaMatricula());

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(matriculaTableModel);
			tbTabelaMatricula.setRowSorter(sorter);
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
