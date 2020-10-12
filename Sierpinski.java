package com.maxim;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Scanner;
import javax.swing.*;

public class Sierpinski extends JFrame
{
    protected static Graphics graphics;
    protected static int level;

    protected static final int SIZE = 243;
    
    public Sierpinski(int level) {
		super("Sierpinski Carpet");
		this.level = level;

		setSize(SIZE, SIZE + 24);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

    }
	
    public void drawSquare(Point upperL, Point lowerR, int level, Graphics g) {

		// if level is 0, simply draws a square using the given values
		if(level == 0) {
			Polygon p = new Polygon();
			p.addPoint(upperL.x, upperL.y);
			p.addPoint(lowerR.x, upperL.y);
			p.addPoint(lowerR.x, lowerR.y);
			p.addPoint(upperL.x, lowerR.y);
			g.drawPolygon(p);
	    }

		else {
	    // calculating positions for each point
			Point p3 = new Point(upperL.x + (lowerR.x-upperL.x)/3, upperL.y);
			Point p4 = new Point(upperL.x + 2*(lowerR.x-upperL.x)/3, upperL.y);

			Point p5 = new Point(upperL.x, (lowerR.y-upperL.y)/3 + upperL.y);
			Point p6 = new Point((lowerR.x-upperL.x)/3+upperL.x, (lowerR.y-upperL.y)/3+upperL.y);
	
			Point p7 = new Point(2*(lowerR.x-upperL.x)/3+upperL.x, (lowerR.y-upperL.y)/3+upperL.y);
			Point p8 = new Point(lowerR.x, (lowerR.y-upperL.y)/3+upperL.y);
		
			Point p9 = new Point(upperL.x, 2*(lowerR.y-upperL.y)/3+upperL.y);
			Point p10 = new Point((lowerR.x-upperL.x)/3+upperL.x, 2*(lowerR.y-upperL.y)/3+upperL.y);

			Point p11 = new Point(2*(lowerR.x-upperL.x)/3+upperL.x, 2*(lowerR.y-upperL.y)/3+upperL.y);
			Point p12 = new Point(lowerR.x, 2*(lowerR.y - upperL.y)/3+upperL.y);
		
			Point p13 = new Point((lowerR.x-upperL.x)/3+upperL.x, lowerR.y);
			Point p14 = new Point(2*(lowerR.x-upperL.x)/3+upperL.x, lowerR.y);

		// drawing the 8 squares using the points calculated
			drawSquare(upperL,p6,level-1,g);
			drawSquare(p3,p7,level-1,g);
			drawSquare(p4,p8,level-1,g);
			drawSquare(p5,p10,level-1,g);
			drawSquare(p7,p12,level-1,g);
			drawSquare(p9,p13,level-1,g);
			drawSquare(p10,p14,level-1,g);
			drawSquare(p11,lowerR,level-1,g);

	    }	

    }

    @Override
    public void paint(Graphics g) {
		Point p1 = new Point(0, 24);
		Point p2 = new Point(SIZE, SIZE + 24);

		drawSquare(p1, p2, level, g);
    }

    public static void main(String [] args){
		System.out.println("Please enter the desired carpet level: ");
		Scanner input = new Scanner(System.in);
		int level = input.nextInt();

		Sierpinski s = new Sierpinski(level);
		Graphics g = s.getGraphics();

		// calls drawSquare() function and draws the square in the frame
		s.paint(g);
	}
}
