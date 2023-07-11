package controle;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entidades.Carta;
import entidades.Jogador;
import janelas.Janela;
import janelas.JanelaJogadores;
import janelas.JanelaTentativaJogador;
import paineis.PainelJogador;
import paineis.PainelPalpiteCartas;
import paineis.PainelResultado;

public class Controle {
	private final List<Jogador> jogadores;
	private int jogadorDaVez;
	private int demaisJogadores;
	private Carta cartaclicada;
	private Carta cartaEscolhida;
	private Janela janela;
	private Boolean gameover = false;
	private ArrayList<Carta> cartas;

	public Controle() {
		jogadores = new ArrayList<>();
		configurarJogadores();
		instanciarCartas();
		start();
	}

	private void configurarJogadores() {
		jogadores.add(new Jogador("Jogador 1"));
		jogadores.add(new Jogador("Jogador 2"));
		jogadores.add(new Jogador("Jogador 3"));
		jogadores.add(new Jogador("Jogador 4"));
	}

	private void instanciarCartas() {
		this.cartas = new ArrayList<>();
		String[] arquivos = { "/imagens/apito.jpg", "/imagens/banaja.jpg", "/imagens/banato.jpg", "/imagens/banema.jpg",
				"/imagens/barulho.jpg", "/imagens/baskete.jpg", "/imagens/coelhujo.jpg", "/imagens/drake.jpg",
				"/imagens/escova.jpg", "/imagens/frango.jpg", "/imagens/franto.jpg", "/imagens/frog.jpg",
				"/imagens/galeixe.jpg", "/imagens/gorijela.jpg", "/imagens/kiwi.jpg", "/imagens/minion.jpg",
				"/imagens/musica.jpg", "/imagens/petovo.jpg", "/imagens/pinguim.jpg", "/imagens/polvo.jpg",
				"/imagens/raposa.jpg", "/imagens/scooby.jpg", "/imagens/senhor.jpg", "/imagens/som.jpg", };
		int i = 1;
		for (String arquivo : arquivos) {
			Carta card = new Carta(arquivo);
			card.setId(i);
			cartas.add(card);
			i++;
		}
	}

	public ArrayList<Carta> criarDeck() {
		SecureRandom sr = new SecureRandom();
		ArrayList<Carta> deck = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			Carta card;
			do {
				int id = sr.nextInt(24) + 1;
				card = buscarCarta(id);
				if (card != null) {
					deck.add(card);
					cartas.remove(card);
				}
			} while (card == null);
		}
		return deck;
	}

	private Carta buscarCarta(int id) {
		for (Carta card : cartas) {
			if (id == card.getId()) {
				return card;
			}
		}
		return null;
	}

	private void start() {
		janela = new Janela(this);
	}

	public void trocarJogadorDaVez() {
		atualizarJogadorDaVez();
		demaisJogadores = 0; // reseta
		if (!gameover) {
			instanciarCartas();
			janela.getContentPane().removeAll();
			janela.getContentPane().add(new JanelaJogadores(janela, this));
			janela.revalidate();
			janela.repaint();
			janela.setVisible(true); // fechar janela aberta e abrir uma nova
		} else {
			int max = 0;
			Jogador Jogmaior = null;
			for (Jogador jogadore : jogadores) {
				if (jogadore.getPontos() > max) {
					max = jogadore.getPontos();
					Jogmaior = jogadore;
				}
			}
			JOptionPane.showMessageDialog(null, Jogmaior.getNome() + " é o vencedor!");

		}
	}

	public void atualizarJogadorDaVez() {
		jogadorDaVez++;
		if (jogadorDaVez >= jogadores.size()) {
			jogadorDaVez = 0; // Volta para o primeiro jogador
		}

	}

	public void exibirFinal() {
		demaisJogadores = 0;
		JanelaTentativaJogador jn = new JanelaTentativaJogador();
		atualizarPainelProxJogador(jn);
	}

	public void atualizarPainelProxJogador(JanelaTentativaJogador jn) {
		if (demaisJogadores < 4) {
			if (demaisJogadores == jogadorDaVez) {
				demaisJogadores++;
				if (demaisJogadores < 4)
					alterarPainel(jn);
				else {
					finalizar(jn);
				}
			} else {
				alterarPainel(jn);
			}
		} else {
			finalizar(jn);
		}
	}

	protected void finalizar(JanelaTentativaJogador jn) {
		jn.setVisible(false);
		calcularPontuacao();
		exibirTelaFinal();
	}

	public void exibirTelaFinal() {
		JanelaTentativaJogador jt = new JanelaTentativaJogador();
		jt.setTitle("Pontuação");
		jt.add(new PainelResultado(this, jt));
		jt.setBackground(Color.DARK_GRAY);
		jt.setVisible(true);
		// verifica se alguem ganhou a rodada
		for (Jogador jg : jogadores) {
			if (jg.getPontos() >= 15) {
				gameover = true;
			}
		}
	}

	private void alterarPainel(JanelaTentativaJogador jn) {
		if (demaisJogadores > 3)
			demaisJogadores = 0;
		jn.getContentPane().removeAll();
		jn.getContentPane().add(new PainelPalpiteCartas(jn, this));
		jn.revalidate();
		jn.repaint();
	}

	public void calcularPontuacao() {
		int qtdAcertos = 0;
		for (Jogador jg : getJogadores()) {
			if (!jg.equals(getJogadorDaVez())) { // verifica se n e da vez
				if (jg.getCartaPalpite().equals(cartaEscolhida)) {
					jg.setPontos(2);
					qtdAcertos++;
				}
				calcularPontuacaoAdicional(jg);
			}
		}
		if (qtdAcertos > 0 && qtdAcertos < 3) {
			getJogadorDaVez().setPontos(2);
		}
	}

	private void calcularPontuacaoAdicional(Jogador jg) {
		for (Jogador jogadore : jogadores) { // pontuacao adicional
			if (!jg.equals(jogadore) && !jogadore.equals(getJogadorDaVez())) {
				if (jg.getCarta().equals(jogadore.getCartaPalpite())) {
					jg.setPontos(1);
				}
			}
		}
	}

	public void indicarCartas(String descricao) {
		JanelaTentativaJogador tentativaJogador = new JanelaTentativaJogador();
		tentativaJogador.setTitle("JOGADORES INDICAM AS CARTAS");
		if (getIndexJogadorDaVez() == getDemaisJogadores())
			atualizarDemais();
		PainelJogador painelJogador = new PainelJogador(descricao, tentativaJogador, this);
		tentativaJogador.add(painelJogador);
	}

	public void atualizarDemais() {
		demaisJogadores++;
	}

	public void atualizarDemais(int i) {
		demaisJogadores = i;
	}

	public Jogador getJogadorDaVez() {
		return jogadores.get(jogadorDaVez);
	}

	public Jogador getJogadorEscolha() {
		return jogadores.get(demaisJogadores);
	}

	public int getIndexJogadorDaVez() {
		return jogadorDaVez;
	}

	public int getDemaisJogadores() {
		return demaisJogadores;
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setCartaEscolhida(Carta cartaEscolhida) {
		this.cartaEscolhida = cartaEscolhida;
	}

	public void setCartaclicada(Carta card) {
		cartaclicada = card;
	}

	public Carta getCartaclicada() {
		return cartaclicada;
	}

	public Carta getCartaEscolhida() {
		return cartaEscolhida;
	}
}