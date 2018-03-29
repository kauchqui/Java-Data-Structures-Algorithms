package pathfinder;/*Quintin Kauchick; qkauchi
//I worked on the homework assignment alone, using only this semester's course materials.
*/
/**
 * Starter code for the Maze path finder problem.
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayDeque;


/*
 * Recursive class to represent a position in a path
 */
class Position{
	public int i;     //row
	public int j;     //column
	public char val;  //1, 0, or 'X'
	
	// reference to the previous position (parent) that leads to this position on a path
	Position parent;
	
	Position(int x, int y, char v){
		i=x; j = y; val=v;
	}
	
	Position(int x, int y, char v, Position p){
		i=x; j = y; val=v;
		parent=p;
	}
	
}


public class PathFinder {
	
	public static void main(String[] args) throws IOException {
		if(args.length<1){
			System.err.println("***Usage: java PathFinder maze_file");
			System.exit(-1);
		}
		
		char [][] maze;
		maze = readMaze(args[0]);
		printMaze(maze);
		Position [] path = stackSearch(maze);
		System.out.println("stackSearch Solution:");
		printPath(path);
		printMaze(maze);
		
		char [][] maze2 = readMaze(args[0]);
		path = queueSearch(maze2);
		System.out.println("queueSearch Solution:");
		printPath(path);
		printMaze(maze2);
	}
	
	
	public static Position [] stackSearch(char [] [] maze) throws NullPointerException{
		
     	ArrayDeque <Position> stackList = new ArrayDeque<Position>();
     	Position first = new Position(0,0,'0',null);
        stackList.addFirst(first);

     while(stackList.isEmpty() == false){
     	Position pos = stackList.removeFirst();
     	if(pos.i == maze.length-1 && pos.j == maze[0].length-1){// this goes back through and changes all of the 'x' chars to '0'
     		for(int row =0; row <maze.length; row++){
     			for(int col =0; col<maze[0].length; col++){
     			if(maze[row][col] != '1'){
     				maze[row][col] = '0';
     			}
     			}
     		}
     		//setting the beginning to 'x'
     		maze[0][0] = 'X';
     		//setting the ending to 'x'
     		maze[pos.i][pos.j] = 'X';
     		Position finPath[] = new Position[maze[0].length*maze[0].length]; //creating the position array that will be returned
     		finPath[0] = pos; //storing first position
     		pos = pos.parent; //moving pos to its parent
     		int inc=0;
     		while(pos != null) {//this places an x on the correct path
     			maze[pos.i][pos.j] = 'X';
     			finPath[inc] = pos;
     			pos = pos.parent;
     			inc++;
     			
     		}
     		return finPath;
     	} else {
     		maze[pos.i][pos.j] = 'X';
     		if(pos.i+1< maze[0].length && maze[pos.i+1][pos.j] == '0') {
     			Position addPos = new Position(pos.i+1, pos.j, '0', pos);
     		 	stackList.addFirst(addPos);
			}
			if(pos.j+1<maze.length && maze[pos.i][pos.j+1] == '0'){
				Position addPos = new Position(pos.i,pos.j+1,'0',pos);
				stackList.addFirst(addPos);
			}
			if(pos.j-1>-1 && maze[pos.i][pos.j-1] == '0') {
				Position addPos = new Position(pos.i,pos.j-1,'0',pos);
				stackList.addFirst(addPos);
			}
			if(pos.i-1>-1 && maze[pos.i-1][pos.j] == '0'){
				Position addPos = new Position(pos.i-1,pos.j,'0',pos);
				stackList.addFirst(addPos);
			}
     	}
     }
     throw new NullPointerException("No path available!");

 }


	







	public static Position [] queueSearch(char [] [] maze) throws NullPointerException{
		ArrayDeque <Position> queueList = new ArrayDeque<Position>();
		Position first = new Position(0,0,'0',null);
        queueList.addFirst(first);

     while(queueList.isEmpty() == false){
     	Position pos = queueList.removeLast();
     	if(pos.i == maze.length-1 && pos.j == maze[0].length-1){
     		for(int row =0; row <maze.length; row++){
     			for(int col =0; col<maze[0].length; col++){
     			if(maze[row][col] != '1'){
     				maze[row][col] = '0';
     			}
     			}
     		}
     		maze[0][0] = 'X';
     		maze[pos.i][pos.j] = 'X';
     		Position finPath[] = new Position[maze[0].length*maze[0].length];
     		finPath[0] = pos;
     		int inc = 0;
     		pos = pos.parent;
     		
     		while(pos != null) {
     			maze[pos.i][pos.j] = 'X';
     			finPath[inc] = pos;
     			pos = pos.parent;
     			inc++;
     			
     		}
     		return finPath;
     	} else {
     		maze[pos.i][pos.j] = 'X';
     		if(pos.i+1< maze[0].length && maze[pos.i+1][pos.j] == '0') {
     			Position addPos = new Position(pos.i+1, pos.j, '0', pos);
     		 	queueList.addFirst(addPos);
			}
			if(pos.j+1<maze.length && maze[pos.i][pos.j+1] == '0'){
				Position addPos = new Position(pos.i,pos.j+1,'0',pos);
				queueList.addFirst(addPos);
			}
			if(pos.j-1>-1 && maze[pos.i][pos.j-1] == '0') {
				Position addPos = new Position(pos.i,pos.j-1,'0',pos);
				queueList.addFirst(addPos);
			}
			if(pos.i-1>-1 && maze[pos.i-1][pos.j] == '0'){
				Position addPos = new Position(pos.i-1,pos.j,'0',pos);
				queueList.addFirst(addPos);
			}
     	}
     }
     throw new NullPointerException("No path available!");

 }


	public static void printPath(Position [] path){
		

		int i = 0;
		System.out.print("The path for the maze is:   ");
		for(int j = 0; j < path.length; j++) {
			if(path[j+1] == null){
				i = j;
				break;
			}
		}
		for(int j = i; j>= 0; j--) {
			if(j == 0){
				System.out.print("[" + path[j].i+"," + path[j].j +"] ");
				break;
				}
			
			System.out.print("[" + path[j].i + "," + path[j].j + "],  ");
		}
		
		
		System.out.println();
	}
	
	/**
	 * Reads maze file in format:
	 * N  -- size of maze
	 * 0 1 0 1 0 1 -- space-separated 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static char [][] readMaze(String filename) throws IOException{
		char [][] maze;
		Scanner scanner;
		try{
			scanner = new Scanner(new FileInputStream(filename));
		}
		catch(IOException ex){
			System.err.println("*** Invalid filename: " + filename);
			return null;
		}
		
		int N = scanner.nextInt();
		scanner.nextLine();
		maze = new char[N][N];
		int i=0; 
		while(i < N && scanner.hasNext()){
			String line =  scanner.nextLine();
			String [] tokens = line.split("\\s+");
			int j = 0;
			for (; j< tokens.length; j++){
				maze[i][j] = tokens[j].charAt(0);
			}
			if(j!=N){
				System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
				return null;
			}
			i++;
		}
		if(i!=N){
			System.err.println("*** Invalid file: has wrong number of rows: " + i);
			return null;
		}
		return maze;
	}
	
	public static void printMaze(char[][] maze){
		
		if(maze==null || maze[0] == null){
			System.err.println("*** Invalid maze array");
			return;
		}
		
		for(int i=0; i< maze.length; i++){
			for(int j = 0; j< maze[0].length; j++){
				System.out.print(maze[i][j] + " ");	
			}
			System.out.println();
		}
		
		System.out.println();
	}

}