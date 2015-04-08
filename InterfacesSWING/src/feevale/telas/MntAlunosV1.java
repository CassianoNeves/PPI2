package feevale.telas;

import feevale.baseInterface.BaseInterface;

public class MntAlunosV1 extends BaseInterface {
	
	public MntAlunosV1() {
		super( "Manutenção De Clientes" );
		
		container.add( getJLabel( 80, "Código" ) );
		container.add( getJTextField( 80 ) );
		container.add( getJLabel( "Nome" ) );
		container.add( getJTextField( 320 ) );
		container.add( getJLabel( "Logradouro" ) );
		container.add( getJTextField( 320 ) );
		container.add( getJLabel( "CEP" ) );
		container.add( getJTextField( 80 ) );
		container.add( getJLabel( "Estado" ) );

		setVisible( true );
	}
	
	public static void main(String[] args) {
		new MntAlunosV1();
	}
	
}