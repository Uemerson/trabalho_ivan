package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import controler.FuncoesGlobais;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmCadastroMatricula extends JInternalFrame implements ActionListener {
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
	private JLabel lblAluno;
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

	private static frmCadastroMatricula singleton = null;
	private JPanel pnlCadastroDeMatricula;
	private JComboBox cbAluno;
	private JButton btnAluno;
	private JTextField txtAluno;
	private JLabel lblAno;

	public static frmCadastroMatricula getFrmCadastroMatricula() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroMatricula();
		}

		return singleton;
	}

	public frmCadastroMatricula() throws ParseException {
		setBounds(100, 100, 721, 300);
		setClosable(true);
		setTitle("Cadastro de Matr\u00EDcula");
		getContentPane().setLayout(null);

		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 685, 64);
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

		pnlCadastroDeMatricula = new JPanel();
		pnlCadastroDeMatricula.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Cadastro de Matr\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		txtRegistro.setBounds(141, 23, 139, 28);
		pnlCadastroDeMatricula.add(txtRegistro);

		lblDataDaMatricula = new JLabel("Data da Matr\u00EDcula");
		lblDataDaMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataDaMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDaMatricula.setBounds(10, 62, 153, 28);
		pnlCadastroDeMatricula.add(lblDataDaMatricula);

		lblAluno = new JLabel("Aluno");
		lblAluno.setHorizontalAlignment(SwingConstants.LEFT);
		lblAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAluno.setBounds(301, 23, 90, 28);
		pnlCadastroDeMatricula.add(lblAluno);

		cbAluno = new JComboBox();
		cbAluno.setEnabled(false);
		cbAluno.setBounds(343, 23, 139, 28);
		pnlCadastroDeMatricula.add(cbAluno);

		txtDataDaMatricula = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDaMatricula.setEnabled(false);
		txtDataDaMatricula.setBounds(141, 62, 139, 28);
		pnlCadastroDeMatricula.add(txtDataDaMatricula);

		lblNivelEmQueEstaSendoMatriculado = new JLabel(" N\u00EDvel em que est\u00E1 sendo matriculado");
		lblNivelEmQueEstaSendoMatriculado.setHorizontalAlignment(SwingConstants.LEFT);
		lblNivelEmQueEstaSendoMatriculado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNivelEmQueEstaSendoMatriculado.setBounds(301, 62, 240, 28);
		pnlCadastroDeMatricula.add(lblNivelEmQueEstaSendoMatriculado);

		cbNivelQueEstaSendoMatriculado = new JComboBox();
		cbNivelQueEstaSendoMatriculado.setEnabled(false);
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
		cbSerie.setModel(new DefaultComboBoxModel(new String[] { "1\u00BA ano", "2\u00BA ano", "3\u00BA ano",
				"4\u00BA ano", "5\u00BA ano", "6\u00BA ano", "7\u00BA ano", "8\u00BA ano", "9\u00BA ano",
				"1\u00BA colegial", "2\u00BA colegial", "3\u00BA colegial" }));
		cbSerie.setBounds(141, 101, 139, 28);
		cbSerie.setSelectedItem(null);
		pnlCadastroDeMatricula.add(cbSerie);

		lblTurno = new JLabel("Turno");
		lblTurno.setHorizontalAlignment(SwingConstants.LEFT);
		lblTurno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTurno.setBounds(301, 101, 55, 28);
		pnlCadastroDeMatricula.add(lblTurno);

		cbTurno = new JComboBox();
		cbTurno.setEnabled(false);
		cbTurno.setModel(new DefaultComboBoxModel(new String[] { "Matutino", "Vespetino" }));
		cbTurno.setBounds(351, 101, 90, 28);
		cbTurno.setSelectedItem(null);
		pnlCadastroDeMatricula.add(cbTurno);

		lblPaiOuResponsavel = new JLabel("Pai ou Respons\u00E1vel");
		lblPaiOuResponsavel.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaiOuResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaiOuResponsavel.setBounds(10, 138, 144, 28);
		pnlCadastroDeMatricula.add(lblPaiOuResponsavel);

		cbPaiOuResponsavel = new JComboBox();
		cbPaiOuResponsavel.setEnabled(false);
		cbPaiOuResponsavel.setBounds(141, 138, 338, 28);
		pnlCadastroDeMatricula.add(cbPaiOuResponsavel);

		lblSecretrio = new JLabel("Secret\u00E1rio (a)");
		lblSecretrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecretrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecretrio.setBounds(451, 101, 90, 28);
		pnlCadastroDeMatricula.add(lblSecretrio);

		cbSecretario = new JComboBox();
		cbSecretario.setEnabled(false);
		cbSecretario.setBounds(551, 101, 124, 28);
		pnlCadastroDeMatricula.add(cbSecretario);

		JButton btnPesquisarResponsavel = new JButton("");
		btnPesquisarResponsavel
				.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/pesquisar.png")));
		btnPesquisarResponsavel.setEnabled(false);
		btnPesquisarResponsavel.setBounds(485, 138, 28, 28);
		pnlCadastroDeMatricula.add(btnPesquisarResponsavel);
		
		btnAluno = new JButton("");
		btnAluno.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/pesquisar.png")));
		btnAluno.setEnabled(false);
		btnAluno.setBounds(489, 23, 28, 28);
		pnlCadastroDeMatricula.add(btnAluno);
		
		txtAluno = new JTextField();
		txtAluno.setEnabled(false);
		txtAluno.setColumns(10);
		txtAluno.setBounds(596, 23, 79, 28);
		pnlCadastroDeMatricula.add(txtAluno);
		
		lblAno = new JLabel("Aluno");
		lblAno.setHorizontalAlignment(SwingConstants.LEFT);
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAno.setBounds(551, 23, 55, 28);
		pnlCadastroDeMatricula.add(lblAno);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNovo) {
			btnNovo_click();
		} else if (e.getSource() == btnSalvar) {
			btnSalvar_click();
		} else if (e.getSource() == btnCancelar) {
			btnCancelar_click();
		}
	}

	private void btnNovo_click() {
		FuncoesGlobais.limpaCampos(pnlCadastroDeMatricula);
		FuncoesGlobais.ativaCampos(pnlCadastroDeMatricula);
		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setText("NOVO");
		txtRegistro.setEnabled(false);
		
		cbAluno.requestFocus();
	}

	private void btnCancelar_click() {

		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o novo cadastrado?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {

			FuncoesGlobais.limpaCampos(pnlCadastroDeMatricula);
			FuncoesGlobais.desativaCampos(pnlCadastroDeMatricula);
			FuncoesGlobais.desativaCampos(pnlCadastroDeMatricula);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeMatricula);
			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);

			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void btnSalvar_click() {

		if (FuncoesGlobais.verificaCampos(pnlCadastroDeMatricula)) {

			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
					JOptionPane.ERROR_MESSAGE);

		} else {

			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar o novo cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {

				FuncoesGlobais.limpaCampos(pnlCadastroDeMatricula);
				FuncoesGlobais.desativaCampos(pnlCadastroDeMatricula);
				FuncoesGlobais.desativaCampos(pnlBotoes);

				btnNovo.setEnabled(true);
				btnPesquisar.setEnabled(true);

				JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}
}
