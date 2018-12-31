package com.jstarcraft.ai.utility;

import java.util.Iterator;

import com.jstarcraft.ai.math.structure.matrix.MathMatrix;
import com.jstarcraft.ai.math.structure.vector.MathVector;
import com.jstarcraft.ai.math.structure.vector.VectorScalar;

/**
 * 搜索工具
 * 
 * @author Birdy
 *
 */
public class SearchUtility {

	/** 阻尼系数(原始性调整) */
	private final static float defaultAlpha = 0.8F;

	/** 收敛系数 */
	private final static float defaultEpsilon = 0.001F;

	public static float[] pageRank(int dimension, MathMatrix matrix) {
		return pageRank(dimension, matrix, defaultAlpha, defaultEpsilon);
	}

	public static float[] pageRank(int dimension, MathMatrix matrix, float alpha, float epsilon) {
		// 随机性调整
		float stochasticity = 1F / dimension;
		// 原始性调整
		float primitivity = (1F - alpha) * stochasticity;

		// 得分
		float[] scores = new float[dimension];
		for (int index = 0; index < dimension; index++) {
			scores[index] = stochasticity;
		}
		// 悬孤
		// TODO 考虑重构为int[],节省存储空间
		boolean[] ganglers = new boolean[dimension];
		for (int rowIndex = 0; rowIndex < dimension; rowIndex++) {
			MathVector vector = matrix.getRowVector(rowIndex);
			if (vector.getElementSize() == 0 || vector.getSum(false) == 0F) {
				ganglers[rowIndex] = true;
			} else {
				vector.scaleValues(alpha);
				vector.shiftValues(primitivity);
			}
		}

		// 判断是否收敛
		float error = 1F;
		while (error >= epsilon) {
			error = 0F;
			for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
				float score = 0F;
				Iterator<VectorScalar> iterator = matrix.getColumnVector(columnIndex).iterator();
				VectorScalar scalar = null;
				int index = -1;
				float value = 0F;
				if (iterator.hasNext()) {
					scalar = iterator.next();
					index = scalar.getIndex();
					value = scalar.getValue();
				}
				for (int rowIndex = 0; rowIndex < dimension; rowIndex++) {
					if (index == rowIndex) {
						// 判断是否为悬孤
						if (ganglers[rowIndex]) {
							score += scores[rowIndex] * stochasticity;
						} else {
							score += scores[rowIndex] * value;
						}
						if (iterator.hasNext()) {
							scalar = iterator.next();
							index = scalar.getIndex();
							value = scalar.getValue();
						} else {
							scalar = null;
							index = -1;
							value = 0F;
						}
					} else {
						// 判断是否为悬孤
						if (ganglers[rowIndex]) {
							score += scores[rowIndex] * stochasticity;
						} else {
							score += scores[rowIndex] * primitivity;
						}
					}
				}
				error += Math.abs(score - scores[columnIndex]);
				scores[columnIndex] = score;
			}
		}
		return scores;
	}

}
