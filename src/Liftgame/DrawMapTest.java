package Liftgame;

import static org.junit.Assert.*;

import java.awt.Graphics;

import javax.crypto.spec.GCMParameterSpec;

import org.junit.Before;
import org.junit.Test;

public class DrawMapTest {
	private DrawMap drawMap = new DrawMap();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testStartTime() {
		drawMap.StartTime();
	}

	@Test
	public void testUpdateGame() {
		drawMap.updateGame();
	}

//	@Test
//	public void testPaintGraphics() {
//		drawMap.paintGraphics(new Graphics);
//	}

	@Test
	public void testInitDrawMap() {
		drawMap.InitDrawMap();
	}
}
