package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import controler.ComboKeyHandler;
import controler.FuncoesGlobais;
import dao.DAOMensalidade;
import dao.DAOMensalidade;
import model.Mensalidade;

public class frmCadastroMensalidade extends JInternalFrame implements ActionListener, InternalFrameListener {
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

	public static frmCadastroMensalidade getInstance() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroMensalidade();
		}

		return singleton;
	}
	
	public static void setInstance(frmCadastroMensalidade estado) {
		singleton = estado;
	}
	
	public frmCadastroMensalidade() {
		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);
		
		addInternalFrameListener(this);
		setBounds(100, 100, 591, 300);
		setClosable(true);
		setTitle("Cadastro de Mensalidade");
		getContentPane().setLayout(null);

		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 99);
		pnlBotoes.setLayout(null);
		getContentPane().add(pnlBotoes);

		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(frmCadastroMensalidade.class.getResource("/imagens/novo 48x48.png")));
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 79);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnNovo);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this);
		btnExcluir.setIcon(new ImageIcon(frmCadastroMensalidade.class.getResource("/imagens/excluir 48x48.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 79);
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setIcon(new ImageIcon(frmCadastroMensalidade.class.getResource("/imagens/editar 48x48.png")));
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 79);
		btnAlterar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAlterar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnAlterar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(frmCadastroMensalidade.class.getResource("/imagens/salvar 48x48.png")));
		btnSalvar.addActionListener(this);
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 79);
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroMensalidade.class.getResource("/imagens/cancelar 48x48.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 79);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnCancelar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(frmCadastroMensalidade.class.getResource("/imagens/lupa 48x48.png")));
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(460, 11, 85, 79);
		btnPesquisar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPesquisar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnPesquisar);

		pnlCadastroDeMensalidade = new JPanel();
		pnlCadastroDeMensalidade.setBorder(
				new TitledBorder(null, "Cadastro de Menssalidade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCadastroDeMensalidade.setBounds(10, 121, 555, 139);
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
		
		cbSerie.setEditable(true);
		JTextField edtCbSerie = (JTextField) cbSerie.getEditor().getEditorComponent();
		edtCbSerie.addKeyListener(new ComboKeyHandler(cbSerie));
		
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
		cbFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"Cheque", "\u00C0 vista", "Cart\u00E3o"}));
		cbFormaPagamento.setBounds(168, 99, 112, 28);
		cbFormaPagamento.setSelectedItem(null);
		
		cbFormaPagamento.setEditable(true);
		JTextField edtCbFormaPagamento = (JTextField) cbFormaPagamento.getEditor().getEditorComponent();
		edtCbFormaPagamento.addKeyListener(new ComboKeyHandler(cbFormaPagamento));
		
		pnlCadastroDeMensalidade.add(cbFormaPagamento);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnNovo) {
				btnNovo_click();
			} else if (e.getSource() == btnCancelar) {
				btnCancelar_click();
			} else if (e.getSource() == btnSalvar) {
				btnSalvar_click();
			} else if (e.getSource() == btnPesquisar) {
				btnPesquisar_click();
			} else if (e.getSource() == btnExcluir) {
				btnExcluir_click();
			} else if (e.getSource() == btnAlterar) {
				btnAlterar_click();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação, tente novamente!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void btnAlterar_click() {
		FuncoesGlobais.ativaCampos(pnlCadastroDeMensalidade);
		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setEnabled(false);
		cbSerie.requestFocus();
	}
	
	private void btnExcluir_click() throws NumberFormatException, SQLException {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cadastro?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			DAOMensalidade daoMensalidade = new DAOMensalidade();

			daoMensalidade.excluirMensalidade(Integer.parseInt(txtRegistro.getText()));
			JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "Sistema",
					JOptionPane.INFORMATION_MESSAGE);

			FuncoesGlobais.limpaCampos(pnlCadastroDeMensalidade);
			FuncoesGlobais.desativaCampos(pnlCadastroDeMensalidade);
			FuncoesGlobais.desativaCampos(pnlBotoes);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			btnNovo.requestFocus();
		}
	}
	
	public void preencheCadastro(Mensalidade mensalidade) {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.limpaCampos(pnlCadastroDeMensalidade);
		FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeMensalidade);
		FuncoesGlobais.desativaCampos(pnlCadastroDeMensalidade);

		btnNovo.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnPesquisar.setEnabled(true);
		
		txtRegistro.setText(Integer.toString(mensalidade.getRegistro()));
		cbSerie.setSelectedItem(mensalidade.getSerie());
		cbFormaPagamento.setSelectedItem(mensalidade.getForma_de_Pagamento());
		txtValorMensalidade.setText(Double.toString(mensalidade.getValor_da_Mensalidade()));
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
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o novo cadastrado?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {

			FuncoesGlobais.limpaCampos(pnlCadastroDeMensalidade);
			FuncoesGlobais.desativaCampos(pnlCadastroDeMensalidade);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeMensalidade);
			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);

			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void btnSalvar_click() throws SQLException {
		if (FuncoesGlobais.verificaCampos(pnlCadastroDeMensalidade)) {

			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
					JOptionPane.ERROR_MESSAGE);

		} else {

			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar o cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
				
				Mensalidade mensalidade = new Mensalidade();
				mensalidade.setSerie(cbSerie.getSelectedItem().toString());
				mensalidade.setValor_da_Mensalidade(Double.parseDouble(txtValorMensalidade.getText()));
				mensalidade.setForma_de_Pagamento(cbFormaPagamento.getSelectedItem().toString());
				
				DAOMensalidade daoMensalidade = new DAOMensalidade();
				if (txtRegistro.getText().equals("NOVO")) {
					daoMensalidade.novoMensalidade(mensalidade);
				}else {
					mensalidade.setRegistro(Integer.parseInt(txtRegistro.getText()));
					daoMensalidade.atualizaMensalidade(mensalidade);
				}
				
				FuncoesGlobais.limpaCampos(pnlCadastroDeMensalidade);
				FuncoesGlobais.desativaCampos(pnlCadastroDeMensalidade);
				FuncoesGlobais.desativaCampos(pnlBotoes);
				
				btnNovo.setEnabled(true);
				btnPesquisar.setEnabled(true);

				JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void btnPesquisar_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaMensalidade.getInstance().isVisible()) {
			frmPesquisaMensalidade.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaMensalidade.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaMensalidade.getInstance().getBtnConfirma().setText("Abrir cadastro de mensalidade");
			frmPesquisaMensalidade.getInstance().setSelected(true);
		} else {
			frmPesquisaMensalidade.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getInstance().getDskPrincipal().add(frmPesquisaMensalidade.getInstance());
			frmPesquisaMensalidade.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaMensalidade.getInstance().getBtnConfirma().setText("Abrir cadastro de mensalidade");
			frmPesquisaMensalidade.getInstance().setVisible(true);
			frmPesquisaMensalidade.getInstance().setSelected(true);
		}
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}
	
	//Limpa buffer de memoria
	public void internalFrameClosed(InternalFrameEvent e) {
		this.singleton = null;
	}

	public void internalFrameClosing(InternalFrameEvent e) {
	}

	public void internalFrameDeactivated(InternalFrameEvent e) {
	}

	public void internalFrameDeiconified(InternalFrameEvent e) {
	}

	public void internalFrameIconified(InternalFrameEvent e) {
	}

	public void internalFrameOpened(InternalFrameEvent e) {
	}
}
