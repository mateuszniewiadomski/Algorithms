public class BinarySearchTree {
    
    class Wezel {
        int klucz;
        Wezel lewo, prawo;
        char przelacznik;

        Wezel(int klucz) {
            this.klucz = klucz;
            this.lewo = null;
            this.prawo = null;
            this.przelacznik = 'L';
        }
    }

    Wezel korzen;
    
    BinarySearchTree() {
        korzen = null;
    }

    void WSTAW(int klucz) {
        korzen = WSTAW(korzen, klucz);
    }

    Wezel WSTAW(Wezel korzen, int klucz) {
        
        if(korzen == null) {
            korzen = new Wezel(klucz);
            return korzen;
        } 
        
        if(korzen.klucz > klucz) {
            korzen.lewo = WSTAW(korzen.lewo, klucz);
        } else if (korzen.klucz < klucz) {
            korzen.prawo = WSTAW(korzen.prawo, klucz);
        } else if (korzen.klucz == klucz) {
            switch (korzen.przelacznik) {
                case 'L':
                    korzen.przelacznik = 'P';
                    korzen.lewo = WSTAW(korzen.lewo, klucz);
                    break;
                case 'P':
                    korzen.przelacznik = 'L';
                    korzen.prawo = WSTAW(korzen.prawo, klucz);
                    break;
                default:
                    break;
            }
        }

        return korzen;
    }

    void SZUKAJ(int klucz) {
        System.out.println(SZUKAJ(korzen, klucz));
    }

    Wezel SZUKAJ(Wezel korzen, int klucz) {
        
        if(korzen == null || korzen.klucz == klucz) {
            return korzen;
        }

        if(korzen.klucz < klucz) {
            return SZUKAJ(korzen.prawo, klucz);
        }

        return SZUKAJ(korzen.lewo, klucz);
    }

    void USUN(int klucz) {
        korzen = USUN(korzen, klucz);
    }

    Wezel USUN(Wezel korzen, int klucz) {
        
        if(korzen == null) {
            return korzen;
        }

        if(korzen.klucz > klucz) {
            korzen.lewo = USUN(korzen.lewo, klucz);
        } else if (korzen.klucz < klucz) {
            korzen.prawo = USUN(korzen.prawo, klucz);
        } else {
            if(korzen.lewo == null) {
                return korzen.prawo;
            } else if(korzen.prawo == null) {
                return korzen.lewo;
            }
            korzen.klucz = MIN(korzen.prawo);
            korzen.prawo = USUN(korzen.prawo, klucz);
        }

        return korzen;
    }

    int MIN(Wezel korzen) {
        int min = korzen.klucz;
        while(korzen.lewo != null) {
            min = korzen.lewo.klucz;
            korzen = korzen.lewo;
        }
        return min;
    }

    void DRUKUJ() {
        DRUKUJ(korzen);
        System.out.println();
        DRUKUJ(korzen, 0);
    }

    void DRUKUJ(Wezel korzen) {
        if(korzen != null) {
            DRUKUJ(korzen.lewo);
            System.out.print(korzen.klucz + " ");
            DRUKUJ(korzen.prawo);
        }
    }

    void DRUKUJ(Wezel korzen, int wysokosc){
        if(korzen == null)
             return;
        DRUKUJ(korzen.prawo, wysokosc+1);
        if(wysokosc != 0){
            for(int i = 0; i < wysokosc - 1; i++) {
                System.out.print("|\t");
            }
                System.out.println("|───── " + korzen.klucz);
        }
        else {
            System.out.println(korzen.klucz);
        }
        DRUKUJ(korzen.lewo, wysokosc+1);
    }    

    public static void main(String[] args) {
        BinarySearchTree drzewo = new BinarySearchTree();
        //int[] tab = {18, 11, 6, 30, 21, 19, 8, 22, 23, 5, 20, 26, 17};
        int[] tab = {17, 18, 11, 6, 30, 18, 19, 18, 17, 22, 17, 23, 18, 18, 26, 17};
        //int[] tab = {12, 12, 12, 12, 12, 12, 12, 12, 21, 12, 12, 12, 12, 12};
        for(int i = 0; i < tab.length; i++) {
            drzewo.WSTAW(tab[i]);
        }
        System.out.println("DRUKUJ:");
        drzewo.DRUKUJ();
        System.out.println("\nSZUKAJ:");
        drzewo.SZUKAJ(11);
        drzewo.SZUKAJ(100);
        System.out.println("\nUSUN:");
        drzewo.USUN(18);
        drzewo.USUN(11);
        drzewo.USUN(6);
        drzewo.USUN(17);
        drzewo.USUN(30);drzewo.USUN(300);
        drzewo.DRUKUJ();
    }
}
