import java.io.*;
import java.sql.*;
public class Extraction
{
public static void main(String args[]) throws IOException,SQLException
{
BufferedWriter bw=new BufferedWriter(new FileWriter("region.txt"));
//BufferedReader br=new BufferedReader(new FileReader("books.txt"));

Connection con=null;
Statement stmt=null;
ResultSet rs=null;
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dwm33");
stmt=con.createStatement();
}
catch(Exception e){System.out.println(e);}
String x;
/*x=br.readLine();
while(x!=null)
{
System.out.println(x);
bw.write(x);
bw.newLine();
x=br.readLine();
}

br.close();
*/
String s="select * from Region;";
rs=stmt.executeQuery(s);
rs.next();
String str="",y="";
for(int i=0;i<8;i++)
{
for(int j=1;j<6;j++)
{y=rs.getString(j);
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
}}