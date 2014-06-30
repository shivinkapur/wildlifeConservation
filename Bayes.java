import java.util.*;
import java.io.*;
class element 
{
	double p[][];
	element(int n,int m)
	{
		p = new double[n][m];
	}
}

class Classifier
{
	int no_attr,no_rows=0;
	String fileArray[][];
	String values[][];
	double class_p[];
	int count;
	element a[];

	void readFile(String fname)throws IOException
	{
		FileInputStream in = null;
		try
		{
			File f = new File(fname);
			in = new FileInputStream(f);
		}
		catch (Exception e)
		{
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String input="";
		input = br.readLine();

		StringTokenizer line = new StringTokenizer(input);
		no_attr=line.countTokens()-1;
		fileArray = new String[100][no_attr+1];

		for(int i=0;i<=no_attr;i++)
			fileArray[no_rows][i]=line.nextToken();

		while(true)
		{
			input = br.readLine();
			if(input==null)
				break;
			line = new StringTokenizer(input);
			no_rows++;
			for(int i=0;i<=no_attr;i++)
				fileArray[no_rows][i]=line.nextToken();
		}
		getAllvalues();
		createTable();
		newEntry();


	}

	boolean in_values(int col_no,String temp)
	{
		for(int i=0;i<=count;i++)
			if(values[i][col_no]!=null && values[i][col_no].equals(temp))
				return true;
			else
				continue;
			return false;
	}

	void getAllvalues()
	{
		values = new String[100][no_attr+1];
		count=0;
		String temp;

		for(int i=0;i<=no_attr;i++)
		{
			for(int j=1;j<=no_rows;j++)
			{
				temp=fileArray[j][i];
				if(in_values(i,temp))
					continue;
				values[count++][i]=temp;
			}
			count=0;
		}
	}

	int getlen(int col_no)
	{
		int i=0;
		while(values[i][col_no]!=null)
			i++;
		return i;
	}

	void display()
	{
		for(int i=0;i<=no_rows;i++)
		{
			for(int j=0;j<=no_attr;j++)
				System.out.println(values[i][j]+"    ");
			System.out.println();
		}
	}

	int getcount(String temp,int col_no)
	{
		int tc=0;
		for(int i=1;i<=no_rows;i++)
			if(fileArray[i][col_no].equals(temp))
				tc++;
		return tc;
	}

	void createTable()
	{
		int tp = getlen(no_attr);
		class_p=new double[tp];

		for(int i=0;i<tp;i++)
		{
			for(int j=1;j<=no_rows;j++)
			{
				if(values[i][no_attr].equals(fileArray[j][no_attr]))
					class_p[i]++;
			}
			class_p[i]/=no_rows;
			System.out.println("P("+values[i][no_attr]+")= "+class_p[i]);

		}
		int tc=0;
		a=new element[no_attr];
		for(int i=0;i<no_attr;i++)
		{
			a[i] = new element(getlen(i),getlen(no_attr));
			for(int j=0;j<getlen(i);j++)
			{
				for(int k=0;k<getlen(no_attr);k++)
				{
					for(int x=1;x<=no_rows;x++)
					{
						if(values[j][i].equals(fileArray[x][i]) && values[k][no_attr].equals(fileArray[x][no_attr]))
							tc++;
					}
					a[i].p[j][k]=(double)tc/getcount(values[k][no_attr],no_attr);
					tc=0;
				}
			}
		}
	}

	void newEntry()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter new Entry");
		String entry[] = new String[no_attr];
		double p_entry[] = new double[getlen(no_attr)];
		String X="X=< ";
		for(int i=0;i<no_attr;i++)
		{
			System.out.println("Enter "+fileArray[0][i]);
			entry[i]=s.next();
			X+=entry[i]+" ";
		}
		X+=">";
		System.out.println("The unseen sample is "+X+"\n");

		double large=0.0;
		int pos=-1;
		for(int i=0;i<getlen(no_attr);i++)
		{
			double product=1.0;
			for(int j=0;j<no_attr;j++)
			{
				product*=a[j].p[getindex(j,entry[j])][i];
			}
			p_entry[i]=class_p[i]*product;
			System.out.println("P(X|"+values[i][no_attr]+").P("+values[i][no_attr]+")="+p_entry[i]);
			if(p_entry[i]>large)
			{
				large=p_entry[i];
				pos=i;
			}
		}

		System.out.println("The DECISION is "+values[pos][no_attr]);
	}

	int getindex(int col_no,String temp)
	{
		for(int i=0;i<getlen(col_no);i++)
		{
			if(values[i][col_no].equals(temp))
				return i;
		}

		System.out.println("INVALID ENTRY");
		return 32676;
	}
}

class Bayes
{
	public static void main(String args[])throws Exception
	{
		Scanner s = new Scanner(System.in);
		Classifier c= new Classifier();
		System.out.println("Enter the name of the input file with its extension ");
		c.readFile(s.next());
	}
}

/*
OUTPUT :
Enter the name of the input file with its extension
Bayesinput1.txt
P(SUNBURNED)= 0.375
P(NONE)= 0.625
Enter new Entry
Enter HAIR
BROWN
Enter HEIGHT
TALL
Enter WEIGHT
AVERAGE
Enter DUBLIN
NO
The unseen sample is X=< BROWN TALL AVERAGE NO >

P(X|SUNBURNED).P(SUNBURNED)=0.0
P(X|NONE).P(NONE)=0.03200000000000001
The DECISION is NONE
Press any key to continue . . .
*/