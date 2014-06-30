import java.util.*;
import java.io.*;
class Apriori1
{

	public static void main(String [] args) throws Exception
	{
		AprioriCalculation ap = new AprioriCalculation();
		ap.aprioriProcess();
	}
}

class AprioriCalculation
{
	Vector <String> candidates = new Vector<String>();
	String configFile = "config1.txt";
	String transaFile = "transaction1.txt";
	String outputFile = "output1.txt";
	int numItems;
	int numTransactions;
	double minSup;
	String oneVal[];
	String itemSep = " ";


	public void aprioriProcess() throws Exception
	{
		int itemSetNumber=0;
		getConfig();
		do
		{
			itemSetNumber++;
			generateCandidates(itemSetNumber);
			calculateFrequentItemsets(itemSetNumber);
			if(candidates.size()!=0)
			{
				System.out.println("Frequent "+itemSetNumber+" -itemsets");
				System.out.println(candidates);
			}
		}while(candidates.size()>1);
	}

	public static String getInput()
	{
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			input=br.readLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return input;
	}


	private void getConfig() throws Exception
	{
		FileInputStream file_in = new FileInputStream(configFile);
		BufferedReader data_in = new BufferedReader(new InputStreamReader(file_in));
		numItems = Integer.valueOf(data_in.readLine()).intValue();
		numTransactions = Integer.valueOf(data_in.readLine()).intValue();
		minSup = Double.valueOf(data_in.readLine()).doubleValue();
		System.out.println("\nInput configuration: "+numItems+" items, "+numTransactions+" Transactions, ");
		System.out.println("minSup = "+minSup+" %");
		System.out.println();
		minSup/=100.0;
		oneVal = new String[numItems];
		for(int i=0;i<oneVal.length;i++)
		oneVal[i]="1";

		FileWriter fw = new FileWriter(outputFile);
		BufferedWriter file_out = new BufferedWriter(fw);
		file_out.write("The number of transactions are "+numTransactions+"\n");
		file_out.write("The number of items are "+numItems+"\n");
		file_out.close();
	}

	private void generateCandidates(int n)
	{
		Vector <String> tempCandidates = new Vector<String>();
		String str1,str2;
		StringTokenizer st1,st2;
		if(n==1)
		{
			for(int i=1;i<=numItems;i++)
			{
				tempCandidates.add(Integer.toString(i));
			}
		}
		else if(n==2)
		{
			for(int i=0;i<candidates.size();i++)
			{
				st1 = new StringTokenizer(candidates.get(i));
				str1 = st1.nextToken();
				for(int j=i+1;j<candidates.size();j++)
				{
					st2 = new StringTokenizer(candidates.elementAt(j));
					str2 = st2.nextToken();
					tempCandidates.add(str1+" "+str2);
				}
			}
		}
		else
		{
			for(int i=0;i<candidates.size();i++)
			{
				for(int j=i+1;j<candidates.size();j++)
				{
					str1 = new String();
					str2 = new String();
					st1 = new StringTokenizer(candidates.get(i));
					st2 = new StringTokenizer(candidates.get(j));

					for(int s=0;s<n-2;s++)
					{
						str1 = str1+" "+st1.nextToken();
						str2 = str1+" "+st2.nextToken();
					}
					if(str2.compareToIgnoreCase(str1)==0)
					tempCandidates.add((str1 + " " + st1.nextToken() + " " + st2.nextToken()).trim());

				}
			}
		}

		candidates.clear();
		candidates = new Vector<String>(tempCandidates);
		tempCandidates.clear();
	}

	private void calculateFrequentItemsets(int n) throws Exception
	{
		Vector <String> frequentCandidates = new Vector<String>();
		FileInputStream file_in;
		BufferedReader data_in;
		FileWriter fw;
		BufferedWriter file_out;

		StringTokenizer st,stFile;
		boolean match;
		boolean trans[] = new boolean[numItems];
		int count[] = new int[candidates.size()];
		fw = new FileWriter(outputFile,true);
		file_out = new BufferedWriter(fw);
		file_in = new FileInputStream(transaFile);
		data_in = new BufferedReader(new InputStreamReader(file_in));
		for(int i=0;i<numTransactions;i++)
		{
			stFile = new StringTokenizer(data_in.readLine(),itemSep);
			for(int j=0;j<numItems;j++)
			{
				trans[j] = (stFile.nextToken().compareToIgnoreCase(oneVal[j])==0);
			}
			for(int c=0;c<candidates.size();c++)
			{
				match = false;
				st = new StringTokenizer(candidates.get(c));
				while(st.hasMoreTokens())
				{
					match = (trans[Integer.valueOf(st.nextToken())-1]);
					if(!match)
					break;
				}
				if(match)
				count[c]++;
			}
		}
		for(int i=0;i<candidates.size();i++)
		{
			if((count[i]/(double)numTransactions)>=minSup)
			{
				frequentCandidates.add(candidates.get(i));
				file_out.write(candidates.get(i)+","+ count[i]/(double)numTransactions+"\n");
			}
		}
		file_out.write("-\n");
		file_out.close();

		candidates.clear();
		candidates = new Vector<String>(frequentCandidates);
		frequentCandidates.clear();
	}
}