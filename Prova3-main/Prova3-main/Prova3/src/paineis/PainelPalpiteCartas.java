package paineis;

import controle.Controle;
import entidades.Jogador;
import janelas.JanelaTentativaJogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelPalpiteCartas extends JPanel {
    Controle controle;
    JanelaTentativaJogador janela2;
    public PainelPalpiteCartas(JanelaTentativaJogador janela2, Controle controle) {
        this.controle=controle;
        this.janela2=janela2;
        janela2.setSize(750, 400);
        janela2.setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel text=new JLabel(controle.getJogadorEscolha().getNome()+" Qual seu palpite?");
        JButton botaoSalvar= new JButton("Próximo Jogador");
        botaoSalvar.addActionListener(salvar);
        add(botaoSalvar,BorderLayout.SOUTH);
        add(text,BorderLayout.NORTH);
        addCards();

    }
   private ActionListener salvar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) { //carta clicada vira o palpite do jogador
            if(controle.getDemaisJogadores()<4) { //smp verificando index do array
                controle.getJogadorEscolha().setCartaPalpite(controle.getCartaclicada());
                controle.atualizarDemais();
                controle.atualizarPainelProxJogador(janela2);
            }else{
                janela2.setVisible(false);
                controle.calcularPontuacao();
            }
        }
    };
    private void addCards(){
        JPanel cartas= new JPanel();
        cartas.setLayout(new GridLayout(1,4));
        for (Jogador jogador: controle.getJogadores()) {
            cartas.add(jogador.getCarta());
        }
        add(cartas,BorderLayout.CENTER);
    }
}