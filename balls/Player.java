package balls;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Player extends UnicastRemoteObject implements IF_Meet, IF_Play {
	public Player (String my_name) throws RemoteException {
		super(0);
		peers = new ArrayList<IF_Play>();
		name = my_name;
	}

	public void hereIAm ( IF_Play other ) {
		peers.add(other);
	}

	private ArrayList<IF_Play> peers;

	public void yourPeers ( ArrayList<IF_Play> peerList ) {
		peers = peerList;
	}

	public void throwBall () throws RemoteException {
		System.out.println("Got the ball: " + name)
		peers.get((int) (Math.random()*(peers.size()-1))).throwBall();
	}

	private String name;

	public static void main ( String[] args ) {

	}
}