package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import controler.FuncoesGlobais;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmCadastroMensalidade extends JInternalFrame implements ActionListener {
	private JPanel pnlBotoes;
	private JButton btnExcluir;
	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JPanel pnlCadastroDeMensalidade;
	private JLabel lblRegistro;
	private JTextField txtRegistro;
	private JLabel lblSrie;
	private JComboBox cbSerie;
	private JLabel lblValorDaMensalidade;
	private JTextField txtValorMensalidade;
	private JLabel lblFormaDePagamento;
	private JComboBox cbFormaPagamento;

	private static frmCadastroMensalidade singleton = null;
	
	public static frmCadastroMensalidade getFrmCadastroMensalidade() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroMensalidade();
		}
		
		return singleton;
	}
	
	public frmCadastroMensalidade() {
		setBounds(100, 100, 591, 258);
		setClosable(true);
		setTitle("Cadastro de Mensalidade");
		getContentPane().setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 64);
		pnlBotoes.setLayout(null);
		getContentPane().add(pnlBotoes);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(this);
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
		btnSalvar.addActionListener(this);
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 44);
		pnlBotoes.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 44);
		pnlBotoes.add(btnCancelar);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(460, 11, 85, 44);
		pnlBotoes.add(btnPesquisar);
		
		pnlCadastroDeMensalidade = new JPanel();
		pnlCadastroDeMensalidade.setBorder(new TitledBorder(null, "Cadastro de Menssalidade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCadastroDeMensalidade.setBounds(10, 85, 555, 139);
		getContentPane().add(pnlCadastroDeMensalidade);
		pnlCadastroDeMensalidade.setLayout(null);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 21, 90, 28);
		pnlCadastroDeMensalidade.add(lblRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(168, 21, 377, 28);
		pnlCadastroDeMensalidade.add(txtRegistro);
		
		lblSrie = new JLabel("S\u00E9rie");
		lblSrie.setHorizontalAlignment(SwingConstants.LEFT);
		lblSrie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSrie.setBounds(10, 62, 90, 28);
		pnlCadastroDeMensalidade.add(lblSrie);
		
		cbSerie = new JComboBox();
		cbSerie.setEnabled(false);
		cbSerie.setModel(new DefaultComboBoxModel(new String[] {"1\u00BA ano", "2\u00BA ano", "3\u00BA ano", "4\u00BA ano", "5\u00BA ano", "6\u00BA ano", "7\u00BA ano", "8\u00BA ano", "9\u00BA ano", "1\u00BA colegial", "2\u00BA colegial", "3\u00BA colegial"}));
		cbSerie.setBounds(168, 62, 112, 28);
		cbSerie.setSelectedItem(null);
		pnlCadastroDeMensalidade.add(cbSerie);
		
		lblValorDaMensalidade = new JLabel("Valor da Mensalidade");
		lblValorDaMensalidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorDaMensalidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorDaMensalidade.setBounds(290, 62, 136, 28);
		pnlCadastroDeMensalidade.add(lblValorDaMensalidade);
		
		txtValorMensalidade = new JTextField();
		txtValorMensalidade.setEnabled(false);
		txtValorMensalidade.setColumns(10);
		txtValorMensalidade.setBounds(432, 62, 112, 28);
		pnlCadastroDeMensalidade.add(txtValorMensalidade);
		
		lblFormaDePagamento = new JLabel("Forma de Pagamento");
		lblFormaDePagamento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormaDePagamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaDePagamento.setBounds(10, 99, 150, 28);
		pnlCadastroDeMensalidade.add(lblFormaDePagamento);
		
		cbFormaPagamento = new JComboBox();
		cbFormaPagamento.setEnabled(false);
		cbFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"Cheque", "Avista", "Cart\u00E3o"}));
		cbFormaPagamento.setBounds(168, 99, 112, 28);
		cbFormaPagamento.setSelectedItem(null);
		pnlCadastroDeMensalidade.add(cbFormaPagamento);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNovo) {
			btnNovo_click();
		}else if (e.getSource() == btnCancelar) {
			btnCancelar_click();
		}else if (e.getSource() == btnSalvar) {
			btnSalvar_click();
		}
	}

	private void btnNovo_click() {
		FuncoesGlobais.limpaCampos(pnlCadastroDeMensalidade);
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.ativaCampos(pnlCadastroDeMensalidade);
		
		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setText("NOVO");
		txtRegistro.setEnabled(false);
		
		cbSerie.requestFocus();
	}

	private void btnCancelar_click() {
		if(JOptionPane.showConfirmDialog(this, 
				"Deseja realmente cancelar o novo cadastrado?", 
				"Sistema", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
			
		
			FuncoesGlobais.limpaCampos(pnlCadastroDeMensalidade);
			FuncoesGlobais.desativaCampos(pnlCadastroDeMensalidade);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeMensalidade);
			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			
			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void btnSalvar_click() {
		if (FuncoesGlobais.verificaCampos(pnlCadastroDeMensalidade)){
			
			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema", JOptionPane.ERROR_MESSAGE);
		
		}else {
			
			if(JOptionPane.showConfirmDialog(this, 
											"Deseja realmente salvar o novo cadastrado?", 
											"Sistema", 
											JOptionPane.YES_NO_OPTION, 
											JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
				
				FuncoesGlobais.limpaCampos(pnlCadastroDeMensalidade);
				FuncoesGlobais.desativaCampos(pnlBotoes);
				
				btnNovo.setEnabled(true);
				btnPesquisar.setEnabled(true);
				
				JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
