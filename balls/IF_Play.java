package balls;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IF_Play extends Remote {
	void yourPeers ( ArrayList<IF_Play> peers ) throws RemoteException;
	void throwBall () throws RemoteException;
}