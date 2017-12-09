package tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Cargo;

public class CargoTableModel extends AbstractTableModel {

	private ArrayList<Cargo> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "NOME", "DESCRIÇÃO" };

	// Constantes representando o índice das colunas
	private static final int REGISTRO = 0;
	private static final int NOME = 1;
	private static final int DESCRICAO = 2;

	public CargoTableModel() {
		linhas = new ArrayList<Cargo>();
	}

	public CargoTableModel(ArrayList<Cargo> listaCargo) {
		linhas = new ArrayList<Cargo>(listaCargo);
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
		case NOME:
			return String.class;
		case DESCRICAO:
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
		Cargo cargo = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			return cargo.getRegistro();
		case NOME:
			return cargo.getNome();
		case DESCRICAO:
			return cargo.getDescricao();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	@Override
	//Se as células não forem editáveis, nem precisamos implementar o método “setValueAt
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cargo cargo = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			cargo.setRegistro((int) aValue);
			break;

		case NOME:
			cargo.setNome((String) aValue);
			break;
		case DESCRICAO:
			cargo.setDescricao((String) aValue);
			break;

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}
	
	// Retorna o usuário referente a linha especificada
	public Cargo getCargo(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o usuário especificado ao modelo
	public void addCargo(Cargo Cargo) {
	    // Adiciona o registro.
	    linhas.add(Cargo);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o último índice. A subtração é necessária
	    // porque os índices começam em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o usuário da linha especificada.
	public void removeCargo(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudança.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de usuários no final da lista.
	public void addListaDeCargos(ArrayList<Cargo> Cargos) {
	    // Pega o tamanho antigo da tabela, que servirá
	    // como índice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(Cargos);
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(indice, indice + Cargos.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de usuários.
	    linhas.clear();
	 
	    // Notifica a mudança.
	    fireTableDataChanged();
	}
}
