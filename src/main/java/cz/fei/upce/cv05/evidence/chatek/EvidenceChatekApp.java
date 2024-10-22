package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {
    
    static final int KONEC_PROGRAMU = 0;
    static final int VYPIS_CHATEK = 1;
    static final int VYPIS_KONKRETNI_CHATKU = 2;
    static final int PRIDANI_NAVSTEVNIKU = 3;
    static final int ODEBRANI_NAVSTEVNIKU = 4;
    static final int CELKOVA_OBSAZENOST = 5;
    static final int VYPIS_PRAZDNE_CHATKY = 6;

    static final int VELIKOST_KEMPU = 10;
    static final int MAX_VELIKOST_CHATKY = 5;
    
    static int[] chatky = new int[VELIKOST_KEMPU];
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        int operace;

        do {
            System.out.println("""
                    MENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> {
                    vypisSeznamuChatek();
                }

                case VYPIS_KONKRETNI_CHATKU -> {
                    int cisloChatky = getCisloChatky(scanner);
                    kontrolaIndexuChatky(cisloChatky);
                    vypisChatky(cisloChatky);
                }

                case PRIDANI_NAVSTEVNIKU -> {
                    int cisloChatky = getCisloChatky(scanner);
                    pridaniNavstevniku(cisloChatky, scanner);
                }

                case ODEBRANI_NAVSTEVNIKU -> {
                    int indexChatky = getCisloChatky(scanner)-1;

                    if (!kontrolaIndexuChatky(indexChatky)){
                        System.out.println("Tato chatka neexistuje");
                    }
                    int pocetNavstevnikuKOdebrani = getPocetNavstevniku(scanner);

                    if (pocetNavstevnikuKOdebrani <= 0) {
                        System.err.println("Neplatna hodnota pro pocet navstevniku");
                        return;
                    }

                    if ((chatky[indexChatky] - pocetNavstevnikuKOdebrani) >= 0) {
                        chatky[indexChatky] -= pocetNavstevnikuKOdebrani;
                        System.out.println("odebráno");
                    } else {
                        System.err.println("Nelze");
                    }

                }

                case CELKOVA_OBSAZENOST -> {
                    int obsazeniKempu = 0;
                    for(int i = 0; i < chatky.length; i++){
                        obsazeniKempu += chatky[i];
                    }
                    System.out.println("V kempu je " +obsazeniKempu+ "lidí");
                }

                case VYPIS_PRAZDNE_CHATKY -> {
                    for(int i : chatky){
                        if(i == 0){
                            System.out.println("Chatka číslo " +i+ "je prázdná");
                        }
                    }
                }

                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu");
                }

                default -> {
                    System.err.println("Neplatna volba");
                }
            }
        } while (operace != 0);
    }

    private static void pridaniNavstevniku(int cisloChatky, Scanner scanner) {
        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }

        int pocetNavstevniku = getPocetNavstevniku(scanner);

        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }

        if ((chatky[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky");
            return;
        }

        chatky[cisloChatky] = pocetNavstevniku + chatky[cisloChatky];
    }

    private static boolean kontrolaIndexuChatky(int cisloChatky) {
        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return false;
        }
        return true;
    }

    private static void vypisChatky(int cisloChatky) {
        System.out.println("Chatka [" + (cisloChatky + 1) + "] = " + chatky[cisloChatky]);
    }

    private static void vypisSeznamuChatek() {
        for (int i = 0; i < chatky.length; i++) {
            System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
        }
    }

    private static int getPocetNavstevniku(Scanner scanner) {
        System.out.print("Zadej pocet navstevniku: ");
        return scanner.nextInt();
    };


    private static int getCisloChatky(Scanner scanner) {
        System.out.print("Zadej cislo chatky: ");
        return scanner.nextInt() - 1;
    }
}
