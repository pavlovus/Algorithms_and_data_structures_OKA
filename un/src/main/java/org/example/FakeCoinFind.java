package org.example;

public class FakeCoinFind {
    private final Coin[] coins;

    public FakeCoinFind(Coin[] coins) {
        this.coins = coins;
    }

    public int findFake() {
        return findFake(0, coins.length - 1);
    }

    private int findFake(int left, int right) {
        if (left == right) return left;
        int mid = left + (right - left) / 2;

        int leftSum = 0;
        int rightSum = 0;

        boolean hasExtra = ((right - left + 1) % 2 != 0);
        int extraIndex = -1;

        int leftEnd = mid;
        int rightStart = mid + 1;

        if (hasExtra) {
            extraIndex = leftEnd;
            leftEnd--;
        }

        for (int i = left; i <= leftEnd; i++)
            leftSum += coins[i].getWeight();

        for (int i = rightStart; i <= right; i++)
            rightSum += coins[i].getWeight();

        if (leftSum < rightSum)
            return findFake(left, leftEnd);
        else if (leftSum > rightSum)
            return findFake(rightStart, right);
        else
            return extraIndex;
    }

    public static void main(String[] args) {
        Coin[] coins = new Coin[5];
        coins[0] = new Coin(2);
        coins[1] = new Coin(2);
        coins[2] = new Coin(1);
        coins[3] = new Coin(2);
        coins[4] = new Coin(2);

        FakeCoinFind finder = new FakeCoinFind(coins);
        int fakeIndex = finder.findFake();

        System.out.println("Підроблена монета під індексом: " + fakeIndex);
    }
}