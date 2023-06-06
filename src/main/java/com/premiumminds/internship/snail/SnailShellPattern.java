package com.premiumminds.internship.snail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {

    CompletableFuture<int[]> future = new CompletableFuture<>();
    
    
    // If the matrix is empty, return an empty array
    if (matrix.length == 1 && matrix[0].length == 0){
      future.complete(new int[]{});
      return future;
    }
    
    int[] result = new int[matrix.length * matrix.length];
    
    // Initial boundries of the matrix: outer most layers to add to the result array
    int left_bound = 0, top_bound = 0, bottom_bound = matrix.length - 1, right_bound = matrix.length - 1;
    int result_index = 0;

    // Add the values to the result array while the bounds do not cross each other
    while(left_bound <= right_bound && top_bound <= bottom_bound){
      // Add the values of the top row inside the horizontal bounds (from left to right)
      for (int i = left_bound; i <= right_bound; i++){
        result[result_index++] = matrix[top_bound][i];
      }
      top_bound++; // Update the top bound

      // Add the values of the right column inside the vertical bounds (from top to bottom)
      for (int i = top_bound; i <= bottom_bound; i++){
        result[result_index++] = matrix[i][right_bound];
      }
      right_bound--; // Update the right bound

      // Add the values of the bottom row inside the horizontal bounds (from right to left)
      for (int i = right_bound; i >= left_bound; i--){
        result[result_index++] = matrix[bottom_bound][i];
      }
      bottom_bound--; // Update the bottom bound

      // Add the values of the left column inside the vertical bounds (from bottom to top)
      for (int i = bottom_bound; i >= top_bound; i--){
        result[result_index++] = matrix[i][left_bound];
      }
      left_bound++; // Update the left bound

    }

    future.complete(result);
    return future;
  };
}
