package balls;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface IF_Play extends Remote {
	void yourPeers ( ArrayList<IF_Play> peers ) throws RemoteException;
	void throwBall ( Ball b ) throws RemoteException;
}