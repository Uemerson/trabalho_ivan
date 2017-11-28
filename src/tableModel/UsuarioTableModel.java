package tableModel;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import model.Usuario;

public class UsuarioTableModel extends AbstractTableModel {

	private ArrayList<Usuario> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "LOGIN", "NOME FUNCIONARIO" };

	// Constantes representando o índice das colunas
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
	// Este método deve verificar o índice recebido por parâmetro e retornar o tipo
	// de classe correspondente à coluna.
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
	// Definir quais células são editáveis ou não
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	// Retornar o conteúdo da célula especificada
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pega o usuário referente a linha especificada.
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
	//Se as células não forem editáveis, nem precisamos implementar o método “setValueAt
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

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}
	
	// Retorna o usuário referente a linha especificada
	public Usuario getUsuario(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o usuário especificado ao modelo
	public void addUsuario(Usuario Usuario) {
	    // Adiciona o registro.
	    linhas.add(Usuario);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o último índice. A subtração é necessária
	    // porque os índices começam em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o usuário da linha especificada.
	public void removeUsuario(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudança.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de usuários no final da lista.
	public void addListaDeUsuarios(ArrayList<Usuario> Usuarios) {
	    // Pega o tamanho antigo da tabela, que servirá
	    // como índice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(Usuarios);
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(indice, indice + Usuarios.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de usuários.
	    linhas.clear();
	 
	    // Notifica a mudança.
	    fireTableDataChanged();
	}
}
