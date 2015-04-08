package exercicios.exercicio4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.beans.Customizer;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import exercicios.componentes.CampoNome;
import exercicios.componentes.CampoTexto;
import exercicios.componentes.CompoCodigo;
import exercicios.componentes.SeletorDeEstados;

public class Base4 extends JFrame{
	
	protected int linha = 0;
	protected int coluna = 0;
	protected int espaçamento = 40;
	protected Container container;
	public Base4( String titulo){
		
		container = getContentPane();
		
		JLabel lbltitulo = new JLabel( titulo );
		lbltitulo.setBounds(0, 0, 750, 100);
		lbltitulo.setHorizontalAlignment( JLabel.CENTER );
		lbltitulo.setBackground( Color.green );
		lbltitulo.setOpaque( true );
		
		Font fonte = new Font( "Arial", Font.PLAIN , 30);
		lbltitulo.setFont( fonte );
		
		container.add(lbltitulo);
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		
	}
	
	public int getLinha() {
		return linha;
	}

	public void setLinha() {
		this.linha = linha + espaçamento;
	}
	
	public void setLinha( int linha) {
		this.linha = this.linha + linha + espaçamento;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna() {
		this.coluna = 30;
	}
	
	public void setColuna( int coluna ) {
		this.coluna = coluna;
	}
	
	public Container getCampoTexto( int coluna, int linha, int txtLargura, int lblLargura, String nome) {
	
		this.linha = linha;
		this.coluna = coluna;
		
		Container container = new Container();
		container.setBounds( coluna, linha, txtLargura+lblLargura, 100 );
		
		JLabel lbl = new JLabel( nome );
		lbl.setBounds( 0, 0, lblLargura, 23 );
		
		JTextField txt = new JTextField();
		txt.setBounds( lblLargura, 0, txtLargura, 23 );
		
		container.add(txt);
		container.add(lbl);
		
		this.coluna = this.coluna + txtLargura+lblLargura+10;
		
		return container;
	}
	
	public Container getCampoTexto( int txtLargura, int lblLargura, String nome) {
		
		return ( getCampoTexto(coluna, linha, txtLargura, lblLargura, nome) );
		
	}
	
	public JComboBox<String> getJComboBox( int coluna, int linha, int cbLargura ){
		
		this.linha = linha;
		this.coluna = coluna;

		JComboBox<String> cb = new SeletorDeEstados();
		cb.setBounds( coluna, linha, cbLargura, 23 );
		
		this.coluna = this.coluna + cbLargura+10;
		
		return cb;
	}
	
	public JComboBox<String> getJComboBox( int cbLargura ) {
		
		return ( getJComboBox( coluna, linha, cbLargura ) );
		
	}
	
	public JRadioButton getRadioButton( int coluna, int linha, int rbLagura, String nome){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JRadioButton rb = new JRadioButton( nome );
		rb.setBounds( coluna, linha, rbLagura, 23 );
		
		this.coluna = this.coluna + rbLagura + 10 ;
		
		return rb;
	}
	
	public JRadioButton getRadioButton( int rbLagura, String nome ) {
		
		return ( getRadioButton( coluna, linha, rbLagura, nome ) );
		
	}
	
	public JLabel getJLabel( int coluna, int linha, int lblLargura, String nome){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JLabel lbl = new JLabel( nome );
		lbl.setBounds( coluna, linha, lblLargura, 23 );
		
		this.coluna = this.coluna + lblLargura + 10;
		
		return lbl;
	}
	
	public JLabel getJLabel( int lblLargura, String nome ) {
		
		return ( getJLabel( coluna, linha, lblLargura, nome ) );
		
	}
	
	public JCheckBox getJCheckBox( int coluna, int linha){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JCheckBox cb = new JCheckBox();
		cb.setBounds( coluna, linha, 23, 23 );
		
		this.coluna = this.coluna + 23 + 10;
		
		return cb;
	}
	
	public JCheckBox getJCheckBox() {
		
		return ( getJCheckBox( coluna, linha ) );
		
	}
	
	public JTabbedPane getJTabbedPane( int coluna, int linha, int largura, int altura ) {
		
		this.linha = linha;
		this.coluna = coluna;
		
		JTabbedPane tp = new JTabbedPane();
		tp.setBounds( coluna, linha, largura, altura );
		
		return tp;
	}
	
	public JPanel getPainel( int coluna, int linha, int largura, int altura ){
		
		JPanel pnl = new JPanel();
		pnl.setBounds( coluna, linha, largura, altura );
		
		return pnl;
	}
	
	public JTextField getJTextField( int coluna, int linha, int txtLargura ){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JTextField txt = new CampoTexto();
		txt.setBounds( coluna, linha, txtLargura, 23 );
		
		this.coluna = this.coluna + txtLargura + 10;
		
		return txt;
	}

	public JTextField getJTextField( int txtLagura) {
		
		return ( getJTextField( coluna, linha, txtLagura ) );
		
	}
	
public JTextField getCampoCodigo( int coluna, int linha, int txtLargura, int tamanho ){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JTextField txt = new CompoCodigo();
		txt.setBounds( coluna, linha, txtLargura, 23 );
		
		this.coluna = this.coluna + txtLargura + 10;
		
		return txt;
	}

	public JTextField getCampoCodigo( int txtLagura, int tamanho) {
		
		return ( getCampoCodigo( coluna, linha, txtLagura, tamanho ) );
		
	}
	
	public JTextField getCampoCodigo( int txtLagura) {
		
		return ( getCampoCodigo( coluna, linha, txtLagura, 0 ) );
		
	}
	
	public JTextField getCampoNome( int coluna, int linha, int txtLargura ){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JTextField txt = new CampoNome();
		txt.setBounds( coluna, linha, txtLargura, 23 );
		
		this.coluna = this.coluna + txtLargura + 10;
		
		return txt;
	}

	public JTextField getCampoNome( int txtLagura) {
		
		return ( getCampoNome( coluna, linha, txtLagura ) );
		
	}
	
	
	
	

}
