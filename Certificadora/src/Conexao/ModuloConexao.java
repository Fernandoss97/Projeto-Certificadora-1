
package Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Rafa
 */
public class ModuloConexao {
    public static Connection conector(){
        java.sql.Connection conexao = null;
        String url = "jdbc:postgresql://localhost:5432/Competencia";
        String usuario = "postgres";
        String senha = "utfpr";
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
