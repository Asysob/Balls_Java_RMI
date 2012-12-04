package balls;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IF_Meet extends Remote {
	void hereIAm ( IF_Play me ) throws RemoteException;
}