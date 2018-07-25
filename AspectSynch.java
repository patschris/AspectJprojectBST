import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;

/**
 * Uses aspects and condition variables to synchonize the methods of the BST.
 * @author Christos Patsouras
 */
@Aspect
public class AspectSynch {

	/**
	* Existence of a writer (thread executing insert or remove)
	*/
	boolean writer = false;
	/**
	* Number of readers (threads executing lookup)
	*/	
	int readers = 0;
	/**
	* Is executed before the call of insert or remove in order to synchronize multiple threads.
	* This thread should wait if another thread executes insert, remove or lookup.
	* @param JoinPoint Consist of things like method calls, method executions, object instantiations, constructor executions, field references and handler executions. 
	*/
	
	@Before("execution (* BinarySearchTree.insert(..)) || execution (* BinarySearchTree.remove(..)) ")
    public synchronized void beforeWriter(JoinPoint joinPoint) throws InterruptedException {
		while (writer || readers>0) wait();
		writer = true;
    }
	
	/**
	* Is executed after the call of insert or remove in order to synchronize multiple threads.
	* This thread should notify that it was the last modifier and now anyone can use the BST.
	* @param JoinPoint Consist of things like method calls, method executions, object instantiations, constructor executions, field references and handler executions. 
	*/
    @After("execution (* BinarySearchTree.insert(..)) || execution (* BinarySearchTree.remove(..))")
    public synchronized void afterWriter(JoinPoint joinPoint) {
    	writer = false;
	    notifyAll();
    }
	
	/**
	* Is executed before the call of lookup in order to synchronize multiple threads.
	* This thread should wait only if another thread modifies (executes insert or remove).
	* @param JoinPoint Consist of things like method calls, method executions, object instantiations, constructor executions, field references and handler executions. 
	*/
	@Before("execution (* BinarySearchTree.lookup(..))")
	public synchronized void beforeReader(JoinPoint joinPoint) throws InterruptedException {
		while (writer) wait();
		readers++;
	}
	
	/**
	* Is executed after the call of lookup in order to synchronize multiple threads.
	* This thread should notify that it was the last accessor and now a modifier can use the BST.
	* @param JoinPoint Consist of things like method calls, method executions, object instantiations, constructor executions, field references and handler executions. 
	*/
	@After("execution (* BinarySearchTree.lookup(..))")
    public synchronized void afterReader(JoinPoint joinPoint) {
		readers--;
		if (readers==0) notify();
	}
}