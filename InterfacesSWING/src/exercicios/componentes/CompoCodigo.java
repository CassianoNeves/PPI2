package exercicios.componentes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class CompoCodigo extends JTextField implements MouseListener, KeyListener {

	private int tamanho = 0;
	private int tamanhoAtual;
	private String texto;
	
	
	public CompoCodigo() {
		addMouseListener( this );
		addKeyListener( this );
	}
	
	public CompoCodigo( int tamanho) {
		setTamanho(tamanho);
		
		addMouseListener( this );
		addKeyListener( this );
	}
	
	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	//MouseListener
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBackground( new Color( 0xD3D3D3 ));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBackground( Color.WHITE);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	//KeyListener

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		
		char ch = e.getKeyChar();
		
		if( ch < '0' || ch > '9' ){
			e.consume();
		}
		
		texto = getText();
		tamanhoAtual = texto.length();
		
		if(!(tamanho == 0) ){
			if(tamanhoAtual >= tamanho ){
				e.consume();
			}
		}
	}
}
