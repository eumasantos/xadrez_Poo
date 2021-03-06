package xadrez.pecas;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peça_xadrez;

public class Cavalo extends Peça_xadrez{

	public Cavalo(Tabuleiro tabul, Cor cor) {
		super(tabul, cor);
	}
	
	//convertendo um cavalo para string
		@Override
		public String toString() {
			return "C";
		}
		//metodo para para saber para quais posições o cavalo pode se mover
		private boolean pode_mover(Posição posicao) {
			//pega a peça da posição e move quando a casa for vazia ou quando a cor for diferente  
			Peça_xadrez p = (Peça_xadrez)getTabul().peca(posicao);
			return p == null || p.getCor() != getCor();
		}

		/*sempre que chamar os movimentos possiveis do Cavalo 
		 * vai retornar uma matriz com todas posições valendo falso(Cavalo preso) 
		 */
		@Override
		public boolean[][] movim_possiveis() {
			boolean [][] matriz = new boolean [getTabul().getLinhas()][getTabul().getColunas()];
			
			Posição p = new Posição (0,0);
		
			// As possibilidades são parecidas com a da classe rei no entanto no esquema 2/1 que é a lógica que a peça cavalo trabalha
			p.setValues(posicao.getLinhas() - 1, posicao.getColunas() - 2);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//
			p.setValues(posicao.getLinhas() - 2, posicao.getColunas() - 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//
			p.setValues(posicao.getLinhas() - 2, posicao.getColunas() + 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//
			p.setValues(posicao.getLinhas() - 1, posicao.getColunas() + 2);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//
			p.setValues(posicao.getLinhas() + 1, posicao.getColunas() + 2);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//
			p.setValues(posicao.getLinhas() + 2, posicao.getColunas() + 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//
			p.setValues(posicao.getLinhas() + 2, posicao.getColunas() - 1);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			//
			p.setValues(posicao.getLinhas() + 1, posicao.getColunas() - 2);
			if (getTabul().existe_posicao(p)&& pode_mover(p)) {
				matriz[p.getLinhas()][p.getColunas()] = true;		
			}
			return matriz;
		}
	

}
