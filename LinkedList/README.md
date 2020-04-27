## Zadanie AL5.1 (4 pkt.) 
Zaimplementuj strukturę listy dowiązaniowej (jednokierunkowej lub dwukierunkowej) niecyklicznej, bez wartownika, której elementami są słowa (czyli ciągi znaków), oraz operacje:
- wstaw(s, L) - wstawia słowo s na początek listy L,
- drukuj(L) - wypisuje elementy listy L,
- szukaj(s, L) - zwraca wskaźnik na węzeł listy zawierający słowo s o ile takie słowo znajduje się na liście L, w przeciwnym wypadku zwraca NULL,
- usuń(s, L) - usuwa z listy L węzeł zawierający słowo s o ile takie słowo znajduje się na liście,
- kasuj(L) - usuwa wszystkie węzły listy L , zwalniając również zajętą przez nie pamięć (free()).
(ta grupa operacji: razem 3pkt.)
- bezpowtorzeń(L) - tworzy kopię listy L, ale bez powtórzeń słów; zwraca wskaźnik na utworzoną kopię. Lista L pozostaje bez zmiany. (0.5 pkt.)
- scal(L1,L2) - tworzy listę L3 zawierającą wszystkie słowa z list L1 i L2 (również ze wszystkimi powtórzeniami); zwraca wskaźnik na utworzoną listę. Listy L1 i L2 przestają istnieć (można więc wykorzystać ich węzły do zbudowania listy L3.). Efektywne (pod względem złozoności czasowej) rozwiązanie doczepi po prostu jedną listę do drugiej. (0.5 pkt.)
