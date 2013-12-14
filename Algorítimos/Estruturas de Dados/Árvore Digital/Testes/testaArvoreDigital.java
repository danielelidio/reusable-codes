package raulJoaquim;

//Classe de teste da Arvore Digital
//-------------------------------------------------------------------------------------//
public class testaArvoreDigital {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArvoreDigital Tree = new ArvoreDigital();
		
		//inserindo elementos
		Tree.inserirElemento("ABC");
		Tree.inserirElemento("AED");
		System.out.println("Tentativa de inserir elemento com caracteres invalidos");
		Tree.inserirElemento("ad2");
		System.out.println("Nao inseriu ad2");
		Tree.inserirElemento("ABAB");
		Tree.inserirElemento("ABCEH");
		//System.out.println(Tree.buscarElemento("ABC").chave);
		System.out.println(Tree.buscarElemento("AED").chave);
		System.out.println(Tree.buscarElemento("ABAB").chave);
		Tree.inserirElemento("TOA");
		Tree.inserirElemento("GBAD");
		System.out.println(Tree.buscarElemento("GBAD").chave);
		//tenta remover no nao terminal
		Tree.removerElemento("ABA");
		//tenta remover No
		Tree.removerElemento("ABAB");
		//tenta remover No em minusculo. É convertido em maiusculo e removido
		Tree.removerElemento("aed");
		//tenta remover No
		Tree.removerElemento("ABCEH");
		System.out.println("removendo caminho ABC...");
		Tree.removerElemento("ABC");
		System.out.println("pronto...");
		//Inserindo em minusculo(automaticamente convertido em maiusculo
		Tree.inserirElemento("jdg");
		Tree.inserirElemento("j");
		Tree.removerElemento("j");
		Tree.inserirElemento("jdge");
		System.out.println("Removendo todos os elementos, um por um");
		Tree.removerElemento("gbad");
		Tree.removerElemento("jdge");
		Tree.removerElemento("jdg");
		Tree.removerElemento("toa");
		System.out.println("pronto...Fim teste");
		
		ArvoreDigital Tree2 = new ArvoreDigital();
		Tree2.start();
		
		
	}
}//;~~
