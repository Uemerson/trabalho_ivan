package tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Mensalidade;

public class MensalidadeTableModel extends AbstractTableModel {

	private ArrayList<Mensalidade> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "SÉRIE", "FORMA DE PAGAMENTO" };

	// Constantes representando o índice das colunas
	private static final int REGISTRO = 0;
	private static final int SERIE = 1;
	private static final int FORMA_PAGAMENTO = 2;

	public MensalidadeTableModel() {
		linhas = new ArrayList<Mensalidade>();
	}

	public MensalidadeTableModel(ArrayList<Mensalidade> listaMensalidade) {
		linhas = new ArrayList<Mensalidade>(listaMensalidade);
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
		case SERIE:
			return String.class;
		case FORMA_PAGAMENTO:
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
		Mensalidade mensalidade = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			return mensalidade.getRegistro();
		case SERIE:
			return mensalidade.getSerie();
		case FORMA_PAGAMENTO:
			return mensalidade.getForma_de_Pagamento();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	@Override
	//Se as células não forem editáveis, nem precisamos implementar o método “setValueAt
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Mensalidade mensalidade = linhas.get(rowIndex);

		switch (columnIndex) {
		case REGISTRO:
			mensalidade.setRegistro((int) aValue);
			break;

		case SERIE:
			mensalidade.setSerie((String) aValue);
			break;
		case FORMA_PAGAMENTO:
			mensalidade.setForma_de_Pagamento((String) aValue);
			break;

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}
	
	// Retorna o usuário referente a linha especificada
	public Mensalidade getMensalidade(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o usuário especificado ao modelo
	public void addMensalidade(Mensalidade Mensalidade) {
	    // Adiciona o registro.
	    linhas.add(Mensalidade);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o último índice. A subtração é necessária
	    // porque os índices começam em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o usuário da linha especificada.
	public void removeMensalidade(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudança.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de usuários no final da lista.
	public void addListaDeMensalidades(ArrayList<Mensalidade> Mensalidades) {
	    // Pega o tamanho antigo da tabela, que servirá
	    // como índice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(Mensalidades);
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(indice, indice + Mensalidades.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de usuários.
	    linhas.clear();
	 
	    // Notifica a mudança.
	    fireTableDataChanged();
	}
}
