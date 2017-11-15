package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class frmCadastroAluno extends JInternalFrame {
	
	private static frmCadastroAluno singleton = null;
	
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JTextField txtRegistro;
	private JLabel lblRegistro;
	
	public frmCadastroAluno() {
		setClosable(true);
		setTitle("Cadastro de aluno");
		setBounds(100, 100, 586, 576);
		getContentPane().setLayout(null);
		
		JPanel pnlOpcoes = new JPanel();
		pnlOpcoes.setBounds(10, 11, 555, 64);
		getContentPane().add(pnlOpcoes);
		pnlOpcoes.setLayout(null);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 11, 80, 44);
		pnlOpcoes.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(100, 11, 80, 44);
		pnlOpcoes.add(btnExcluir);
		btnExcluir.setEnabled(false);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(190, 11, 80, 44);
		pnlOpcoes.add(btnAlterar);
		btnAlterar.setEnabled(false);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(280, 11, 80, 44);
		pnlOpcoes.add(btnSalvar);
		btnSalvar.setEnabled(false);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(370, 11, 80, 44);
		pnlOpcoes.add(btnCancelar);
		btnCancelar.setEnabled(false);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setEnabled(false);
		btnPesquisar.setBounds(460, 11, 85, 44);
		pnlOpcoes.add(btnPesquisar);
		
		JPanel pnlInformacoesAluno = new JPanel();
		pnlInformacoesAluno.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es do aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInformacoesAluno.setBounds(10, 86, 555, 449);
		getContentPane().add(pnlInformacoesAluno);
		pnlInformacoesAluno.setLayout(null);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 17, 80, 28);
		pnlInformacoesAluno.add(lblRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setBounds(100, 19, 445, 28);
		pnlInformacoesAluno.add(txtRegistro);
		txtRegistro.setColumns(10);
		
	}
	
	public static frmCadastroAluno getFrmCadastroAluno() {
		if (singleton == null) {
			singleton = new frmCadastroAluno();
		}
		
		//singleton.setVisible(true);
		
		return singleton;
	}
}
