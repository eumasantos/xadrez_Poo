package xadrez.pecas;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Partida_xadrez;
import xadrez.Peça_xadrez;

public class Rei extends Peça_xadrez{

	//dependencia para a classe partida:
	private Partida_xadrez partida_xadrez;
	public Rei(Tabuleiro tabul, Cor cor, Partida_xadrez partida_xadrez) {
		super(tabul, cor);
		this.partida_xadrez = partida_xadrez;
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
/* metodo auxiliar para testar a condição de roque
 * testa se nesta posição há uma torre ee se ela está apta para roque 
 * */
		private boolean testar_torre_roque(Posição posicao) {
			Peça_xadrez p = (Peça_xadrez)getTabul().peca(posicao);
			return p!= null && p instanceof Torre && p.getCor() == getCor() && p.getContMov() == 0;
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
			//movimentos do roque do rei
			if (getContMov () == 0 && !partida_xadrez.getXeque()) {
				Posição torre_1 = new Posição(posicao.getLinhas(),posicao.getColunas() + 3);//posicao onde a torre deve estar
				//testar se na posicao tem uma torre apta para roque e testa se as duas casas estão vazias		
				if (testar_torre_roque(torre_1)) {
							Posição p1 = new Posição(posicao.getLinhas(), posicao.getColunas() + 1);
							Posição p2 = new Posição(posicao.getLinhas(), posicao.getColunas() + 2);
							if (getTabul().peca(p1) == null && getTabul().peca(p2)== null){
								matriz[posicao.getLinhas()][posicao.getColunas() + 2] = true;//inclue na matriz a casa para o movimento
							}
						}
				
				//movimentos do roque da rainha
				Posição torre_2 = new Posição(posicao.getLinhas(),posicao.getColunas() - 4);//posicao onde a torre deve estar
				//testar se na posicao tem uma torre apta para roque e testa se as duas casas estão vazias		
				if (testar_torre_roque(torre_2)) {
							Posição p1 = new Posição(posicao.getLinhas(), posicao.getColunas() - 1);
							Posição p2 = new Posição(posicao.getLinhas(), posicao.getColunas() - 2);
							Posição p3 = new Posição(posicao.getLinhas(), posicao.getColunas() - 3);
							if (getTabul().peca(p1) == null && getTabul().peca(p2)== null && getTabul().peca(p3)== null){
								matriz[posicao.getLinhas()][posicao.getColunas() - 2] = true;//o rei pode mover duas casas para a esquerda
								
							}
						}
			}
			
			return matriz;
		}
	

}
