package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class frmMenu extends JFrame implements ActionListener{

	private JPanel pnlPrincipal;
	private JDesktopPane dskPrincipal;
	private JMenuBar mnbPrincipal;
	private JMenuItem mntmAlunos;
	private JMenuItem mntmFuncionarios;
	private JMenuItem mntmCargos;
	private JMenuItem mntmUsuario;
	private JMenuItem mntmMatricula;
	private JMenuItem mntmResponsveis;
	private JMenuItem mntmMensalidades;
	private JMenuItem mntmDisciplina;

	public frmMenu() {
		setTitle("Menu Principal");
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		
		mnbPrincipal = new JMenuBar();
		setJMenuBar(mnbPrincipal);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnbPrincipal.add(mnCadastro);
		
		mntmUsuario = new JMenuItem("Usu\u00E1rio");
		mntmUsuario.addActionListener(this);
		mnCadastro.add(mntmUsuario);
		
		
		mntmFuncionarios= new JMenuItem("Funcionario");
		mntmFuncionarios.addActionListener(this);
		mnCadastro.add(mntmFuncionarios);
		
		mntmCargos = new JMenuItem("Cargo");
		mntmCargos.addActionListener(this);
		mnCadastro.add(mntmCargos);
		
		mntmAlunos = new JMenuItem("Aluno");
		mntmAlunos.addActionListener(this);
		mnCadastro.add(mntmAlunos);
		
		mntmResponsveis = new JMenuItem("Respons\u00E1vel");
		mntmResponsveis.addActionListener(this);
		mnCadastro.add(mntmResponsveis);
		
		mntmMatricula = new JMenuItem("Matr\u00EDcula");
		mntmMatricula.addActionListener(this);
		mnCadastro.add(mntmMatricula);
		
		mntmMensalidades = new JMenuItem("Mensalidade");
		mntmMensalidades.addActionListener(this);
		mnCadastro.add(mntmMensalidades);
		
		mntmDisciplina = new JMenuItem("Disciplina");
		mntmDisciplina.addActionListener(this);
		mnCadastro.add(mntmDisciplina);
		
		JMenuBar menuBar = new JMenuBar();
		mnCadastro.add(menuBar);
		//setUndecorated(true);
		
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		
		//Obtem o tamanho do Jframe
		dskPrincipal = new JDesktopPane();
		dskPrincipal.setBounds(	0, 
								0, 
								(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
								(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		pnlPrincipal.add(dskPrincipal);
	
	}
	
	private void mntmAlunos_click() {
		
			frmCadastroAluno form;
			try {
				form = frmCadastroAluno.getFrmCadastroAluno();
				form.setVisible(true);
				dskPrincipal.add(form);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private void mntmFuncionarios_click() throws ParseException {
		try {
			frmCadastroFuncionario form = new frmCadastroFuncionario();
			form.setVisible(true);
			dskPrincipal.add(form);
		}catch(java.lang.IllegalArgumentException ex) {
			System.out.println("Cadastro de aluno já está aberto!");
		}
	}
	
	private void mntmCargo_click() {
		try {
			frmCadastroCargo form = new frmCadastroCargo();
			form.setVisible(true);
			dskPrincipal.add(form);
		}catch(java.lang.IllegalArgumentException ex) {
			System.out.println("Cadastro de aluno já está aberto!");
		}
	}
	
	private void mntmUsuario_click() {
		try {
			frmCadastroUsuario form = new frmCadastroUsuario();
			form.setVisible(true);
			dskPrincipal.add(form);
		}catch(java.lang.IllegalArgumentException ex) {
			System.out.println("Cadastro de aluno já está aberto!");
		}
	}
	
	private void mntmMatricula_click() throws ParseException{
		try {
			frmCadastroMatricula form = new frmCadastroMatricula();
			form.setVisible(true);
			dskPrincipal.add(form);
		}catch(java.lang.IllegalArgumentException ex) {
			System.out.println("Cadastro de aluno já está aberto!");
		}
	}
	
	private void mntmResponsavel_click() throws ParseException{
		try {
			frmCadastroResponsaveis form = new frmCadastroResponsaveis();
			form.setVisible(true);
			dskPrincipal.add(form);
		}catch(java.lang.IllegalArgumentException ex) {
			System.out.println("Cadastro de aluno já está aberto!");
		}
	}
	
	private void mntmMenssalidade_click() throws ParseException{
		try {
			frmCadastroMenssalidade form = new frmCadastroMenssalidade();
			form.setVisible(true);
			dskPrincipal.add(form);
		}catch(java.lang.IllegalArgumentException ex) {
			System.out.println("Cadastro de aluno já está aberto!");
		}
	}
	
	private void mntmDisciplina_click(){
		try {
			frmCadastroDisciplina form = new frmCadastroDisciplina();
			form.setVisible(true);
			dskPrincipal.add(form);
		}catch(java.lang.IllegalArgumentException ex) {
			System.out.println("Cadastro de aluno já está aberto!");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmAlunos) {
			mntmAlunos_click();
			
		}else if(e.getSource() == mntmFuncionarios ) {
			
			try {
				mntmFuncionarios_click();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()== mntmCargos) {
			
			mntmCargo_click();
			
		}else if(e.getSource()== mntmUsuario) {
			
			mntmUsuario_click();
			
		}else if(e.getSource()== mntmMatricula) {
			
			try {
				mntmMatricula_click();
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}else if(e.getSource()== mntmResponsveis) {
			
			try {
				mntmResponsavel_click();
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}else if(e.getSource()== mntmMensalidades) {
			
			try {
				mntmMenssalidade_click();
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}else if(e.getSource()== mntmDisciplina) {
			
			mntmDisciplina_click();
		}
	}
}