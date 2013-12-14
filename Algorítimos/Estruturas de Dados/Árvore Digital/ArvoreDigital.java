package raulJoaquim;

import javax.swing.JOptionPane;

//-------------------------------------------------------------------------------------//
/* ArvoreDigital.java
 * Arvore Digital
 * Objetivo: Implementacao de uma estrutura de dados Arvore Digital com metodos
 * de busca, insercao, remocao e impressao.
 * Desenvolvedor : Raul Joaquim Camara dos Santos
 * Local: Dimap/UFRN - Natal/RN -Brasil
 * Data: 08/04/2008
 * Ultima Modificacao: 17/04/2008
 */
//-------------------------------------------------------------------------------------//
//Classe que implementa uma Arvore Digital
public class ArvoreDigital {
	
	//***ATRIBUTOS***//
	
	//alfabeto utilizado na arvore
	private final Character[] alfabeto = {'A','B','C','D','E','F','G','H','I','J',
			 								'K','L','M','N','O','P','Q','R','S','T',
			 								'U','V','W','X','Y','Z'};
	private final int MAX_FILHOS; //numero maximo de filhos
	private Raiz raiz; //apontador para a raiz
	private String print = " "; //atributo de impressao		
	private class Raiz{ //no especial raiz
		private NoDigital[] filhos;
		public Raiz(){
			this.filhos = new NoDigital[MAX_FILHOS];
		}
	}
	
	//***CONSTRUTOR**//
	
	public ArvoreDigital() {
		super();
		this.MAX_FILHOS = 26; //numero de chaves possiveis no alfabeto
		this.raiz = null;
		
	}
	
	//***METODOS***//
	
	/*========================================================================================*/
	//	Metodo de busca de um elemento com chave x na arvore Digital
	public NoDigital buscarElemento(String x) {
		x = x.toUpperCase(); //se as chaves estiverem em minusculo, passam a ser maiusculo
		if (!this.pertenceAlfabeto(x)){//testa entrada correta
			System.out.println("A string possui caracteres que nao pertencem" +
					" ao alfabeto da Arvore Digital");return null;}
		if (raiz == null){return null;}
		NoDigital dig = new NoDigital(MAX_FILHOS);
		boolean pesquisa =false;
			for (int j = 0; j < MAX_FILHOS;j++){
				while(raiz.filhos[j] == null){j++;}//passa dos nulos
				if (raiz.filhos[j].chave != null && raiz.filhos[j].chave == x.charAt(0)){//achou
					dig = raiz.filhos[j];
					pesquisa = true; 
					if (dig.terminal && x.length() == 1){//chegou no final
						return dig;
					}
				}
			}
			if (pesquisa){//busca pela arvore
				int i = 1;
				while (i < x.length()){
					boolean proximoNo = false; //passar para o proximo no
					if (i == x.length()-1 && dig.terminal){//achou
						return dig;
					}
					while (!proximoNo){
						//pesquisa

						for (int j = 0 ;j < MAX_FILHOS;j++){
							
							if (dig.filhos[j] != null && dig.filhos[j].chave == x.charAt(i)){
								i++; 				 //proxima chave do parametro
								dig = dig.filhos[j]; //desce na arvore
								if (i == x.length() && dig.terminal){return dig;}
								proximoNo = true; 	 //passa para o proximo
							}
						}
						if(!proximoNo){return null;} //a ultima chave pesquisada 
													 //nao e equivalente
					}
				}
			}
			return null;
	}
	/*========================================================================================*/
	//	Metodo de insercao de um elemento com chave x na arvore Digital
	public boolean inserirElemento(String x){
		x = x.toUpperCase(); //se as chaves estiverem em minusculo, passam a ser maiusculo
		if (!this.pertenceAlfabeto(x)){return false;}
		NoDigital dig; //no de percurso
		dig = new NoDigital(MAX_FILHOS);
		boolean pesquisa =false;
		//NOVA RAIZ
		if(this.raiz == null){//nao existe elementos na arvore
			raiz = new Raiz();
			for(int i=0; i < MAX_FILHOS; i++){ //aloca filhos da raiz
				this.raiz.filhos[i] = new NoDigital(MAX_FILHOS);
			}
			raiz.filhos[0].chave = x.charAt(0);
			if (x.length() == 1){//chegou no final
				if (!dig.terminal)//no ja existe na arvore
				{
					System.out.println(exibirArvorePreOrdem());
					return false;
				}
				else { //novo no
					dig.terminal =true;
					System.out.println(exibirArvorePreOrdem());
					return true;
				}
			}
			else {//ainda nao chegou ao fim
				dig = raiz.filhos[0];
				pesquisa = true;
			}
		}
		//RAIZ JA INICIALIZADA
		else{ 
			int j=0;
			//passa pelos nos nulos
			while (raiz.filhos[j] == null){j++;}
			while (raiz.filhos[j].chave == null && j < MAX_FILHOS-1){
				j++;
			}
			int k=0;
			//testa se insere um novo no filho na raiz
			if (!existeChave(raiz,x.charAt(0)) ){
				while(raiz.filhos[k] ==null){k++;}
				while (raiz.filhos[k].chave != null && k < MAX_FILHOS-1){k++;}
				raiz.filhos[k].chave = x.charAt(0);
				if (x.length() == 1){//chegou no final
					if (raiz.filhos[j].terminal)//no ja existe na arvore
					{
						System.out.println(exibirArvorePreOrdem());
						return false;
					}
					else {
						raiz.filhos[j].chave = x.charAt(0);
						raiz.filhos[j].terminal =true;
						System.out.println(exibirArvorePreOrdem());
						return true;
					}
				}
				else{//ainda nao chegou ao fim
					dig = raiz.filhos[k];
					pesquisa = true; //autorizado a pesquisar
				}
			}
			else{//raiz nao tem novo filho

				if (j == 25){//chegou no fim e nao encontrou. Inserir ate o fim
					NoDigital pai = new NoDigital(MAX_FILHOS); //no que guarda o pai
					inserir(dig,pai,x.substring(1));
					System.out.println(exibirArvorePreOrdem());
					return true;
				}
				if (raiz.filhos[j].chave !=null && raiz.filhos[j].chave == x.charAt(0)){//achou
					if (x.length() == 1){//chegou no final
						if (raiz.filhos[j].terminal)//no ja existe na arvore
						{
							System.out.println(exibirArvorePreOrdem());
							return false;
						}
						else {
							raiz.filhos[j].chave = x.charAt(0);
							raiz.filhos[j].terminal =true;
							System.out.println(exibirArvorePreOrdem());
							return true;
						}
					}
					else{//ainda nao chegou ao fim
						dig = raiz.filhos[j];
						pesquisa = true; //autorizado a pesquisar
					}
				}
				else {//nao achou

					while (j < MAX_FILHOS-1){
					while(raiz.filhos[j] == null){
						j++;
						if(j == MAX_FILHOS-1){j=0;}
						}

					if(x.charAt(0) == raiz.filhos[j].chave){

						if (x.length() == 1){//chegou no final
							if (raiz.filhos[j].terminal)//no ja existe na arvore
							{
								System.out.println(exibirArvorePreOrdem());
								return false;
							}
							else {
								raiz.filhos[j].chave = x.charAt(0);
								raiz.filhos[j].terminal =true;
								System.out.println(exibirArvorePreOrdem());
								return true;
							}
						}
						else{//ainda nao chegou ao fim
							dig = raiz.filhos[j];
							pesquisa = true; //autorizado a pesquisar
							break;
						}
						}
					j++;
					}
				}
			}
		}
		//pesquisa no resto da arvore
		NoDigital pai = new NoDigital(MAX_FILHOS); //no que guarda o pai
		if (pesquisa){ //insere o No nos filhos da raiz 
			int i = 1;
			while (i < x.length()){//le todo o parametro x

				boolean proximoNo = false; //passar para o proximo no
				while (!proximoNo){
					//pesquisa
					for (int j = 0 ;j < MAX_FILHOS;j++){
						while (dig.filhos[j] == null && j < MAX_FILHOS-1){j++;} //passa pelos nos nulos
						if (j == 25){//chegou no fim e nao encontrou. Inserir ate o fim
							inserir(dig,pai,x.substring(i));
							System.out.println(exibirArvorePreOrdem());
							return true;
						}
						boolean sair =false;
						int k = j;
						while (!sair && dig.filhos[k].chave == x.charAt(i)){//se entrar aqui, apenas desce
							i++; 				 //proxima chave do parametro
							pai = dig;			 //pai sera o no anterior na pesquisa
							dig = dig.filhos[k]; //desce na arvore
							if (!temFilhos(dig)){sair =true;}
							else {//procura se tem outro filho com mesma chave da string x
								for (int w = 0 ;w < MAX_FILHOS;w++){
									if(dig.filhos[w] != null){
										if (i == x.length()){sair = true;} 
										else if (dig.filhos[w].chave == x.charAt(i)){
											k = w;
										}
									}
								}
							}
							proximoNo = true; 	 //passa para o proximo
						}
					}
				}
			}
			//chegou ao fim
			if (dig.terminal){
				System.out.println(exibirArvorePreOrdem());
				return false; 
			} //elemento ja existe na arvore
			else {//insercao da chave
				dig.pai = pai;
				dig.chave = x.charAt(i-1);
				dig.terminal =true;
				System.out.println(exibirArvorePreOrdem());
				return true; //sucesso
			}
		}
		return false;
	}
	//auxiliar para insercao de novos filhos na raiz
	private boolean existeChave(Raiz dig, char x){
		for (int i =0; i <MAX_FILHOS; i++){
			if(dig.filhos[i] == null){i++;}
			if (dig.filhos[i].chave != null && dig.filhos[i].chave == x){return true;}
		}
		return false;
	}
	//Insere o resto da string na arvore
	private void inserir(NoDigital dig, NoDigital pai, String chave){
		int k=0;
		for (int i =0; i< chave.length(); i++){
			while(dig.filhos[k] != null && k < MAX_FILHOS){k++;}//busca filho sem chave
			//System.out.println("filho "+ k + ": " + dig.chave + chave.charAt(i));
			dig.filhos[k] = new NoDigital(MAX_FILHOS);
			dig.filhos[k].chave = chave.charAt(i);
			pai = dig;
			dig = dig.filhos[k];
			dig.pai = pai;
			k=0;
		}
		dig.terminal = true; //o ultimo no sera terminal 
	}
	/*========================================================================================*/
	//	Metodo de remocao de um elemento com chave x na arvore Digital
	public boolean removerElemento(String x) {
		x = x.toUpperCase(); //se as chaves estiverem em minusculo, passam a ser maiusculo
		if (!this.pertenceAlfabeto(x)){return false;}
		NoDigital dig = new NoDigital(MAX_FILHOS);
		//busca o elemento a ser removido
		dig = this.buscarElemento(x);

		if (dig == null){//nao achou
			System.out.println(exibirArvorePreOrdem());
			return false;
		} 
		else{
			//pesquisa abaixo do no
			if (temFilhos(dig)){
				dig.terminal = false;
				System.out.println(exibirArvorePreOrdem());
				return true;
			}
			else{
				//agora pesquisa acima do no

				if (dig.pai == null){//dig nao tem pai, remove dig
					dig = null;
					System.out.println(exibirArvorePreOrdem());
					return true;
				} 
				else{//remove ate achar um No terminal ou nao houver mais Nos
					NoDigital sobe = new NoDigital(MAX_FILHOS);
					NoDigital aux = new NoDigital(MAX_FILHOS);
					dig.terminal =false;
					sobe = dig;
					while (sobe != null && !(sobe.terminal)){
						if(temFilhos(sobe)){//pa'ra remocao
							System.out.println(exibirArvorePreOrdem());
							return true;
						}
						aux = sobe;
						sobe = sobe.pai;

						if (aux.pai == null){ //o pai e a raiz
							for(int i =0; i < MAX_FILHOS; i++){
								if (raiz.filhos[i] == aux){raiz.filhos[i] = null;}
							}
							//testa se a raiz ainda tem filhos

							if (!temFilhos(raiz)){raiz = null;return true;}
						}
						else{//apaga o No
							for(int i =0; i < MAX_FILHOS; i++){
								if (aux.pai.filhos[i] == aux){aux.pai.filhos[i] = null;}
							}
							aux = null;
						}
					}
					System.out.println(exibirArvorePreOrdem());
					return true;
				}
			}
		}
	}
	//Metodo auxiliar para saber se um no tem filhos
	private boolean temFilhos(NoDigital dig){
		for(int i =0; i < MAX_FILHOS; i++){
			if (dig.filhos[i] != null){
				if(dig.filhos[i].chave != null)
					return true;
				} //verifica se tem filhos
		}
		return false;
	}
	//Metodo auxiliar para saber se a raiz tem filhos
	private boolean temFilhos(Raiz dig){
		for(int i =0; i < MAX_FILHOS; i++){
			if (dig.filhos[i] != null){
				if(dig.filhos[i].chave != null)
					return true;
				} //verifica se tem filhos
		}
		return false;
	}
	/*========================================================================================*/
	/*Metodo que exibe a arvore em pre ordem
	na representacao de parenteses aninhados*/
	public String exibirArvorePreOrdem(){
		if (raiz == null)return "";
		Raiz p = this.raiz; // recebe o no raiz
		for(int i =0; i < MAX_FILHOS; i++){

			if (p.filhos[i] != null && p.filhos[i].chave != null){
				print = print + "(" + Character.toString(p.filhos[i].chave)+ " ";
				if (p.filhos[i].terminal) print = print +"t";
			}
			for(int j =0; j < MAX_FILHOS; j++){
				if (p.filhos[i] != null && p.filhos[i].filhos[j] != null){
					print = print + "(" + exibirArvorePreOrdem(p.filhos[i].filhos[j])+")";

				}
			}
		}
			print = print + ")";
			String pr =print;
			print = " ";
			return pr;
		}
	/*Metodo que exibe a arvore em pre ordem (metodo privado para auxiliar 
	 para recursao) na representacao de parenteses aninhados*/
	private String exibirArvorePreOrdem(NoDigital p){
		
			if (p.chave != null){
				print = Character.toString(p.chave)+ " ";
				if (p.terminal) print = print +"t";
			}
			for(int j =0; j < MAX_FILHOS; j++){
				if (p.filhos[j] != null){
					print = print + "(" + exibirArvorePreOrdem(p.filhos[j])+")";

				}
			}
		String pr =print;
		print = " ";
		return pr;
	}
	//Metodo que testa se um dado char consta no alfabeto da classe
	private boolean pertenceAlfabeto(String x){
		x = x.toUpperCase(); //se as chaves estiverem em minusculo, passam a ser maiusculo
		int teste = 0;
		for (int j =0; j < x.length();j++){
			for (int i=0; i < alfabeto.length; i++){//pesquisa no alfabeto
				if ( alfabeto[i] == x.charAt(j) ){
					teste++;
				}
			}
		}
		if (teste == x.length())return true; //Todos Pertencem
		else return false;
	}
	/*========================================================================================*/
	//Metodo de execucao da Arvore AVL
	public void start(){
		String parametro;
		String tipodeOperacao =" ";
		JOptionPane.showMessageDialog(null,"Arvore Digital \n Aceita entrada de caracteres de A a Z (Nao Case Sensitive)"," Arvore Digital",JOptionPane.PLAIN_MESSAGE);
		System.out.println("**Historico de operações**");
		while (tipodeOperacao != null){//inicio do loop
			tipodeOperacao = JOptionPane.showInputDialog("Entre com o tipo de operacao" +
					" que deseja fazer:\n [1] Busca \n [2] Insercao \n [3] " +
			"Remocao\n [4] Impressao");
			if(tipodeOperacao == null || tipodeOperacao.length() == 0){return;}//entrada vazia(cancelamento do panel)

			boolean testeOperador =false;
			for(int j =0; j < tipodeOperacao.length(); j++){//testa se entrada nao é inteiro
				if (!Character.isDigit(tipodeOperacao.charAt(j))){ 
					testeOperador = true;
				}	
			}
			//condicao de erro
			if (testeOperador){JOptionPane.showMessageDialog(null,"Entrada incorreta (entre com um " +
					"caracteres de A a Z)"," Arvore Digital ",JOptionPane.PLAIN_MESSAGE);}
			else{
				//IMPRESSAO
				if(tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '4'){//impressao
					JOptionPane.showMessageDialog(null,"A arvore atual e': " + exibirArvorePreOrdem()
							,"Impressao",JOptionPane.PLAIN_MESSAGE); //impressao
					System.out.println("Impressao da arvore: " + this.exibirArvorePreOrdem());
				}//passa
				else{
					parametro = JOptionPane.showInputDialog("Entre com o parametro da operacao (A a Z)");
					if (parametro == null ||parametro.length() == 0){return;}//teste de cancelamento ou entrada vazia
					if (!this.pertenceAlfabeto(parametro)){ //testa se entrada nao é string
						System.out.println("entrou");
						JOptionPane.showMessageDialog(null,"Entrada incorreta (entrada de A a Z)"," Arvore Digital ",JOptionPane.ERROR_MESSAGE);
					}
					else{
						//BUSCA
						if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '1'){
							if(this.buscarElemento(parametro) == null){//busca
								JOptionPane.showMessageDialog(null,"Nao achou elemento: " + parametro
										,"Impressao",JOptionPane.INFORMATION_MESSAGE);
								System.out.println("Busca: Nao achou o elemento" + parametro);
							}
							else{
								JOptionPane.showMessageDialog(null,"Achou elemento: " + parametro
										,"Impressao",JOptionPane.INFORMATION_MESSAGE);
								System.out.println("Busca: Achou o elemento" + parametro);
							}
						}
						//INSERCAO
						else if(tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '2'){//insercao
							this.inserirElemento(parametro);
							JOptionPane.showMessageDialog(null,"A arvore atual e': " + exibirArvorePreOrdem()
									,"Impressao",JOptionPane.PLAIN_MESSAGE);
							System.out.println("Tentativa de insercao de: " + parametro);
						}
						//REMOCAO
						else if(tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '3'){//remocao
							this.removerElemento(parametro);
							JOptionPane.showMessageDialog(null,"A arvore atual e': " + exibirArvorePreOrdem()
									,"Impressao",JOptionPane.PLAIN_MESSAGE);
							System.out.println("Tentativa de remocao de: " + parametro);
						}
						else if(tipodeOperacao == null){//cancelou 
							System.out.println("Fim da operacao");
							return; //teste de cancelamento
						}
						else{//Se diferente de todas as outras opcoes, entrada incorreta
							JOptionPane.showMessageDialog(null,"Entrada incorreta"," Arvore Digital ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			System.out.println(exibirArvorePreOrdem());
		}
		System.out.println("Finalizando operacao do programa de execucao da Arvore Arvore Digital");
	}

}
