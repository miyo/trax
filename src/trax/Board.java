package trax;

public class Board {
	
	private byte[] board = new byte[262144]; // 512 * 512;
	private byte[] avail = new byte[262144]; // 512 * 512;
	
	private int top = 256;
	private int left = 256;
	
	public static final byte NONE = 0;
	public static final byte WRRW = 1; // N:W, E:R, W:R, S:W
	public static final byte RWWR = 2; // N:R, E:W, W:W, S:R
	public static final byte RWRW = 3; // N:R, E:W, W:R, S:W
	public static final byte WRWR = 4; // N:W, E:R, W:W, S:R
	public static final byte WWRR = 5; // N:W, E:W, W:R, S:R
	public static final byte RRWW = 6; // N:R, E:R, W:W, S:W
	public static final byte AVAIL_RED      = 1;
	public static final byte AVAIL_WHITE    = 2;
	
	private int turn = 0;
	
	public void init(){
		turn = 0;
		for(int i = 0; i < board.length; i++){
			board[i] = NONE;
			avail[i] = NONE;
		}
	}
	
	public void set(int x, int y, byte mark){
		int x0, y0;
		y0 = top + y;
		x0 = left + x;
		// for next
		if(y == 0) top--;
		if(x == 0) left--;
		board[(y0 << 8) + x0] = mark;
		if(board[((y0-1) << 8) + x0] == NONE){ // N
			if(mark == WRRW || mark == WRWR || mark == WWRR){
				avail[((y0-1) << 8) + x0] = AVAIL_WHITE;
			}else{
				avail[((y0-1) << 8) + x0] = AVAIL_RED;
			}
		}
		if(board[(y0 << 8) + (x0+1)] == NONE){ // E
			if(mark == RWWR || mark == RWRW || mark == WWRR){
				avail[(y0 << 8) + (x0+1)] = AVAIL_WHITE;
			}else{
				avail[(y0 << 8) + (x0+1)] = AVAIL_RED;
			}
		}
		if(board[(y0 << 8) + (x0-1)] == NONE){ // W
			if(mark == RWWR || mark == WRWR || mark == RRWW){
				avail[(y0 << 8) + (x0-1)] = AVAIL_WHITE;
			}else{
				avail[(y0 << 8) + (x0-1)] = AVAIL_RED;
			}
		}
		if(board[((y0+1) << 8) + x0] == NONE){ // S
			if(mark == WRRW || mark == RWRW || mark == RRWW){
				avail[(y0 << 8) + (x0-1)] = AVAIL_WHITE;
			}else{
				avail[((y0+1) << 8) + x0] = AVAIL_RED;
			} // S
		}
	}

}
