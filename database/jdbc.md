Connect to MySQL database by jdbc

```java
public static void main (String[] args) throws SQLException {

    String url = "jdbc:mariabdb://localhost/schemaName";
    String userName = "root";
    String password = "root";
    
    // write the SQL query
    String query = "SELECT * FROM card";

    // check if the connection driver exists
    try {
        Class.forName("come.mysql.cj.jdbc.Driver");    
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }


    // Create the connection and exec the query
    // use the auto-close try catch to close the connection
    try (Connection conn = DriverManager.getConnection(url, userName, password)) {
        
        // create a statement
        Statement statement = conn.createStatement();
        
        // exec the query
        ResultSet result = statement.executeQuery(query);
        
        // append to a string and print in the command line
        // We have explicitly specify the number of column to append
        while (result.next()) {
            String output = "";
            for (int i = 1; i <=6; i++) {
                output += result.getString(i) + ":" ;
            }
            System.out.println(output);
        }
        
    }   catch (SQLException e) {
        e.printStackTrace();
    } finally {
    
        // close the resources
        try {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        } catch (Exception e) {} // nothing we can do about it
        
        
        // it's a SQLException
        try {
    
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
```

Connect to remote a2hosting:
modify the url and password.
I have to hard code the password in the code
```java
String url = "jdbc:mariadb://hostname/databaseName"
String password = "password";
```
