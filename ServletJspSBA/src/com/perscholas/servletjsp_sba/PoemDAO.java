package com.perscholas.servletjsp_sba;

import java.sql.*; // 1st step

public class PoemDAO {

    private String url;
    private String user;
    private String password;
    private Connection jdbcConnection;
     
    public PoemDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
    PoemDAO() {
    	
    }
	// 2nd step load and register driver
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "student";
		String password = "123";

		// 3rd step is create connection
		Connection conn = null; // declare connection object here
		try {
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Poem getPoemByTitle(String title) {

		Connection conn = getConnection();
		// 4th step is create statement

		// Using Statement object (old way) is commented out below

		// Statement st = null;
		PreparedStatement pst = null;
		try {
			// st = conn.createStatement();
			pst = conn.prepareStatement("select ID, title, author from poem where title=?");
			pst.setString(1, title);

			// 5th step is Execute query
			ResultSet rs = null;
			// rs = st.executeQuery("select title, author from poem where title = " + title);
			rs = pst.executeQuery();
			Poem poem = null;
			// 6th step is process ResultSet
			while (rs.next()) {
				// System.out.println(rs.getString("title") + " " + rs.getString("author"));
				poem = new Poem();
				poem.ID = rs.getInt("ID");
				poem.title = rs.getString("title");
				poem.author = rs.getString("author");
				poem.poemText = getPoemTextByID(poem.ID);
			}
			// 7th, and final, step is close connection and other resources
			conn.close();
			return poem;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public void insertPoem(Poem poem) {
		Connection conn = getConnection();
		
		try {
			int newID = -1;
			String sql = "insert into poem (title, author) values (?,?)";
			ResultSet rs = null;
			//PreparedStatement pst = null;
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, poem.title);
			pst.setString(2, poem.author);
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if(rs != null && rs.next()){
			System.out.println("Content ID:" + rs.getInt(1));
			}
			//PreparedStatement pstId
//			PreparedStatement pstId = conn.prepareStatement("select ID from poem where title=?");
//			pstId.setString(1, poem.title);
				if (rs.next()) {
					//one way of printing
					//System.out.printf("%d poem inserted", recordNumAffected);
					newID = rs.getInt(1); 
					poem.ID = newID;
					insertPoemText(poem);		
				} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

		public void insertPoemText(Poem poem) {
			Connection conn = getConnection();
			try {
				
				//ResultSet rs = null;
				PreparedStatement pst = conn.prepareStatement("insert into poemtext values(?,?)");
				pst.setInt(1, poem.ID);
				pst.setString(2, poem.poemText);
		
				pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		public void updatePoemText(Poem poem) {
			Connection conn = getConnection();
			try {
				
				//ResultSet rs = null;
				String sql = "update poemtext  set content =? where ID=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, poem.poemText);
				pst.setInt(2, poem.ID);
		
				pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		public String getPoemTextByID(int ID) {
			Connection conn = getConnection();
			try {
				PreparedStatement pst = conn.prepareStatement("select content from poemtext where ID=?");
				pst.setInt(1, ID);
	
				ResultSet rs = null;
				rs = pst.executeQuery();
				String  poemText = null;
				// 6th step is process ResultSet
				if (rs.next()) {
					// System.out.println(rs.getString("title") + " " + rs.getString("author"));
					poemText = rs.getString("content");
				}
				// 7th, and final, step is close connection and other resources
				conn.close();
				return poemText; 
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	
		}
}
