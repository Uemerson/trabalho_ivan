package tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Funcionario;

public class FuncionarioTableModel extends AbstractTableModel {

	private ArrayList<Funcionario> funcionario;
	private String[] colunas = new String[] { "REGISTRO", "NOME", "CPF", "CARGO" };

	public FuncionarioTableModel(ArrayList<Funcionario> funcionarios) {
		this.funcionario = funcionarios;
	}

	public FuncionarioTableModel() {
		this.funcionario = new ArrayList<Funcionario>();
	}

	public int getRowCount() {
		return funcionario.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void setValueAt(Funcionario aValue, int rowIndex) {
		Funcionario funcionarios = funcionario.get(rowIndex);

		funcionarios.setRegistro(aValue.getRegistro());
		funcionarios.setNome(aValue.getNome());
		funcionarios.setCPF(aValue.getCPF());
		funcionarios.setCargo(aValue.getCargo());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Funcionario funcionarios = funcionario.get(rowIndex);

		switch (columnIndex) {
		case 0:
			funcionarios.setRegistro(Integer.parseInt(aValue.toString()));
		case 1:
			funcionarios.setNome(aValue.toString());
		case 2:
			funcionarios.setCPF(aValue.toString());
		case 3:
			funcionarios.setCargo(aValue.toString());

		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Funcionario funcionarioSelecionado = funcionario.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = Integer.toString(funcionarioSelecionado.getRegistro());
			break;
		case 1:
			valueObject = funcionarioSelecionado.getNome();
			break;
		case 2:
			valueObject = funcionarioSelecionado.getCPF();
			break;
		case 3:
			valueObject = funcionarioSelecionado.getCargo();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Usuario.class");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Funcionario getFuncionario(int indiceLinha) {
		return funcionario.get(indiceLinha);
	}

	public void addFuncionario(Funcionario u) {
		funcionario.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeFuncionario(int indiceLinha) {
		funcionario.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeFuncionario(ArrayList<Funcionario> novosFuncionario) {

		int tamanhoAntigo = getRowCount();
		funcionario.addAll(novosFuncionario);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		funcionario.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return funcionario.isEmpty();
	}

}
