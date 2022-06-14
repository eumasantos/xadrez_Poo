package xadrez.pecas;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Partida_xadrez;
import xadrez.Peça_xadrez;

public class Peao extends Peça_xadrez {
	
	private Partida_xadrez partida_xadrez;//dependencia para a classe partida

	public Peao(Tabuleiro tabul, Cor cor, Partida_xadrez partida_xadrez) {
		super(tabul, cor);
		this.partida_xadrez = partida_xadrez;
	
	}
	
	//convertendo um peao para string:
	@Override
	public String toString() {
			return "P";
			}

	@Override
	public boolean[][] movim_possiveis() {
		boolean [][] matriz = new boolean [getTabul().getLinhas()][getTabul().getColunas()];
		Posição p = new Posição(0,0); //criação de posicao auxiliar
		
		//Peão branco
		//verificar posicoes acima
		if (getCor() == Cor.BRANCA) {
			p.setValues(posicao.getLinhas() - 1, posicao.getColunas());
			/* se a posicao de uma linha acima do peão branco existir e estiver vazia 
			 * ele pode mover para ela:*/
			if (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
				matriz [p.getLinhas()][p.getColunas()] = true;
			}
			//regra para o peao poder mover 2 vezes:
			p.setValues(posicao.getLinhas() - 2, posicao.getColunas());
			Posição p2 = new Posição (posicao.getLinhas() - 1, posicao.getColunas());
			/* se a posicao de uma linha acima do peão branco existir, se estiver vazia e 
			 * se o contador de movimentos for 0, ele pode mover para ela. Condição implementada para p1 e para p2 (2 linhas)*/
			if (getTabul().existe_posicao(p) && !getTabul().existe_peca(p) && getTabul().existe_posicao(p2) && !getTabul().existe_peca(p2) && getContMov() == 0) {
				matriz [p.getLinhas()][p.getColunas()] = true;
		}
			//verificar casa na diagonal esquerda :
			
			p.setValues(posicao.getLinhas() - 1, posicao.getColunas() - 1);
			/* se a posicao existe e tem uma peça adversária no local pode mover para lá*/
			if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
				matriz [p.getLinhas()][p.getColunas()] = true;
			}
			//verificar casa na diagonal direita
			p.setValues(posicao.getLinhas() - 1, posicao.getColunas() + 1);
			/* se a posicao existe e tem uma peça adversária no local pode mover para lá*/
			if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
				matriz [p.getLinhas()][p.getColunas()] = true;
			}
			//Código para movimento de em passant peças brancas:
			if (posicao.getLinhas() == 3) {
				Posição esquerda = new Posição(posicao.getLinhas(),posicao.getColunas() - 1);
				if (getTabul().existe_posicao(esquerda) && existePecaAdversaria(esquerda) && getTabul().peca(esquerda) == partida_xadrez.getVuneravel_enPassant()) {
					matriz[esquerda.getLinhas() - 1][esquerda.getColunas()] = true;
				}
				Posição direita = new Posição(posicao.getLinhas(),posicao.getColunas() + 1);
				if (getTabul().existe_posicao(direita) && existePecaAdversaria(direita) && getTabul().peca(direita) == partida_xadrez.getVuneravel_enPassant()) {
					matriz[direita.getLinhas() - 1][direita.getColunas()] = true;
				}
			}
		}
		//peão preto
		else {
			p.setValues(posicao.getLinhas() + 1, posicao.getColunas());
			/* se a posicao de uma linha acima do peão branco existir e estiver vazia 
			 * ele pode mover para ela:*/
			if (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
				matriz [p.getLinhas()][p.getColunas()] = true;
			}
			//regra para o peao poder mover 2 vezes:
			p.setValues(posicao.getLinhas() + 2, posicao.getColunas());
			Posição p2 = new Posição (posicao.getLinhas() + 1, posicao.getColunas());
			/* se a posicao de uma linha acima do peão branco existir, se estiver vazia e 
			 * se o contador de movimentos for 0, ele pode mover para ela. Condição implementada para p1 e para p2 (2 linhas)*/
			if (getTabul().existe_posicao(p) && !getTabul().existe_peca(p) && getTabul().existe_posicao(p2) && !getTabul().existe_peca(p2) && getContMov() == 0) {
				matriz [p.getLinhas()][p.getColunas()] = true;
		}
			//verificar casa na diagonal esquerda :
			
			p.setValues(posicao.getLinhas() + 1, posicao.getColunas() - 1);
			/* se a posicao existe e tem uma peça adversária no local pode mover para lá*/
			if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
				matriz [p.getLinhas()][p.getColunas()] = true;
			}
			//verificar casa na diagonal direita
			p.setValues(posicao.getLinhas() + 1, posicao.getColunas() + 1);
			/* se a posicao existe e tem uma peça adversária no local pode mover para lá*/
			if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
				matriz [p.getLinhas()][p.getColunas()] = true;
			}
			
			//Código para movimento de em passant peças pretas:
			if (posicao.getLinhas() == 4) {
				Posição esquerda = new Posição(posicao.getLinhas(),posicao.getColunas() - 1);
				if (getTabul().existe_posicao(esquerda) && existePecaAdversaria(esquerda) && getTabul().peca(esquerda) == partida_xadrez.getVuneravel_enPassant()) {
					matriz[esquerda.getLinhas() + 1][esquerda.getColunas()] = true;
				}
				Posição direita = new Posição(posicao.getLinhas(),posicao.getColunas() + 1);
				if (getTabul().existe_posicao(direita) && existePecaAdversaria(direita) && getTabul().peca(direita) == partida_xadrez.getVuneravel_enPassant()) {
					matriz[direita.getLinhas() + 1][direita.getColunas()] = true;
				}
			}
		}
		
		return matriz;
	}	

}
