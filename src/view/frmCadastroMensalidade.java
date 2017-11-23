package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class frmCadastroMensalidade extends JInternalFrame {
	private JPanel pnlBotoes;
	private JButton btnExcluir;
	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JPanel pnlCadastroDeMenssalidade;
	private JLabel lblRegistro;
	private JTextField txtRegistro;
	private JLabel lblSrie;
	private JComboBox cbSerie;
	private JLabel lblValorDaMenssalida;
	private JTextField textField;
	private JLabel lblFormaDePagamento;
	private JComboBox comboBox;

	private static frmCadastroMensalidade singleton = null;
	
	public static frmCadastroMensalidade getFrmCadastroMensalidade() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroMensalidade();
		}
		
		return singleton;
	}
	
	public frmCadastroMensalidade() {
		setBounds(100, 100, 591, 300);
		setClosable(true);
		setTitle("Cadastro de Menssalidade");
		getContentPane().setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 64);
		pnlBotoes.setLayout(null);
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
		
		pnlCadastroDeMenssalidade = new JPanel();
		pnlCadastroDeMenssalidade.setBorder(new TitledBorder(null, "Cadastro de Menssalidade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCadastroDeMenssalidade.setBounds(10, 85, 555, 175);
		getContentPane().add(pnlCadastroDeMenssalidade);
		pnlCadastroDeMenssalidade.setLayout(null);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 21, 90, 28);
		pnlCadastroDeMenssalidade.add(lblRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(168, 23, 377, 28);
		pnlCadastroDeMenssalidade.add(txtRegistro);
		
		lblSrie = new JLabel("S\u00E9rie");
		lblSrie.setHorizontalAlignment(SwingConstants.LEFT);
		lblSrie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSrie.setBounds(10, 60, 90, 28);
		pnlCadastroDeMenssalidade.add(lblSrie);
		
		cbSerie = new JComboBox();
		cbSerie.setEnabled(false);
		cbSerie.setModel(new DefaultComboBoxModel(new String[] {"1\u00BA ano", "2\u00BA ano", "3\u00BA ano", "4\u00BA ano", "5\u00BA ano", "6\u00BA ano", "7\u00BA ano", "8\u00BA ano", "9\u00BA ano", "1\u00BA colegial", "2\u00BA colegial", "3\u00BA colegial"}));
		cbSerie.setBounds(168, 62, 112, 28);
		pnlCadastroDeMenssalidade.add(cbSerie);
		
		lblValorDaMenssalida = new JLabel("Valor da Menssalidade");
		lblValorDaMenssalida.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorDaMenssalida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorDaMenssalida.setBounds(290, 62, 136, 28);
		pnlCadastroDeMenssalidade.add(lblValorDaMenssalida);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(432, 62, 112, 28);
		pnlCadastroDeMenssalidade.add(textField);
		
		lblFormaDePagamento = new JLabel("Forma de Pagamento");
		lblFormaDePagamento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormaDePagamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaDePagamento.setBounds(10, 99, 150, 28);
		pnlCadastroDeMenssalidade.add(lblFormaDePagamento);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cheque", "Avista", "Cart\u00E3o"}));
		comboBox.setBounds(168, 101, 112, 28);
		pnlCadastroDeMenssalidade.add(comboBox);

	}

}
