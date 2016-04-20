
public class Remover implements Runnable{

	int[] array;
	
	public Remover(int[] array)
	{
		this.array = array;
	}
	
	public void run()
	{
		int pointer = 0;
		int counter = 0;
		
		while(true)
		{
			for(int i : array)
			{
				if(i == 0)
					counter++;
			}
			
			if(counter >= 5)
			{
				System.out.println("Waiting for new number generation...");
				synchronized(array)
				{
					try {
						array.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				counter = 0;
			}

			synchronized(array)
			{
				System.out.println("Number " + array[pointer] + " from index " + pointer + " has been removed!");
				array[pointer] = 0;
				array.notifyAll();
			}
			pointer++;
			
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(pointer > 4)
				pointer = 0;
			
			counter = 0;
		}
	}
}
