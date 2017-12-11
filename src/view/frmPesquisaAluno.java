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
import dao.DAOAluno;
import model.Aluno;
import tableModel.FuncionarioTableModel;
import tableModel.AlunoTableModel;
import tableModel.UsuarioTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JRadioButton;

public class frmPesquisaAluno extends JInternalFrame
		implements ActionListener, InternalFrameListener, DocumentListener, KeyListener {
	private JTable tbTabelaAluno;

	private static frmPesquisaAluno singleton = null;
	private JScrollPane srpPainelTabela;
	private JTextField txtBuscarPor;
	private AlunoTableModel alunoTableModel;
	private JRadioButton rdbtnFiltrarPorRegistro;
	private JRadioButton rdbtnFiltrarPorNome;
	private JRadioButton rdbtnFiltrarPorCpf;
	private JRadioButton rdbtnFiltrarPorRG;
	private JButton btnConfirma;

	public static frmPesquisaAluno getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaAluno();
		}

		return singleton;
	}

	public frmPesquisaAluno() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Aluno");
		setBounds(100, 100, 719, 396);
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 103, 685, 213);
		getContentPane().add(srpPainelTabela);

		alunoTableModel = new AlunoTableModel();
		alunoTableModel.addListaDeAlunos(new DAOAluno().listaAluno());

		tbTabelaAluno = new JTable();
		tbTabelaAluno.setModel(alunoTableModel);
		tbTabelaAluno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(alunoTableModel);
		tbTabelaAluno.setRowSorter(sorter);

		srpPainelTabela.setViewportView(tbTabelaAluno);

		btnConfirma = new JButton("Abrir cadastro de respons\u00E1vel");
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
		rdbtnFiltrarPorNome.setBounds(144, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorNome);

		rdbtnFiltrarPorCpf = new JRadioButton("Filtrar por CPF");
		rdbtnFiltrarPorCpf.addActionListener(this);
		rdbtnFiltrarPorCpf.setBounds(266, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorCpf);

		rdbtnFiltrarPorRG = new JRadioButton("Filtrar por RG");
		rdbtnFiltrarPorRG.addActionListener(this);
		rdbtnFiltrarPorRG.setBounds(387, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorRG);

		btngpGrupoFiltros.add(rdbtnFiltrarPorNome);
		btngpGrupoFiltros.add(rdbtnFiltrarPorCpf);
		btngpGrupoFiltros.add(rdbtnFiltrarPorRegistro);
		btngpGrupoFiltros.add(rdbtnFiltrarPorRG);

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
		tbTabelaAluno.setRowSorter(null);
		alunoTableModel.limpar();
		alunoTableModel.addListaDeAlunos(new DAOAluno().listaAluno());

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(alunoTableModel);
		tbTabelaAluno.setRowSorter(sorter);

		// tbTabelaFuncionario.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == rdbtnFiltrarPorRegistro) {
				rdbtnFiltrarPorRegistro_click();
			} else if (e.getSource() == rdbtnFiltrarPorCpf) {
				rdbtnFiltrarPorCPF_click();
			} else if (e.getSource() == rdbtnFiltrarPorNome) {
				rdbtnFiltrarPorNome_click();
			}else if (e.getSource() == rdbtnFiltrarPorRG) {
				rdbtnFiltrarPorRG_click();
			} else if (e.getSource() == btnConfirma) {
				btnConfirma_click();
			} 
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void rdbtnFiltrarPorRG_click() {
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

	private void btnConfirma_click() throws ParseException, SQLException, PropertyVetoException {
	
		if (btnConfirma.getText().equals("Abrir cadastro de aluno")) {
			
			if (tbTabelaAluno.getSelectedRow() > -1) {
				frmCadastroAluno.getInstance();
	
				if (frmCadastroAluno.getInstance().isVisible()) {
					frmCadastroAluno.getInstance().preencheCadastro(new DAOAluno().buscaAluno(alunoTableModel.getAluno(tbTabelaAluno.getSelectedRow()).getRegistro()));
					frmCadastroAluno.getInstance().setSelected(true);
				} else {
	
					frmCadastroAluno.getInstance().setVisible(true);
					frmMenu.getFrmMenu().getDskPrincipal().add(frmCadastroAluno.getInstance());
					frmCadastroAluno.getInstance().setSelected(true);
					frmCadastroAluno.getInstance().preencheCadastro(new DAOAluno()
							.buscaAluno(alunoTableModel.getAluno(tbTabelaAluno.getSelectedRow()).getRegistro()));
				}
	
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de funcionario!",
						"Sistema", JOptionPane.ERROR_MESSAGE);
			}
		
		} else if (btnConfirma.getText().equals("Abrir cadastro de matrícula")){
			
			if (tbTabelaAluno.getSelectedRow() > -1) {
				frmCadastroMatricula.getInstance();
	
				if (frmCadastroMatricula.getInstance().isVisible()) {
					frmCadastroMatricula.getInstance().preencheAluno(new DAOAluno().buscaAluno(alunoTableModel.getAluno(tbTabelaAluno.getSelectedRow()).getRegistro()));
					frmCadastroMatricula.getInstance().setSelected(true);
				} else {
	
					frmCadastroMatricula.getInstance().setVisible(true);
					frmMenu.getFrmMenu().getDskPrincipal().add(frmCadastroMatricula.getInstance());
					frmCadastroMatricula.getInstance().setSelected(true);
					frmCadastroMatricula.getInstance().preencheAluno(new DAOAluno()
							.buscaAluno(alunoTableModel.getAluno(tbTabelaAluno.getSelectedRow()).getRegistro()));
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
			tbTabelaAluno.setRowSorter(null);
			alunoTableModel.limpar();

			if (rdbtnFiltrarPorRegistro.isSelected())
				alunoTableModel.addListaDeAlunos(
						new DAOAluno().listaAluno(Integer.parseInt(txtBuscarPor.getText())));
			else if (rdbtnFiltrarPorNome.isSelected())
				alunoTableModel
						.addListaDeAlunos(new DAOAluno().listaAlunoNome(txtBuscarPor.getText()));
			else if (rdbtnFiltrarPorRG.isSelected())
				alunoTableModel
						.addListaDeAlunos(new DAOAluno().listaAlunoRG(txtBuscarPor.getText()));
			else if (rdbtnFiltrarPorCpf.isSelected())
				alunoTableModel
						.addListaDeAlunos(new DAOAluno().listaAlunoCPF(txtBuscarPor.getText()));

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(alunoTableModel);
			tbTabelaAluno.setRowSorter(sorter);
		} else {
			tbTabelaAluno.setRowSorter(null);
			alunoTableModel.limpar();
			alunoTableModel.addListaDeAlunos(new DAOAluno().listaAluno());

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(alunoTableModel);
			tbTabelaAluno.setRowSorter(sorter);
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
