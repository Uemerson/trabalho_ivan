package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controler.FuncoesGlobais;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.InternalFrameEvent;

public class frmCadastroCargo extends JInternalFrame implements ActionListener, InternalFrameListener {
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

	private static frmCadastroCargo singleton = null;
	
	public static frmCadastroCargo getInstance() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroCargo();
		}
		
		return singleton;
	}
	
	public frmCadastroCargo() {
		addInternalFrameListener(this);
		setBounds(100, 100, 585, 245);
		setClosable(true);
		setTitle("Cadastro de Cargo");
		getContentPane().setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setLayout(null);
		pnlBotoes.setBounds(10, 11, 549, 64);
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
		btnPesquisar.setEnabled(false);
		btnPesquisar.setBounds(460, 11, 85, 44);
		pnlBotoes.add(btnPesquisar);
		
		pnlCadastroDeCargo = new JPanel();
		pnlCadastroDeCargo.setBorder(new TitledBorder(null, "Cadastro de Cargo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCadastroDeCargo.setBounds(10, 80, 549, 129);
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

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnNovo) {
			btnNovo_click();
		}else if(e.getSource() == btnCancelar) {
			btnCancelar_click();
		}else if (e.getSource() == btnSalvar) {
			btnSalvar_click();
		}
		
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
		if(JOptionPane.showConfirmDialog(this, 
				"Deseja realmente cancelar o novo cadastrado?", 
				"Sistema", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
			
		
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
		if (FuncoesGlobais.verificaCampos(pnlCadastroDeCargo) == true) {
				
				JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema", JOptionPane.ERROR_MESSAGE);
			
		}else {
				
				if(JOptionPane.showConfirmDialog(this, 
												"Deseja realmente salvar o novo cadastrado?", 
												"Sistema", 
												JOptionPane.YES_NO_OPTION, 
												JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
					
					FuncoesGlobais.limpaCampos(pnlCadastroDeCargo);
					FuncoesGlobais.desativaCampos(pnlCadastroDeCargo);
					FuncoesGlobais.desativaCampos(pnlBotoes);
					
					btnNovo.setEnabled(true);
					btnPesquisar.setEnabled(true);
					
					JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public void internalFrameActivated(InternalFrameEvent e) {
	}
	public void internalFrameClosed(InternalFrameEvent e) {
	}
	public void internalFrameClosing(InternalFrameEvent e) {
		this.singleton = null;
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
