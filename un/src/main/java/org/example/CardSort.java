package org.example;

import java.util.Arrays;

public class CardSort {
    private static class Card implements Comparable<Card> {
        String suit;
        int rank;

        Card(String suit, int rank) {
            this.suit = suit.toLowerCase();
            this.rank = rank;
        }

        @Override
        public int compareTo(Card o) {
            int suitOrder = suitOrder(this.suit) - suitOrder(o.suit);
            if (suitOrder != 0) return suitOrder;
            return this.rank - o.rank;
        }

        private int suitOrder(String s) {
            return switch (s) {
                case "піка" -> 1;
                case "черва" -> 2;
                case "трефа" -> 3;
                case "бубна" -> 4;
                default -> 0;
            };
        }

        @Override
        public String toString() { return suit + " " + rank; }
    }

    private final Card[] deck;
    public CardSort(Card[] deck) {
        this.deck = deck;
    }


    public void swap(int i, int j) {
        Card temp = deck[j];
        deck[j] = deck[i];
        deck[i] = temp;
    }

    public void sort() {
        int N = deck.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0; j--)
                if (deck[j].compareTo(deck[j-1]) < 0) swap(j, j-1);
    }

    public Card[] getDeck() {return deck;}

    public static void main(String[] args) {
        Card[] cards = {new Card("черва", 14),
                new Card("трефа", 2),
                new Card("піка", 10),
                new Card("бубна", 4),
                new Card("трефа", 13),
                new Card("черва", 3),
                new Card("піка", 2)};

        CardSort cs = new CardSort(cards);

        System.out.println("До сортування:");
        System.out.println(Arrays.toString(cs.deck));

        cs.sort();

        System.out.println("Після сортування:");
        System.out.println(Arrays.toString(cs.deck));
    }
}