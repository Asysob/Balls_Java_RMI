package balls;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Player extends UnicastRemoteObject implements IF_Meet, IF_Play {
	public Player (String my_name, int peer_count) throws RemoteException {
		super(0);
		peers = new ArrayList<IF_Play>();
		peers.add((IF_Play) this);
		name = my_name;
		n_peers = peer_count;
	}

	private int n_peers;

	public void hereIAm ( IF_Play other ) throws RemoteException {
		peers.add(other);
		n_peers--;
		if (n_peers == 0) {
			for (IF_Play p : peers)
				p.yourPeers(peers);
			throwBall();
		}
	}

	private ArrayList<IF_Play> peers;

	public void yourPeers ( ArrayList<IF_Play> peerList ) {
		peers = peerList;
	}

	public void throwBall () throws RemoteException {
		System.out.println("Got the ball: " + name);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {}
		peers.get((int) (Math.random()*(peers.size()-1))).throwBall();
	}

	private String name;

	public static void main ( String[] args ) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String rmi_name = "BallBoss";	
		try {
			if (args.length == 1) {
				// I am the boss
				Player p = new Player("Boss",Integer.parseInt(args[0]));
				Registry registry = LocateRegistry.getRegistry();
				registry.rebind(rmi_name,p);
			}
			else {
				// I am another player
				String host = args[0];
				Player p = new Player(args[1],0);
				Registry registry = LocateRegistry.getRegistry(host);
				IF_Meet meeting_place = (IF_Meet) registry.lookup(rmi_name);
				meeting_place.hereIAm((IF_Play) p);
			}
		} catch (Exception e) {
			System.err.println("Player exception:");
			e.printStackTrace();
		}
	}
}