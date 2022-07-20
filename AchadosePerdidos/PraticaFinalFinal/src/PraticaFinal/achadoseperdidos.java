package PraticaFinal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class achadoseperdidos {

		private final String url = "jdbc:postgresql://localhost/achadoseperdidos";
	
	private final String user = "postgres";
		private final String password = "123456";
		Connection conn = null ;
		PreparedStatement statement;
		

	  private static final String INSERT_USERS_SQL = "INSERT INTO achadoseperdidos" +
	        "  (local_item, data_item, nome, obs, tipo) VALUES " +
	        " ( ?, ?, ?, ?, ?)";
	  
	  
	  private static final String SELECT_ALL_QUERY = "select * from AchadosPerdidos";
	  
	   private static final String UPDATE_USERS_SQL= "update achadoseperdidos "+
			  "  (nome, local_item, data_item, obs ) VALUES " +
			  " ( ?, ?, ?, ? ) "; 
      
	   private static final String DELETE_USERS_SQL = "delete from achadoseperdidos where id_item = ?";
	                                                                                    
	   
			  
	    
	    public Connection connect() throws SQLException {
			try {
				
				conn = DriverManager.getConnection(url, user, password);
				
				if(conn != null){
					System.out.println("Conexão ao Banco de dados feita com sucesso");
				}else {
					System.out.println("Conexão ao Banco de dados falho!!!!");
				}
				
				//versão do postgreeSQL
				Statement statement = (Statement) conn.createStatement();
				ResultSet resultSet = ((java.sql.Statement) statement).executeQuery("SELECT VERSION()");
				
				if (resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			return conn;
		}
		


	    public void insertRecord(String local_item, String data_item, String nome, String obs, String tipo) throws SQLException {
	        System.out.println(INSERT_USERS_SQL);
	        // Step 1: Establishing a Connection
	        try (
	        		//Connection connection = DriverManager.getConnection(url, user, password);

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setString(1, local_item);
	            preparedStatement.setString(2, data_item);
	            preparedStatement.setString(3, nome);
	            preparedStatement.setString(4, obs);
	            preparedStatement.setString(5, tipo);
	        

	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {

	            // print SQL exception information
	            printSQLException1(e);
	        }

	        // Step 4: try-with-resource statement will auto close the connection.
	    }
	    public void insertUpdate(String nome, String  local_item, String data_item, String obs) throws SQLException {
	        System.out.println(UPDATE_USERS_SQL);
	        // Step 1: Establishing a Connection
	        try (
	        		Connection connection = DriverManager.getConnection(url, user, password);

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USERS_SQL)) {
	            preparedStatement.setString(1, nome);
	            preparedStatement.setString(2, local_item);
	            preparedStatement.setString(3, data_item);
	            preparedStatement.setString(4, obs);
	            //preparedStatement.setString(5, tipo);
	        

	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {

	            // print SQL exception information
	            printSQLException1(e);
	        }

	        // Step 4: try-with-resource statement will auto close the connection.
	    }
	    public void getUserBuscarNome(String nome) {
	    	Alterar al = new Alterar();
	    	try {

	            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY);

	            preparedStatement.setString(1, nome);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                System.out.println(rs.getString("local_obj"));
	                al.txtlocal.setText(rs.getString("local_obj"));

	                System.out.println(rs.getString("data_obj"));
	                al.txtdata.setText(rs.getString("data_obj"));

	                System.out.println(rs.getString("nome"));
	                al.txtnome.setText(rs.getString("nome"));

	                System.out.println(rs.getString("status"));
	                al.txtstatus.setText(rs.getString("status"));

	                System.out.println(rs.getString("tipo"));
	                al.txttipo.setText(rs.getString("tipo"));

	                System.out.println(rs.getString("observacao"));
	                al.txtobservacao.setText(rs.getString("observacao"));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	 

	    } 
	  
//	    
	    
	    public void deletar(int id_item) throws SQLException {
    	 
    	  
    	  PreparedStatement x = conn.prepareStatement(DELETE_USERS_SQL);
    	  x.setInt(1, id_item);
    	  x.executeUpdate();
    	  
    	  
    	  
    	  
      }
	    

	    public static void printSQLException1(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	    
		
		public Connection closeConn() throws SQLException {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return conn;
		}
		
		public void criartabela() {
	    	try {
	            statement = conn.prepareStatement("CREATE TABLE item\r\n"
	            		+ "(\r\n"
	            		+ "	id_item  		SERIAL,\r\n"
	            		+ "	local_item   VARCHAR(20),\r\n"
	            		+ "	data_item   VARCHAR(12),\r\n"
	            		+ "	nome VARCHAR(40),\r\n"
	            		+ "	obs    VARCHAR(40),\r\n"
	            		+ "	tipo VARCHAR(20),\r\n"
	            		+ "  PRIMARY KEY(id_item)\r\n"
	            		+ ");\r\n"
	            		+ "INSERT INTO achadoseperdidos (local_item, data_item, nome, obs, tipo)\r\n"
	            		+ "	VALUES ('shopping', '07/11/1996', 'tenis', 'perdido', 'pano');	\r\n"
	            		+ "INSERT INTO achadoseperdidos (local_item, data_item, nome, obs, tipo)\r\n"
	            		+ "	VALUES ('escola', '13/06/2010', 'mochilla', 'achado', 'couro');\r\n"
	            		+ "INSERT INTO achadoseperdidos (local_item, data_item, nome, obs, tipo)\r\n"
	            		+ "	VALUES ('casa', '12/09/2010', 'vasilha', 'perdido', 'plastico');		\r\n"
	            		+ "INSERT INTO achadoseperdidos (local_item, data_item, nome, obs, tipo)\r\n"
	            		+ "	VALUES ('faculdade', '01/05/2022', 'mouse', 'perdido', 'plastico');	"); 

	    		 statement.executeUpdate();
			}catch (SQLException e) {
	            printSQLException1(e);
	        }
	    	
	    	


	}
		
	
		

		private void printSQLException(SQLException e) {
			// TODO Auto-generated method stub
			
		}

		public static String getInsertUsersSql() {
			return INSERT_USERS_SQL;
		}



		



	 



	 

			
		}