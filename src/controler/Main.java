package controler;

import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.DAOConexaoMySQL;
import view.frmLogin;
import view.frmMenu;

public class Main {
	public static void main(String Args[]) throws SQLException {
		
		//Muda o look and feels padr�o do java
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		//Chama o menu principal
		new frmLogin().setVisible(true);
		
	}
}
