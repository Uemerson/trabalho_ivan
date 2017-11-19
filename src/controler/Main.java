package controler;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.frmLogin;
import view.frmMenu;

public class Main {
	public static void main(String Args[]) {
		
		//Muda o look and feels padrão do java
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
