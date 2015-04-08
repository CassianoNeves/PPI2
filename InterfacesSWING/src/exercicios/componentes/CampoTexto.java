package exercicios.componentes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class CampoTexto extends JTextField implements MouseListener{

	public CampoTexto() {
		addMouseListener( this );
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

}
