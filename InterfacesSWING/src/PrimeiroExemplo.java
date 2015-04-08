import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import feevale.telas.MntAlunos;

/** alguns lementos que extendem JComponent:
 * 
 * JLabel--
 * JTextField--
 * JComboBox--
 * JTextArea--
 * JCheckBox
 * JRadioButton--
 * JButton
 * ...
 */
public class PrimeiroExemplo {

	public static void main(String[] args) {
		
		System.out.println( "Iniciando..." );
		
		JFrame frm = new JFrame();
		frm.setBounds( 120, 200, 700, 550 );
		
		frm.setLayout( null );
		Container container = frm.getContentPane();
		JLabel lbl = new JLabel( "FEEVALE" );
		lbl.setBounds( 20, 30, 120, 23 );
		container.add( lbl );
		
		JTextField tf = new JTextField();
		tf.setBounds( 120, 30, 80, 23 );
		container.add( tf );

		frm.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		frm.setVisible( true );
	}
}






