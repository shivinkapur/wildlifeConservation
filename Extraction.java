import java.io.*;
import java.sql.*;
public class Extraction
{
	public static void main(String args[]) throws IOException,SQLException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("region.txt"));
		BufferedReader br=new BufferedReader(new FileReader("region1.txt"));
		BufferedReader br1=new BufferedReader(new FileReader("plant.txt"));
		BufferedWriter bw5=new BufferedWriter(new FileWriter("missing.txt"));
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
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

		String s="select * from Region;";
		rs=stmt.executeQuery(s);
		rs.next();						
		String str="",y="";
		for(int i=0;i<8;i++)
		{
			for(int j=1;j<6;j++)
			{
				y=rs.getString(j);
				if(j<5)
					str+=y+",";
				else 
					str+=y+";";
			}
			bw.write(str);
			bw.newLine();
			str="";
			rs.next();
		}
		
		
		String p=br1.readLine();
		while(p!=null)
		{
			bw.write(p);
			bw.newLine();
			p=br1.readLine();
		}

		s="";
		s="select * from Species;";
		rs=stmt.executeQuery(s);
		rs.next();						
		str="";
		y="";
		for(int i=0;i<8;i++)
		{
			for(int j=1;j<6;j++)
			{
				y=rs.getString(j);
				if(j<5)
					str+=y+",";
				else 
					str+=y+";";
			}
			bw5.write(str);
			bw5.newLine();
			bw.write(str);
			bw.newLine();
			str="";
			rs.next();
		}
		bw5.close();
		bw.close();
		con.close();
	}
//---------------------------------------------------------------------------------------------------------
	static int findcoma(String x,int num)				//Finding index of comas
	{
		int count=0,i=0;
		while(x!=null)
		{						
			if(x.charAt(i)==',')
				count++;
			if(count==num)
				return i;
			i++;
		}
		return i;
	}
}