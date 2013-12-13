function quickSort(vet, esq, dir){
    var ce = esq;
    var cd = dir;
    var meio = parseInt((ce + cd)/ 2);                          
    while(ce < cd){
        while(vet[ce] < vet[meio]){
            ce++;                               
        }
        while(vet[cd] > vet[meio]){
            cd--;
        }
        if(ce <= cd){
            var temp = vet[ce];
            vet[ce] = vet[cd];
            vet[cd] = temp;
            ce++;
            cd--;
        }                       
    }           
    if(cd > esq)
        quickSort(vet, esq, cd);                
 
    if(ce < dir)
        quickSort(vet, ce, dir);                
}
 
var vet = [4,10,3,9,7,1,12]; //adicionando elementos 
document.write(vet.join(" ")+"<br/>");
var esq = 0;
var dir = vet.length - 1; //indice máximo do array
quickSort(vet, esq, dir);       
document.write(vet.join(" "))
