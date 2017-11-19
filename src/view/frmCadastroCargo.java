package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class frmCadastroCargo extends JInternalFrame {
	private JTextField txtRegistro;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JLabel lblRegistro;
	private JLabel lblNome;
	private JLabel lblDescricao;
	private JPanel pnlCadastroDeCargo;
	private JPanel pnlBotoes;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;

	
	public frmCadastroCargo() {
		setBounds(100, 100, 585, 302);
		setClosable(true);
		setTitle("Cadastro de Cargo");
		getContentPane().setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setLayout(null);
		pnlBotoes.setBounds(10, 11, 549, 64);
		getContentPane().add(pnlBotoes);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 11, 80, 44);
		pnlBotoes.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 44);
		pnlBotoes.add(btnExcluir);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 44);
		pnlBotoes.add(btnAlterar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 44);
		pnlBotoes.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 44);
		pnlBotoes.add(btnCancelar);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setEnabled(false);
		btnPesquisar.setBounds(460, 11, 85, 44);
		pnlBotoes.add(btnPesquisar);
		
		pnlCadastroDeCargo = new JPanel();
		pnlCadastroDeCargo.setBorder(new TitledBorder(null, "Cadastro de Cargo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCadastroDeCargo.setBounds(10, 80, 549, 178);
		getContentPane().add(pnlCadastroDeCargo);
		pnlCadastroDeCargo.setLayout(null);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 11, 90, 28);
		pnlCadastroDeCargo.add(lblRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(110, 11, 338, 28);
		pnlCadastroDeCargo.add(txtRegistro);
		
		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 50, 90, 28);
		pnlCadastroDeCargo.add(lblNome);
		
		lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescricao.setBounds(10, 89, 90, 28);
		pnlCadastroDeCargo.add(lblDescricao);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(110, 50, 338, 28);
		pnlCadastroDeCargo.add(txtNome);
		
		txtDescricao = new JTextField();
		txtDescricao.setEnabled(false);
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(110, 89, 338, 28);
		pnlCadastroDeCargo.add(txtDescricao);

	}

}
