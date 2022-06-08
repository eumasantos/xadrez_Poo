package xadrez.pecas;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peça_xadrez;

public class Rei extends Peça_xadrez{

	public Rei(Tabuleiro tabul, Cor cor) {
		super(tabul, cor);
	}
	
	//convertendo um rei para string
		@Override
		public String toString() {
			return "R";
		}
		//metodo para para saber para quais posições o rei pode se mover
		private boolean pode_mover(Posição posicao) {
			//pega a peça da posição e move quando a casa for vazia ou quando a cor for diferente  
			Peça_xadrez p = (Peça_xadrez)getTabul().peca(posicao);
			return p == null || p.getCor() != getCor();
		}

		/*sempre que chamar os movimentos possiveis do REI 
		 * vai retornar uma matriz com todas posições valendo falso(REI preso) 
		 */
		@Override
		public boolean[][] movim_possiveis() {
			boolean [][] matriz = new boolean [getTabul().getLinhas()][getTabul().getColunas()];
			
			Posição p = new Posição (0,0);
			//testar direções:
			//acima:
			p.setValues(posicao.getLinhas() - 1, posicao.getColunas());
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//abaixo:
			p.setValues(posicao.getLinhas() + 1, posicao.getColunas());
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//esquerda:
			p.setValues(posicao.getLinhas(), posicao.getColunas() - 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//direita:
			p.setValues(posicao.getLinhas(), posicao.getColunas() + 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//noreoeste:
			p.setValues(posicao.getLinhas() - 1, posicao.getColunas() - 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//nordeste:
			p.setValues(posicao.getLinhas() - 1, posicao.getColunas() + 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//sudoeste:
			p.setValues(posicao.getLinhas() + 1, posicao.getColunas() - 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//sudeste:
			p.setValues(posicao.getLinhas() + 1, posicao.getColunas() + 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			return matriz;
		}
	

}
