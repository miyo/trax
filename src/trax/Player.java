package trax;


public class Player {
	
	private final Board b = new Board();
	private final IO com = new IO();
	
	public static int PLAYER_RED;
	public static int PLAYER_WHITE;
	
	public int side;
	
	public void setPlayerSide(int s){
		side = s;
	}
	
	public void play(){
		// wait and record
		com.recv();
		b.set(com.recvX, com.recvY, com.recvMark);
		// search
		
		// send message
	}
	
	public void firstMove(){
		b.set(0, 0, Board.WRRW);
		com.send(0, 0, Board.WRRW);
		//b.set(0, 0, Board.WRWR);
		//com.send(0, 0, Board.WRWR);
	}
	

}
