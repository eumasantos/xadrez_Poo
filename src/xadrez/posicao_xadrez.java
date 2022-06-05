//classe que representa a posição do xadrez como ele será utilizado. Exemplo linha 1, coluna A... 
package xadrez;

import tabuleiro.Posição;

public class posicao_xadrez {
	
	private char coluna;
	private int linha;
	public posicao_xadrez(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new Exceção_xad ("Erro de posição no Xadrez. Valores válidos: A1 até H8.");
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public char getColuna() {
		return coluna;
	}
	
	public int getLinha() {
		return linha;
	}
	
	protected Posição convert_posic() {
		return new Posição(8 - linha, coluna - 'a');
	}
	//converter da posição padrão da matriz para formato como mostra no tabuleiro
	protected static posicao_xadrez nova_posic(Posição posicao) {
		return new posicao_xadrez ((char)('a' + posicao.getColunas()),8 - posicao.getLinhas());
	}
	@Override
	public String toString() {
		return "" + coluna + linha;//para imprimir a posição do xadrez na ordem
	}
}
