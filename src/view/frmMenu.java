package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOConexaoMySQL;
import model.Usuario;

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
	
	public static frmMenu getFrmMenu(Usuario usuario){
		if (singleton == null) {
			singleton = new frmMenu(usuario);
		}
		
		return singleton;
	}
	
	public static frmMenu getFrmMenu() {
		return singleton;
	}
	
	public frmMenu(Usuario usuario) {
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

		mntmCadastroFuncionario = new JMenuItem("Funcionario");
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
		mnPesquisar.add(mntmPesquisarFuncionario);
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

	}
	
	public JDesktopPane getDskPrincipal() {
		return dskPrincipal;
	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mntmCadastroUsuario) {
			try {
				mntmCadastroUsuarios_click();
			} catch (ParseException | PropertyVetoException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == mntmCadastroFuncionario) {
			try {
				mntmCadastroFuncionarios_click();
			} catch (ParseException | PropertyVetoException | SQLException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == mntmCadastroCargo) {
			try {
				mntmCadastroCargos_click();
			} catch (ParseException | PropertyVetoException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == mntmCadastroAluno) {
			try {
				mntmCadastroAlunos_click();
			} catch (ParseException | PropertyVetoException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == mntmCadastroResponsavel) {
			try {
				mntmCadastroResponsaveis_click();
			} catch (ParseException | PropertyVetoException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == mntmCadastroMatricula) {
			try {
				mntmCadastroMatriculas_click();
			} catch (ParseException | PropertyVetoException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == mntmCadastroMensalidade) {
			try {
				mntmCadastroMensalidades_click();
			} catch (ParseException | PropertyVetoException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == mntmPesquisarFuncionario) {
			try {
				mntmPesquisarFuncionario_click();
			} catch (ParseException | PropertyVetoException | SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	private void mntmCadastroUsuarios_click() throws ParseException, PropertyVetoException {
		frmCadastroUsuario.getFrmCadastroUsuario();

		if (frmCadastroUsuario.getFrmCadastroUsuario().isVisible()) {
			frmCadastroUsuario.getFrmCadastroUsuario().setSelected(true);
		} else {
			frmCadastroUsuario.getFrmCadastroUsuario().setVisible(true);
			dskPrincipal.add(frmCadastroUsuario.getFrmCadastroUsuario());
			frmCadastroUsuario.getFrmCadastroUsuario().setSelected(true);
		}
	}

	private void mntmCadastroFuncionarios_click() throws ParseException, PropertyVetoException, SQLException {
		frmCadastroFuncionario.getFrmCadastroFuncionario();

		if (frmCadastroFuncionario.getFrmCadastroFuncionario().isVisible()) {
			frmCadastroFuncionario.getFrmCadastroFuncionario().setSelected(true);
		} else {
			//Reseta as configurações do formulario, por que nesse caso a pessoa está abrindo novamente
			frmCadastroFuncionario.getFrmCadastroFuncionario().resetaFormulario();
			frmCadastroFuncionario.getFrmCadastroFuncionario().setVisible(true);
			dskPrincipal.add(frmCadastroFuncionario.getFrmCadastroFuncionario());
			frmCadastroFuncionario.getFrmCadastroFuncionario().setSelected(true);
		}
	}

	private void mntmCadastroCargos_click() throws ParseException, PropertyVetoException {
		frmCadastroCargo.getFrmCadastroCargo();

		if (frmCadastroCargo.getFrmCadastroCargo().isVisible()) {
			frmCadastroCargo.getFrmCadastroCargo().setSelected(true);
		} else {
			frmCadastroCargo.getFrmCadastroCargo().setVisible(true);
			dskPrincipal.add(frmCadastroCargo.getFrmCadastroCargo());
			frmCadastroCargo.getFrmCadastroCargo().setSelected(true);
		}
	}

	private void mntmCadastroAlunos_click() throws ParseException, PropertyVetoException {

		frmCadastroAluno.getFrmCadastroAluno();

		if (frmCadastroAluno.getFrmCadastroAluno().isVisible()) {
			frmCadastroAluno.getFrmCadastroAluno().setSelected(true);
		} else {
			frmCadastroAluno.getFrmCadastroAluno().setVisible(true);
			dskPrincipal.add(frmCadastroAluno.getFrmCadastroAluno());
			frmCadastroAluno.getFrmCadastroAluno().setSelected(true);
		}

	}

	private void mntmCadastroResponsaveis_click() throws ParseException, PropertyVetoException {

		frmCadastroResponsavel.getFrmCadastroResponsaveis();

		if (frmCadastroResponsavel.getFrmCadastroResponsaveis().isVisible()) {
			frmCadastroResponsavel.getFrmCadastroResponsaveis().setSelected(true);
		} else {
			frmCadastroResponsavel.getFrmCadastroResponsaveis().setVisible(true);
			dskPrincipal.add(frmCadastroResponsavel.getFrmCadastroResponsaveis());
			frmCadastroResponsavel.getFrmCadastroResponsaveis().setSelected(true);
		}

	}

	private void mntmCadastroMatriculas_click() throws ParseException, PropertyVetoException {

		frmCadastroMatricula.getFrmCadastroMatricula();

		if (frmCadastroMatricula.getFrmCadastroMatricula().isVisible()) {
			frmCadastroMatricula.getFrmCadastroMatricula().setSelected(true);
		} else {
			frmCadastroMatricula.getFrmCadastroMatricula().setVisible(true);
			dskPrincipal.add(frmCadastroMatricula.getFrmCadastroMatricula());
			frmCadastroMatricula.getFrmCadastroMatricula().setSelected(true);
		}

	}

	private void mntmCadastroMensalidades_click() throws ParseException, PropertyVetoException {
		frmCadastroMensalidade.getFrmCadastroMensalidade();

		if (frmCadastroMensalidade.getFrmCadastroMensalidade().isVisible()) {
			frmCadastroMensalidade.getFrmCadastroMensalidade().setSelected(true);
		} else {
			frmCadastroMensalidade.getFrmCadastroMensalidade().setVisible(true);
			dskPrincipal.add(frmCadastroMensalidade.getFrmCadastroMensalidade());
			frmCadastroMensalidade.getFrmCadastroMensalidade().setSelected(true);
		}
	}
	
	private void mntmPesquisarFuncionario_click() throws ParseException, PropertyVetoException, SQLException {
		frmPesquisaFuncionario.getFrmPesquisaFuncionario();
		
		if (frmPesquisaFuncionario.getFrmPesquisaFuncionario().isVisible()) {
			frmPesquisaFuncionario.getFrmPesquisaFuncionario().setSelected(true);
		} else {
			frmPesquisaFuncionario.getFrmPesquisaFuncionario().setVisible(true);
			dskPrincipal.add(frmPesquisaFuncionario.getFrmPesquisaFuncionario());
			frmPesquisaFuncionario.getFrmPesquisaFuncionario().setSelected(true);
		}
	}
	
	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	// Evento ao sair
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