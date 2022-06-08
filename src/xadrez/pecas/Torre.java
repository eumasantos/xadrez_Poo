package xadrez.pecas;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peça_xadrez;

public class Torre extends Peça_xadrez{
	
//construtor repassando a chamada para super classe
	public Torre(Tabuleiro tabul, Cor cor) {
		super(tabul, cor);
	}
	//convertendo uma torre para string
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movim_possiveis() {
		boolean [][] matriz = new boolean [getTabul().getLinhas()][getTabul().getColunas()];
		Posição p = new Posição(0,0); //criação de posicao auxiliar
		
		/*verificar posicoes acima
		 * logica para  marcar como veradeiro as posições acima da peça*/
		p.setValues(posicao.getLinhas() - 1, posicao.getColunas());
		//enquanto  a posicao existir e estiver vaga será marcada como verddeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setLinhas(p.getLinhas()-1);//linha andando para cima	
		}
		//testar se existe casa e se essa capa possui peça adversária
		if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
			matriz [p.getLinhas()][p.getColunas()] = true;
		}
		
		/*verificar posicoes a esquerda
		 * logica para  marcar como veradeiro as posições a esquerda da peça*/
		p.setValues(posicao.getLinhas(), posicao.getColunas()-1);
		//enquanto  a posicao existir e estiver vaga será marcada como verddeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setColunas(p.getColunas()-1);//linha andando para cima	
		}
		//testar se existe casa e se essa capa possui peça adversária
		if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
			matriz [p.getLinhas()][p.getColunas()] = true;
		}
		
		/*verificar posicoes a direita
		 * logica para  marcar como veradeiro as posições a direita da peça*/
		p.setValues(posicao.getLinhas(), posicao.getColunas()+1);
		//enquanto  a posicao existir e estiver vaga será marcada como verddeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setColunas(p.getColunas()+1);//linha andando para cima	
		}
		//testar se existe casa e se essa capa possui peça adversária
		if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
			matriz [p.getLinhas()][p.getColunas()] = true;
		}
		
		/*verificar posicoes para baixo
		 * logica para  marcar como veradeiro as posições para baixo da peça*/
		p.setValues(posicao.getLinhas() + 1, posicao.getColunas());
		//enquanto  a posicao existir e estiver vaga será marcada como verddeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setLinhas(p.getLinhas()+1);//linha andando para cima	
		}
		//testar se existe casa e se essa capa possui peça adversária
		if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
			matriz [p.getLinhas()][p.getColunas()] = true;
		}
		
		return matriz;
	}

}
