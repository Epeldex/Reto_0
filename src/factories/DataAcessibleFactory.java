package factories;

import controller.DataAcessibleDBImplementation;
import controller.DataAcessibleFileImplementation;
import interfaces.DataAccessible;

public class DataAcessibleFactory {

	private static DataAccessible obj1 = new DataAcessibleDBImplementation();
	private static DataAccessible obj2 = new DataAcessibleFileImplementation();

	/**
	 * Returns an instance of the AccountManageable interface implementation.
	 *
	 * @return the instance of the AccountManageable interface implementation
	 */
	public static DataAccessible getUdEnunciados() {
		return obj1;
	}
	public static DataAccessible getConvocatorias() {
		return obj2;
	}
    
}
