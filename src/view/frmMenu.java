package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.DAOAluno;
import dao.DAOConexaoMySQL;
import dao.DAOFuncionario;
import model.AlunoRelatorio;
import model.Usuario;
import net.sf.jasperreports.engine.JRException;
import relatorio.Relatorio;

public class frmMenu extends JFrame implements ActionListener, WindowListener {

	private JPanel pnlPrincipal;
	private JDesktopPane dskPrincipal;
	private JMenuBar mnbPrincipal;
	private JMenuItem mntmCadastroAluno;
	private JMenuItem mntmCadastroFuncionario;
	private JMenuItem mntmCadastroCargo;
	private JMenuItem mntmCadastroUsuario;
	private JMenuItem mntmCadastroMatricula;
	private JMenuItem mntmCadastroResponsavel;
	private JMenuItem mntmCadastroMensalidade;
	private JMenuItem mntmPesquisarFuncionario;

	private static frmMenu singleton = null;
	private JMenuItem mntmPesquisaUsuario;
	private JMenuItem mntmPesquisarAluno;
	private JMenuItem mntmPesquisarCargo;
	private JMenuItem mntmPesquisarResponsavel;
	private JMenuItem mntmPesquisarMatricula;
	private JMenuItem mntmPesquisarMensalidade;
	private JMenuItem mntmRelatorioAlunosCadastrados;

	// Nao esquecer de limapr buffer
	public static frmMenu getInstance(Usuario usuario) throws SQLException {
		if (singleton == null) {
			singleton = new frmMenu(usuario);
		}

		return singleton;
	}

	public static void setInstance(frmMenu estado) {
		singleton = estado;
	}

	public static frmMenu getInstance() {
		return singleton;
	}

	private void liberaMenuAdministrador() {
		mntmCadastroUsuario.setVisible(true);
		mntmPesquisaUsuario.setVisible(true);

		mntmCadastroFuncionario.setVisible(true);
		mntmPesquisarFuncionario.setVisible(true);

		mntmCadastroCargo.setVisible(true);
		mntmPesquisarCargo.setVisible(true);

		mntmCadastroAluno.setVisible(true);
		mntmPesquisarAluno.setVisible(true);

		mntmCadastroResponsavel.setVisible(true);
		mntmPesquisarResponsavel.setVisible(true);

		mntmCadastroMatricula.setVisible(true);
		mntmPesquisarMatricula.setVisible(true);

		mntmCadastroMensalidade.setVisible(true);
		mntmPesquisarMensalidade.setVisible(true);
		
		mntmRelatorioAlunosCadastrados.setVisible(true);
	}

	private void liberaMenuSecretario() {
		mntmCadastroUsuario.setVisible(false);
		mntmPesquisaUsuario.setVisible(false);

		mntmCadastroFuncionario.setVisible(false);
		mntmPesquisarFuncionario.setVisible(false);

		mntmCadastroCargo.setVisible(false);
		mntmPesquisarCargo.setVisible(false);

		mntmCadastroAluno.setVisible(true);
		mntmPesquisarAluno.setVisible(true);

		mntmCadastroResponsavel.setVisible(true);
		mntmPesquisarResponsavel.setVisible(true);

		mntmCadastroMatricula.setVisible(true);
		mntmPesquisarMatricula.setVisible(true);

		mntmCadastroMensalidade.setVisible(false);
		mntmPesquisarMensalidade.setVisible(false);
		
		mntmRelatorioAlunosCadastrados.setVisible(true);
	}

	public frmMenu(Usuario usuario) throws SQLException {
		addWindowListener(this);
		setTitle("Menu Principal");

		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 640, 480);

		mnbPrincipal = new JMenuBar();
		setJMenuBar(mnbPrincipal);

		JMenu mnCadastro = new JMenu("Cadastro");
		mnbPrincipal.add(mnCadastro);

		mntmCadastroUsuario = new JMenuItem("Usu\u00E1rio");
		mntmCadastroUsuario.addActionListener(this);
		mnCadastro.add(mntmCadastroUsuario);

		mntmCadastroFuncionario = new JMenuItem("Funcion\u00E1rio");
		mntmCadastroFuncionario.addActionListener(this);
		mnCadastro.add(mntmCadastroFuncionario);

		mntmCadastroCargo = new JMenuItem("Cargo");
		mntmCadastroCargo.addActionListener(this);
		mnCadastro.add(mntmCadastroCargo);

		mntmCadastroAluno = new JMenuItem("Aluno");
		mntmCadastroAluno.addActionListener(this);
		mnCadastro.add(mntmCadastroAluno);

		mntmCadastroResponsavel = new JMenuItem("Respons\u00E1vel");
		mntmCadastroResponsavel.addActionListener(this);
		mnCadastro.add(mntmCadastroResponsavel);

		mntmCadastroMatricula = new JMenuItem("Matr\u00EDcula");
		mntmCadastroMatricula.addActionListener(this);
		mnCadastro.add(mntmCadastroMatricula);

		mntmCadastroMensalidade = new JMenuItem("Mensalidade");
		mntmCadastroMensalidade.addActionListener(this);
		mnCadastro.add(mntmCadastroMensalidade);

		JMenu mnPesquisar = new JMenu("Pesquisar");
		mnbPrincipal.add(mnPesquisar);

		mntmPesquisarFuncionario = new JMenuItem("Funcion\u00E1rio");
		mntmPesquisarFuncionario.addActionListener(this);

		mntmPesquisaUsuario = new JMenuItem("Usu\u00E1rio");
		mntmPesquisaUsuario.addActionListener(this);
		mnPesquisar.add(mntmPesquisaUsuario);
		mnPesquisar.add(mntmPesquisarFuncionario);

		mntmPesquisarAluno = new JMenuItem("Aluno");
		mntmPesquisarAluno.addActionListener(this);

		mntmPesquisarCargo = new JMenuItem("Cargo");
		mntmPesquisarCargo.addActionListener(this);
		mnPesquisar.add(mntmPesquisarCargo);
		mnPesquisar.add(mntmPesquisarAluno);

		mntmPesquisarResponsavel = new JMenuItem("Respons\u00E1vel");
		mntmPesquisarResponsavel.addActionListener(this);
		mnPesquisar.add(mntmPesquisarResponsavel);

		mntmPesquisarMatricula = new JMenuItem("Matr\u00EDcula");
		mntmPesquisarMatricula.addActionListener(this);
		mnPesquisar.add(mntmPesquisarMatricula);

		mntmPesquisarMensalidade = new JMenuItem("Mensalidade");
		mntmPesquisarMensalidade.addActionListener(this);
		mnPesquisar.add(mntmPesquisarMensalidade);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		mnbPrincipal.add(mnRelatrio);
		
		mntmRelatorioAlunosCadastrados = new JMenuItem("Alunos Cadastrados");
		mntmRelatorioAlunosCadastrados.addActionListener(this);
		mnRelatrio.add(mntmRelatorioAlunosCadastrados);
		// setUndecorated(true);

		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);

		// Obtem o tamanho do Jframe
		dskPrincipal = new JDesktopPane();
		dskPrincipal.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

		pnlPrincipal.add(dskPrincipal);

		// Libera os menus de acordo com o cargo do usu�rio
		if (new DAOFuncionario().buscaCargo(usuario).getNome().equals("Administrador")) {
			liberaMenuAdministrador();
		} else if (new DAOFuncionario().buscaCargo(usuario).getNome().replace("(a)", "").equals("Secretario")) {
			liberaMenuSecretario();
		} else if (new DAOFuncionario().buscaCargo(usuario).getNome().replace("(a)", "").equals("Diretor")) {
			liberaMenuAdministrador();
		} else {
			liberaMenuSecretario();
		}
	}

	public JDesktopPane getDskPrincipal() {
		return dskPrincipal;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == mntmCadastroUsuario) {
				mntmCadastroUsuarios_click();
			} else if (e.getSource() == mntmCadastroFuncionario) {
				mntmCadastroFuncionarios_click();
			} else if (e.getSource() == mntmCadastroCargo) {
				mntmCadastroCargos_click();
			} else if (e.getSource() == mntmCadastroAluno) {
				mntmCadastroAlunos_click();
			} else if (e.getSource() == mntmCadastroResponsavel) {
				mntmCadastroResponsaveis_click();
			} else if (e.getSource() == mntmCadastroMatricula) {
				mntmCadastroMatriculas_click();
			} else if (e.getSource() == mntmCadastroMensalidade) {
				mntmCadastroMensalidades_click();
			} else if (e.getSource() == mntmPesquisarFuncionario) {
				mntmPesquisarFuncionario_click();
			} else if (e.getSource() == mntmPesquisaUsuario) {
				mntmPesquisarUsuario_click();
			} else if (e.getSource() == mntmPesquisarAluno) {
				mntmPesquisarAluno_click();
			} else if (e.getSource() == mntmPesquisarCargo) {
				mntmPesquisarCargo_click();
			} else if (e.getSource() == mntmPesquisarResponsavel) {
				mntmPesquisarResponsavel_click();
			} else if (e.getSource() == mntmPesquisarMatricula) {
				mntmPesquisarMatricula_click();
			} else if (e.getSource() == mntmPesquisarMensalidade) {
				mntmPesquisarMensalidade_click();
			} else if (e.getSource() == mntmRelatorioAlunosCadastrados) {
				mntmRelatorioAlunosCadastrados_click();
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir a��o, tente novamente!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void mntmRelatorioAlunosCadastrados_click() throws SQLException, ParseException {
		//Gera a lista de alunos cadastrados
		ArrayList<AlunoRelatorio> lista = new ArrayList<>();
		
		DAOAluno daoAluno = new DAOAluno();
		
		for (int i = 0; i < daoAluno.listaAluno().size(); i++) {
			
			MaskFormatter mask = new MaskFormatter("(##)####-####");
			mask.setValueContainsLiteralCharacters(false);
			
			lista.add(new AlunoRelatorio(
					daoAluno.listaAlunoRelatorio().get(i).getRegistro(),
					daoAluno.listaAlunoRelatorio().get(i).getNome(),
					daoAluno.listaAlunoRelatorio().get(i).getCidade(),
					daoAluno.listaAlunoRelatorio().get(i).getEstado(),
					mask.valueToString(daoAluno.listaAlunoRelatorio().get(i).getCelular()).toString()
					));
		}
		
		Relatorio r = new Relatorio();
		try {
			r.gerarRelatorio(lista);
		} catch (JRException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao gerar relat�rio de alunos cadastrados!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void mntmCadastroUsuarios_click() throws ParseException, PropertyVetoException, SQLException {
		if (frmCadastroUsuario.getInstance().isVisible()) {
			frmCadastroUsuario.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmCadastroUsuario.getInstance());
			frmCadastroUsuario.getInstance().setVisible(true);
			frmCadastroUsuario.getInstance().setSelected(true);
		}
	}

	private void mntmCadastroFuncionarios_click() throws ParseException, PropertyVetoException, SQLException {
		if (frmCadastroFuncionario.getInstance().isVisible()) {
			frmCadastroFuncionario.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmCadastroFuncionario.getInstance());
			frmCadastroFuncionario.getInstance().setVisible(true);
			frmCadastroFuncionario.getInstance().setSelected(true);
		}
	}

	private void mntmCadastroCargos_click() throws ParseException, PropertyVetoException {
		if (frmCadastroCargo.getInstance().isVisible()) {
			frmCadastroCargo.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmCadastroCargo.getInstance());
			frmCadastroCargo.getInstance().setVisible(true);
			frmCadastroCargo.getInstance().setSelected(true);
		}
	}

	private void mntmCadastroAlunos_click() throws ParseException, PropertyVetoException, SQLException {
		if (frmCadastroAluno.getInstance().isVisible()) {
			frmCadastroAluno.getInstance().setSelected(true);
		} else {
			frmCadastroAluno.getInstance().setVisible(true);
			dskPrincipal.add(frmCadastroAluno.getInstance());
			frmCadastroAluno.getInstance().setSelected(true);
		}
	}

	private void mntmCadastroResponsaveis_click() throws ParseException, PropertyVetoException {
		if (frmCadastroResponsavel.getInstance().isVisible()) {
			frmCadastroResponsavel.getInstance().setSelected(true);
		} else {
			frmCadastroResponsavel.getInstance().setVisible(true);
			dskPrincipal.add(frmCadastroResponsavel.getInstance());
			frmCadastroResponsavel.getInstance().setSelected(true);
		}

	}

	private void mntmCadastroMatriculas_click() throws ParseException, PropertyVetoException, SQLException {
		if (frmCadastroMatricula.getInstance().isVisible()) {
			frmCadastroMatricula.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmCadastroMatricula.getInstance());
			frmCadastroMatricula.getInstance().setVisible(true);
			frmCadastroMatricula.getInstance().setSelected(true);
		}

	}

	private void mntmCadastroMensalidades_click() throws ParseException, PropertyVetoException {
		if (frmCadastroMensalidade.getInstance().isVisible()) {
			frmCadastroMensalidade.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmCadastroMensalidade.getInstance());
			frmCadastroMensalidade.getInstance().setVisible(true);
			frmCadastroMensalidade.getInstance().setSelected(true);
		}
	}

	private void mntmPesquisarFuncionario_click() throws ParseException, PropertyVetoException, SQLException {
		if (frmPesquisaFuncionario.getInstance().isVisible()) {
			frmPesquisaFuncionario.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmPesquisaFuncionario.getInstance());
			frmPesquisaFuncionario.getInstance().setVisible(true);
			frmPesquisaFuncionario.getInstance().setSelected(true);
		}
	}

	private void mntmPesquisarAluno_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaAluno.getInstance().isVisible()) {
			frmPesquisaAluno.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmPesquisaAluno.getInstance());
			frmPesquisaAluno.getInstance().setVisible(true);
			frmPesquisaAluno.getInstance().setSelected(true);
		}
	}

	private void mntmPesquisarUsuario_click() throws ParseException, PropertyVetoException, SQLException {
		if (frmPesquisaUsuario.getInstance().isVisible()) {
			frmPesquisaUsuario.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmPesquisaUsuario.getInstance());
			frmPesquisaUsuario.getInstance().setVisible(true);
			frmPesquisaUsuario.getInstance().setSelected(true);
		}
	}

	private void mntmPesquisarCargo_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaCargo.getInstance().isVisible()) {
			frmPesquisaCargo.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmPesquisaCargo.getInstance());
			frmPesquisaCargo.getInstance().setVisible(true);
			frmPesquisaCargo.getInstance().setSelected(true);
		}
	}

	private void mntmPesquisarResponsavel_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaResponsavel.getInstance().isVisible()) {
			frmPesquisaResponsavel.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmPesquisaResponsavel.getInstance());
			frmPesquisaResponsavel.getInstance().setVisible(true);
			frmPesquisaResponsavel.getInstance().setSelected(true);
		}
	}

	private void mntmPesquisarMatricula_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaMatricula.getInstance().isVisible()) {
			frmPesquisaMatricula.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmPesquisaMatricula.getInstance());
			frmPesquisaMatricula.getInstance().setVisible(true);
			frmPesquisaMatricula.getInstance().setSelected(true);
		}
	}

	private void mntmPesquisarMensalidade_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaMensalidade.getInstance().isVisible()) {
			frmPesquisaMensalidade.getInstance().setSelected(true);
		} else {
			dskPrincipal.add(frmPesquisaMensalidade.getInstance());
			frmPesquisaMensalidade.getInstance().setVisible(true);
			frmPesquisaMensalidade.getInstance().setSelected(true);
		}
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	// Evento ao sair e limpando a memoria do singleton
	public void windowClosing(WindowEvent e) {

		Object[] titulos_botoes = { "Sair", "Logoff", "Cancelar" };

		switch (JOptionPane.showOptionDialog(this, "O que deseja fazer?", "Sistema", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, titulos_botoes, titulos_botoes[0])) {
		// Sair
		case JOptionPane.YES_OPTION:

			try {
				DAOConexaoMySQL.closeInstance();
				System.exit(0);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			break;

		// Logoff
		case JOptionPane.NO_OPTION:

			dispose();

			try {
				frmLogin form = new frmLogin();
				form = new frmLogin();
				form.setVisible(true);
				// this.singleton = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			break;

		// Cancelar
		case JOptionPane.CANCEL_OPTION:

			break;

		default:
			break;
		}

	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}
}