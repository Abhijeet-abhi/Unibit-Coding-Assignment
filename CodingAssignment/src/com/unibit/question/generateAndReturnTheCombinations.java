package com.unibit.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class generateAndReturnTheCombinations {

	/*
	 * Using two pointer technique
	 */

	public static List<List<Integer>> findPossibleCombinationsFirst(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			/*
			 * The comboValue variable calculates the difference between the target value
			 * and the current element of the array
			 */
			int comboValue = target - nums[i];
			// checking if map containing key or not
			if (map.containsKey(comboValue)) {
				List<Integer> allCombinations = new ArrayList<>();
				// add the current element to allCombinations
				allCombinations.add(nums[i]);
				// add the value to allCombinations
				allCombinations.add(comboValue);
				// add the allPosible combinations to list.
				result.add(allCombinations);
			}
			// add possible pair to map
			map.put(nums[i], i);
		}
		return result;
	}

	/*
	 * Using recursion and backtracking approch
	 */

	public static List<List<Integer>> findPossibleCombinationsSecond(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		// Sort the input array to handle duplicates
		Arrays.sort(nums);
		generateAllCombinations(result, new ArrayList<>(), nums, target, 0);
		return result;
	}

	public static void generateAllCombinations(List<List<Integer>> result, List<Integer> currList, int[] nums,
			int index, int start) {
		/*
		 * If the targeted value is negative then simply return because the addition of
		 * current combination can not be target value
		 */
		if (index < 0)
			return;
		// Check if the targeted value is 0 then add it to the list
		else if (index == 0)
			result.add(new ArrayList<>(currList));

		/*
		 * Now, we need to find all combinations by using each element from the input
		 * array We start from the 'start' index number to avoid using the same element
		 * again and again in the combination
		 */
		for (int i = start; i < nums.length; i++) {
			// Skip generating duplicate combinations
			if (i > start && nums[i] == nums[i - 1])
				continue;
			// Add the current element to the combination
			currList.add(nums[i]);
			// Recursive call with updated 'index' value and 'start' index to move forward
			// in the array
			generateAllCombinations(result, currList, nums, index - nums[i], i + 1);
			// removing the last added element
			currList.remove(currList.size() - 1);
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 2, 2, -4, -6, -2, 8 };
		int target = 4;

		List<List<Integer>> firstCombination = findPossibleCombinationsFirst(nums, target);
		System.out.println("First Combination For '" + target + "' : " + firstCombination);
		// Merge the array and sort it in ascending order
		Arrays.sort(nums);
		System.out.println("Merge Into a single Array : " + Arrays.toString(nums));

		System.out.println("\n");

		// Double the target value and find combinations of digits that equal the
		// doubled target value
		int doubledTarget = target * 2;
		List<List<Integer>> secondCombination = findPossibleCombinationsSecond(nums, doubledTarget);
		System.out.println("Second Combination For '" + doubledTarget + "' : " + secondCombination);
		// Merge the array and sort it in ascending order
		Arrays.sort(nums);
		System.out.println("Merge Into a single Array : " + Arrays.toString(nums));

	}

}

// Time Complexity:
// - Sorting method taking O(N log N) time.
// - The first method 'findPossibleCombinationsFirst' method uses a two-pointer
// technique so it is O(N) complexity time.
// - Then the second method 'findPossibleCombinationsSecond' method uses recursion
//   and backtracking so it is O(2^N) complexity time.
// - Overall the time complexity of the program is O(2^N).

// Space Complexity:
// - The 'findPossibleCombinationsFirst' method using some additional space to
//   store the map of elements and their indexes so space will be O(N).
// - The findPossibleCombinationsSecond method uses recursion and backtracking to
//   generate all combinations.
//   It creates a current List to store current combinations so for each iteration
//   It will take N complexity, that means it will take O(N^2).
// - Overall the space complexity of the program is O(N^2).