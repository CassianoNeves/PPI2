package exercicios.exercicio4;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.w3c.dom.CDATASection;

import exercicios.componentes.SeletorDeEstados;

public class TestaBase4 extends Base4{
	
	JTextField lblCodigo;
	JTextField lblNome;
	JTextField lblLogradouro;
	JTextField lblCep;
	SeletorDeEstados cbEstados;
	ButtonGroup gpSexo;
	JRadioButton rbMasculino;
	JRadioButton rbFeminino;
	JTabbedPane tp1;
	JPanel painel1;
	JPanel painel2;
	JCheckBox ckFornecedor;
	
	public TestaBase4(){
		super( "Exercicio 2 de Java" );
		
		setBounds(0, 0, 750, 600 );
		setLocationRelativeTo(null);
		setLayout(null);
		
		container.add(  getJLabel( 30, 130, 55, "Código:" ) );
		container.add( lblCodigo = getCampoCodigo(60) );
		container.add( getJLabel( 50, "Nome:" ) );
		container.add( lblNome = getCampoNome(230) );
		container.add( getJLabel( 90, "Logradouro:" ) );
		container.add( lblLogradouro = getJTextField(100) );
		
		setLinha();
		setColuna();
		
		container.add( getJLabel( 40, "CEP:" ) );
		container.add( lblCep = getJTextField(100) );
		
		//container.add( getComboBox( 100, 60, "Estado:", estados ));
		container.add( getJLabel(65, "Estados:" ));
		container.add( cbEstados = (SeletorDeEstados) getJComboBox(170));
		
		container.add( getJLabel(40, "Sexo:"));
		container.add( rbMasculino = getRadioButton(100, "Masculino"));
		container.add( rbFeminino =  getRadioButton(100, "Feminino"));
		
		gpSexo = new ButtonGroup();
		gpSexo.add( rbMasculino );
		gpSexo.add( rbFeminino );
		
		
		setColuna();
		setLinha();
		
		container.add( getJLabel(90, "Fornecedor:" ));
		container.add( ckFornecedor = getJCheckBox());
		
		setLinha();
		setColuna( 95 );
		
		container.add( tp1 = getJTabbedPane( getColuna(), getLinha(), 500, 200 ) );
		
		tp1.add( painel1 = getPainel(getColuna(), getLinha(), 500, 200), "Painel 1" );
		tp1.add( painel2 = getPainel(getColuna(), getLinha(), 500, 200), "Painel 2" );
		
		setLinha(200);
		
		JLabel lbl = new JLabel( "testes" );
		lbl.setPreferredSize( new Dimension( 60, 30 ));
		
		painel1.add( lbl );
		
		//Rever este treicho do codigo
		
		JTextArea txtArea = new JTextArea();
		txtArea.setBorder( BorderFactory.createTitledBorder( "Titulo ") );
		txtArea.setLineWrap( true );
		
		JScrollPane scroll = new JScrollPane( txtArea );
		scroll.setBounds(50, 50, 500, 200);
		
		Container containerTexto = new Container();
		containerTexto.setBounds(50, 50, 500, 200);
		containerTexto.setLayout( null );
		containerTexto.add( scroll );
		
		painel2.add( containerTexto );
		
		JButton btSalvar = new JButton( "Salvar" );
		btSalvar.setBounds( 420, getLinha(), 100, 30);
		btSalvar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				validaCampos();

			}

		});
		
		JButton btCancelar = new JButton( "Cancelar" );
		btCancelar.setBounds( 530, getLinha(), 100, 30);
		btCancelar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				limparCampos();
				
			}

		});
		
		container.add( btSalvar );
		container.add( btCancelar );
		
		setVisible(true);
		
	}
	
	private void validaCampos() {
	
		if(! lblCodigo.getText().equals( "" ) ){
			if(! lblNome.getText().equals( "" ) ){
				if(! lblLogradouro.getText().equals( "" ) ){
					if (! lblCep.getText().equals( "" )) {
						if (! cbEstados.getSelectedItem().equals( "--Selecione--" )) {
							if ( ( rbMasculino.isSelected() ) || ( rbFeminino.isSelected() ) ) {
								JOptionPane.showMessageDialog(this, "Todos os dados foram Preenchidos!");
							} else {
								JOptionPane.showMessageDialog(this, "O Sexo dever ser Selecionado!" );
								rbMasculino.requestFocusInWindow();
							}
						} else {
							JOptionPane.showMessageDialog(this, "O Estado dever ser Selecionado!" );
							cbEstados.requestFocusInWindow();
						}
					} else{
						JOptionPane.showMessageDialog(this, "O CEP dever ser preenchido!" );
						lblCep.requestFocusInWindow();
					}
				} else{
					JOptionPane.showMessageDialog(this, "O Logradouro dever ser preenchido!" );
					lblLogradouro.requestFocusInWindow();
				}
			} else{
				JOptionPane.showMessageDialog(this, "O Nome dever ser preenchido!" );
				lblNome.requestFocusInWindow();
			}
		} else{
			JOptionPane.showMessageDialog(this, "O Código dever ser preenchido!" );
			lblCodigo.requestFocusInWindow();
		}
		
	}
	
	private void limparCampos() {
		
		cbEstados.getCdEstado();
		cbEstados.getNomeEstado();
		cbEstados.setCdEstado( "RS" );
		
		lblCodigo.setText( null );
		lblNome.setText( null );
		lblLogradouro.setText( null );
		lblCep.setText( null );
		cbEstados.setSelectedIndex(0);
		gpSexo.clearSelection();
		ckFornecedor.setSelected( false );
	
	}
	
	public static void main(String[] args) {
		new TestaBase4();
	}
	
	

	

}
