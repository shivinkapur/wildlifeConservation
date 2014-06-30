import java.io.*;
import java.sql.*;
public class Loading
{
	public static void main(String args[]) throws IOException,SQLException
	{
	
		BufferedReader br=new BufferedReader(new FileReader("regiontransform.txt"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("loadout.txt"));
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String arr[]=new String[6];
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:dwm33");
			stmt=con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	
		String p=br1.readLine();
		int count=0;
		while((p!=null)&&(count<8))
		{
			
			arr=p.split(",");
			String s="insert into loadregion values(arr[0]+"\,"+arr[1]+"\,"+arr[2]+"\,"+arr[3]+"\,"+arr[4]+"\,"+arr[5]"+"\;");
			System.out.println("s="+s);
			bw.write(s);
			bw.newLine();
			stmt.executeUpdate(s);
			count++;
			p=br1.readLine();
		}
		bw.close();
	}
}
	

		
