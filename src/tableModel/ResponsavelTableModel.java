package tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Responsavel;

public class ResponsavelTableModel extends AbstractTableModel {

	private ArrayList<Responsavel> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "NOME", "CPF", "RG" };

	// Constantes representando o índice das colunas
	private static final int REGISTRO = 0;
	private static final int NOME = 1;
	private static final int CPF = 2;
	private static final int RG = 3;

	public ResponsavelTableModel() {
		linhas = new ArrayList<Responsavel>();
	}

	public ResponsavelTableModel(ArrayList<Responsavel> listaResponsavel) {
		linhas = new ArrayList<Responsavel>(listaResponsavel);
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
		case CPF:
			return String.class;
		case RG:
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
		Responsavel responsavel = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			return responsavel.getRegistro();
		case NOME:
			return responsavel.getNome_do_Responsavel();
		case CPF:
			return responsavel.getCPF();
		case RG:
			return responsavel.getRG();

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	@Override
	// Se as células não forem editáveis, nem precisamos implementar o método
	// “setValueAt
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Responsavel responsavel = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			responsavel.setRegistro((int) aValue);
			break;

		case NOME:
			responsavel.setNome_do_Responsavel((String) aValue);
			break;

		case CPF:
			responsavel.setCPF((String) aValue);
			break;

		case RG:
			responsavel.setRG((String) aValue);

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}

	// Retorna o usuário referente a linha especificada
	public Responsavel getResponsavel(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	// Adiciona o usuário especificado ao modelo
	public void addResponsavel(Responsavel Responsavel) {
		// Adiciona o registro.
		linhas.add(Responsavel);

		// Pega a quantidade de registros e subtrai 1 para
		// achar o último índice. A subtração é necessária
		// porque os índices começam em zero.
		int ultimoIndice = getRowCount() - 1;

		// Notifica a mudança.
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	// Remove o usuário da linha especificada.
	public void removeResponsavel(int indiceLinha) {
		// Remove o registro.
		linhas.remove(indiceLinha);

		// Notifica a mudança.
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	// Adiciona uma lista de usuários no final da lista.
	public void addListaDeResponsavels(ArrayList<Responsavel> Responsavels) {
		// Pega o tamanho antigo da tabela, que servirá
		// como índice para o primeiro dos novos registros
		int indice = getRowCount();

		// Adiciona os registros.
		linhas.addAll(Responsavels);

		// Notifica a mudança.
		fireTableRowsInserted(indice, indice + Responsavels.size());
	}

	// Remove todos os registros.
	public void limpar() {
		// Remove todos os elementos da lista de usuários.
		linhas.clear();

		// Notifica a mudança.
		fireTableDataChanged();
	}
}