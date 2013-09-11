package com.amgregori.androidgo;

public class Point {
	final int x;
	final int y;
	final char color;

	Point(int x, int y, char color){
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public char getColor(){
		return this.color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if(this == other){
			return true;
		}else if(other instanceof Point &&
				((Point) other).getX() == x &&
				((Point) other).getY() == y &&
				((Point) other).getColor() == color){
			return true;
		}
		return false;
	}

	//public int hashCode() {
	// // Get the number of possible digits in this board's X or Y coordinates. 
	// String digits = Integer.toString(Integer.toString(this.boardSize).length());
	// 
	// // Construct an int in the form 1[X][Y][color] and return it.
	// StringBuilder sb = new StringBuilder();
	// sb.append('1');
	// sb.append(String.format("%0" + digits + "d", this.x));
	// sb.append(String.format("%0" + digits + "d", this.y));
	// sb.append(this.color);
	// return Integer.parseInt(sb.toString());
	//}

}