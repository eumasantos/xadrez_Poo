package xadrez.pecas;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peça_xadrez;

public class Bispo extends Peça_xadrez{

	public Bispo(Tabuleiro tabul, Cor cor) {
		super(tabul, cor);
	}
	//convertendo um bispo para string:
		@Override
		public String toString() {
				return "B";
				}
		

		@Override
		public boolean[][] movim_possiveis() {
			boolean [][] matriz = new boolean [getTabul().getLinhas()][getTabul().getColunas()];
			Posição p = new Posição(0,0); //criação de posicao auxiliar
			
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
			//testar se existe casa e se essa capa possui peça adversária
			if (getTabul().existe_posicao(p) && existePecaAdversaria(p)) {
				matriz [p.getLinhas()][p.getColunas()] = true;
			}
			
			return matriz;
		}


}
