import java.util.Random;
public class GenNum implements Runnable{

	int [] array;
	Random rand = new Random();
	
	public GenNum(int [] array)
	{
		this.array = array;
	}
	
	public void run()
	{
		int counter = 0;
		int counter_gen = 0;
		
		while(true)
		{	
			for(int i : array)
			{
				if(i > 0)
					counter++;
			}
			
			if(counter == 5)
			{
				System.out.println("Array full! waiting for empty slots...");
				synchronized (array)
				{
					try {
						array.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			synchronized(array){
				array[counter_gen] = rand.nextInt(100) + 1;
				System.out.println("Number " + array[counter_gen] + " is added!");
				array.notifyAll();
			}
			counter_gen++;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(counter_gen >= 5)
			{
				System.out.println("Generator entering 5s waiting phase...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				counter_gen = 0;
				System.out.println("Generator woken from 5s waiting phase!");
			}
		}
	}
}
