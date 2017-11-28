package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controler.ComboKeyHandler;
import dao.DAOConexaoMySQL;
import dao.DAOUsuario;
import model.Usuario;

public class frmLogin extends JFrame implements ActionListener, KeyListener, WindowListener{

	private JPanel contentPane;
	private JPasswordField txtSenha;
	private JButton btnEntrar;
	private JComboBox cbLogin;
	
	public frmLogin() throws SQLException{
		addWindowListener(this);
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 231, 344);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 147, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 211, 46, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 236, 205, 28);
		contentPane.add(txtSenha);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addKeyListener(this);
		btnEntrar.addActionListener(this);
		btnEntrar.setBounds(126, 281, 89, 23);
		contentPane.add(btnEntrar);
		
		JLabel lblCadeado = new JLabel("");
		lblCadeado.setIcon(new ImageIcon(frmLogin.class.getResource("/imagens/usuarioLogin_128x128.png")));
		lblCadeado.setBounds(47, 9, 142, 127);
		contentPane.add(lblCadeado);
		
		/*
		 * Carrega combobox com dados cadastrados no banco de dados
		 * */
		cbLogin = new JComboBox();
		DefaultComboBoxModel<String> cbLoginModel = new DefaultComboBoxModel<>();
		for (int i = 0; i < new DAOUsuario().listaUsuarios().size(); i++) {
			cbLoginModel.addElement(new DAOUsuario().listaUsuarios().get(i).getLogin());
		}
		
		cbLogin.setModel(cbLoginModel);
		cbLogin.setBounds(10, 172, 205, 28);
		//cbLogin.setSelectedItem(null);
		
		cbLogin.setEditable(true);
		JTextField edtCbLogin = (JTextField) cbLogin.getEditor().getEditorComponent();
		edtCbLogin.addKeyListener(new ComboKeyHandler(cbLogin));
		
		contentPane.add(cbLogin);
		DAOConexaoMySQL.getInstance();
	}
	
	private void btnEntrar_click() throws SQLException{
		
		DAOUsuario dao = new DAOUsuario();
		
		if (dao.verificaLogin(new Usuario(cbLogin.getSelectedIndex(), cbLogin.getSelectedItem().toString(), txtSenha.getText())) != null) {
           JOptionPane.showMessageDialog(null,"Login realizado com sucesso", "Sistema", JOptionPane.INFORMATION_MESSAGE);
           
           dispose();
           
           frmMenu.getFrmMenu(new Usuario(cbLogin.getSelectedIndex(), cbLogin.getSelectedItem().toString(), txtSenha.getText())).setVisible(true);;
           
		}else {
			JOptionPane.showMessageDialog(null,"Acesso Negado!", "Sistema", JOptionPane.ERROR_MESSAGE);
			txtSenha.setText(null);
			txtSenha.requestFocus();
		}
	
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnEntrar) {
			try {
				btnEntrar_click();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == btnEntrar) {
			try {
				btnEntrar_keyPressed(e);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	private void btnEntrar_keyPressed(KeyEvent e) throws SQLException {
		if (e.getKeyCode() == e.VK_ENTER) {
			btnEntrar_click();
		}
	}
	
	public void windowActivated(WindowEvent e) {
	}
	
	public void windowClosed(WindowEvent e) {
	}
	
	public void windowClosing(WindowEvent e) {
		try {
			DAOConexaoMySQL.closeInstance();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
}
