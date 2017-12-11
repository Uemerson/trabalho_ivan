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
	private String[] colunas = new String[] { "REGISTRO", "ALUNO", "REPONSÁVEL", "DATA MATRÍCULA" };

	// Constantes representando o índice das colunas
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
	// Este método deve verificar o índice recebido por parâmetro e retornar o tipo
	// de classe correspondente à coluna.
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
	// Definir quais células são editáveis ou não
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	// Retornar o conteúdo da célula especificada
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pega o usuário referente a linha especificada.
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
	// Se as células não forem editáveis, nem precisamos implementar o método
	// “setValueAt
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

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}

	// Retorna o usuário referente a linha especificada
	public Matricula getMatricula(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	// Adiciona o usuário especificado ao modelo
	public void addMatricula(Matricula Matricula) {
		// Adiciona o registro.
		linhas.add(Matricula);

		// Pega a quantidade de registros e subtrai 1 para
		// achar o último índice. A subtração é necessária
		// porque os índices começam em zero.
		int ultimoIndice = getRowCount() - 1;

		// Notifica a mudança.
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	// Remove o usuário da linha especificada.
	public void removeMatricula(int indiceLinha) {
		// Remove o registro.
		linhas.remove(indiceLinha);

		// Notifica a mudança.
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	// Adiciona uma lista de usuários no final da lista.
	public void addListaDeMatriculas(ArrayList<Matricula> Matriculas) {
		// Pega o tamanho antigo da tabela, que servirá
		// como índice para o primeiro dos novos registros
		int indice = getRowCount();

		// Adiciona os registros.
		linhas.addAll(Matriculas);

		// Notifica a mudança.
		fireTableRowsInserted(indice, indice + Matriculas.size());
	}

	// Remove todos os registros.
	public void limpar() {
		// Remove todos os elementos da lista de usuários.
		linhas.clear();

		// Notifica a mudança.
		fireTableDataChanged();
	}
}