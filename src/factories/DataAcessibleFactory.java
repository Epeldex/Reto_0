package factories;

import controller.DataAcessibleDBImplementation;
import interfaces.DataAccessible;

public class DataAcessibleFactory {

	private static DataAccessible obj1 = new DataAcessibleDBImplementation();
	/**
	 * Returns an instance of the DataAccessible interface implementation.
	 *
	 * @return the instance of the DataAccessible interface implementation
	 */
	public static DataAccessible getDataAccessible() {
		return obj1;
	}
    
}
