package feevale.baseInterface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class CampoDeFormulario extends JTextField implements MouseListener {

	public CampoDeFormulario() {
		addMouseListener( this );
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBackground( Color.YELLOW );
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground( Color.WHITE );
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
}