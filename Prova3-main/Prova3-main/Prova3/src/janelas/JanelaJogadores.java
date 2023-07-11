package janelas;

import controle.Controle;
import paineis.Painel;
import paineis.PainelCartas;
import javax.swing.*;
import java.awt.*;

public class JanelaJogadores extends JPanel {
	public JanelaJogadores(Janela janela, Controle control) {
		PainelCartas painel1 = new PainelCartas(control);
		Painel painel2 = new Painel(janela, painel1, control);
		setLayout(new BorderLayout());
		JLabel jogadorLabel = new JLabel(control.getJogadorDaVez().getNome() + " qual seu palpite?");
		add(jogadorLabel, BorderLayout.NORTH);
		add(painel1, BorderLayout.CENTER);
		add(painel2, BorderLayout.SOUTH);
		setSize(670, 730);
		setVisible(true);
	}
}