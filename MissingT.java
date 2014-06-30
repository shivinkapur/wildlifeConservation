import java.io.*;
import java.sql.*;
public class MissingT
{
	public static void main(String args[]) throws IOException,SQLException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("missing.txt"));
		BufferedReader br=new BufferedReader(new FileReader("missing.txt"));
		BufferedWriter bw1=new BufferedWriter(new FileWriter("finalmissing.txt"));
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
		String s="select * from Species;";
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
		
		bw.close();
		
		String x="";
		x=br.readLine();
		String formatted="",str2="",str3="",str4="";
		int count=0,index=0,c2=0,c3=0,c4=0;					
		while(x!=null)
		{
			index=x.indexOf('$');
			if(index==-1)
			{
				bw1.write(x);
				formatted="";
				bw1.newLine();
				x=br.readLine();
			}
			else
			{	
				formatted+=x.substring(0,index-1);
				c2=findcoma(x,2);
				c3=findcoma(x,3);
				c4=findcoma(x,4);
				str3=x.substring(c4,x.length());
				str2=x.substring(c2+1,c3);
				System.out.println(str2);
				if(str2.equals("mammal"))
					str4+=",deer";
				if(str2.equals("bird"))
					str4+=",fishes and frogs";
				if(str2.equals("reptile"))
					str4+=",insects and frogs";
				if(str2.equals("fish"))
					str4+=",small fishes";
				if(str2.equals("amphibian"))
					str4+=",fishes";
		
				formatted+=str4+str3;
				str4="";
				str3="";
				bw1.write(formatted);
				formatted="";
				bw1.newLine();
				x=br.readLine();
			}
		}	
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