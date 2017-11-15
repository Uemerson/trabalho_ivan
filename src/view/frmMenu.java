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
import java.awt.event.ActionEvent;

public class frmMenu extends JFrame implements ActionListener{

	private JPanel pnlPrincipal;
	private JDesktopPane dskPrincipal;
	private JMenuBar mnbPrincipal;
	private JMenuItem mntmAlunos;

	public frmMenu() {
		setTitle("Menu Principal");
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		
		mnbPrincipal = new JMenuBar();
		setJMenuBar(mnbPrincipal);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnbPrincipal.add(mnCadastro);
		
		JMenuItem mntmUsurio = new JMenuItem("Usu\u00E1rio");
		mnCadastro.add(mntmUsurio);
		
		JMenuItem mntmFuncionarios = new JMenuItem("Funcionarios");
		mnCadastro.add(mntmFuncionarios);
		
		JMenuItem mntmCargos = new JMenuItem("Cargos");
		mnCadastro.add(mntmCargos);
		
		mntmAlunos = new JMenuItem("Alunos");
		mntmAlunos.addActionListener(this);
		mnCadastro.add(mntmAlunos);
		
		JMenuItem mntmResponsveis = new JMenuItem("Respons\u00E1veis");
		mnCadastro.add(mntmResponsveis);
		
		JMenuItem mntmMatrcula = new JMenuItem("Matr\u00EDcula");
		mnCadastro.add(mntmMatrcula);
		
		JMenuItem mntmMensalidades = new JMenuItem("Mensalidades");
		mnCadastro.add(mntmMensalidades);
		
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
		try {
			frmCadastroAluno form = frmCadastroAluno.getFrmCadastroAluno();
			form.setVisible(true);
			dskPrincipal.add(form);
		}catch(java.lang.IllegalArgumentException ex) {
			System.out.println("Cadastro de aluno já está aberto!");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmAlunos) {
			mntmAlunos_click();
		}
	}
}