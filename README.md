# TDE-01 — Flood Fill com Pilha e Fila

Resolução de Problemas Estruturados em Computação
Bacharelado em Engenharia de Software — PUCPR
Professora Lisiane Reips

## Integrantes
-João Pedro Carminatti
-Gustavo Nery
-Bento Barp
-Felipe Braga
-Erick Misturi Machado

## Sobre

Implementação do algoritmo Flood Fill em Java puro, com duas estratégias de
armazenamento dos pixels vizinhos: Pilha e Fila, ambas implementadas do zero
com nós encadeados. Nenhuma estrutura pronta do Java foi utilizada.

## Como executar

1. Abrir o projeto no IntelliJ (JDK 17 ou superior)
2. Garantir que `entrada/teste.png` existe
3. Executar a classe `floodfill.Main`

As imagens finais são gravadas em `saida/` e os frames da animação em
`saida/frames/pilha` e `saida/frames/fila`.

Parâmetros configuráveis no topo da `Main`: imagem de entrada, ponto inicial,
nova cor e intervalo entre frames.

## Estrutura das classes

| Pacote | Classe | Responsabilidade |
|---|---|---|
| model | Ponto | Coordenada imutável de um pixel |
| estrutura | No | Elo da corrente encadeada |
| estrutura | EstruturaDePontos | Interface comum entre Pilha e Fila |
| estrutura | Pilha | LIFO — insere e remove no topo |
| estrutura | Fila | FIFO — insere no fim, remove do início |
| estrutura | EstruturaVaziaException | Remoção em estrutura vazia |
| imagem | GerenciadorDeImagem | Único ponto de contato com File e BufferedImage |
| imagem | GravadorDeFrames | Grava a animação a cada N pixels pintados |
| algoritmo | FloodFill | O algoritmo; recebe a estrutura pelo construtor |
| (raiz) | Main | Monta as dependências e dispara a execução |

A classe `FloodFill` depende da interface `EstruturaDePontos`, não das classes
concretas. Trocar Pilha por Fila é uma única linha na `Main` — o algoritmo é
escrito uma vez só e não contém nenhuma verificação de qual estrutura está em uso.

## Resultado

Com a imagem de teste (160x160) e ponto inicial (5,5), as duas estratégias
pintam exatamente 8.165 pixels e produzem a mesma imagem final. O que muda é a
ordem de preenchimento, visível comparando os frames intermediários das duas
animações.