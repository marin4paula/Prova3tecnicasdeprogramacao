package paineis;

import controle.Controle;
import janelas.Janela;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Painel extends JPanel {
	protected Janela janela;
	protected JTextField texto;
	protected PainelCartas painelcartas;
	private final Controle controle;
	
	public JTextField getTexto() {
		return texto;
	}
	public void setTexto(JTextField texto) {
		this.texto = texto;
	}

	public Painel(Janela janela, PainelCartas painelcartas, Controle controle) {
		this.janela = janela;
		this.painelcartas = painelcartas;
		this.controle = controle;
		JLabel frase = new JLabel();
		frase.setText("Descreva a carta marcada");
		texto = new JTextField();
        JButton botaoPassarVez = new JButton("Passar a Vez");
		botaoPassarVez.addActionListener(PassarVez);
		setLayout(new GridLayout(1, 3));
		add(frase);
		add(texto);
		add(botaoPassarVez);
		setBackground(Color.lightGray);
		setVisible(true);
	}

	ActionListener PassarVez = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controle.getJogadorDaVez().setCarta(controle.getCartaclicada());
		    if(getTexto().getText() != null) {
			    controle.getJogadorDaVez().setFrase(getTexto().getText());}
		    else {
		    	controle.getJogadorDaVez().setFrase("");
		    }
			controle.setCartaEscolhida(controle.getJogadorDaVez().getCarta());
			controle.indicarCartas(texto.getText());
			janela.setVisible(false);
		}
	};

}