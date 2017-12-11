

//Nome do nosso pacote //                
package br.com.ufba.grouprecommendation.dao;
 
//Classes necessárias para uso de Banco de dados //
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLObject {

    //<editor-fold defaultstate="collapsed" desc="----- PROPRIEDADES -----">
    
    public static Connection conn;
    private static MySQLObject instance;
    private static boolean flag;
    private static boolean flagStatement;
    private static String status;
    
    private static Statement statement;
    public static Statement getStatement() throws SQLException {
         if (!flag) {
            statement =   MySQLObject.getConexaoMySQL().createStatement(); 
            flagStatement = true;
            return statement;
        } else {
            return statement;
        }
        
    }
    public static void setStatement(Statement statement) {
        MySQLObject.statement = statement;
    }
    
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="----- GERENCIAMENTO DE CONEXÕES  -----">
    
    private MySQLObject() {
        
    }
 
    public static Connection getConexaoMySQL() {
        if (!flag) {
            conn =  CreateMySQLConnection();
            if(conn != null)    
                flag = true;
            
            return conn;
        } else {
            return conn;
        }
        
    }

    private static Connection CreateMySQLConnection() {
    
    try {
        
        // Carregando o JDBC Driver padrão
        String driverName = "com.mysql.jdbc.Driver";                        
        Class.forName(driverName);

        // Configurando a nossa conexão com um banco de dados//
        String serverName = "localhost";    //caminho do servidor do BD
        String mydatabase ="mate84";        //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";        //nome de um usuário de seu BD      
        String password = "root";      //sua senha de acesso
        conn = DriverManager.getConnection(url, username, password);

        //Testa sua conexão//  
        if (conn != null) {
            status = ("STATUS--->Conectado com sucesso!");
        } else {
            status = ("STATUS--->Não foi possivel realizar conexão");
        }
        
        return conn;

    } catch (ClassNotFoundException e) {  //Driver não encontrado
        System.out.println("O driver expecificado nao foi encontrado.");
        return null;
    } catch (SQLException e) {
        //Não conseguindo se conectar ao banco
        System.out.println("Nao foi possivel conectar ao Banco de Dados.");
        return null;
        }
    }

    public static boolean FecharConexao() {
        try {
            MySQLObject.getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
   
    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return MySQLObject.getConexaoMySQL();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="----- MÉTODOS DE ACESSO A DADOS  -----">
    
       
    public void ExecuteNonQuery(String SQLQuery) throws SQLException {
        
        getStatement().executeUpdate(SQLQuery);
    }
    
    public ResultSet ExecuteQuery(String SQLQuery) throws SQLException {
         return getStatement().executeQuery(SQLQuery);
         
     }
    
    //</editor-fold> 

}

