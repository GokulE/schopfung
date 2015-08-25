import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.json.simple.JSONObject;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public String InsertQuery( String user_name1,String user_name2,String user_name3,String user_name4,String user_name5) throws Exception {
  String result = null;
    try {
	
	
	Class.forName("com.mysql.jdbc.Driver");
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/Handicrafts","root","root");
                        statement=connect.createStatement();
						
	//String sql=("insert into (Name,Email,Mobile_number,DOB) values( "'" ++ "'","'" +  + "'","'" +  + "'","'" +  +"'")");
	String sql=("insert into User_Details (Name,Email,Mobile_number,DOB) values('"+user_name1+"','"+user_name3+"','"+user_name4+"','"+user_name5+"')");
                        System.out.println(sql);
			String User_id_from_Db = null;
			statement.executeUpdate(sql);
                        resultSet=statement.executeQuery("select * from User_Details");
						
                        while(resultSet.next())
                        {
							result = resultSet.getString(1) + resultSet.getString(2) + "inside InsertQuery";
                            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
							User_id_from_Db = resultSet.getString(1);
                        }
						
		
		sql=("insert into User_Login (User_Id,Password,Email,User_Type_Id) values('"+User_id_from_Db+"','"+user_name2+"','"+user_name3+"',1)");
		statement.executeUpdate(sql);
                        resultSet=statement.executeQuery("select * from User_Details");
						
                        while(resultSet.next())
                        {
							result = resultSet.getString(1) + resultSet.getString(2) + "inside InsertQuery";
                            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
                        }
						close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		      result = ex.toString() + "inside InsertQuery exception";
			  

		}
	
	return result;
	
	}
      // This will load the MySQL driver, each DB has its own driver
    //  Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      //connect = DriverManager
        //  .getConnection("jdbc:mysql://localhost:3306/Handicrafts?"
            //  + "user=root&password=root");

      // Statements allow to issue SQL queries to the database
      //statement = connect.createStatement();
      // Result set get the result of the SQL query
      /*resultSet = statement
          .executeQuery("select * from Handicrafts.User_Details");
      writeResultSet(resultSet);*/

      // PreparedStatements can use variables and are more efficient
      //preparedStatement = connect
        //  .prepareStatement("insert into  Handicrafts.User_Details values (1, harsha, ?, ?, ? , ?)");
      // "myuser, webpage, datum, summery, User_Details from Handicrafts.User_Details");
      // Parameters start with 1
      //preparedStatement.setString(1, user_name1);
      //preparedStatement.setString(2, user_name2);
      //preparedStatement.setString(3, user_name3);
      //preparedStatement.setString(4, user_name4);
      //preparedStatement.setString(5, user_name5);
      //preparedStatement.executeUpdate();

      //preparedStatement = connect
        //  .prepareStatement("SELECT * from Handicrafts.User_Details");
      //resultSet = preparedStatement.executeQuery();
      //writeResultSet(resultSet);

      // Remove again the insert comment
     /* preparedStatement = connect
      .prepareStatement("delete from Handicrafts.User_Details where myuser= ? ; ");
      preparedStatement.setString(1, "Test");
      preparedStatement.executeUpdate();*/
      
      //resultSet = statement
      //.executeQuery("select * from Handicrafts.User_Details");
      //writeMetaData(resultSet);
      
    //} catch (Exception e) {
      ///throw e;
    //} finally {
     // close();
  //  }

  

  public String SelectQuery(String table_name,String columns_needed,String criteria)
	{
	                     String Id=null;
						 String password = null;
						 String Email = null;
						 String jsonText = null;
						 String sql=("select " + columns_needed +   " from " + table_name +  " where " + criteria );
				
                 try
		{
			                    
                        
						//'"+criteria+"';
						System.out.println(sql);
                       
			Class.forName("com.mysql.jdbc.Driver");
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/Handicrafts","root","root");
                        statement=connect.createStatement();
			
                        resultSet=statement.executeQuery(sql);
						String user_name = null;
						if(!resultSet.first()){
						return "Empty Row" + sql;
						}
						else
                        if(resultSet.next())
                        {
                         Id=resultSet.getString(1);
						 password = resultSet.getString(2);
						 Email = resultSet.getString(3);
                        }
						
  JSONObject obj=new JSONObject();
  obj.put("Id",Id);
  obj.put("password",password);
  obj.put("Email",Email);
 jsonText = obj.toString();                       
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			jsonText = ex.toString() + sql;
		        

		}
		return jsonText;
	}
  
  
  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String user_id = resultSet.getString("User_id");
      String name = resultSet.getString("Name");
      String email = resultSet.getString("Email");
      Date date = resultSet.getDate("Mobile_number");
      String DOB = resultSet.getString("DOB");
      System.out.println("User: " + user_id);
      System.out.println("Website: " + name);
      System.out.println("Summery: " + email);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + DOB);
    }
  }

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

}