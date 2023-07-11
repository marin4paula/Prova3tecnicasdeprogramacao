package persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Jogador;


public class JogadorDAO {
	public void inserir (List<Jogador>jog) {
		try {
			Connection conexao = new Conexao().getConexao();
			PreparedStatement inserir = 
					conexao.prepareStatement("insert into jogadores(nome1,carta1,frase1,pontos1,nome2, carta2, frase2, pontos2,nome3,carta3, frase3, pontos3, nome4, carta4, frase4, pontos4)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
			inserir.setString(1, jog.get(0).getNome());
			inserir.setString(2, jog.get(0).getCarta().getFoto());
			inserir.setString(3, jog.get(0).getFrase());
			inserir.setInt(4, jog.get(0).getPontos());
			
			inserir.setString(5, jog.get(1).getNome());
			inserir.setString(6, jog.get(1).getCarta().getFoto());
			inserir.setString(7, jog.get(1).getFrase());
			inserir.setInt(8, jog.get(1).getPontos());
			
			inserir.setString(9, jog.get(2).getNome());
			inserir.setString(10, jog.get(2).getCarta().getFoto());
			inserir.setString(11, jog.get(2).getFrase());
			inserir.setInt(12, jog.get(2).getPontos());
			
			inserir.setString(13, jog.get(3).getNome());
			inserir.setString(14, jog.get(3).getCarta().getFoto());
			inserir.setString(15, jog.get(3).getFrase());
			inserir.setInt(16, jog.get(3).getPontos());
			
			inserir.executeUpdate();
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}
