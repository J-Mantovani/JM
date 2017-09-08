#include <stdio.h>
#include <Windows.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>
#include <conio.h>


struct pixel //Struct que define os pixels
{
    unsigned char R, G, B;
    int flag;
};

struct pilhaPrincipal  //Struct que define a pilha principal
{
    int tamanho;
    struct posicaoImagem *proximo;
};

struct posicaoImagem  //Struct que define o formato da imagem
{
    unsigned int x;
    unsigned int y;
    struct posicaoImagem *anterior;
};

void insere(int x, int y, struct pilhaPrincipal * pilha)
{
    struct posicaoImagem *posicao = (struct posicaoImagem *)malloc(sizeof(struct posicaoImagem));
    if(posicao == NULL)
    {
        printf("!!Erro!!");
    }
    else
    {
        posicao->x = x;
        posicao->y = y;
        if(pilha->proximo == NULL)
        {
            posicao->anterior = NULL;
            pilha->proximo = posicao;
        }
        else
        {
            posicao->anterior = pilha->proximo;
            pilha->proximo = posicao;
        }
        pilha->tamanho++;
    }
}



int main()
{

    struct pixel ** img;
    unsigned char Br, Bg, Bb;
    char arquivo[50];
    char *ppm;
    char ch[16], c;
    int y = 1, largura, altura, max, flag, i, j;
    int contagem = 0;

    struct pilhaPrincipal *pilha;
    struct posicaoImagem *aux;

    system("color 0b");              //
    system("title Pixel Count");     //Diretrizes visuais
    setlocale(LC_ALL, "Portuguese"); //

    FILE *file;
    do{
    printf("Digite o nome do arquivo: ");
    scanf("%s", &arquivo);
    if(y == 1)
    {
        if (( file = fopen(arquivo,"rb")) == NULL) //Teste de funcionalidade
        {
            printf("\%s: Não abriu o arq.\n", arquivo );
            system("PAUSE");
            return 1;
        }
    }

    pilha = (struct pilhaPrincipal *) malloc(sizeof(struct pilhaPrincipal));
    if(pilha == NULL)
    {
        return 0;
    }
    else
    {
        pilha->proximo = NULL;
        pilha->tamanho = 0;
    }

    if(!fgets(ch,sizeof(ch),file))
    {
        exit(1);
    }

    if(ch[0] != 'P'||ch[1] != '6')  //Verifica o tipo do arquivo.
    {
        printf("\nArquivo não compátivel");
        while((pilha->tamanho) > 0 );
    }
    else
    {
        fscanf(file, "%d%d%d",&largura, &altura, &max);//Busca as dimensões do arquivo .ppm
        fscanf(file, "%c", &c);
    }
    if(largura == 0||altura ==0)
    {
        printf("\nTamanho do Arquivo inválido.");
    }
    else
    {
        printf("\nArquivo .ppm identificado.");
        printf("\nLargura: %d", largura);
        printf("\nAltura: %d", altura);
    }
    if(max == 255)
    {
        printf("\nArquivo 8 bits");
    }
    else
    {
        printf("\nArquivo 16 bits");
    }
    printf("\n");

    img = ((struct pixel**)calloc(altura, sizeof(struct pixel*)));

    for(j = 0; j < altura; j++)
    {
        img[j] = calloc(largura, sizeof(struct pixel));
    }

    for(i = 0; i < altura; i++)
    {
        for(j = 0; j < largura; j++)
        {
            fscanf(file, "%c%c%c", &img[i][j].R, &img[i][j].G, &img[i][j].B);
        }
    }
    Br = img[0][0].R;
    Bg = img[0][0].G;
    Bb = img[0][0].B;

    /*for(i = 0; i < altura; i++){
        for(j = 0; j < largura; j++){
            if(img[i][j].R != Br || img[i][j].G != Bg || img[i][j].B != Bb){
                printf("x");
                //getch();
            }
            else
                printf("_");
                //getch();
        }
        printf("\n");
    }*/

    struct posicaoImagem *posicao = (struct posicaoImagem *)malloc(sizeof(struct posicaoImagem));

    for(i = 0; i < altura; i++)
    {
        for(j = 0; j < largura; j++)
        {
            if((img[i][j].R != Br || img[i][j].G != Bg || img[i][j].B != Bb) && img[i][j].flag != 1)
            {
                img[i][j].flag = 1;

                if(i > 0)
                    {
                        if((img[i-1][j].R != Br || img[i-1][j].G != Bg || img[i-1][j].B != Bb) && img[i-1][j].flag != 1)
                        {
                            img[i-1][j].flag = 1;
                            insere(i-1, j, pilha);
                        }
                    }

                if(j < largura-1)
                    {
                        if((img[i][j+1].R != Br || img[i][j+1].G != Bg || img[i][j+1].B != Bb) && img[i][j+1].flag != 1)
                        {
                            img[i][j+1].flag = 1;
                            insere(i, j+1, pilha);
                        }
                    }
                     if(i < altura-1)
                    {
                        if((img[i+1][j].R != Br || img[i+1][j].G != Bg || img[i+1][j].B != Bb) && img[i+1][j].flag != 1)
                        {
                            img[i+1][j].flag = 1;
                            insere(i+1, j, pilha);
                        }
                    }

                    if(j > 0)
                    {
                        if((img[i][j-1].R != Br || img[i][j-1].G != Bg || img[i][j-1].B != Bb) && img[i][j-1].flag != 1)
                        {
                            img[i][j-1].flag = 1;
                            insere(i, j-1, pilha);
                        }
                    }






                while((pilha->tamanho) > 0 )
                {
                    if(pilha->proximo == NULL)
                    {
                        printf("Erro//");
                    }
                    else
                    {
                        aux = pilha->proximo;
                        posicao->x = aux->x;
                        posicao->y = aux->y;

                        pilha->proximo = aux->anterior;
                        pilha->tamanho--;
                        free(aux);
                    }
                    i = posicao->x;
                    j = posicao->y;


                if(i > 0)
                    {
                        if((img[i-1][j].R != Br || img[i-1][j].G != Bg || img[i-1][j].B != Bb) && img[i-1][j].flag != 1)
                        {
                            img[i-1][j].flag = 1;
                            insere(i-1, j, pilha);
                        }
                    }

                if(j < largura-1)
                    {
                        if((img[i][j+1].R != Br || img[i][j+1].G != Bg || img[i][j+1].B != Bb) && img[i][j+1].flag != 1)
                        {
                            img[i][j+1].flag = 1;
                            insere(i, j+1, pilha);
                        }
                    }
                     if(i < altura-1)
                    {
                        if((img[i+1][j].R != Br || img[i+1][j].G != Bg || img[i+1][j].B != Bb) && img[i+1][j].flag != 1)
                        {
                            img[i+1][j].flag = 1;
                            insere(i+1, j, pilha);
                        }
                    }

                    if(j > 0)
                    {
                        if((img[i][j-1].R != Br || img[i][j-1].G != Bg || img[i][j-1].B != Bb) && img[i][j-1].flag != 1)
                        {
                            img[i][j-1].flag = 1;
                            insere(i, j-1, pilha);
                        }
                    }
                }
            contagem++;
            }
        }
    }
    printf("Quantidade de Objetos: %d\n\n", contagem);
    contagem = 0;
    }while(arquivo != 0);
    if(fclose(file) == EOF)
    {
        printf("Não foi possível fechar o arquivo!\n");
        system("pause");
    }

    system("pause");

}
