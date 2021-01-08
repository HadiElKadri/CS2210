/**
 * @author Hadi El-Kadri
 * Class GraphException throws an exception when a node exists already or does not
 */
public class GraphException extends Exception{
		public GraphException()
		{
			super ("Error! Node does not exist or already exists!");
		}
	}
