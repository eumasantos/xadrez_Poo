package xadrez.pecas;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peça_xadrez;

public class Peao extends Peça_xadrez {

	public Peao(Tabuleiro tabul, Cor cor) {
		super(tabul, cor);
	
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
		}
		
		return matriz;
	}	

}
