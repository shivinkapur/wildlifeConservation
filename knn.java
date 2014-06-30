import java.io.*;
class knn
{
	public static void main(String arg[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new FileWriter("knnout2.txt"));
		int a[]={100,700,140,210,350,400,550,160,870,220};
		int cls[]={0,1,0,0,1,1,1,0,1,0};
		int diff[]=new int[10];
		int p=300;
		for(int i=0;i<10;i++)
		{
			diff[i]=a[i]-p;
			if(diff[i]<0)
				diff[i]=diff[i]*(-1);
		}
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(diff[j]>diff[j+1])
				{
					int t=diff[j];
					diff[j]=diff[j+1];
					diff[j+1]=t;
					t=cls[j];
					cls[j]=cls[j+1];
					cls[j+1]=t;
				}
			}
		}
		bw.write("Data Set:");
		bw.newLine();
		for(int i=0;i<10;i++)
		{
			int x=a[i];
			bw.write(x+",");
		}
		bw.newLine();
		bw.write("Input : "+p);
		bw.newLine();
		for(int i=0;i<10;i++)
		{
			System.out.println(cls[i]);
		}
		int c0=0,c1=0;
		for(int i=0;i<5;i++)
		{
			if(cls[i]==0)
				c0++;
			else
				c1++;
		}
		if(c1>c0)
			bw.write("Densely Populated");
		else
			bw.write("Sparsely Populated");
		bw.close();
	}
}