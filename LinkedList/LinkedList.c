#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct lista {
    struct lista *nastepny;
    char s[20];
} lista;

lista* wstaw(char *s, lista *L) {
    lista *nowy = malloc(sizeof(lista));
    strcpy((*nowy).s, s);
    (*nowy).nastepny = L;
    return nowy;
}

void drukuj(lista *L) {
    while(L != NULL) {
        printf("%s\n", (*L).s);
        L = (*L).nastepny;
    }
}

lista* szukaj(char *s, lista *L) {
    while(L != NULL) {
        if(strcmp((*L).s, s) == 0) {
            return L;
        } else {
            L = (*L).nastepny;
        }
        
    }
    return NULL;
} 

lista* usun(char *s, lista *L) {
    lista *l = L;
    lista *usun = NULL;
    while(l != NULL) {
        if(strcmp((*l).s, s) == 0) {
            break;
        }
        usun = l;
        l = (*l).nastepny;
    }
    if(l != NULL){
        if(l == L) {
            L = (*l).nastepny;
        } else {
            (*usun).nastepny = (*l).nastepny;
        }
        free(l);
    }
}

lista* kasuj(lista **L) {
    lista* l;
    while(*L != NULL) {
        l = *L;
        *L = (**L).nastepny;
        free(l);
    }
}

lista* bezpowtorzen(lista *L) {
    lista *nowy = NULL;
    lista *l1 = L;
    lista *l2 = NULL;
    bool n = true;
    while(l1 != NULL) {
        n = true;
        while(l2 != NULL) {
            if(strcmp((*l1).s, (*l2).s) == 0) {
                n = false;
            }
            l2 = (*l2).nastepny;
        }
        if(n) {
            nowy = wstaw((*l1).s, nowy);
            l2 = nowy;
        }
        l1 = (*l1).nastepny;
    }
    return nowy;
}

lista* scal(lista *L1, lista *L2) {
    lista *l = L1;
    while((*l).nastepny != NULL) {
        l = (*l).nastepny;
    }
    (*l).nastepny = L2;
    return L1;
}

int main() {
    lista *L = NULL;
    lista *L1 = NULL;
    lista *L2 = NULL; 
    lista *L3 = NULL;

    printf("_______________\n1) Wstaw\n");   
    L = wstaw("Zuzanna", L);
    L = wstaw("Julia", L);
    L = wstaw("Maja", L);
    L = wstaw("Zofia", L);
    L = wstaw("Hanna", L);
    L = wstaw("Antoni", L);
    L = wstaw("Jakub", L);
    L = wstaw("Jan", L);
    L = wstaw("Szymon", L);
    L = wstaw("Aleksander", L);

    printf("_______________\n2) Drukuj\n");
    drukuj(L);

    printf("_______________\n3) Szukaj\n");
    printf("%p\n", szukaj("Julia", L));
    printf("%p\n", szukaj("Mateusz", L));

    printf("_______________\n4) Usun\n");
    usun("Jan", L); 
    usun("Antoni", L); 
    usun("Jakub", L); 
    usun("Szymon", L);
    drukuj(L);

    printf("_______________\n5) Kasuj\n");
    kasuj(&L);
    drukuj(L);

    printf("_______________\n6) Bezpowtorzen\n");
    L = wstaw("Zuzanna", L);
    L = wstaw("Julia", L);
    L = wstaw("Maja", L);
    L = wstaw("Julia", L);
    L = wstaw("Zofia", L);
    L = wstaw("Julia", L);
    L = wstaw("Szymon", L);
    L = wstaw("Hanna", L);
    
    L1 = bezpowtorzen(L);
    printf("L1:\n");
    drukuj(L1);
    printf("\nL:\n");
    drukuj(L);
    kasuj(&L);
    
    L = wstaw("Antoni", L);
    L = wstaw("Szymon", L);
    L = wstaw("Jakub", L);
    L = wstaw("Julia", L);
    L = wstaw("Szymon", L);
    L = wstaw("Jan", L);
    L = wstaw("Szymon", L);
    L = wstaw("Aleksander", L);

    L2 = bezpowtorzen(L);
    printf("\nL3:\n");
    drukuj(L2);
    printf("\nL:\n");
    drukuj(L);
    kasuj(&L);

    printf("_______________\n7) Scal\n");
    L3 = scal(L1, L2);
    printf("\nL3:\n");
    drukuj(L3);
    return 0;
}