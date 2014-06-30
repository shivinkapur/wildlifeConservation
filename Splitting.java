import java.io.*;
import java.sql.*;
public class Splitting
{
	public static void main(String args[]) throws IOException,SQLException
	{
		BufferedReader br=new BufferedReader(new FileReader("split1.txt"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("split1.txt"));
		BufferedWriter bw1=new BufferedWriter(new FileWriter("split2.txt"));
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
		rs.next();						//MDB Extraction
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
		con.close();
		bw.close();
		y=br.readLine();
		while(y!=null)
		{
			int secondcoma=findcoma(y,2);
			int thirdcoma=findcoma(y,3);
			String format=y.substring(0,secondcoma);
			String x=y.substring(secondcoma,thirdcoma);
			System.out.println(x);
			x=x.replace(" ",",");
			System.out.println(x);
			format+=x+y.substring(thirdcoma,y.length()-1);
			System.out.println(format);
			bw1.write(format);
			format="";
			bw1.newLine();
			y=br.readLine();
		}
		br.close();
		bw1.close();
	}
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