package feevale.telas;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MntAlunos extends JFrame {
	
	public MntAlunos() {
		
		setBounds( 120, 200, 700, 550 );
		
		setLayout( null );
		Container container = getContentPane();

		JLabel lbl = new JLabel( "FEEVALE" );
		lbl.setBounds( 20, 30, 120, 23 );
		container.add( lbl );
	
		lbl.setForeground( new Color( 250, 250, 250 ) );
		lbl.setBackground( Color.BLUE );
		lbl.setOpaque( true );
		
		
		
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setVisible( true );	}
}