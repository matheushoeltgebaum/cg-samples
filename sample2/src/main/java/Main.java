/// \file Exemplo_N2_Jogl_Eclipse.java
/// \brief Exemplo_N2_Jogl_Eclipse: desenha uma linha na diagonal.
/// \version $Revision: 1.0 $
/// \author Dalton Reis.
/// \date 03/05/13.
/// Obs.: variaveis globais foram usadas por questoes didaticas mas nao sao recomendas para aplicacoes reais.

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Main implements GLEventListener, KeyListener {
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;
	private Ponto4D pto1 = new Ponto4D(  0.0,   0.0, 0.0, 1.0);
	private Ponto4D pto2 = new Ponto4D(200.0, 200.0, 0.0, 1.0);
	private float ortho2D_minX = -400.0f, ortho2D_maxX =  400.0f, ortho2D_minY = -400.0f, ortho2D_maxY =  400.0f;

	public void init(GLAutoDrawable drawable) {
		System.out.println(" --- init ---");
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));
		System.out.println("Espaco de desenho com tamanho: " + drawable.getWidth() + " x " + drawable.getHeight());
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	//exibicaoPrincipal
	public void display(GLAutoDrawable arg0) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluOrtho2D(ortho2D_minX, ortho2D_maxX, ortho2D_minY, ortho2D_maxY);

		SRU();

		gl.glPointSize(2.0f);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_POINTS);
			for(int i = 1; i <= 72; i++) {
				gl.glVertex2d(RetornaX(i*5.0, 100.0), RetornaY(i*5.0, 100.0));
			}
		gl.glEnd();

		gl.glFlush();
	}	

	public void keyPressed(KeyEvent e) {
		System.out.println(" --- keyPressed ---");
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
			case 73: //Tecla "I"
				if(ortho2D_maxX > 120.0 && ortho2D_maxY > 120.0){
					ortho2D_minX = ortho2D_minX+10;
					ortho2D_maxX = ortho2D_maxX-10;
					ortho2D_minY = ortho2D_minY+10;
					ortho2D_maxY = ortho2D_maxY-10;
					glDrawable.display();
				}
				break;
			case 79: //Tecla "O"
				if(ortho2D_maxX < 680.0 && ortho2D_maxY < 680.0){
					ortho2D_minX = ortho2D_minX-10;
					ortho2D_maxX = ortho2D_maxX+10;
					ortho2D_minY = ortho2D_minY-10;
					ortho2D_maxY = ortho2D_maxY+10;
					glDrawable.display();
				}
				break;
			case 69: //Tecla "E"
				ortho2D_minX = ortho2D_minX + 10;
				ortho2D_maxX = ortho2D_maxX + 10;
				glDrawable.display();
				break;
			case 68: //Tecla "D"
				ortho2D_minX = ortho2D_minX-10;
				ortho2D_maxX = ortho2D_maxX-10;
				glDrawable.display();
				break;
			case 67: //Tecla "C"
				ortho2D_minY = ortho2D_minY-10;
				ortho2D_maxY = ortho2D_maxY-10;
				glDrawable.display();
				break;
			case 66: //Tecla "B"
				ortho2D_minY = ortho2D_minY+10;
				ortho2D_maxY = ortho2D_maxY+10;
				glDrawable.display();
				break;
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		System.out.println(" --- reshape ---");
	    gl.glMatrixMode(GL.GL_PROJECTION);
	    gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		System.out.println(" --- displayChanged ---");
	}

	public void keyReleased(KeyEvent arg0) {
		System.out.println(" --- keyReleased ---");
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(" --- keyTyped ---");
	}
	
	public void SRU() {
//		gl.glDisable(gl.GL_TEXTURE_2D);
//		gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
//		gl.glDisable(gl.GL_LIGHTING); //TODO: [D] FixMe: check if lighting and texture is enabled

		// eixo x
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glLineWidth(1.0f);
		gl.glBegin( GL.GL_LINES );
			gl.glVertex2f( -200.0f, 0.0f );
			gl.glVertex2f(  200.0f, 0.0f );
			gl.glEnd();
		// eixo y
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin( GL.GL_LINES);
			gl.glVertex2f(  0.0f, -200.0f);
			gl.glVertex2f(  0.0f, 200.0f );
		gl.glEnd();
	}

	public double RetornaX(double angulo, double raio) {
		return (raio * Math.cos(Math.PI * angulo / 180.0));
	}
	public double RetornaY(double angulo, double raio) {
		return (raio * Math.sin(Math.PI * angulo / 180.0));
	}

}
