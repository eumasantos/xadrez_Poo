package xadrez;

import tabuleiro.Pecas;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

/*subclasse da classe peça*/
public abstract class Peça_xadrez extends Pecas  {
	
	private Cor cor;

	/*construtor que recebe tabuleiro e cor
	 * tabuleiro repassa a chamada para o construtor da super classe: pecas*/
	public Peça_xadrez(Tabuleiro tabul, Cor cor) {
		super(tabul);
		this.cor = cor;
	}
/*Não existe set para que as cores não sejam modificadas, apenas acessadas*/
	public Cor getCor() {
		return cor;
	}	
	
	//metodo para retornar a posicao do xadrez
	//pega posicção e converte para posicao de xadrez
	public posicao_xadrez getPosicao_xadrez() {
		return posicao_xadrez.nova_posic(posicao);
	}
	
	/*operação será usada em todas as peças
	 * Protegida pois será acessivel somente pelo mesmo pacote
	 * verificar se existe uma peça adversaria nessa posicão
	*/
	protected boolean existePecaAdvesaria(Posição posicao) {
		Peça_xadrez p = (Peça_xadrez)getTabul().peca(posicao);//pega peça
		return p!=null && p.getCor()!= cor;//testa se a peça pega nessa posição é de cor diferente da peça do jogador(adversária) 
	}

}
