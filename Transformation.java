import java.io.*;
import java.sql.*;
public class Transformation
{
	public static void main(String args[]) throws IOException,SQLException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("regiontransform.txt"));
		BufferedReader br=new BufferedReader(new FileReader("region1.txt"));
		BufferedReader br2=new BufferedReader(new FileReader("region1.txt"));
		BufferedReader br1=new BufferedReader(new FileReader("plant.txt"));
		BufferedReader br3=new BufferedReader(new FileReader("regiondedup1.txt"));
		BufferedWriter bw1=new BufferedWriter(new FileWriter("finaldedup.txt"));
		BufferedReader br4=new BufferedReader(new FileReader("missing.txt"));
		BufferedWriter bw4=new BufferedWriter(new FileWriter("finalmissing.txt"));
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String y="";
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

//----------------------------------------------------------------------------------

		String x=br2.readLine();
		String formatted="",str2="";
		int count=0,index=0;					
		while(x!=null)					//Formatted Extraction
		{
			count++;
			int secondcoma=findcoma(x,2);
			int thirdcoma=findcoma(x,3);
			String format=x.substring(0,secondcoma);
			String z=x.substring(secondcoma,thirdcoma);
			z=z.replace(" ",",");
			format+=z+x.substring(thirdcoma,x.length()-1);
			x=format;
			format="";
			str2=x.replaceAll("mountains","M");
			str2=str2.replaceAll("desert","D");
			str2=str2.replaceAll("wetlands","W");
			str2=str2.replaceAll("plains","P");
			str2=str2.replaceAll("forests","F");
			index=str2.indexOf(',');
			formatted+=count+""+str2.substring(index,str2.length());
			bw.write(formatted);
			bw.newLine();
			x=br2.readLine();
			formatted="";
		}
		br2.close();
		
//-----------------------------------------------------------------------------------Deduplication
		String str="",s="";
		y="";
		x="";
		x=br3.readLine();
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
					break;
			}
			if(v!=0)
			{
				bw.write(x);
				bw.newLine();
			}
			x=br3.readLine();
		}
		
//-----------------------------------------------------------------------------------------------------------Calculated values
		y=br1.readLine();
		int firstcoma=0,secondcoma=0,thirdcoma=0;
		int avgpop=0;
		String pop2="",pop7="",pop12="";

		while(y!=null)			//Average plant pop calculation
		{
			firstcoma=findcoma(y,1);
			secondcoma=findcoma(y,2);
			thirdcoma=findcoma(y,3);
			pop2=y.substring(firstcoma+1,secondcoma);
			pop7=y.substring(secondcoma+1,thirdcoma);
			pop12=y.substring(thirdcoma+1,y.indexOf(';'));
			avgpop=(Integer.parseInt(pop2)+Integer.parseInt(pop7)+Integer.parseInt(pop12))/3;
			formatted=y.substring(0,y.indexOf(';'))+","+avgpop+";";
			bw.write(formatted);
			bw.newLine();
			formatted="";
			y=br1.readLine();
		}

//-----------------------------------------------------------------------------------------------Missing Info
		
		x="";
		x=br4.readLine();
		formatted="";
		str2="";
		String str3="",str4="";
		index=0;count=0;
		int c2=0,c3=0,c4=0;					
		while(x!=null)
		{
			index=x.indexOf('$');
			if(index==-1)
			{
				bw4.write(x);
				formatted="";
				bw4.newLine();
				bw.write(x);
				bw.newLine();
				x=br4.readLine();
			}
			else
			{	
				formatted+=x.substring(0,index-1);
				c2=findcoma(x,2);
				c3=findcoma(x,3);
				c4=findcoma(x,4);
				str3=x.substring(c4,x.length());
				str2=x.substring(c2+1,c3);
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
				bw4.write(formatted);
				bw4.newLine();
				bw.write(formatted);
				bw.newLine();
				formatted="";
				x=br4.readLine();
			}
		}	
		bw4.close();
		br1.close();
		bw.close();
		bw1.close();
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