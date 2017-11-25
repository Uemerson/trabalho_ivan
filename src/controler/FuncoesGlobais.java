package controler;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FuncoesGlobais {

	// Limpa todos os campos que estão no painel
	public static void limpaCampos(JPanel painel) {

		for (int i = 0; i < painel.getComponentCount(); i++) {
			// varre todos os componentes
			Component c = painel.getComponent(i);

			// JTextField
			if (c instanceof JTextField && !(c instanceof JFormattedTextField)) {
				JTextField field = (JTextField) c;
				field.setText(null);
			}

			// JFormattedTextField
			else if (c instanceof JTextField && c instanceof JFormattedTextField) {
				JFormattedTextField field = (JFormattedTextField) c;
				field.setValue(null);
			}

			else if (c instanceof JEditorPane) {
				JEditorPane field = (JEditorPane) c;
				field.setText(null);
			}

			// JCombobox
			else if (c instanceof JComboBox) {
				JComboBox field = (JComboBox) c;
				field.setSelectedItem(null);
			}
		}

	}

	// Desativa todos os campos que estão no painel
	public static void desativaCampos(JPanel painel) {
		for (int i = 0; i < painel.getComponentCount(); i++) {
			// varre todos os componentes
			Component c = painel.getComponent(i);
			c.setEnabled(false);
		}
	}

	// Ativa todos os campos que estão no painel
	public static void ativaCampos(JPanel painel) {
		for (int i = 0; i < painel.getComponentCount(); i++) {
			// varre todos os componentes
			Component c = painel.getComponent(i);
			c.setEnabled(true);
		}
	}

	// Verifica todos os campos em branco
	public static boolean verificaCampos(JPanel painel) {
		boolean vazio = false;

		for (int i = 0; i < painel.getComponentCount(); i++) {
			// varre todos os componentes
			Component c = painel.getComponent(i);

			// JTextField
			if (c instanceof JTextField && !(c instanceof JFormattedTextField)) {
				JTextField field = (JTextField) c;

				if (field.getText().equals("") || field.getText().equals(null)) {
					field.setBorder(new LineBorder(new Color(255, 67, 4, 255)));
					vazio = true;
				} else {
					JTextField bordaPadrao = new JTextField();
					field.setBorder(bordaPadrao.getBorder());
				}
			}

			// JFormattedTextField
			else if (c instanceof JTextField && c instanceof JFormattedTextField) {
				JFormattedTextField field = (JFormattedTextField) c;

				if (field.getValue() == null) {
					field.setBorder(new LineBorder(new Color(255, 67, 4, 255)));
					vazio = true;
				} else {
					JFormattedTextField bordaPadrao = new JFormattedTextField();
					field.setBorder(bordaPadrao.getBorder());
				}
			}

			// JEditorPane
			else if (c instanceof JEditorPane) {
				JEditorPane field = (JEditorPane) c;

				if (field.getText().equals("") || field.getText().equals(null)) {
					field.setBorder(new LineBorder(new Color(255, 67, 4, 255)));
					vazio = true;
				} else {
					JFormattedTextField bordaPadrao = new JFormattedTextField();
					field.setBorder(bordaPadrao.getBorder());
				}
			}

			// JComboBox
			else if (c instanceof JComboBox) {
				JComboBox field = (JComboBox) c;

				if (field.getSelectedIndex() <= -1) {
					field.setBorder(new LineBorder(new Color(255, 67, 4, 255)));
					vazio = true;
				} else {
					JComboBox bordaPadrao = new JComboBox();
					field.setBorder(bordaPadrao.getBorder());
				}
			}
		}

		return vazio;
	}

	// Verifica os campos em branco
	public static boolean verificaCampos(JPanel painel, ArrayList<Component> comp) {
		boolean vazio = false;

		for (int i = 0; i < painel.getComponentCount(); i++) {

			// varre todos os componentes
			Component c = painel.getComponent(i);
			boolean pula_campo = false;

			for (int j = 0; j < comp.size(); j++) {
				if (c == comp.get(j))
					pula_campo = true;
			}
			
			if (!pula_campo) {
				// JTextField
				if (c instanceof JTextField && !(c instanceof JFormattedTextField)) {
					JTextField field = (JTextField) c;

					if (field.getText().equals("") || field.getText().equals(null)) {
						field.setBorder(new LineBorder(new Color(255, 67, 4, 255)));
						vazio = true;
					} else {
						JTextField bordaPadrao = new JTextField();
						field.setBorder(bordaPadrao.getBorder());
					}
				}

				// JFormattedTextField
				else if (c instanceof JTextField && c instanceof JFormattedTextField) {
					JFormattedTextField field = (JFormattedTextField) c;

					if (field.getValue() == null) {
						field.setBorder(new LineBorder(new Color(255, 67, 4, 255)));
						vazio = true;
					} else {
						JFormattedTextField bordaPadrao = new JFormattedTextField();
						field.setBorder(bordaPadrao.getBorder());
					}
				}

				// JEditorPane
				else if (c instanceof JEditorPane) {
					JEditorPane field = (JEditorPane) c;

					if (field.getText().equals("") || field.getText().equals(null)) {
						field.setBorder(new LineBorder(new Color(255, 67, 4, 255)));
						vazio = true;
					} else {
						JFormattedTextField bordaPadrao = new JFormattedTextField();
						field.setBorder(bordaPadrao.getBorder());
					}
				}

				// JComboBox
				else if (c instanceof JComboBox) {
					JComboBox field = (JComboBox) c;

					if (field.getSelectedIndex() <= -1) {
						field.setBorder(new LineBorder(new Color(255, 67, 4, 255)));
						vazio = true;
					} else {
						JComboBox bordaPadrao = new JComboBox();
						field.setBorder(bordaPadrao.getBorder());
					}
				}
			}
		}
		return vazio;
	}

	// Reseta borda dos campos
	public static void resetaBordaPadrao(JPanel painel) {
		for (int i = 0; i < painel.getComponentCount(); i++) {
			// varre todos os componentes
			Component c = painel.getComponent(i);

			// JTextField
			if (c instanceof JTextField && !(c instanceof JFormattedTextField)) {
				JTextField field = (JTextField) c;
				JTextField bordaPadrao = new JTextField();
				field.setBorder(bordaPadrao.getBorder());
			}

			// JFormattedTextField
			else if (c instanceof JTextField && c instanceof JFormattedTextField) {
				JFormattedTextField field = (JFormattedTextField) c;
				JFormattedTextField bordaPadrao = new JFormattedTextField();
				field.setBorder(bordaPadrao.getBorder());
			}
			// JEditorPane
			else if (c instanceof JEditorPane) {
				JEditorPane field = (JEditorPane) c;
				JEditorPane bordaPadrao = new JEditorPane();
				field.setBorder(bordaPadrao.getBorder());
			}

			// JComboBox
			else if (c instanceof JComboBox) {
				JComboBox field = (JComboBox) c;
				JComboBox bordaPadrao = new JComboBox();
				field.setBorder(bordaPadrao.getBorder());
			}
		}
	}
}
