package entidades;

public class Jogador {
	private final String nome;
	private Carta carta;
	private Carta cartaPalpite;
	private int pontos;
	private String frase="";
	public Jogador(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public Carta getCartaPalpite() {
		return cartaPalpite;
	}

	public void setCartaPalpite(Carta cartaPalpite) {
		this.cartaPalpite = cartaPalpite;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos += pontos;
	}
	public String getFrase() {
		return frase;
	}
	public void setFrase(String frase) {
		this.frase = frase;
	}
	
}