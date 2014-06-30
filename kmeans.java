import java.io.*;
import java.util.*;

class kmeans
{
static int arr[]=new int[20];
static int k1[]=new int[20];
static int k2[]=new int[20];
static int j=0,k=0;

static float m1,m2;

static float tem1,tem2;

static int n,i;
public static void main(String args[])
{

try
{
Scanner sc=new Scanner(System.in);

System.out.println("\n Specify the number of values you want to enter:");
n=sc.nextInt();

System.out.println("\n Enter the values:");
for(i=0;i<n;i++)
arr[i]=sc.nextInt();

m1=3;m2=4;
do
{
cal();
display();
avg();
if((tem1==m1)&&(tem2==m2))
break;
}while(true);
}
catch(Exception e)
{
}

}

public static void cal()
{j=0;
  k=0;
for(i=0;i<n;i++)
{
if(Math.abs(m1-arr[i])< Math.abs(m2-arr[i]))
k1[j++]=arr[i];
else
k2[k++]=arr[i];
}

}


public static void avg ()
{
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
 }

public static void display()
{
System.out.println("\n m1\t m2\t k1\t\t k2");
System.out.println("\n" +m1+"\t"+m2 +"\t");
for(i=0;i<j;i++)
System.out.print(k1[i]+" ");
System.out.println("\t");
for(i=0;i<k;i++)
System.out.print(k2[i]+" ");
}

}