import java.util.LinkedList;
import java.util.Random;

/**
 * Using a number of threads executing a random number of BST's method
 * @author Christos Patsouras
 */
public class Main {
	public static void main(String[] args) {
		int i, nt = 20;
		/****************/
		/* Creating BST */
		/****************/
		BinarySearchTree b = new BinarySearchTree();
		/************************************/
		/* Creating a list to store threads */
		/************************************/
	    LinkedList<Thread> l = new LinkedList<Thread>();
	    /***********************/
	    /* Creating nt threads */
	    /***********************/
	    for (i=0; i<nt; i++){
	    	l.add(new Thread(new Runnable() {
				/***************************************************************/
				/* Every thread executes a specific number of commands (iter)  */
				/* Commands: insert, remove, lookup							   */
				/* Pick one of them randomly (funcNum)						   */
				/***************************************************************/
				@Override
				public void run() {
					int j, iter, min, max, funcNum, data;
					iter = 20;
					for (j=0; j<iter; j++) {
						min = 1; 
						max = 3;
						funcNum = new Random().nextInt(max) + min;
						min = 0; 
						max = 20;
						data = new Random().nextInt(max) + min;
						switch (funcNum) {
							case 1:		
								b.insert(data);
								break;
							case 2:	
								b.remove(data);
								break;
							case 3:		
								b.lookup(data);
								break;
							default:
								System.out.println("Never should be here");
								break;
						}
					}
				}
			}));
	    }
	    /*****************************************/
	    /* Starting the execution of the threads */
	    /*****************************************/
		System.out.println("I am going to start " + nt + " threads");
		for (Thread t : l) {
	    	t.start();
	    	System.out.println(t.getName() + " started");
	    }
	    /******************************************/
	    /* Wait for the completion of the threads */
	    /******************************************/
	    for (Thread t : l) {
			try {
				t.join();
				System.out.println(t.getName() + " joined");
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("A problem occured. Exiting unsuccessfully");
				System.exit(-1);
			}
		}
		System.out.println("Threads joined");
		/************************/
		/* Print the final tree */
		/************************/
		System.out.print("\nThe final tree is:");
		b.printTree(b.getRoot());
		System.out.println(new String());

		System.exit(0);
	}
}