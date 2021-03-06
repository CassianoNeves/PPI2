package feevale.baseInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BaseInterface extends JFrame {

	private int linha;
	private int saltoLinha = 27;
	protected Container container;
	
	public BaseInterface() {
		this( "FEEVALE" );
	}

	public BaseInterface( String titulo ) {
		
		setBounds( 120, 200, 700, 550 );
		
		setLayout( null );
		container = getContentPane();
		
		container.setBackground( Color.CYAN );

		JLabel lbl = new JLabel( titulo );
		lbl.setBounds( 0, 0, 700, 35 );
		container.add( lbl );
		lbl.setHorizontalAlignment( JLabel.CENTER );
	
		lbl.setForeground( new Color(  0xD9D919 ) );
		lbl.setBackground( new Color(  0x7093DB ) );
		lbl.setOpaque( true );
		
		Font fonte = new Font( "Broadway", Font.PLAIN, 30 );
		lbl.setFont( fonte );
		
		setResizable( false );
		
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	}
	
	protected void setLinha(int linha) {
		this.linha = linha;
	}
	
	protected int getLinha() {
		return linha;
	}
	 
	protected void setSaltoLinha(int saltoLinha) {
		this.saltoLinha = saltoLinha;
	}
	
	protected int getSaltoLinha() {
		return saltoLinha;
	}
	
	protected JTextField getJTextField( int largura ) {

		JTextField tf = new JTextField();
		tf.setBounds( 120, linha, largura, 23 );
		
		return tf;
	}

	protected JLabel getJLabel( String label ) {
		return( getJLabel( linha + saltoLinha, label ) );
	}

	protected JLabel getJLabel( int linha, String label ) {

		this.linha = linha;
		JLabel lbl = new JLabel( label );
		lbl.setBounds( 30, linha, 100, 23 );
		
		return lbl;
	}

}