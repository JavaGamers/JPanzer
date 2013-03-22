package view;

import java.awt.Dimension;
import java.awt.Graphics;

public interface Territorio{
	public String getNome();
	public int getCosto();
	public void paint(Graphics g);
	public Dimension getPreferredSize();
	public int getBonus();
}
