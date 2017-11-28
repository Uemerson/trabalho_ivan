package tableModel;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import model.Usuario;

public class UsuarioTableModel extends AbstractTableModel {

	private ArrayList<Usuario> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "LOGIN", "NOME FUNCIONARIO" };

	// Constantes representando o �ndice das colunas
	private static final int REGISTRO = 0;
	private static final int LOGIN = 1;
	private static final int NOME_FUNCIONARIO = 2;

	public UsuarioTableModel() {
		linhas = new ArrayList<Usuario>();
	}

	public UsuarioTableModel(ArrayList<Usuario> listaUsuario) {
		linhas = new ArrayList<Usuario>(listaUsuario);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	};

	@Override
	// Este m�todo deve verificar o �ndice recebido por par�metro e retornar o tipo
	// de classe correspondente � coluna.
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case REGISTRO:
			return Integer.class;
		case LOGIN:
			return String.class;
		case NOME_FUNCIONARIO:
			return String.class;
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	@Override
	// Definir quais c�lulas s�o edit�veis ou n�o
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	// Retornar o conte�do da c�lula especificada
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pega o usu�rio referente a linha especificada.
		Usuario usuario = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			return usuario.getRegistro();
		case LOGIN:
			return usuario.getLogin();
		case NOME_FUNCIONARIO:
			return usuario.getNome_funcionario();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	@Override
	//Se as c�lulas n�o forem edit�veis, nem precisamos implementar o m�todo �setValueAt
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Usuario usuario = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			usuario.setRegistro((int) aValue);
			break;

		case LOGIN:
			usuario.setLogin((String) aValue);
			break;
		case NOME_FUNCIONARIO:
			usuario.setNome_funcionario((String) aValue);
			break;

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
	}
	
	// Retorna o usu�rio referente a linha especificada
	public Usuario getUsuario(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o usu�rio especificado ao modelo
	public void addUsuario(Usuario Usuario) {
	    // Adiciona o registro.
	    linhas.add(Usuario);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o �ltimo �ndice. A subtra��o � necess�ria
	    // porque os �ndices come�am em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o usu�rio da linha especificada.
	public void removeUsuario(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudan�a.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de usu�rios no final da lista.
	public void addListaDeUsuarios(ArrayList<Usuario> Usuarios) {
	    // Pega o tamanho antigo da tabela, que servir�
	    // como �ndice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(Usuarios);
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(indice, indice + Usuarios.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de usu�rios.
	    linhas.clear();
	 
	    // Notifica a mudan�a.
	    fireTableDataChanged();
	}
}
