package paineis;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controle.Controle;
import janelas.JanelaTentativaJogador;

public class PainelJogador extends JPanel {
	protected PainelResposta painelJanelaR;
	protected PainelCartas painelcartas;
	public PainelJogador(String descricaocarta, JanelaTentativaJogador janela, Controle controle) {
		painelcartas= new PainelCartas(controle);
		painelJanelaR = new PainelResposta(descricaocarta, janela,controle);
		setLayout(new BorderLayout());
		add(painelJanelaR,BorderLayout.NORTH);	
		add(painelcartas,BorderLayout.CENTER);
		setVisible(true);
	}
}