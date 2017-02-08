package com.solutions.services;

import org.springframework.stereotype.Service;

import com.solutions.bean.Data;

@Service
public class KnapsakService {

	/*
	 * Process data and return the output. throws Excpetion if weights and
	 * values array length mismatch
	 */
	public int knapSack(Data bean) throws Exception {
		int max_weight = bean.getMax_weight();
		int weight[] = bean.getWeights();
		int values[] = bean.getValues();
		int index, w;
		if (weight.length != values.length) {
			throw new Exception("Array length mismatch");
		}

		int n = values.length;
		int knapSack[][] = new int[n + 1][max_weight + 1];

		for (index = 0; index <= n; index++) {
			for (w = 0; w <= max_weight; w++) {
				if (index == 0 || w == 0)
					knapSack[index][w] = 0;
				else if (weight[index - 1] <= w)
					knapSack[index][w] = Integer.max(values[index - 1] + knapSack[index - 1][w - weight[index - 1]],
							knapSack[index - 1][w]);
				else
					knapSack[index][w] = knapSack[index - 1][w];
			}
		}

		return knapSack[n][max_weight];
	}
}
