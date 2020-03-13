## Zadanie AL6.2. (2 pkt.) 
Celem zadania jest sprawdzenie ilości kolizji w haszowaniu ciągu kluczy, które są napisami, z łańcuchową metodą usuwania kolizji. W tym celu należy zadeklarować tablicę T[m] liczb całkowitych, gdzie T[i] ma zawierać ilość tych kluczy k, dla których h(k)=i. Czyli najpierw trzeba wyzerować tablicę T, a potem dla kolejnych kluczy k wyliczać h(k) i zwiekszać T[h(k)] o 1. Przeprowadzić testy dla dużych (>1000) wartości m ("korzystnych" - liczba pierwsza i "niekorzystnych"), zakładając, że wstawiamy około 2*m kluczy, i wypisywać, jaka jest:
– ilość zerowych pozycji w tablicy T;
– maksymalna wartość w T;
– średnia wartość pozycji niezerowych.
Klucze-napisy do testowania są w pliku 3700.txt. Wykaz liczb pierwszych (przydatnych przy doborze rozmiaru tablicy): pierwsze.txt.

Przykładowe schematy konwersji napisu na liczbę:

abcdef... -> ((256*a+b) XOR (256*c+d)) XOR (256*e+f) ...

abc...x -> (...((111*a+b)*111+c)*111+ ... )*111 +x

W drugim schemacie "111" to przykładowa stała. Działania wykonujemy na długich liczbach bez znaku, ignorując przepełnienia. (jeśli z jakiegoś powodu powyższa konwersja zwróci liczbę ujemną, jest to błąd).

## Zadanie AL6.3. (4 pkt.) 
Zaprogramować – ZGODNIE Z PRZYDZIELONYM WARIANTEM (zapisanym jako komentarz w formularzu z punktacją) – wybrane operacje na tablicy z haszowaniem z adresowaniem otwartym oraz przeprowadzić testy i pomiary:

Operacje przetestować osobno na małej tablicy, z wydrukiem kontrolnym,
testy/pomiary przeprowadzić na tablicy większego rozmiaru, np. rzędu kilku tysięcy.
W tablicy haszowań mają być struktury zawierające dwa pola:

ilosc – typu int
nazwisko – ciąg znaków

Kluczami mają być nazwiska (patrz schematy zamiany w AL6.2). W tablicy haszowań mogą znajdować się wskaźniki na te struktury bądź całe struktury. W pliku nazwiskaASCII (ściągniętym z nieistniejącej już chyba strony www.futrega.org/etc/nazwiska.html) znajduje się wykaz zapisów postaci: [liczba typu int] [nazwisko], które można wykorzystać.

Warianty operacji i pomiarów

[W] Operacja WSTAW. Pomiar: średnia ilość prób wykonanych przy wstawianiu elementu (średnia ze wszystkich wstawień) przy różnych wypełnieniach tablicy: 50%, 70% i 90%.
[S] Operacje WSTAW i SZUKAJ. Pomiar: wypełnienie tablicy do 80% i wykonanie pewnej ilości operacji szukaj (np. ostatnich 20) ze zliczaniem i wydrukiem ilości prób przy szukaniu każdego klucza.
[U] Operacje WSTAW i USUŃ. Pomiar: wstawiać elementy aż do wypełnienia tablicy w 80%, potem usunąć połowę wstawionych elementów i następnie znowu dopełnić tablicę innymi elementami do 80%. Po tych operacjach zliczyć i wypisać, ile pozycji w tablicy jest wypełnionych znacznikiem DEL (miejsce po usuniętym elemencie)
Warianty techniki rozwiązywania kolizji
[OL] adresowanie otwarte liniowe
[OK] adresowanie otwarte kwadratowe
[OD] adresowanie otwarte, dwukrotne haszowanie
Możliwe kombinacje wariantów: [W+OL], [W+OK], [W+OD], [S+OL], [S+OK], [S+OD], [U+OL], [U+OK], [U+OD].