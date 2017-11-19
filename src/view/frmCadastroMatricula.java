package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;


public class frmCadastroMatricula extends JInternalFrame {
	private JPanel pnlBotoes;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JTextField txtRegistro;
	private JLabel lblDataDaMatricula;
	private JLabel lblRegistro;
	private JLabel lblAno;
	private JFormattedTextField txtDataDaMatricula;
	private JLabel lblNivelEmQueEstaSendoMatriculado;
	private JComboBox cbNivelQueEstaSendoMatriculado;
	private JLabel lblSerie;
	private JComboBox cbSerie;
	private JLabel lblTurno;
	private JComboBox cbTurno;
	private JLabel lblPaiOuResponsavel;
	private JComboBox cbPaiOuResponsavel;
	private JLabel lblSecretrio;
	private JComboBox cbSecretario;

	 frmCadastroMatricula() throws ParseException {
		setBounds(100, 100, 721, 300);
		setClosable(true);
		setTitle("Cadastro de Matr\u00EDcula");
		getContentPane().setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 685, 64);
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
		
		JPanel pnlCadastroDeMatricula = new JPanel();
		pnlCadastroDeMatricula.setBorder(new TitledBorder(null, "Cad\u00E1stro de Matr\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCadastroDeMatricula.setBounds(10, 86, 685, 174);
		getContentPane().add(pnlCadastroDeMatricula);
		pnlCadastroDeMatricula.setLayout(null);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 23, 90, 28);
		pnlCadastroDeMatricula.add(lblRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(141, 23, 338, 28);
		pnlCadastroDeMatricula.add(txtRegistro);
		
		lblDataDaMatricula = new JLabel("Data da Matr\u00EDcula");
		lblDataDaMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataDaMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDaMatricula.setBounds(10, 60, 153, 28);
		pnlCadastroDeMatricula.add(lblDataDaMatricula);
		
		lblAno = new JLabel("Ano");
		lblAno.setHorizontalAlignment(SwingConstants.LEFT);
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAno.setBounds(509, 23, 90, 28);
		pnlCadastroDeMatricula.add(lblAno);
		
		JComboBox cbAno = new JComboBox();
		cbAno.setEnabled(false);
		cbAno.setToolTipText("");
		cbAno.setBounds(551, 23, 124, 28);
		pnlCadastroDeMatricula.add(cbAno);
		
		txtDataDaMatricula = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDaMatricula.setEnabled(false);
		txtDataDaMatricula.setBounds(141, 62, 139, 30);
		pnlCadastroDeMatricula.add(txtDataDaMatricula);
		
		lblNivelEmQueEstaSendoMatriculado = new JLabel(" N\u00EDvel em que est\u00E1 sendo matriculado");
		lblNivelEmQueEstaSendoMatriculado.setHorizontalAlignment(SwingConstants.LEFT);
		lblNivelEmQueEstaSendoMatriculado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNivelEmQueEstaSendoMatriculado.setBounds(301, 62, 255, 28);
		pnlCadastroDeMatricula.add(lblNivelEmQueEstaSendoMatriculado);
		
		cbNivelQueEstaSendoMatriculado = new JComboBox();
		cbNivelQueEstaSendoMatriculado.setEnabled(false);
		cbNivelQueEstaSendoMatriculado.setToolTipText("");
		cbNivelQueEstaSendoMatriculado.setBounds(551, 62, 124, 28);
		pnlCadastroDeMatricula.add(cbNivelQueEstaSendoMatriculado);
		
		lblSerie = new JLabel("S\u00E9rie");
		lblSerie.setHorizontalAlignment(SwingConstants.LEFT);
		lblSerie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSerie.setBounds(10, 101, 90, 28);
		pnlCadastroDeMatricula.add(lblSerie);
		
		cbSerie = new JComboBox();
		cbSerie.setEnabled(false);
		cbSerie.setForeground(new Color(0, 0, 0));
		cbSerie.setModel(new DefaultComboBoxModel(new String[] {"1\u00BA ano", "2\u00BA ano", "3\u00BA ano", "4\u00BA ano", "5\u00BA ano", "6\u00BA ano", "7\u00BA ano", "8\u00BA ano", "9\u00BA ano", "1\u00BA colegial", "2\u00BA colegial", "3\u00BA colegial"}));
		cbSerie.setToolTipText("");
		cbSerie.setBounds(141, 101, 139, 28);
		pnlCadastroDeMatricula.add(cbSerie);
		
		lblTurno = new JLabel("Turno");
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTurno.setBounds(271, 101, 90, 28);
		pnlCadastroDeMatricula.add(lblTurno);
		
		cbTurno = new JComboBox();
		cbTurno.setEnabled(false);
		cbTurno.setModel(new DefaultComboBoxModel(new String[] {"Matutino", "Vespetino"}));
		cbTurno.setToolTipText("");
		cbTurno.setBounds(351, 101, 90, 28);
		pnlCadastroDeMatricula.add(cbTurno);
		
		lblPaiOuResponsavel = new JLabel("Pai ou Respons\u00E1vel");
		lblPaiOuResponsavel.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaiOuResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaiOuResponsavel.setBounds(10, 138, 144, 28);
		pnlCadastroDeMatricula.add(lblPaiOuResponsavel);
		
		cbPaiOuResponsavel = new JComboBox();
		cbPaiOuResponsavel.setEnabled(false);
		cbPaiOuResponsavel.setEditable(true);
		cbPaiOuResponsavel.setToolTipText("");
		cbPaiOuResponsavel.setBounds(141, 138, 338, 28);
		pnlCadastroDeMatricula.add(cbPaiOuResponsavel);
		
		lblSecretrio = new JLabel("Secret\u00E1rio (a)");
		lblSecretrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecretrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecretrio.setBounds(451, 101, 90, 28);
		pnlCadastroDeMatricula.add(lblSecretrio);
		
		cbSecretario = new JComboBox();
		cbSecretario.setEnabled(false);
		cbSecretario.setToolTipText("");
		cbSecretario.setBounds(551, 101, 124, 28);
		pnlCadastroDeMatricula.add(cbSecretario);
		
		JButton btnPesquisarResponsavel = new JButton("");
		btnPesquisarResponsavel.setEnabled(false);
		btnPesquisarResponsavel.setBounds(489, 138, 28, 28);
		pnlCadastroDeMatricula.add(btnPesquisarResponsavel);
		

	}
}
