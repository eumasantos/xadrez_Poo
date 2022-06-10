package xadrez.pecas;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peça_xadrez;

public class Rainha extends Peça_xadrez{
	
//construtor repassando a chamada para super classe
	public Rainha(Tabuleiro tabul, Cor cor) {
		super(tabul, cor);
	}
	//convertendo uma Rainha para string. Foi utilizada letra Q(queen para representar a peça
	@Override
	public String toString() {
		return "Q";
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
		//enquanto  a posicao existir e estiver vaga será marcada como verdadeira
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
		//enquanto  a posicao existir e estiver vaga será marcada como verdadeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setLinhas(p.getLinhas()+1);//linha andando para cima	
		}
		//testar se existe casa e se essa capa possui peça adversária
		if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
			matriz [p.getLinhas()][p.getColunas()] = true;
		}
		/*verificar posicoes noroeste
		 * logica para  marcar como verdadeiro as posições a noroeste da peça*/
		p.setValues(posicao.getLinhas() - 1, posicao.getColunas() - 1);
		//enquanto  a posicao existir e estiver vaga será marcada como verddeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setValues(p.getLinhas() - 1, p.getColunas()- 1);//linha andando para noroeste	
		}
		//testar se existe casa e se essa capa possui peça adversária
		if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
			matriz [p.getLinhas()][p.getColunas()] = true;
		}
		
		/*verificar posicoes nordeste
		 * logica para  marcar como verdadeiro as posições a nordeste da peça*/
		p.setValues(posicao.getLinhas() - 1, posicao.getColunas()+ 1);
		//enquanto  a posicao existir e estiver vaga será marcada como verdadeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setValues(p.getLinhas() - 1, p.getColunas() + 1);//linha andando para nordeste	
		}
		//testar se existe casa e se essa capa possui peça adversária
		if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
			matriz [p.getLinhas()][p.getColunas()] = true;
		}
		
		/*verificar posicoes a sudeste
		 * logica para  marcar como verdadeiro as posições a sudeste da peça*/
		p.setValues(posicao.getLinhas() + 1, posicao.getColunas() + 1);
		//enquanto  a posicao existir e estiver vaga será marcada como verddeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setValues(p.getLinhas() + 1, p.getColunas() + 1);//linha andando para sudeste	
		}
		//testar se existe casa e se essa capa possui peça adversária
		if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
			matriz [p.getLinhas()][p.getColunas()] = true;
		}
		
		/*verificar posicoes para sudoeste
		 * logica para  marcar como veradeiro as posições a sudoeste da peça*/
		p.setValues(posicao.getLinhas() + 1, posicao.getColunas() - 1);
		//enquanto  a posicao existir e estiver vaga será marcada como verddeira
		while (getTabul().existe_posicao(p) && !getTabul().existe_peca(p)) {
			matriz[p. getLinhas()][p.getColunas()] = true;
			p.setValues(p.getLinhas() + 1, p.getColunas() - 1);//linha andando para sudoeste		
		}
		
		return matriz;
	}

}
