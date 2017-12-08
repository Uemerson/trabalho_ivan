package tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Responsavel;

public class ResponsavelTableModel extends AbstractTableModel {

	private ArrayList<Responsavel> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "NOME", "CPF", "RG" };

	// Constantes representando o �ndice das colunas
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
	// Este m�todo deve verificar o �ndice recebido por par�metro e retornar o tipo
	// de classe correspondente � coluna.
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
	// Definir quais c�lulas s�o edit�veis ou n�o
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	// Retornar o conte�do da c�lula especificada
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pega o usu�rio referente a linha especificada.
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
	// Se as c�lulas n�o forem edit�veis, nem precisamos implementar o m�todo
	// �setValueAt
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

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
	}

	// Retorna o usu�rio referente a linha especificada
	public Responsavel getResponsavel(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	// Adiciona o usu�rio especificado ao modelo
	public void addResponsavel(Responsavel Responsavel) {
		// Adiciona o registro.
		linhas.add(Responsavel);

		// Pega a quantidade de registros e subtrai 1 para
		// achar o �ltimo �ndice. A subtra��o � necess�ria
		// porque os �ndices come�am em zero.
		int ultimoIndice = getRowCount() - 1;

		// Notifica a mudan�a.
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	// Remove o usu�rio da linha especificada.
	public void removeResponsavel(int indiceLinha) {
		// Remove o registro.
		linhas.remove(indiceLinha);

		// Notifica a mudan�a.
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	// Adiciona uma lista de usu�rios no final da lista.
	public void addListaDeResponsavels(ArrayList<Responsavel> Responsavels) {
		// Pega o tamanho antigo da tabela, que servir�
		// como �ndice para o primeiro dos novos registros
		int indice = getRowCount();

		// Adiciona os registros.
		linhas.addAll(Responsavels);

		// Notifica a mudan�a.
		fireTableRowsInserted(indice, indice + Responsavels.size());
	}

	// Remove todos os registros.
	public void limpar() {
		// Remove todos os elementos da lista de usu�rios.
		linhas.clear();

		// Notifica a mudan�a.
		fireTableDataChanged();
	}
}