O objetivo deste código é ajudar um explorador a encontrar um caminho dentro de um labirinto, dada uma posição de partida e outra de destino. O algoritmo é do tipo tentativa e erro e usa chamadas recursivas.
_____________________________________________
INSTRUÇÕES PARA RODAR O PROGRAMA

Utilizar o seguinte comando:

$ /bin/java ProgPrincipal <arquivo de entrada (*.txt)> comando

em que o arquivo de entrada é um mapa conforme descrição mais abaixo e o comando um dos inteiros (1,2,3,4) em que:

1. Caminho mais curto. 
2. Caminho mais longo. 
3. Caminho mais valioso. 
4. Caminho mais rápido. 

Exemplo: java ProgPrincipal mapa1.txt 2

Um arquivo mapa1.txt é incluído no diretório bin como exemplo.
O programa irá escrever na tela a solução do problema.

--------------------------------------------------
ARQUIVO DE ENTRADA

O arquivo de entrada deve conter a definição do labirinto, a lista de itens espalhados pelo mesmo, e as posições de partida/destino, respeitando a seguinte formatação:

<numero de linhas do labirinto> <numero de colunas do labirinto>
<linha 0>
<linha 1>
<linha 2>
(...) 
<número de itens>
<linha item 0> <coluna item 0> <valor item 0> <peso do item 0>
<linha item 1> <coluna item 1> <valor item 1> <peso do item 1>
<linha item 2> <coluna item 2> <valor item 2> <peso do item 2>
(...)
<linha posição de partida> <coluna posição de partida>
<linha posição de destino> <coluna posição de destino>

Exemplo:
7 5
.....
.X.X.
.X.X.
.....
.X.X.
.X.X.
.....
3
4 0 4 3
3 2 1 8
2 0 8 6
6 2
0 2

em que um labirinto é definido pelos caracteres '.' (caminho livre) e 'X' (parede), onde o explorador não pode percorrer.

--------------------------------------------------
SAÍDA

A saída é impressa na seguinte formatação:

<tamanho do caminho encontrado> <tempo para percorrer o caminho>
<linha da posição 0> <coluna da posição 0>
<linha da posição 1> <coluna da posição 1>
<linha da posição 2> <coluna da posição 2>
(...)
<quantidade de itens coletados> <valor total dos itens> <peso total dos itens>
<linha item coletado 0> <coluna item coletado 0>
<linha item coletado 1> <coluna item coletado 1>
<linha item coletado 2> <coluna item coletado 2>
(...)