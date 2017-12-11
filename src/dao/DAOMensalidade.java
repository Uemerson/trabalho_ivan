package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Mensalidade;

public class DAOMensalidade {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public void novoMensalidade(Mensalidade mensalidade) throws SQLException {
		SQL = "INSERT INTO MENSALIDADE (SERIE, VALOR_MENSAL, FORMA_PAGAMENTO) VALUES (?, ?, ?)";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, mensalidade.getSerie());
		preparedStatement.setDouble(2, mensalidade.getValor_da_Mensalidade());
		preparedStatement.setString(3, mensalidade.getForma_de_Pagamento());
		preparedStatement.execute();
		
		System.out.println("Mensalidade cadastrado com sucesso!");
	}
	
	public ArrayList<Mensalidade> listaMensalidade() throws SQLException{
		ArrayList<Mensalidade> listaMensalidade = new ArrayList<>();
		
		SQL = "SELECT * FROM MENSALIDADE";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			listaMensalidade.add(new Mensalidade(rs.getInt("ID"), rs.getDouble("VALOR_MENSAL"), rs.getString("SERIE"), rs.getString("FORMA_PAGAMENTO")));
		}
		
		return listaMensalidade;
	}
	
	public ArrayList<Mensalidade> listaMensalidade(int ID) throws SQLException{
		ArrayList<Mensalidade> listaMensalidade = new ArrayList<>();
		
		SQL = "SELECT * FROM MENSALIDADE WHERE MENSALIDADE.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			listaMensalidade.add(new Mensalidade(rs.getInt("ID"), rs.getDouble("VALOR_MENSAL"), rs.getString("SERIE"), rs.getString("FORMA_PAGAMENTO")));
		}
		
		return listaMensalidade;
	}
	
	public ArrayList<Mensalidade> listaMensalidadeSerie(String Serie) throws SQLException{
		ArrayList<Mensalidade> listaMensalidade = new ArrayList<>();
		
		SQL = "SELECT * FROM MENSALIDADE WHERE MENSALIDADE.SERIE LIKE ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + Serie + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			listaMensalidade.add(new Mensalidade(rs.getInt("ID"), rs.getDouble("VALOR_MENSAL"), rs.getString("SERIE"), rs.getString("FORMA_PAGAMENTO")));
		}
		
		return listaMensalidade;
	}
	
	public ArrayList<Mensalidade> listaMensalidadePagamento(String Pagamento) throws SQLException{
		ArrayList<Mensalidade> listaMensalidade = new ArrayList<>();
		
		SQL = "SELECT * FROM MENSALIDADE WHERE MENSALIDADE.FORMA_PAGAMENTO LIKE ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + Pagamento + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			listaMensalidade.add(new Mensalidade(rs.getInt("ID"), rs.getDouble("VALOR_MENSAL"), rs.getString("SERIE"), rs.getString("FORMA_PAGAMENTO")));
		}
		
		return listaMensalidade;
	}
	
	public Mensalidade buscaMensalidade(int ID) throws SQLException{
		
		SQL = "SELECT * FROM MENSALIDADE WHERE MENSALIDADE.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		
		return new Mensalidade(rs.getInt("ID"), rs.getDouble("VALOR_MENSAL"), rs.getString("SERIE"), rs.getString("FORMA_PAGAMENTO"));
	}

	public void excluirMensalidade(int ID) throws SQLException {
		SQL = "DELETE FROM MENSALIDADE WHERE MENSALIDADE.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		preparedStatement.executeUpdate();
		System.out.println("Mensalidade excluida com sucesso!");
	}
	
	public void atualizaMensalidade(Mensalidade mensalidade) throws SQLException {
		SQL = "UPDATE MENSALIDADE SET SERIE = ?, VALOR_MENSAL = ?, FORMA_PAGAMENTO = ? WHERE MENSALIDADE.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, mensalidade.getSerie());
		preparedStatement.setDouble(2, mensalidade.getValor_da_Mensalidade());
		preparedStatement.setString(3, mensalidade.getForma_de_Pagamento());
		preparedStatement.setInt(4, mensalidade.getRegistro());
		
		preparedStatement.execute();
		System.out.println("Mensalidade atualizado com sucesso!");
	}
}
