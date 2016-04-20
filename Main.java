
public class Main {

	public static void main(String args[])
	{
		int[] queue = new int[5];
		
		GenNum task1 = new GenNum(queue);
		Remover task2 = new Remover(queue);
		
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		
		t1.start();
		t2.start();
	}
}
