package tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Cargo;

public class CargoTableModel extends AbstractTableModel {

	private ArrayList<Cargo> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "NOME", "DESCRI��O" };

	// Constantes representando o �ndice das colunas
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
	// Este m�todo deve verificar o �ndice recebido por par�metro e retornar o tipo
	// de classe correspondente � coluna.
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
	// Definir quais c�lulas s�o edit�veis ou n�o
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	// Retornar o conte�do da c�lula especificada
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pega o usu�rio referente a linha especificada.
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
	//Se as c�lulas n�o forem edit�veis, nem precisamos implementar o m�todo �setValueAt
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

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
	}
	
	// Retorna o usu�rio referente a linha especificada
	public Cargo getCargo(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o usu�rio especificado ao modelo
	public void addCargo(Cargo Cargo) {
	    // Adiciona o registro.
	    linhas.add(Cargo);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o �ltimo �ndice. A subtra��o � necess�ria
	    // porque os �ndices come�am em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o usu�rio da linha especificada.
	public void removeCargo(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudan�a.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de usu�rios no final da lista.
	public void addListaDeCargos(ArrayList<Cargo> Cargos) {
	    // Pega o tamanho antigo da tabela, que servir�
	    // como �ndice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(Cargos);
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(indice, indice + Cargos.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de usu�rios.
	    linhas.clear();
	 
	    // Notifica a mudan�a.
	    fireTableDataChanged();
	}
}
