package paineis;

import controle.Controle;
import entidades.Carta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelCartas extends JPanel {
	private final Controle controle;

	public PainelCartas(Controle controle) {
		this.controle = controle;
		gerarCartasAleatorias();
		setLayout(new GridLayout(2, 3));
		setBackground(Color.lightGray);
		setVisible(true);
	}

	private void gerarCartasAleatorias() {
		ArrayList<Carta> cartas = controle.criarDeck();
		for (Carta carta : cartas) {
			carta.addActionListener(selectcard);
			add(carta);
		}
	}

	ActionListener selectcard = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Carta clickedCard = (Carta) e.getSource();
			JOptionPane.showMessageDialog(null, "Você escolheu a carta " + clickedCard.getId(),
					"entidades.Carta escolhida", JOptionPane.WARNING_MESSAGE);
			controle.setCartaclicada(clickedCard);
		}
	};
}