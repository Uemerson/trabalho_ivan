package tableModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import model.Matricula;

public class MatriculaTableModel extends AbstractTableModel {

	private ArrayList<Matricula> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "ALUNO", "REPONS�VEL", "DATA MATR�CULA" };

	// Constantes representando o �ndice das colunas
	private static final int REGISTRO = 0;
	private static final int ALUNO = 1;
	private static final int RESPONSAVEL = 2;
	private static final int DATA_MATRICULA = 3;

	public MatriculaTableModel() {
		linhas = new ArrayList<Matricula>();
	}

	public MatriculaTableModel(ArrayList<Matricula> listaMatricula) {
		linhas = new ArrayList<Matricula>(listaMatricula);
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
		case ALUNO:
			return String.class;
		case RESPONSAVEL:
			return String.class;
		case DATA_MATRICULA:
			return Date.class;

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
		Matricula matricula = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			return matricula.getRegistro();
		case ALUNO:
			return matricula.getNomeAluno();
		case RESPONSAVEL:
			return matricula.getNomeResponsavel();
		case DATA_MATRICULA:
			return matricula.getData_de_Matricula();

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	@Override
	// Se as c�lulas n�o forem edit�veis, nem precisamos implementar o m�todo
	// �setValueAt
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Matricula matricula = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			matricula.setRegistro((int) aValue);
			break;

		case ALUNO:
			matricula.setNomeAluno((String) aValue);
			break;

		case RESPONSAVEL:
			matricula.setNomeResponsavel((String) aValue);
			break;

		case DATA_MATRICULA:
			try {
				matricula.setData_de_Matricula(new SimpleDateFormat("dd/MM/yyyy").parse((String) aValue));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
	}

	// Retorna o usu�rio referente a linha especificada
	public Matricula getMatricula(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	// Adiciona o usu�rio especificado ao modelo
	public void addMatricula(Matricula Matricula) {
		// Adiciona o registro.
		linhas.add(Matricula);

		// Pega a quantidade de registros e subtrai 1 para
		// achar o �ltimo �ndice. A subtra��o � necess�ria
		// porque os �ndices come�am em zero.
		int ultimoIndice = getRowCount() - 1;

		// Notifica a mudan�a.
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	// Remove o usu�rio da linha especificada.
	public void removeMatricula(int indiceLinha) {
		// Remove o registro.
		linhas.remove(indiceLinha);

		// Notifica a mudan�a.
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	// Adiciona uma lista de usu�rios no final da lista.
	public void addListaDeMatriculas(ArrayList<Matricula> Matriculas) {
		// Pega o tamanho antigo da tabela, que servir�
		// como �ndice para o primeiro dos novos registros
		int indice = getRowCount();

		// Adiciona os registros.
		linhas.addAll(Matriculas);

		// Notifica a mudan�a.
		fireTableRowsInserted(indice, indice + Matriculas.size());
	}

	// Remove todos os registros.
	public void limpar() {
		// Remove todos os elementos da lista de usu�rios.
		linhas.clear();

		// Notifica a mudan�a.
		fireTableDataChanged();
	}
}