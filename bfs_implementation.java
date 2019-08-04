/*
LAB-10--SEC B
SOLVED IN UP TIME

INPUT FORMAT:
No of test cases
n=no of total people in the city
quitness of total people inthe city(eg- 8 3 2 5 7 8) if n=5
no of combinations of ppl wealthier than someone(i.e x>y) and inputing xi and yi
2 1
3 2
4 3
no of friends
1 2 3 4 5 	position of friends(output will be the quitness of these numbred friends only)




*/
import java.io.*;
import java.util.*;
import java.lang.*;
class bfs_implementation{
	public static void main(String args[]){

		Scanner Sc = new Scanner(System.in);
		int T=Sc.nextInt();
		for(int j=0;j<T;j++){

		int n=Sc.nextInt();
		int [] arr_k=new int [n];
		for(int i=0;i<n;i++){
			int k=Sc.nextInt();
			arr_k[i]=k;

		}
		int m=Sc.nextInt();
		int [] arr_m=new int [2*m];
		for(int i=0;i<2*m;i++){
			int w=Sc.nextInt();
			arr_m[i]=w;

		}
		int q=Sc.nextInt();
		int [] arr_q=new int [q];
		for(int i=0;i<q;i++){
			int a=Sc.nextInt();
			arr_q[i]=a;
		}
		adjacency(n,arr_m,arr_k,arr_q);
		}
	}

	public static void add(LinkedList L,int toadd){

		// addition of a Node at the end
		Node ptr=L.head;
		Node y = new Node(toadd);
		while(ptr.next!=null){
			ptr=ptr.next;
		}
		ptr.next=y;
			
	}
	public static void delete(LinkedList L){
		//deletion of a no. placed at 1st position
		L.head=L.head.next;
	}

	public static void adjacency(int n,int [] arr,int [] arr_k,int [] arr_q){
		//implementation of an adjacency list using an array of type linkedlist.
		LinkedList [] adjlist=new LinkedList [n];
		for(int i=0;i<n;i++){
			adjlist[i]=new LinkedList();
		}

		int p=arr.length;
		int [] Xpairs=new int [p/2];
		int [] Ypairs=new int [p/2];
		int kx=0;
		for(int i=0;i<p;i+=2){
			if (i%2==0){
				int x=arr[i];
				Xpairs[kx++]=x;
			}
			
		}
		int ky=0;
		for(int i=1;i<p;i+=2){
			if (i%2!=0){
				int y=arr[i];
				Ypairs[ky++]=y;
			}
			
		}


		for(int y=1;y<n+1;y++){
			Node temp= adjlist[y-1].head;
			Node self=new Node(y);
			temp.next=self;
			temp=temp.next;

			for(int q=0;q<(p/2);q++){
				if(Ypairs[q]==y){
				//insert corresponding x's in adjlist[y-1]
					Node toadd=new Node (Xpairs[q]);
					temp.next=toadd;
					temp=temp.next;


				}	
			}
			



		}

for(int i=1;i<n+1;i++){
bfs(i,n,adjlist,arr_q,arr_k);
}
int [] M=find_min(arr_q,adjlist,arr_k);
for(int i=0;i<arr_q.length;i++)
	System.out.println(M[i]);



}
public static int[] find_min(int [] arr_q,LinkedList [] adjlist,int [] arr_k){
//friends
	int min=-1;
	int [] M=new int[arr_q.length];
	int k=0;

	for(int i=0;i<arr_q.length;i++){
		int fr=arr_q[i];
		//now find that friend 
		//find min in adjlist[fr]

		Node temporary = adjlist[fr-1].head.next.next;
		if (temporary==null){
			min = -1;
		}
		else{
			min=arr_k[temporary.data-1];
			while(temporary!=null){
			if (arr_k[temporary.data-1]<min)
				min=arr_k[temporary.data-1];
			
			temporary=temporary.next;
			}
		}
			// System.out.println("m"+min);
		
			M[k++]=min;
	}
	return M;

}

//IMPLEMENTING BFS:
public static void bfs(int s,int n,LinkedList [] adjlist,int[] arr_q,int [] arr_k){

	LinkedList queue=new LinkedList();
	boolean visited[]=new boolean[n];
	int [] dist =new int[n];

	for(int i=0;i<n;i++){
		visited[i]=false;
		dist[i]=-5;
	}
	
	visited[s-1]=true;
	dist[s-1]=0;

	add(queue,s);
	Node check=queue.head;
	while(check.next!=null){
		int v=queue.head.next.data;		
		delete(queue);
		//neighbours of v-- (if not in visited , add to queue,visited=true)
		Node ptr= adjlist[v-1].head.next.next;	//only neighbours
		while(ptr!=null){
			// System.out.println(ptr.data);
			if (visited[ptr.data-1]==false){
				dist[ptr.data-1]=0;
				dist[ptr.data-1]=dist[v-1]+1;
				add(queue,ptr.data);
				visited[ptr.data-1]=true;
			}
			ptr=ptr.next;
		}

		check=check.next;
		
	}
	for(int p=0;p<n;p++){
		if(dist[p]>1){
			// System.out.println("REQUIRED"+(p+1));
			add(adjlist[s-1],p+1);
		}
	}


}




}

class Node{
	public int data;
	public Node next;
	public Node(int x)
	{

		data=x;
		next=null;
	}
}


class LinkedList
{	Node head;
	public LinkedList(){
	head=new Node(1);
	}
	
}