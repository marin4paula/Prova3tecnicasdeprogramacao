package entidades;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Carta extends JButton {
	protected int id;
	protected String foto;
	public Carta(String imagem) {
		foto=imagem;
		ImageIcon icon = createImageIcon(imagem);
		if (icon != null) {
			Image image = icon.getImage().getScaledInstance(224, 324, Image.SCALE_SMOOTH);
			setIcon(new ImageIcon(image));
		}
		setPreferredSize(new Dimension(200, 200));
	}

	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		return new ImageIcon(imgURL);

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoto() {
		return foto;
	}
}