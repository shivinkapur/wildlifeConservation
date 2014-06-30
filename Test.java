import java.io.*;
import java.sql.*;
public class Test
{
	public static void main(String args[]) throws IOException,SQLException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("regiondedup.txt"));
		BufferedReader br=new BufferedReader(new FileReader("regiondedup1.txt"));
		
		BufferedWriter bw1=new BufferedWriter(new FileWriter("finaldedup.txt"));
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
			System.out.println(str);
			bw.write(str);
			bw.newLine();
			str="";
			rs.next();
		}
		//con.close();
		bw.close();
			
		String x="";

		x=br.readLine();
		while(x!=null)
		{

			int t=0;
			int v=0;
			s="select * from Region;";
			rs=stmt.executeQuery(s);
			rs.next();
			for(int i=0;i<8;i++)
			{

				str="";y="";
				for(int j=2;j<6;j++)
				{
					y=rs.getString(j);
					if(j<5)
						str+=y+",";
					else 
						str+=y+";";
				}

				rs.next();

				char xch[] = x.toCharArray();
				int k ;
				char xch2[] =new char[xch.length];

				for( k=0;k<xch.length;k++)
				{
					if(xch[k]==',')
					{
						k++;
						int c=k;
						for(t=0;t<((xch.length)-k);t++)
						{	
							xch2[t]=xch[c];
							c++;		
						}
						break;
					}
				}



				char xch3[] =new char[t];

				for( k=0;k<t;k++)
					xch3[k]=xch2[k];
				String x2 = new String(xch3);
				v=str.compareTo(x2);

				if(v==0)
				{	
					break;
				}


			}
			if(v!=0)
			{
				bw1.write(x);
				bw1.newLine();
				System.out.println(x);
			}

			x=br.readLine();


		}
		bw1.close();
		con.close();	

	}
}