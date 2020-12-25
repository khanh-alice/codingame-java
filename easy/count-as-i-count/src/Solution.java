import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Solution {

  public static final int WIN_SCORE = 50;
  public static final int MAX_TURN_COUNT = 4;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int initialScore = in.nextInt();
    int solutionCount = 0;
    int turnCount = 0;
    Queue<Integer> currTurn = new ArrayDeque<>(calcPossibleScores(initialScore));
    Queue<Integer> nextTurn = new ArrayDeque<>();

    while (turnCount < MAX_TURN_COUNT) {
      while (!currTurn.isEmpty()) {
        int score = currTurn.poll();

        if (score == WIN_SCORE) {
          solutionCount++;
        } else if (score < WIN_SCORE) {
          nextTurn.addAll(calcPossibleScores(score));
        }
      }

      turnCount++;
      currTurn = nextTurn;
      nextTurn = new ArrayDeque<>();
    }

    System.out.println(solutionCount);
  }

  private static List<Integer> calcPossibleScores(int score) {
    List<Integer> possibleScores = new ArrayList<>();

    for (int i = 1; i <= 12; i++) {
      int nextScore = score + i;

      if (nextScore <= WIN_SCORE) {
        possibleScores.add(nextScore);
        if (i > 1) possibleScores.add(nextScore);
      } else {
        break;
      }
    }

    return possibleScores;
  }
}
