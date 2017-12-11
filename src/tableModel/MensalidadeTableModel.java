package tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Mensalidade;

public class MensalidadeTableModel extends AbstractTableModel {

	private ArrayList<Mensalidade> linhas;

	// Array com os nomes das colunas.
	private String[] colunas = new String[] { "REGISTRO", "S�RIE", "FORMA DE PAGAMENTO" };

	// Constantes representando o �ndice das colunas
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
	// Este m�todo deve verificar o �ndice recebido por par�metro e retornar o tipo
	// de classe correspondente � coluna.
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
	// Definir quais c�lulas s�o edit�veis ou n�o
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	// Retornar o conte�do da c�lula especificada
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pega o usu�rio referente a linha especificada.
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
	//Se as c�lulas n�o forem edit�veis, nem precisamos implementar o m�todo �setValueAt
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

		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
	}
	
	// Retorna o usu�rio referente a linha especificada
	public Mensalidade getMensalidade(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o usu�rio especificado ao modelo
	public void addMensalidade(Mensalidade Mensalidade) {
	    // Adiciona o registro.
	    linhas.add(Mensalidade);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o �ltimo �ndice. A subtra��o � necess�ria
	    // porque os �ndices come�am em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o usu�rio da linha especificada.
	public void removeMensalidade(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudan�a.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de usu�rios no final da lista.
	public void addListaDeMensalidades(ArrayList<Mensalidade> Mensalidades) {
	    // Pega o tamanho antigo da tabela, que servir�
	    // como �ndice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(Mensalidades);
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(indice, indice + Mensalidades.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de usu�rios.
	    linhas.clear();
	 
	    // Notifica a mudan�a.
	    fireTableDataChanged();
	}
}
