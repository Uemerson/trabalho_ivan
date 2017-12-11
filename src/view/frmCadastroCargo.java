package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import controler.FuncoesGlobais;
import dao.DAOCargo;
import dao.DAOFuncionario;
import model.Cargo;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class frmCadastroCargo extends JInternalFrame implements ActionListener, InternalFrameListener {
	private JTextField txtRegistro;
	private JTextField txtNome;
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

	private static frmCadastroCargo singleton = null;
	private JEditorPane edpDescricao;

	public static frmCadastroCargo getInstance() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroCargo();
		}

		return singleton;
	}

	public static void setInstance(frmCadastroCargo estado) {
		singleton = estado;
	}
	
	public frmCadastroCargo() {
		addInternalFrameListener(this);
		setBounds(100, 100, 585, 379);
		setClosable(true);
		setTitle("Cadastro de Cargo");
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		pnlBotoes = new JPanel();
		pnlBotoes.setLayout(null);
		pnlBotoes.setBounds(10, 11, 549, 100);
		getContentPane().add(pnlBotoes);

		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(frmCadastroCargo.class.getResource("/imagens/novo 48x48.png")));
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 79);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnNovo);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this);
		btnExcluir.setIcon(new ImageIcon(frmCadastroCargo.class.getResource("/imagens/excluir 48x48.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 79);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setIcon(new ImageIcon(frmCadastroCargo.class.getResource("/imagens/editar 48x48.png")));
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 79);
		btnAlterar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAlterar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnAlterar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(frmCadastroCargo.class.getResource("/imagens/salvar 48x48.png")));
		btnSalvar.addActionListener(this);
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 79);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroCargo.class.getResource("/imagens/cancelar 48x48.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 79);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnCancelar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(frmCadastroCargo.class.getResource("/imagens/lupa 48x48.png")));
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(460, 11, 85, 79);
		btnPesquisar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPesquisar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnPesquisar);

		pnlCadastroDeCargo = new JPanel();
		pnlCadastroDeCargo.setBorder(
				new TitledBorder(null, "Cadastro de Cargo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCadastroDeCargo.setBounds(10, 123, 549, 219);
		getContentPane().add(pnlCadastroDeCargo);
		pnlCadastroDeCargo.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 19, 90, 28);
		pnlCadastroDeCargo.add(lblRegistro);

		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(110, 19, 420, 28);
		pnlCadastroDeCargo.add(txtRegistro);

		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 59, 90, 28);
		pnlCadastroDeCargo.add(lblNome);

		lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescricao.setBounds(10, 99, 90, 28);
		pnlCadastroDeCargo.add(lblDescricao);

		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(110, 59, 420, 28);
		pnlCadastroDeCargo.add(txtNome);

		edpDescricao = new JEditorPane();
		edpDescricao.setEnabled(false);
		edpDescricao.setBounds(110, 103, 420, 97);
		pnlCadastroDeCargo.add(edpDescricao);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnNovo) {
				btnNovo_click();
			} else if (e.getSource() == btnCancelar) {
				btnCancelar_click();
			} else if (e.getSource() == btnSalvar) {
				btnSalvar_click();
			} else if (e.getSource() == btnAlterar) {
				btnAlterar_click();
			} else if (e.getSource() == btnPesquisar) {
				btnPesquisar_click();
			} else if (e.getSource() == btnExcluir) {
				btnExcluir_click();
			}
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			ex.printStackTrace();
		}
	}

	private void btnExcluir_click() throws NumberFormatException, SQLException {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cadastro?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			DAOFuncionario daoFuncionario = new DAOFuncionario();

			daoFuncionario.excluirFuncionario(Integer.parseInt(txtRegistro.getText()));
			JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "Sistema",
					JOptionPane.INFORMATION_MESSAGE);

			DAOCargo daoCargo = new DAOCargo();
			daoCargo.excluirCargo(Integer.parseInt(txtRegistro.getText()));

			FuncoesGlobais.limpaCampos(pnlCadastroDeCargo);
			FuncoesGlobais.desativaCampos(pnlCadastroDeCargo);
			FuncoesGlobais.desativaCampos(pnlBotoes);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			btnNovo.requestFocus();
		}
	}

	private void btnAlterar_click() {
		FuncoesGlobais.ativaCampos(pnlCadastroDeCargo);
		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setEnabled(false);
		txtNome.requestFocus();
	}

	private void btnNovo_click() {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.ativaCampos(pnlCadastroDeCargo);
		FuncoesGlobais.limpaCampos(pnlCadastroDeCargo);

		txtRegistro.setText("NOVO");
		txtRegistro.setEnabled(false);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtNome.requestFocus();
	}

	private void btnCancelar_click() {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o novo cadastrado?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

			FuncoesGlobais.limpaCampos(pnlCadastroDeCargo);
			FuncoesGlobais.desativaCampos(pnlCadastroDeCargo);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeCargo);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);

			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void btnSalvar_click() {

		ArrayList<Component> listaComp = new ArrayList<>();
		listaComp.add(edpDescricao);

		if (FuncoesGlobais.verificaCampos(pnlCadastroDeCargo, listaComp) == true) {

			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
					JOptionPane.ERROR_MESSAGE);

		} else {

			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar o novo cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

				try {
					Cargo cargo = new Cargo();
					cargo.setNome(txtNome.getText());
					cargo.setDescricao(edpDescricao.getText().isEmpty() ? null : edpDescricao.getText());

					DAOCargo daoCargo = new DAOCargo();

					if (txtRegistro.getText().equals("NOVO")) {
						daoCargo.novoCargo(cargo);
					} else {
						cargo.setRegistro(Integer.parseInt(txtRegistro.getText()));
						daoCargo.atualizaCargo(cargo);
					}
					FuncoesGlobais.limpaCampos(pnlCadastroDeCargo);
					FuncoesGlobais.desativaCampos(pnlCadastroDeCargo);
					FuncoesGlobais.desativaCampos(pnlBotoes);

					btnNovo.setEnabled(true);
					btnPesquisar.setEnabled(true);

					if (txtRegistro.getText().equals("NOVO")) {
						JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this, "Cadastro atualizado com sucesso!", "Sistema",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(this, "Não foi possivel salvar o cadastro de cargo, tente novamente!",
							"Sistema", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private void btnPesquisar_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaCargo.getInstance().isVisible()) {
			frmPesquisaCargo.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaCargo.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaCargo.getInstance().getBtnConfirma().setText("Abrir cadastro de cargo");
			frmPesquisaCargo.getInstance().setSelected(true);
		} else {
			frmPesquisaCargo.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getInstance().getDskPrincipal().add(frmPesquisaCargo.getInstance());
			frmPesquisaCargo.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaCargo.getInstance().getBtnConfirma().setText("Abrir cadastro de cargo");
			frmPesquisaCargo.getInstance().setVisible(true);
			frmPesquisaCargo.getInstance().setSelected(true);
		}
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}

	public void preencheCadastro(Cargo cargo) {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.limpaCampos(pnlCadastroDeCargo);
		FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeCargo);
		FuncoesGlobais.desativaCampos(pnlCadastroDeCargo);

		btnNovo.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnPesquisar.setEnabled(true);

		txtRegistro.setText(Integer.toString(cargo.getRegistro()));
		txtNome.setText(cargo.getNome());
		edpDescricao.setText(cargo.getDescricao());
	}

	// Limpa memoria
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
