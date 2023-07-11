package paineis;

import controle.Controle;
import janelas.JanelaTentativaJogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelResposta extends JPanel {
	protected JanelaTentativaJogador janela2;
	protected JLabel frase;
	protected String descricao;
	private Controle controle;

	public PainelResposta(String descricao, JanelaTentativaJogador janela2, Controle controle) {
		this.janela2 = janela2;
		this.descricao = descricao;
		this.controle = controle;
		JButton passarvez = new JButton();
		passarvez.setText("Próximo Jogador");
		passarvez.addActionListener(PassarVez);

		frase = new JLabel();
		frase.setText("A frase do jogador foi: " + descricao);

		add(new JLabel("Vez de: " + controle.getJogadorEscolha().getNome()));
		add(frase);
		add(passarvez);

		setLayout(new GridLayout(1, 3));
		setBackground(Color.lightGray);
		setVisible(true);
	}

	ActionListener PassarVez = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// antes de passar a vez salva a carta escolhida do no anterior
			controle.getJogadorEscolha().setCarta(controle.getCartaclicada());
			controle.atualizarDemais();

			if (controle.getDemaisJogadores() < 4) {
				if (controle.getDemaisJogadores() == controle.getIndexJogadorDaVez()) { // jogador da vez n joga!
					controle.atualizarDemais();
					if (controle.getDemaisJogadores() > 3) { // tratar ultimo jogador
						controle.atualizarDemais(3); // volta 1 pra n estourar o index
						janela2.setVisible(false);
						controle.exibirFinal();
					} else
						alterarPainel();
				} else {
					alterarPainel();
				}
			} else {
				janela2.setVisible(false);
				controle.exibirFinal();
			}
		}
	};

	private void alterarPainel() {
		janela2.getContentPane().removeAll();
		janela2.getContentPane().add(new PainelJogador(descricao, janela2, controle));
		janela2.revalidate();
		janela2.repaint();
	}
}