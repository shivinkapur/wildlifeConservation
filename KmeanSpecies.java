import java.io.*;
import java.sql.*;
public class KmeanSpecies
{
	public static void main(String args[]) throws IOException,SQLException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("kmeansout.txt"));
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
		int arr[]=new int[20];
		int k1[]=new int[20];
		int k2[]=new int[20];
		 int j=0,k=0;

		float m1,m2;

		float tem1,tem2;

		 int n=0,i;
		
		String s="select avgspop from Species_pop;";
		rs=stmt.executeQuery(s);
		rs.next();						//MDB Extraction
		String str="",y="";
		for(i=0;i<8;i++)
		{
			y=rs.getString(1);
			int w=Integer.parseInt(y);
			arr[i]=w;
			n++;
			y="";
			rs.next();
		}
		
		m1=arr[1];m2=arr[5];
		do
		{
			j=0;
			k=0;
			for(i=0;i<n;i++)
			{
				if(Math.abs(m1-arr[i])< Math.abs(m2-arr[i]))
					k1[j++]=arr[i];
				else
					k2[k++]=arr[i];
			}


			System.out.println("\n m1\t m2\t k1\t\t k2");
			System.out.println("\n" +m1+"\t"+m2 +"\t");
			
			

			tem1=m1;tem2=m2;
			int s1=0,s2=0;
	   		for( i=0;i<j;i++)
			{
  				s1=s1+ k1[i];
  				m1=s1/j;
			}
			for( i=0;i<k;i++)
			{
  				s2=s2+ k2[i];
  				m2=s2/k;
			}
			if((tem1==m1)&&(tem2==m2))
					break;
		}while(true);
		bw.write("Cluster 1: ");
		for(i=0;i<j;i++)
			bw.write(k1[i]+" ");
		bw.newLine();
		bw.write("Cluster 2: ");
		for(i=0;i<k;i++)
			bw.write(k2[i]+" ");
	bw.close();
		
	}

}


