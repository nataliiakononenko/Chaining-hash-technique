import java.util.*;

public class ChainingHash
{
   public static void main(String[] args)
   {
	   Scanner input = new Scanner(System.in);

	   System.out.println("Type in the length of the hash-table: ");

	   double loadFactor;
	   int count = 0;

	   try
	   {
	   		int h = input.nextInt();

	   		while (h < 1)
	   		{
				System.out.println("The hash-table length must be a positive number, greater tan zero! Try again: ");
	   			h = input.nextInt();
			}

	   		SingleLinkedList[] hashTable = new SingleLinkedList[h];

       		for(int i = 0; i < h; i++)
       		{
				hashTable[i] = new SingleLinkedList(null, 0);
			}

       		for(int i = 0; i < 15; i++)
       		{
	   			System.out.println("Type in the value: ");
	   			int v = input.nextInt();

	   			int number = hashTable[0].hash(v, h);

	   			hashTable[number].addFirst(v);

			}

       		for(int i = 0; i < h; i++)
       		{
				if(hashTable[i].head != null)
				{
					count++;
				}
			}

			loadFactor = (double) 15/h;

			System.out.println("\nThe load factor is: " + loadFactor + "\n \nDistribution of the numbers in the hash-table:");

       		for(int i = 0; i < h; i++)
       		{
				if(hashTable[i].head != null)
				{
					System.out.print(i + ": ");
					hashTable[i].printList();
				}
				else
				{
					System.out.print(i + ": ");
				}
				System.out.println(" ");
			}
       }

       catch(InputMismatchException ex)
       {
			System.out.println("Try again. (" +
			"Incorrect input: an integer is required)");
			input.nextLine();
	   }

   }
}

class Node
{
	int value;
	Node next;

	Node (int value, Node next)
	{
		this.value = value;
		this.next = next;
	}
}

class SingleLinkedList
{
	Node head;
	int elementCount;

	SingleLinkedList (Node head, int elementCount)
	{
		this.head = head;
		this.elementCount = elementCount;
	}

	public void addFirst(int x)
	{
		if(elementCount != 0)
		{
			this.head = new Node (x, head);
			this.elementCount = ++elementCount;
		}
		else
		{
			this.head = new Node (x, null);
			this.elementCount = 1;
		}
	}

	public int hash(int v, int h)
	{
		return Math.abs((int) v % h);
	}

	public void printList()
	{
		if(elementCount != 0)
		{
			Node cPtr = head;
			for (int i=1; i<=elementCount; i++)
			{
				System.out.print(cPtr.value + " ");
				if (i % 5 == 0 && cPtr.next != null)
				{
					System.out.println("");
					System.out.print("   ");
			    }
				cPtr = cPtr.next;
			}
		}
	}
}