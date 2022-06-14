package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Pecas;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

/* classe onde contem as regras do jogo*/
public class Partida_xadrez {
	
	private Tabuleiro tabul;
	private int vez;//define de quem é a vez de jogar
	private Cor jogadorAtual;
	private boolean Xeque;
	private boolean xequeMate;
	private Peça_xadrez vuneravel_enPassant;//propriedade da jogada en passant. Começa com nulo (não há ainda nenhuma peça vulnerável a essa jogada)
	private Peça_xadrez promocao;////propriedade da jogada promocao. Começa com nulo (não há ainda nenhum apeça vulnerável a essa jogada)
	
	//declaração das listas para pelas no tabuleiro e peças capturadas:
	private List<Pecas> pecasNoTabul = new ArrayList<>();
	private List<Pecas> pecasCapturadas = new ArrayList<>();

	//Construtor
	public Partida_xadrez() {
		tabul = new Tabuleiro(8,8);//criação da dimensao do tabuleiro
		vez = 1;
		jogadorAtual = Cor.BRANCA;//o primeiro a jogar são as peças brancas
		Xeque = false; //opcional. por padrão já é falso
		iniciarPartida();//chamando a inicialização da partida
		}
	//apenas metodos gets pois esses atributos não podem ser alterados	
	public int getVez() {
		return vez;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getXeque() {
		return Xeque;
	}
	 
	public boolean getXequeMate() {
		return xequeMate;
	}
	
	public Peça_xadrez getVuneravel_enPassant() {
		return vuneravel_enPassant;
	}
	
	public Peça_xadrez getPromocao() {
		return promocao;
	}
	
	/*O método abaixo retorna uma matriz de pecas de xadrez correspondente 
	 * a uma partida
	 */
	public Peça_xadrez[][] getPecas(){
		/* matriz com variavel auxliar criada para que o programa enxerque apenas as peças
		 * que estão na camada de xadrez e não as peças que estão na camada tabuleiro
		 */
		Peça_xadrez[][] matriz = new Peça_xadrez[tabul.getLinhas()][tabul.getColunas()];
		/* for para percorrer a matriz de peças do tabuleiro */
				for (int i=0; i<tabul.getLinhas(); i++) {//for para percorrer as linhas
					for (int j=0; j<tabul.getColunas(); j++) {//for para percorrer as colunas
						matriz[i][j] = (Peça_xadrez) tabul.peca(i,j);
					}
				}	
				return matriz;
	}

	//operação para mostrar os movimentos possiveis ao escolher uma origem
	public boolean [][]movim_possiveis(posicao_xadrez posicaoOrigem){
		Posição posicao = posicaoOrigem.convert_posic();
		validarPosicaoOrigem(posicao);
		return tabul.peca(posicao).movim_possiveis();
	}
	
	//metodo para mover peça de origem para destino
	public Peça_xadrez performance_mov(posicao_xadrez posicaoOrigem, posicao_xadrez posicaoDestino) {
		//convertendo as posições do xadrez para posição da matriz
		Posição origem = posicaoOrigem.convert_posic();
		Posição destino = posicaoDestino.convert_posic();
		//validando posição de origem(ver se realmente há peça nessa posição)
		validarPosicaoOrigem(origem);
		//validando posição de destino
		validarPosicaoDestino(origem,destino);
		//variavel que recebe o resultado do movimento:
		Pecas capturaPeca = operacaoMovimentoPeca(origem, destino);
		//chama o proximo jogador:
		
		//testar se o movimento colocou o jogador em xeque:
		if (testeXeque (jogadorAtual)) {
			desfazerMovim(origem, destino, capturaPeca);
			throw new Exceção_xad("Você não pode se colocar em xeque!");
		}
		//declaração de variavel para o em passant
		Peça_xadrez pecaMovida = (Peça_xadrez)tabul.peca(destino);
		
		//movimento de jogada"promocao"
		//Antes do xeque  
		promocao = null;
		if (pecaMovida instanceof Peao) {
			if ((pecaMovida.getCor() == Cor.BRANCA && destino.getLinhas() == 0 || pecaMovida.getCor() == Cor.PRETA && destino.getLinhas() == 7)) {
				promocao = (Peça_xadrez)tabul.peca(destino);//joga o peao para a variavel promocao
				promocao = substituirPecaPromovida("Q");//começa trocando pela rainha
			}
		}
		
		
		
		//testar se o oponente se colocou em xeque
		Xeque = (testeXeque(oponente(jogadorAtual))) ? true : false;
		
		if (testeXequeMate(oponente (jogadorAtual))) {
			xequeMate = true;
		}
		else {
		proximaVez();
		}
		
		/*testar se a peça movida foi um peão que moveu 2 casas, se sim esse peão
		 * está vulnerável a en passant */
		if (pecaMovida instanceof Peao && (destino.getLinhas() == origem.getLinhas() - 2 || destino.getLinhas() == origem.getLinhas() + 2)) {
			vuneravel_enPassant = pecaMovida;
		}
		else {
			vuneravel_enPassant = null;
		}	
		
		return (Peça_xadrez)capturaPeca;
	}
	
	//metodo para trocar a peça removida por outra
	public Peça_xadrez substituirPecaPromovida (String type) {
		if (promocao == null) {
			throw new IllegalStateException("Não há peça para ser removida");
		}
		if (!type.equals ("8") && !type.equals("C") && !type.equals("T") && !type.equals("Q")) {
			//throw new InvalidParameterException("Letra inválida para promoção!");
			return promocao;
		}
		
		Posição pos = promocao.getPosicao_xadrez().convert_posic();
		Pecas p = tabul.remover_Peca(pos);
		pecasNoTabul.remove(p);
		
		Peça_xadrez novaPeca = novaPeca(type, promocao.getCor());
		tabul.colocar_peca(novaPeca, pos);
		pecasNoTabul.add(novaPeca);
		
		return novaPeca;
		
	}
	//método auxiliar para instanciar a peça especifica
	private Peça_xadrez novaPeca(String type, Cor cor) {
		if (type.equals("B")) return new Bispo(tabul, cor);
		if (type.equals("C")) return new Cavalo(tabul, cor);
		if (type.equals("Q")) return new Rainha(tabul, cor);
		return new Torre(tabul, cor);
		
	}
	
	//operaçao de movimento da peça:
	private Pecas operacaoMovimentoPeca(Posição origem, Posição destino) {
		Peça_xadrez p = (Peça_xadrez)tabul.remover_Peca(origem);//retirando peça que estava na posicao de origem /peça = variavel p
		p.incrementarcontMov();
		Pecas capturaPeca = tabul.remover_Peca(destino);//remover possivel peça que esteja no destino. a peça se torna peça capturada
		tabul.colocar_peca(p, destino);//colocando a peça retirada da origem apra posicao de destino
		
		/*se houver movimento, e o movimento capturar uma peça, a peça é retirada da lista do 
		 * tabuleiro e adiciona na lista de peças capturadas
		 */
		if (capturaPeca != null) {
			pecasNoTabul.remove(capturaPeca);
			pecasCapturadas.add(capturaPeca);
		}
		
		//movimento especial roque na torre do lado do rei
		//para descobrir se o movimento foi um roque/rei
		if(p instanceof Rei && destino.getColunas() == origem.getColunas() + 2 ) {
			Posição origemT = new Posição(origem.getLinhas(), origem.getColunas() + 3);
			Posição destinoT = new Posição(origem.getLinhas(), origem.getColunas() + 1);
			Peça_xadrez torre = (Peça_xadrez)tabul.remover_Peca(origemT);//retira de onde ela estiver
			tabul.colocar_peca(torre, destinoT);//coloca na posição de destino
			torre.incrementarcontMov();//incrementa a quantidade de movimentos da torre

		}
		/*movimento especial roque na torre do lado da rainha 
		(muda apenas a posição de origem e destino em relação ao roque do rei*/
		//para descobrir se o movimento foi um roque/rainha
		if(p instanceof Rei && destino.getColunas() == origem.getColunas() - 2 ) {
			Posição origemT = new Posição(origem.getLinhas(), origem.getColunas() - 4);
			Posição destinoT = new Posição(origem.getLinhas(), origem.getColunas() - 1);
			Peça_xadrez torre = (Peça_xadrez)tabul.remover_Peca(origemT);
			tabul.colocar_peca(torre, destinoT);
			torre.incrementarcontMov();
		}
		
		/*Movimento em passant
		 * sempre que uma peça for movida, testar se foi en passant
		 */
		if (p instanceof Peao) {
			//se o peão andou na diagonal e não capturou peça, significa que foi em passant
			if (origem.getColunas() != destino.getColunas() && capturaPeca == null) {
				Posição peaoPosicao;
				if (p.getCor() == Cor.BRANCA) {
					peaoPosicao = new Posição(destino.getLinhas() + 1, destino.getColunas());
				}
				else {
					peaoPosicao = new Posição(destino.getLinhas() - 1, destino.getColunas());
				}
				capturaPeca = tabul.remover_Peca(peaoPosicao);
				pecasCapturadas.add(capturaPeca);//adicionando na lista de peças capturadas
				pecasNoTabul.remove(capturaPeca);//removendo a peça capturada do tabuleiro
				
			}
		}
		
		return capturaPeca;
	}
	//metodo para desfazer movimento
	private void desfazerMovim(Posição origem, Posição destino, Pecas capturaPeca ) {
		Peça_xadrez p = (Peça_xadrez)tabul.remover_Peca(destino);//tira peça de destino
		p.decrementarcontMov();
		tabul.colocar_peca(p, origem);//devolve para posição de origem
		
		//testa se a peça tinha sido capturada, se sim, volta para posição de destino
		if (capturaPeca != null) {
			tabul.colocar_peca(capturaPeca, destino);//volta a peça para o tabuleiro na posição de destino
			pecasCapturadas.remove(capturaPeca);//tirar a peça da lista de capturadas e colocar na lista do tabuleiro:
			pecasNoTabul.add(capturaPeca);
		}
		
		//desfazer movimento especial roque na torre do lado do rei
		//para descobrir se o movimento foi um roque/rei
		if(p instanceof Rei && destino.getColunas() == origem.getColunas() + 2 ) {
			Posição origemT = new Posição(origem.getLinhas(), origem.getColunas() + 3);
			Posição destinoT = new Posição(origem.getLinhas(), origem.getColunas() + 1);
			Peça_xadrez torre = (Peça_xadrez)tabul.remover_Peca(destinoT);//retira de onde ela estiver
			tabul.colocar_peca(torre, origemT);//coloca na posição de destino
			torre.decrementarcontMov();//incrementa a quantidade de movimentos da torre

		}
		/*desfazer movimento especial roque na torre do lado da rainha 
		(muda apenas a posição de origem e destino em relação ao roque do rei*/
		//para descobrir se o movimento foi um roque/rainha
		if(p instanceof Rei && destino.getColunas() == origem.getColunas() - 2 ) {
			Posição origemT = new Posição(origem.getLinhas(), origem.getColunas() - 4);
			Posição destinoT = new Posição(origem.getLinhas(), origem.getColunas() - 1);
			Peça_xadrez torre = (Peça_xadrez)tabul.remover_Peca(destinoT);
			tabul.colocar_peca(torre, origemT);
			torre.decrementarcontMov();
		}
		
		/*Movimento em passant
		 * sempre que uma peça for movida, verificar se foi peão 
		 */
		if (p instanceof Peao) {
			//se o peão andou na diagonal e não capturou peça, significa que foi em passant
			if (origem.getColunas() != destino.getColunas() && capturaPeca == vuneravel_enPassant) {
				Peça_xadrez peao = (Peça_xadrez)tabul.remover_Peca(destino);//retirar a peça do local incorreto
				Posição peaoPosicao;
				if (p.getCor() == Cor.BRANCA) {
					peaoPosicao = new Posição(3,destino.getLinhas());
				}
				else {
					peaoPosicao = new Posição(4,destino.getLinhas());
				}
				tabul.colocar_peca(peao, peaoPosicao);			
			}
		}		
	}
	
	//implementação da operaçao de validação de origem:
	private void validarPosicaoOrigem (Posição posicao) {
		if (!tabul.existe_peca(posicao)) {
			throw new Exceção_xad ("Não existe peça na posição de origem!");
		}
	//	if (jogadorAtual != ((Peça_xadrez)tabul.peca(posicao)).getCor());
		//	throw new Exceção_xad("Essa peça não é sua!");
		if (jogadorAtual != ((Peça_xadrez)tabul.peca(posicao)).getCor()) {
			throw new Exceção_xad("Essa peça não é sua!");
		}
		//testar se existe movimentos possiveis para a peça.
		//Se não tiver nenhum movimento possivel, lança uma esceção:
		if (!tabul.peca(posicao).existeMovPossivel()) {
			throw new Exceção_xad("Não existe movimentos possíveis para a peça escolhida ");
		}
	}
	//implementação da operaçao de validação de destino:
	private void validarPosicaoDestino(Posição origem, Posição destino) {
		if (!tabul.peca(origem).movimPossivelPosic(destino)) {
			throw new Exceção_xad ("A peça escolhida não pode ser mover para a posição de destino");
		}
			
		}
	
	//metodo que troca a "vez"
	private void proximaVez() {
		vez++; //incrementa a vez
		//expressão para mudança de jogador:
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	//metodo para devolver o oponente de um cor
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;	
	}
	//metodo para localizar um rei de determianda cor
	private Peça_xadrez rei(Cor cor) {
		List<Pecas> list = pecasNoTabul.stream().filter(x -> ((Peça_xadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Pecas p : list) {
			if (p instanceof Rei) {
			return (Peça_xadrez)p;
			}
		}
		//se não encontrar nenhum rei, lança a seguinte exceção:
		throw new IllegalStateException("Não existe" + cor + "rei no tabuleiro");
	}

	private boolean testeXeque(Cor cor) {
		//pegando a posição do rei:
		Posição posicaoRei =  rei(cor).getPosicao_xadrez().convert_posic();
		//lista das peças do oponente:
		List<Pecas> pecaOponente = pecasNoTabul.stream().filter(x -> ((Peça_xadrez)x).getCor() == oponente (cor)).collect(Collectors.toList());
		//varredura da lista para ver os movimentos possiveis:
		for (Pecas p : pecaOponente) {
			boolean [][] matriz = p.movim_possiveis();
			if (matriz [posicaoRei.getLinhas()][posicaoRei.getColunas()]) {
				return true;//rei está em xeque
			}
		}
		return false;//rei não está em xeque
	}
	//metodo para o xeque mate
	private boolean testeXequeMate(Cor cor) {
		//teste para ver se está em xeque
		if (!testeXeque(cor)) {
			return false;
		}
		//lista de peças da mesma cor
		List<Pecas> list = pecasNoTabul.stream().filter(x -> ((Peça_xadrez)x).getCor() == cor).collect(Collectors.toList());
		//percorrer todas as peças da lista e testar se tem alguma peça que tire do xeque
		for (Pecas p : list) {
			boolean [][] matriz = p.movim_possiveis();
			for (int i=0; i<tabul.getLinhas(); i++) {
				for (int j=0; j<tabul.getColunas(); j++) {
					if (matriz [i][j]) {
						Posição origem = ((Peça_xadrez)p).getPosicao_xadrez().convert_posic();
						Posição destino = new Posição (i,j);
						Pecas capturaPeca = operacaoMovimentoPeca(origem, destino);
						boolean testeXeque = testeXeque (cor);
						desfazerMovim (origem, destino, capturaPeca);
						if (!testeXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	//metodo que recebe as coordenadas do xadrez:
	private void nova_peca(char coluna, int linha, Peça_xadrez peca) {
		tabul.colocar_peca(peca, new posicao_xadrez(coluna, linha).convert_posic());
		//sempre que uma peça for instanciada ela também será colocada na lista do tabuleiro:
		pecasNoTabul.add(peca);
	}
	
	//metodo responsavel por iniciar a partida de xadrez, colocando as pecas no tabuleiro
	private void iniciarPartida() {
		
		//nova_peca('b', 6, new Torre(tabul, Cor.BRANCA));//colcando uma nova peça em determinada posicao
		//nova_peca('e', 8, new Rei(tabul, Cor.PRETA)); 
	//	nova_peca('e', 1, new Rei(tabul, Cor.BRANCA));
		//nova_peca('c', 1, new Torre(tabul, Cor.BRANCA));
		//nova_peca('c', 2, new Torre(tabul, Cor.BRANCA));
		//nova_peca('d', 2, new Torre(tabul, Cor.BRANCA));
		//nova_peca('e', 2, new Torre(tabul, Cor.BRANCA));
		//nova_peca('d', 1, new Rei(tabul, Cor.BRANCA));
		//nova_peca('c', 7, new Torre(tabul, Cor.PRETA));
		//nova_peca('c', 8, new Torre(tabul, Cor.PRETA));
		//nova_peca('d', 7, new Torre(tabul, Cor.PRETA));
		//nova_peca('e', 7, new Torre(tabul, Cor.PRETA));
		nova_peca('a', 1, new Torre(tabul, Cor.BRANCA));
		nova_peca('b', 1, new Cavalo(tabul, Cor.BRANCA));
		nova_peca('c', 1, new Bispo(tabul, Cor.BRANCA));
		nova_peca('d', 1, new Rainha(tabul, Cor.BRANCA));
		nova_peca('e', 1, new Rei(tabul, Cor.BRANCA, this));
		nova_peca('f', 1, new Bispo(tabul, Cor.BRANCA));
		nova_peca('g', 1, new Cavalo(tabul, Cor.BRANCA));
		nova_peca('h', 1, new Torre(tabul, Cor.BRANCA));
		nova_peca('a', 2, new Peao(tabul, Cor.BRANCA, this));
		nova_peca('b', 2, new Peao(tabul, Cor.BRANCA, this));
		nova_peca('c', 2, new Peao(tabul, Cor.BRANCA, this));
		nova_peca('d', 2, new Peao(tabul, Cor.BRANCA, this));
		nova_peca('e', 2, new Peao(tabul, Cor.BRANCA, this));
		nova_peca('f', 2, new Peao(tabul, Cor.BRANCA, this));
		nova_peca('g', 2, new Peao(tabul, Cor.BRANCA, this));
		nova_peca('h', 2, new Peao(tabul, Cor.BRANCA, this));
		
		nova_peca('a', 8, new Torre(tabul, Cor.PRETA));
		nova_peca('b', 8, new Cavalo(tabul, Cor.PRETA));
		nova_peca('c', 8, new Bispo(tabul, Cor.PRETA));
		nova_peca('d', 8, new Rainha(tabul, Cor.PRETA));
		nova_peca('e', 8, new Rei(tabul, Cor.PRETA, this));
		nova_peca('f', 8, new Bispo(tabul, Cor.PRETA));
		nova_peca('g', 8, new Cavalo(tabul, Cor.PRETA));
		nova_peca('h', 8, new Torre(tabul, Cor.PRETA));
		nova_peca('a', 7, new Peao(tabul, Cor.PRETA, this));
		nova_peca('b', 7, new Peao(tabul, Cor.PRETA, this));
		nova_peca('c', 7, new Peao(tabul, Cor.PRETA, this));
		nova_peca('d', 7, new Peao(tabul, Cor.PRETA, this));
		nova_peca('e', 7, new Peao(tabul, Cor.PRETA, this));
		nova_peca('f', 7, new Peao(tabul, Cor.PRETA, this));
		nova_peca('g', 7, new Peao(tabul, Cor.PRETA, this));
		nova_peca('h', 7, new Peao(tabul, Cor.PRETA, this));
		
		

	}
		
	}
