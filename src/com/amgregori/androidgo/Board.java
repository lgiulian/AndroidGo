package com.amgregori.androidgo;

import java.util.HashSet;

public class Board {
	int boardSize;
	char[] position;

	Board(int boardSize, String position){
		if(position.length() != boardSize*boardSize)
			throw new BoardSizeException();

		this.position = position.toCharArray();
		this.boardSize = boardSize; 
	}
	
	Board(String position){
		int boardSize = (int) Math.sqrt(position.length());
		if(position.length() != boardSize*boardSize)
			throw new BoardSizeException(); 
		
		this.position = position.toCharArray();
		this.boardSize = boardSize;
	}
	
	Board(int boardSize){
		this(boardSize, emptyBoardPosition(boardSize));
	}

	Board(){
		this(19);
	}
	
	public char[] getPosition(){
		return position;
	}

	private static String emptyBoardPosition(int boardSize){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < boardSize*boardSize; i++)
			sb.append(Game.EMPTY);
		return sb.toString();
	}

	public void removeStones(HashSet<Point> stones){
		for(Point s : stones){
			setStone(s.getX(), s.getY(), Game.EMPTY);
		}
	}

	public char getStone(int x, int y){
		if(x >= 0 && x < this.boardSize && y >= 0 && y < boardSize) 
			return this.position[y * this.boardSize + x];
		return Game.OUT_OF_BOUNDS;
	}

	public String toString(){
		return new String(this.position);
	}

	public int getBoardSize(){
		return this.boardSize;
	}

	public void setStone(int x, int y, char color){
		this.position[y * this.boardSize + x] = color;
	}

	public Point[] getSurrounding(int x, int y){
		Point[] surrounding = {
				// Left
				new Point(x-1, y, getStone(x-1, y)),
				// Right
				new Point(x+1, y, getStone(x+1, y)),
				// Top
				new Point(x, y-1, getStone(x, y-1)),
				// Bottom
				new Point(x, y+1, getStone(x, y+1))
		};
		return surrounding;
	}

	public HashSet<Point> getChain(int referenceX, int referenceY){ 
		HashSet<Point> chain = new HashSet<Point>();
		Point p = new Point(referenceX, referenceY, getStone(referenceX, referenceY));
		if(p.getColor() != Game.WHITE && p.getColor() != Game.BLACK){
			return chain;
		}
		chain.add(p);
		return getChain(referenceX, referenceY, chain);
	}

	private HashSet<Point> getChain(int referenceX, int referenceY, HashSet<Point> chain){
		for(Point p : getSurrounding(referenceX, referenceY)){
			if(p.getColor() == getStone(referenceX, referenceY) &&
					chain.add(new Point(p.getX(), p.getY(), p.getColor()))){
				chain = getChain(p.getX(), p.getY(), chain);
			}
		}
		return chain;
	}
	
//	public HashSet<Integer> getChainIndices(int referenceX, int referenceY){
//		HashSet<Integer> chain = new HashSet<Integer>();
//		char color = getStone(referenceX, referenceY);
//		if(color != Game.WHITE && color != Game.BLACK){
//			return chain;
//		}
//		chain.add(referenceX + referenceY * boardSize);
//		return getChainIndices(referenceX, referenceY, chain);
//	}
//	
//	private HashSet<Integer> getChainIndices(int referenceX, int referenceY, HashSet<Integer> chain){
//		for(Point p : getSurrounding(referenceX, referenceY)){
//			if(p.getColor() == getStone(referenceX, referenceY) &&
//					chain.add(p.getX() + p.getY() * boardSize)){
//				chain = getChainIndices(p.getX(), p.getY(), chain);
//			}
//		}
//		return chain;
//	}

	public boolean isCaptured(int x, int y){
		// Raise an exception if the point in x, y is neither white nor black.
		if(getStone(x, y) != Game.WHITE && getStone(x, y) != Game.BLACK){
			// Exception
		}

		// Check if chain has any liberties.  If so, return true, otherwise false. 
		for(Point p : getChain(x, y)){
			for(Point s : getSurrounding(p.getX(), p.getY())){
				if(s.getColor() == Game.EMPTY)
					return false;
			}
		}
		return true;
	}

	public HashSet<Point> getChainLiberties(int x, int y){
		HashSet<Point> liberties = new HashSet<Point>(); 
		for(Point p : getChain(x, y)){
			for(Point s : getSurrounding(p.getX(), p.getY())){
				if(s.getColor() == Game.EMPTY)
					liberties.add(new Point(s.getX(), s.getY(), Game.EMPTY));
			}
		}
		return liberties;
	}

	public Board clone(){
		return new Board(boardSize, toString());
	}

	public void print(){
		StringBuilder sb = new StringBuilder();
		StringBuilder nb = new StringBuilder();

		for(int i = 0; i < boardSize; i++){
			if(i > 0){
				nb.append(String.format("%3s", Integer.toString(i)));
			}else{
				nb.append(String.format("%6s", Integer.toString(i)));
			}
		}

		String n = nb.toString();
		sb.append(n);
		sb.append('\n');

		for(int i = 0; i < this.position.length; i++){
			if(i == 0){
				sb.append(String.format("%3s", Integer.toString(0)));
				sb.append(' ');
			}else if(i > 0 && i % this.boardSize == 0){
				sb.append(' ');
				sb.append(i / this.boardSize - 1);
				sb.append('\n');
				sb.append(String.format("%3s", Integer.toString(i / this.boardSize )));
				sb.append(' ');
			}
			switch(this.position[i]){
			case Game.EMPTY: sb.append("-+-");
			break;
			case Game.WHITE: sb.append("-0-");
			break;
			case Game.BLACK: sb.append("-X-");
			break;
			}
		}

		sb.append(' ');
		sb.append(this.boardSize-1);
		sb.append('\n');
		sb.append(n);

		System.out.println(sb.toString());
	}

}

class BoardSizeException extends RuntimeException{
	BoardSizeException(){ super(); }
}
