package feevale.telas;

import feevale.baseInterface.BaseInterface;

public class MntProfessores extends BaseInterface {
	
	public MntProfessores() {
		
		container.add( getJLabel( 80, "Código" ) );
		container.add( getJTextField( 80 ) );
		container.add( getJLabel( "Nome" ) );
		container.add( getJTextField( 320 ) );
		container.add( getJLabel( "Logradouro" ) );
		
		setVisible( true );
	}
	
	public static void main(String[] args) {
		new MntProfessores();
	}
}